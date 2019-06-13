package wyy.com.easynetworkmonitor.demo3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import wyy.com.easynetworkmonitor.R;

/**
 * author: wyj
 * Date:2019/5/29
 * Description
 */
public class DemoActivity3 extends Activity  {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);
        tv=findViewById(R.id.tv);
        tv.setText("default");

    }


}



