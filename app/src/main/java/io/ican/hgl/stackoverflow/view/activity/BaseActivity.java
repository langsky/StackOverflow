package io.ican.hgl.stackoverflow.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import org.jsoup.nodes.Document;

import java.util.Map;

import io.ican.hgl.stackoverflow.R;
import io.ican.hgl.stackoverflow.TheApp;
import io.ican.hgl.stackoverflow.engineer.GlobalParser;
import io.ican.hgl.stackoverflow.engineer.JsoupEngineer;
import io.ican.hgl.stackoverflow.util.NavUtils;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

import static io.ican.hgl.stackoverflow.util.C.BASE_URL;

/**
 * Created by swd1 on 16-12-13.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    protected NavUtils navUtils;
    protected BindDrawer drawer;
    protected NavListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNavMenuAndView();
    }

    protected class BindDrawer implements NavUtils.NavBindDrawer {
        @Override
        public DrawerLayout bindDrawerLayout() {
            return (DrawerLayout) findViewById(R.id.drawer_layout);
        }
    }

    protected class NavListener implements NavUtils.NavItemClickListener {
        @Override
        public void onNavItemClicked(final MenuItem item) {
            JsoupEngineer.MAIN_PAGE(BASE_URL).map(new Func1<Document, Map<String, String>>() {
                @Override
                public Map<String, String> call(Document document) {
                    return GlobalParser.parseMenus(document);
                }
            }).subscribe(new Action1<Map<String, String>>() {
                @Override
                public void call(Map<String, String> map) {
                    String key = item.getTitle().toString();
                    Log.i(TAG, map.get(key));
                }
            });
        }
    }

    protected abstract void initNavMenuAndView();

}
