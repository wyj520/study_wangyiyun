package wyy.com.easynetworkmonitor.demo1;

import android.app.Application;
import android.content.IntentFilter;

import wyy.com.easynetworkmonitor.demo1.utils.Constant;

/**
 * author: wyj
 * Date:2019/5/29
 * Description：网络管理类，全局单例
 */
public class NetworkManager {

    private Application application;
    private static volatile NetworkManager instance;
    private NetStateReciver reciver;

    private NetworkManager(){
        reciver=new NetStateReciver();
    }
    public void setListener(NetChangeObserver listener){
        reciver.setListener(listener);
    }

    public static NetworkManager getInstance(){
         if (instance==null){
             synchronized (NetworkManager.class){
                 if (instance==null){
                     instance=new NetworkManager();
                 }
             }
         }
         return instance;
    }

    public Application getApplication() {
        return application;
    }

    public void init(Application application){
        this.application=application;
        //注册广播
        IntentFilter filter=new IntentFilter();
        filter.addAction(Constant.NET_CHANGE_ACTION);
        application.registerReceiver(reciver,filter);
    }

}
