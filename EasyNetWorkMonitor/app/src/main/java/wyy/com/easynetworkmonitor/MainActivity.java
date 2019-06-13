package wyy.com.easynetworkmonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import wyy.com.easynetworkmonitor.demo1.DemoActivity1;
import wyy.com.easynetworkmonitor.demo2.DemoActivity2;
import wyy.com.easynetworkmonitor.demo3.DemoActivity3;

public class MainActivity extends AppCompatActivity    {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * demo1的局限：每个activity都要加上监听，当activity多的时候不优雅；
         */
        findViewById(R.id.demo1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DemoActivity1.class));
            }
        });

        /**
         * demo2的：利用eventbud的原理，实现订阅；但依然使用广播
         */
        findViewById(R.id.demo2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DemoActivity2.class));
            }
        });

        /**
         * 不使用广播
         */
        findViewById(R.id.demo3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DemoActivity3.class));
            }
        });
    }

}
