package wyy.com.easynetworkmonitor.demo2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import wyy.com.easynetworkmonitor.demo2.annotation.Network;
import wyy.com.easynetworkmonitor.demo2.bean.MethodManager;
import wyy.com.easynetworkmonitor.demo2.utils.Constant;
import wyy.com.easynetworkmonitor.demo2.utils.NetworkUtils;

/**
 * author: wyj
 * Date:2019/5/29
 * Description:
 * 监听网络状态的广播
 */
public class NetStateReciver  extends BroadcastReceiver{

    private NetType netType;
    private Map<Object,List<MethodManager>> networkList;
    private NetChangeObserver listener;

    public NetStateReciver(){
        //初始化网络
        netType= NetType.NONOE;
        networkList=new HashMap<>();
    }

    public void setListener(NetChangeObserver listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
          if (intent==null || intent.getAction()==null){
              Log.d(Constant.LOG_TAG,"异常...");
              return;
          }

          //处理广播事件
        if (intent.getAction().equalsIgnoreCase(Constant.NET_CHANGE_ACTION)){
              Log.d(Constant.LOG_TAG,"网络发生改变");
             netType= NetworkUtils.getNetType();//网络连接类型

              if (NetworkUtils.isNetworkAvailable()){
                  Log.d(Constant.LOG_TAG,"有网络");

                  if (listener!=null){
                      Log.d(Constant.LOG_TAG,"回调网络类型");
                      listener.onConnect(netType); //回调网络连接类型
                  }else {
                      if (listener!=null){
                          Log.d(Constant.LOG_TAG,"回调无网络");
                          listener.onDisConnect();
                      }
                  }
              }else {
                  Log.d(Constant.LOG_TAG,"无网络");
                  if (listener!=null){
                      Log.d(Constant.LOG_TAG,"回调无网络");
                      listener.onDisConnect();
                  }
              }

              //总线：全局通知
             post(netType);
        }
    }

    //同时分发
    private void post(NetType netType) {
         Set<Object> set=networkList.keySet();
         //比如：后获取activity对象
        for (Object getter: set){
           List<MethodManager> methodList=networkList.get(getter);
           if (methodList!=null){
               //循环每个方法
               for (MethodManager manager:methodList){
                   if (manager.getType().isAssignableFrom(netType.getClass())){ //两者参数一样
                        switch ( manager.getNetType()){
                            case AUTO:
                                invoke(manager,getter,netType);
                                break;

                            case WIFI:
                                invoke(manager,getter,netType);
                                break;

                            case CMENT:
                                invoke(manager,getter,netType);
                                break;

                            case CMWAP:
                                invoke(manager,getter,netType);
                                break;

                            case NONOE:
                                invoke(manager,getter,netType);
                                break;
                        }
                   }
               }
           }
        }
    }


    /**
     * 将运用中所有Activity注册了网络监听的方法添加到集合
     * key：activity  ， value “activity中所有注解方法
     * @param register
     */
    public void registerObserver(Object register){
        //activity中所有网络监听注解方法
        List<MethodManager> methoList=networkList.get(register);

        if (methoList==null){
            //开始添加方法
            methoList=findAnnotataionMethod(register);
            networkList.put(register,methoList);
        }
    }
    public void unRegisterObserver(Object regidter){
        if (!networkList.isEmpty()){
            networkList.remove(regidter);
        }

        //运用退出时
      /*  if (!networkList.isEmpty()){
              networkList.clear();
        }
        NetworkManager.getInstance().getApplication().unregisterReceiver(this);//注销当前广播

        networkList=null;*/
    }

    private void invoke(MethodManager methodManager,Object getter,NetType type){
        try {
            //在activity中执行方法，参数值为netType；
            methodManager.getMethod().invoke(getter,netType);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    //从注解中找到方法添加到集合
    private List<MethodManager> findAnnotataionMethod(Object register) {
        List<MethodManager> methoslist=new ArrayList<>();

         Class<?> clazz=register.getClass();

         //拿到activity中所有方法:
        // public Method[] getMethods()返回某个类的所有公用（public）方法包括其继承类的公用方法，当然也包括它所实现接口的方法。
        //public Method[] getDeclaredMethods()对象表示的类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。当然也包括它所实现接口的方法。
      Method[] methods= clazz.getDeclaredMethods();
      for (Method method:methods){
          Log.d("****",register.getClass().getName()+"的方法"+method.getName());
          //获取方法的注解
          Network network=method.getAnnotation(Network.class);
          if (network==null){ //只有注解为Networm的方法才能往下
              continue;
          }
        //  Log.d("****",register.getClass().getName()+"的注解"+network.netType());
          //方法的参数校验
          Class<?>[] params = method.getParameterTypes();
          if (params.length!=1){ //注解方法只有一个参数，
              throw  new RuntimeException(method.getName()+"方法不符合"); //只有参数为一个的方法才能往下
          }

          //过滤方法完成:params[0]:注解方法参数，network.netType();注解类型，method该注解方法
          MethodManager manager=new MethodManager(params[0],network.netType(),method);
          methoslist.add(manager);
      }
        return methoslist;
    }


}
