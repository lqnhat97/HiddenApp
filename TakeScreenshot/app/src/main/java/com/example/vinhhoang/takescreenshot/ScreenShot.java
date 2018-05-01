package com.example.vinhhoang.takescreenshot;

import android.graphics.Bitmap;
import android.view.View;

public class ScreenShot {
    public static Bitmap takeScreenShot(View v){
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);
        Bitmap bitmap=Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        return bitmap;
    }

    public static  Bitmap takeScreenShotOfRootView(View v){
        return takeScreenShot(v.getRootView());
    }
}
