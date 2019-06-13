package wyy.com.easynetworkmonitor.demo1;

/**
 * author: wyj
 * Date:2019/5/29
 * Description:网络监听接口
 */
public interface NetChangeObserver {
     void onConnect(NetType type);//网络连接类型
     void onDisConnect();//没网络

}
