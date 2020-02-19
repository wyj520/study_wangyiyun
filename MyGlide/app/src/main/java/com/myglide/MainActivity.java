package com.myglide;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.myglide.cache.FileCache;
import com.myglide.cache.LocalCacheUtils;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private final String url1="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560857590379&di=adadb6deac1bbc22bc3285fac039d8ad&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F7e9efbb317693219453cbd0c05e56e3740868a3e30932-yC4jeB_fw658";

    LinearLayout ll;
  TextView cachesize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll=findViewById(R.id.ll);
        cachesize=findViewById(R.id.cachesize);
        cachesize.setText(FileCache.getTotalCacheSize());
        //加载一张
        findViewById(R.id.b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addSign();
                cachesize.setText(FileCache.getTotalCacheSize());
            }
        });

        //加载十张
        findViewById(R.id.b2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMore();
                cachesize.setText(FileCache.getTotalCacheSize());
            }
        });

        findViewById(R.id.b3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll.removeAllViews();
                FileCache.clearAllCache(new File(LocalCacheUtils.CACHE_PATH));
                cachesize.setText(FileCache.getTotalCacheSize());
            }
        });
    }



    private void addSign() {
        ImageView imageView=new ImageView(MainActivity.this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        ll.addView(imageView);

        MyGlide.with(MainActivity.this).load(url1)
                .loading(R.mipmap.ic_launcher)
                .listener(new RequestListener() {
                    @Override
                    public boolean onSuccess(Bitmap bitmap) {
                        Toast.makeText(MainActivity.this, "onSuccess", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onFailure() {
                        Toast.makeText(MainActivity.this, "onSuccess", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }).into(imageView);
    }

    void  addMore(){
        for (int i = 1; i <= 10; i++) {
            ImageView imageView=new ImageView(MainActivity.this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setScaleType(ImageView.ScaleType.MATRIX);

            ll.addView(imageView);

            String url=null;
            switch (i){
                case 1:
                    url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560856159981&di=9bf4661b763dc70a95c5e4740702c7da&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn%2Fw1000h1000%2F20180115%2F01aa-fyqrewi3036612.jpg";
                    break;
                case 2:
                    url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560856159981&di=9bf4661b763dc70a95c5e4740702c7da&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn%2Fw1000h1000%2F20180115%2F01aa-fyqrewi3036612.jpg";
                    break;
                case 3:
                    url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560857590379&di=adadb6deac1bbc22bc3285fac039d8ad&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F7e9efbb317693219453cbd0c05e56e3740868a3e30932-yC4jeB_fw658";
                    break;
                case 4:
                    url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560857391581&di=3f75d4147c3b72d648288931657727d5&imgtype=0&src=http%3A%2F%2Fwww.sinaimg.cn%2Fent%2Fm%2Ff%2Fp%2F2008-09-23%2FU2519P28T3D2179122F326DT20080923031027.jpg";
                    break;
                case 5:
                    url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560857391581&di=3f75d4147c3b72d648288931657727d5&imgtype=0&src=http%3A%2F%2Fwww.sinaimg.cn%2Fent%2Fm%2Ff%2Fp%2F2008-09-23%2FU2519P28T3D2179122F326DT20080923031027.jpg";
                    break;
                case 6:
                    url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560857391581&di=3f75d4147c3b72d648288931657727d5&imgtype=0&src=http%3A%2F%2Fwww.sinaimg.cn%2Fent%2Fm%2Ff%2Fp%2F2008-09-23%2FU2519P28T3D2179122F326DT20080923031027.jpg";
                    break;
                case 7:
                    url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560857590379&di=adadb6deac1bbc22bc3285fac039d8ad&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F7e9efbb317693219453cbd0c05e56e3740868a3e30932-yC4jeB_fw658";
                    break;
                case 8:
                    url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560857391581&di=3f75d4147c3b72d648288931657727d5&imgtype=0&src=http%3A%2F%2Fwww.sinaimg.cn%2Fent%2Fm%2Ff%2Fp%2F2008-09-23%2FU2519P28T3D2179122F326DT20080923031027.jpg";
                    break;
                case 9:
                    url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560856159981&di=9bf4661b763dc70a95c5e4740702c7da&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn%2Fw1000h1000%2F20180115%2F01aa-fyqrewi3036612.jpg";
                    break;

                case 10:
                    url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560857391581&di=3f75d4147c3b72d648288931657727d5&imgtype=0&src=http%3A%2F%2Fwww.sinaimg.cn%2Fent%2Fm%2Ff%2Fp%2F2008-09-23%2FU2519P28T3D2179122F326DT20080923031027.jpg";
                    break;
            }

            MyGlide.with(MainActivity.this).load(url)
                    .loading(R.mipmap.ic_launcher)
                    .listener(new RequestListener() {
                        @Override
                        public boolean onSuccess(Bitmap bitmap) {
                            Toast.makeText(MainActivity.this, "onSuccess", Toast.LENGTH_SHORT).show();
                            return false;
                        }

                        @Override
                        public boolean onFailure() {
                            Toast.makeText(MainActivity.this, "onSuccess", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    }).into(imageView);
        }
    }
}
