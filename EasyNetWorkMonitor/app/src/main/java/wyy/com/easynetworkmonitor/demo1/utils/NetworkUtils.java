package wyy.com.easynetworkmonitor.demo1.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import wyy.com.easynetworkmonitor.demo1.NetType;
import wyy.com.easynetworkmonitor.demo1.NetworkManager;

/**
 * author: wyj
 * Date:2019/5/29
 * Description
 */
public class NetworkUtils {
    /**
     * 网络是否可用
     */
    public static boolean isNetworkAvailable(){
        ConnectivityManager connManager= (ConnectivityManager) NetworkManager.getInstance().getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connManager==null){
            return false;
        }

        //返回所有网络信息
        NetworkInfo[] info=connManager.getAllNetworkInfo();

        if (info!=null){
            for (NetworkInfo anInfo : info){
                if (anInfo.getState() == NetworkInfo.State.CONNECTED){
                    return true;//只要有网络连接就返回true
                }
            }
        }
        return false;
    }

    /**
     * 获取当前网络类型
     */
    public static NetType getNetType(){
        ConnectivityManager connManager= (ConnectivityManager) NetworkManager.getInstance().getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connManager==null){
            return NetType.NONOE;
        }

        //获取当前激活的网络连接信息
        NetworkInfo networkInfo=connManager.getActiveNetworkInfo();
        if (networkInfo==null){
            return NetType.NONOE;
        }

        int nType=networkInfo.getType();

        if (nType==ConnectivityManager.TYPE_MOBILE){
            if (networkInfo.getExtraInfo().toLowerCase().equals("cmnet")){
                return NetType.CMENT;
            }else {
                return NetType.CMWAP;
            }
        }else if (nType==ConnectivityManager.TYPE_WIFI){
            return NetType.WIFI;
        }
        return NetType.NONOE;
    }

    /**
     * 打开网络设置界面
     */
    public static  void openSetting(Context context,int requestCode){
        Intent intent=new Intent("/");
        ComponentName cm=new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
         intent.setComponent(cm);
         intent.setAction("android.intent.action.VIEW");
        ((Activity)context).startActivityForResult(intent,requestCode);
    }

}
