package mybus.com.myeventbus.bus;

import java.lang.reflect.Method;

/**
 * author: wyj
 * Date:2019/4/18
 * Description
 * 保存符合要求的订阅方法的封装类
 */
public class MethodManager {
    //参数类型
    private Class<?> type;

    //线程模式
    private ThreadMode threadMode;

    //执行订阅方法
    private Method method;

    /**
     * @param type 参数类型
     * @param threadMode 线程模式
     * @param method 方法
     */
    public MethodManager(Class<?> type, ThreadMode threadMode, Method method) {
        this.type = type;
        this.threadMode = threadMode;
        this.method = method;
        // method.invoke(); //方法执行，这就是需要拿到method的意义
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public ThreadMode getThreadMode() {
        return threadMode;
    }

    public void setThreadMode(ThreadMode threadMode) {
        this.threadMode = threadMode;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
