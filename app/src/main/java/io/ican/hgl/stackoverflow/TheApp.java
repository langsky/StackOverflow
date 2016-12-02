package io.ican.hgl.stackoverflow;

import android.app.Application;

/**
 * Created by hgl on 16-12-2.
 */

public class TheApp extends Application{
    public static TheApp instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
