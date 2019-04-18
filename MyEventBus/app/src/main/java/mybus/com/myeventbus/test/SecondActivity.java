package mybus.com.myeventbus.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import mybus.com.myeventbus.R;
import mybus.com.myeventbus.bus.EventBus;

/**
 * author: wyj
 * Date:2019/4/18
 * Description
 */
public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.btn_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //事件发布
                EventBus.getDefault().post(new Bean(12,"牛逼哄哄"));
                finish();
            }
        });
    }
}
