package com.myglide;

import android.graphics.Bitmap;

/**
 * author: wyj
 * Date:2019/6/18
 * Description
 */
public interface RequestListener {
    boolean onSuccess(Bitmap bitmap);

    boolean onFailure();

}
