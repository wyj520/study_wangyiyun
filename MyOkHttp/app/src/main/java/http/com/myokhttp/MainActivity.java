package http.com.myokhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String url="https://www.baidu.com/";
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);

        findViewById(R.id.request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetUtils.sendJsonRequest(url, null, Bean.class, new JsonDataListener<Bean>() {
                    @Override
                    public void onSuccess(Bean bean) {
                                  tv.setText(bean.toString());
                    }

                    @Override
                    public void onFailure() {
                        tv.setText("onFailure");
                    }
                });
            }
        });
    }
}
