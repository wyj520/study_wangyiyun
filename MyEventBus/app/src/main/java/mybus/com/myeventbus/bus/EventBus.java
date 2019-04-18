package mybus.com.myeventbus.bus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * author: wyj
 * Date:2019/4/18
 * Description
 */
public class EventBus {
    //volatile  :修饰的变量不允许内存被修改；是一个轻量级同步快
    private static volatile EventBus instance;

    //用来保存带注解（订阅方法），key用于该方法的类，可以使activity，fragment等； value是集合：一个类可能有多个带注解的方法；
    private Map<Object,List<MethodManager>> cacheMap;

    private EventBus(){
        cacheMap=new HashMap<>();
    }

    public static EventBus getDefault(){
        if (instance==null){
             synchronized (EventBus.class){ //同步锁：防止instance==null之后，同时无数次的创建下一步
                 if (instance==null){ //再次判断，同步代码块可能有个线程排序创建新的对象，如果创建了就不在创建
                     instance=new EventBus();
                 }

             }
        }
        return instance;
    }

    //收到信息的方法:即EventBus的订阅:
    public void register(Object getter){
        //找到类中带注解的方法
         List<MethodManager> methodList=cacheMap.get(getter); //得找到getter类中所有的方法集合
         if (methodList==null){
             methodList=findAnnotationMethod(getter);
              cacheMap.put(getter,methodList); //key是类，value是该类方法集合
         }

    }

    /**
     * 获取getter类中所有带指定注解的方法:while循环一直去找带注解的方法
     */
    private List<MethodManager> findAnnotationMethod(Object getter) {
        List<MethodManager> methodLists=new ArrayList<>();
         Class<?> clazz=getter.getClass();//拿到gerter类
          Method[] methods= clazz.getMethods();//拿到该类的所有方法


        //不断地循环去找含有订阅的方法，直至为空
        while (clazz!=null){
            String claxxName=clazz.getName();
            //如果是java和android中的类就不处理；因为肯定没有我们自定义的注解
            if (claxxName.startsWith("java.") || claxxName.startsWith("javax.") || claxxName.startsWith("android.")) {
                   break;
            }
            for (Method method: methods) { //遍历方法拿到带Subscribe注解的方法；
                Subscribe annotation=method.getAnnotation(Subscribe.class);
                if (annotation==null){
                    continue;
                }

                Type returnType= method.getGenericReturnType();//获取方法的返回值
                if (!"void".equals(returnType.toString())){ //如果返回值不是void
                    throw  new RuntimeException(method.getName()+"方法返回值必须是void");
                }

                //我们设计的注解参数只有一个
                Class<?>[] paramterTypes=method.getParameterTypes();//拿到该方法的参数集合
                if (paramterTypes.length!=1){
                    throw  new RuntimeException(method.getName()+"方法参数只能是一个");
                }

                //完全符合要求了
                MethodManager manager=new MethodManager(paramterTypes[0],annotation.threadMode(),method);

                methodLists.add(manager);
            }
           
            clazz=clazz.getSuperclass();
        }
        return methodLists;
    }

    /**
     * 发送动作:setter 是发送的实体类：我们这里是Bean
     */
    public void post(Object setter){
        Set<Object> set=cacheMap.keySet();
        //获取setter对象
        for (Object getter:set) {
            //获取setter中所有带注解的方法
          List<MethodManager> methodList=  cacheMap.get(getter);
          if (methodList!=null){
              //循环执行每个订阅方法
              for (MethodManager method: methodList ) {
                  //isAssignableFrom :判断class1和class2指定的类或接口是否相同
                  if (method.getType().isAssignableFrom(setter.getClass()));{    //如果发送方和订阅费匹配上了：即Subscribe注解的方法参数和发送的参数是一个类型：我们测试的是Bean类；method.getType()得到参数类型
                    invoke(method,getter,setter);
                  }

              }
          }
        }

    }

    private void invoke(MethodManager method, Object getter, Object setter) {
        try {
            Method excute=method.getMethod();
            excute.invoke(getter,setter); //执行该方法：参数1：执行该方法的对象；参数2：该对象执行该方法的参数值

        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}
