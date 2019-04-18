package mybus.com.myeventbus.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import mybus.com.myeventbus.R;
import mybus.com.myeventbus.bus.EventBus;
import mybus.com.myeventbus.bus.Subscribe;
import mybus.com.myeventbus.bus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    TextView tv_test;
    TextView tv_test1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_test=findViewById(R.id.tv_test);
        tv_test1=findViewById(R.id.tv_test1);

        findViewById(R.id.btn_subscribe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注册
                EventBus.getDefault().register(MainActivity.this);

                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
    }

    //订阅1
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void test1(Bean bean){
        tv_test.setText(bean.getAge()+"--"+bean.getName());
    }
    //订阅2
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void test2(Bean bean){
        tv_test1.setText(bean.getAge()+"--"+bean.getName());
    }

}
