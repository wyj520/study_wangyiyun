package wyy.com.easynetworkmonitor.demo1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import wyy.com.easynetworkmonitor.R;
import wyy.com.easynetworkmonitor.demo1.utils.Constant;

/**
 * author: wyj
 * Date:2019/5/29
 * Description
 */
public class DemoActivity1 extends Activity implements NetChangeObserver{

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);
        tv=findViewById(R.id.tv);
        //注册
        NetworkManager.getInstance().setListener(this);
    }

    @Override
    public void onConnect(NetType type) {
        Log.d(Constant.LOG_TAG,"DemoActivity3>>>"+type.name());
        tv.setText("当前网络状态"+type.name());
    }

    @Override
    public void onDisConnect() {
        Log.d(Constant.LOG_TAG,"DemoActivity3>>>"+"disConnect");
        tv.setText("当前网络状态"+ "disConnect");
    }
}



