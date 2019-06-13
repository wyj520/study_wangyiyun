package wyy.com.easynetworkmonitor.demo1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;
import java.util.Map;

import wyy.com.easynetworkmonitor.demo1.utils.Constant;
import wyy.com.easynetworkmonitor.demo1.utils.NetworkUtils;

/**
 * author: wyj
 * Date:2019/5/29
 * Description:
 * 监听网络状态的广播
 */
public class NetStateReciver  extends BroadcastReceiver{

    private NetType netType;
    private NetChangeObserver listener;

    public NetStateReciver(){
        //初始化网络
        netType=NetType.NONOE;
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
             netType=NetworkUtils.getNetType();//网络连接类型

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
        }
    }


}
