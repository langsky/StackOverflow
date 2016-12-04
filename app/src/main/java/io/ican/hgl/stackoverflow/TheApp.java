package io.ican.hgl.stackoverflow;

import android.app.Application;

/**
 * Created by hgl on 16-12-2.
 */

public class TheApp extends Application {
    public static final String BASE_URL = "http://stackoverflow.com";

    public static TheApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
