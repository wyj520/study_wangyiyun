package wyy.com.easynetworkmonitor.demo3;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import android.os.Build;

import wyy.com.easynetworkmonitor.demo2.annotation.Network;
import wyy.com.easynetworkmonitor.demo3.core.NetworkCallabackImpl;

/**
 * author: wyj
 * Date:2019/5/29
 * Description：网络管理类，全局单例
 */
public class NetworkManager {

    private Application application;
    private static volatile NetworkManager instance;


    private NetworkManager(){
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

    //application中执行
    public void init(Application application){
        this.application=application;

      //不通过广播
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            ConnectivityManager.NetworkCallback networkCallback=new NetworkCallabackImpl();
            NetworkRequest.Builder builder=new NetworkRequest.Builder();
            NetworkRequest request=builder.build();

            ConnectivityManager cmgr= (ConnectivityManager) NetworkManager.getInstance().getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cmgr!=null){
                cmgr.registerNetworkCallback(request,networkCallback);
            }

        }else {
        }

    }

}
