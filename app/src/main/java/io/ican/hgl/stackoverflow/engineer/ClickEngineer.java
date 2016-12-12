package io.ican.hgl.stackoverflow.engineer;

import android.util.Log;

/**
 * Created by swd1 on 16-12-5.
 */

public class ClickEngineer {

    private static final String TAG = "ClickEngineer";

    public static void onItemClicked(String url) {
        Log.e(TAG, url);
    }
}
