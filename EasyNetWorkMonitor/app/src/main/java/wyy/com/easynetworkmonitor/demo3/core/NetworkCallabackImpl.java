package wyy.com.easynetworkmonitor.demo3.core;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.lang.annotation.Retention;

/**
 * author: wyj
 * Date:2019/5/30
 * Description
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NetworkCallabackImpl  extends ConnectivityManager.NetworkCallback{

    @Override
    public void onAvailable(Network network) {
        super.onAvailable(network);
        Log.d("*****","网络已连接");
    }

    @Override
    public void onLost(Network network) {
        super.onLost(network);
        Log.d("*****","网络已断开");
    }

    @Override
    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities);
        if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)){
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.d("*****","网络类型发生变化，类型为WIFI");
            }else {
                Log.d("*****","网络类型发生变化，类型为其他");
            }
        }
    }
}
