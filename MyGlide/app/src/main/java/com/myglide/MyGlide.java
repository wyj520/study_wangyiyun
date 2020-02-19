package com.myglide;

import android.content.Context;

/**
 * author: wyj
 * Date:2019/6/18
 * Description
 */
public class MyGlide {
    public static BitmapRequest with(Context context){
        return new BitmapRequest(context);
    }
}
