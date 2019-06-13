package wyy.com.easynetworkmonitor;

import android.app.Application;

import wyy.com.easynetworkmonitor.demo1.NetworkManager;

/**
 * author: wyj
 * Date:2019/5/29
 * Description
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
       NetworkManager.getInstance().init(this);

       wyy.com.easynetworkmonitor.demo2.NetworkManager.getInstance().init(this);
        wyy.com.easynetworkmonitor.demo3.NetworkManager.getInstance().init(this);


    }
}
