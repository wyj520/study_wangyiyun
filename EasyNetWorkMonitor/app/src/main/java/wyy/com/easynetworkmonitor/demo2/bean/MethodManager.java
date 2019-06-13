package wyy.com.easynetworkmonitor.demo2.bean;

import java.lang.reflect.Method;

import wyy.com.easynetworkmonitor.demo2.NetType;

/**
 * author: wyj
 * Date:2019/5/30
 * Description
 */
public class MethodManager {
    //参数类型：NetType netType
     private Class<?>  type;

     //网络类型(netType=NetType.WIFI)
     private NetType netType;

     //需要执行的方法（自动执行）
    private Method method;

    public MethodManager(Class<?> type, NetType netType, Method method) {
        this.type = type;
        this.netType = netType;
        this.method = method;

        //核心方法：反射中的方法执行，就不需要在acticity中手动去执行方法
        //method.invoke()
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public NetType getNetType() {
        return netType;
    }

    public void setNetType(NetType netType) {
        this.netType = netType;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
