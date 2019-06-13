package wyy.com.easynetworkmonitor.demo2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import wyy.com.easynetworkmonitor.R;
import wyy.com.easynetworkmonitor.demo2.annotation.Network;
import wyy.com.easynetworkmonitor.demo2.utils.Constant;

/**
 * author: wyj
 * Date:2019/5/29
 * Description
 */
public class DemoActivity2 extends Activity  {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);
        tv=findViewById(R.id.tv);
        tv.setText("default");

        NetworkManager.getInstance().registerObserver(this);
    }

   @Network(netType=NetType.AUTO)
    public void netWorkA(NetType netType){
        switch (netType){
            case WIFI:
                tv.setText("WIFI");
                break;

            case CMENT:
                tv.setText("CMENT");
                break;

            case CMWAP:
                tv.setText("CMWAP");
                break;

            case NONOE:
                tv.setText("NONOE");
                break;

        }
   }

    @Network(netType=NetType.WIFI) //只过滤WiFi
    public void netWorkB(NetType netType){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkManager.getInstance().unRegisterObserver(this);
    }
}



