package io.ican.hgl.stackoverflow.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import java.util.EnumMap;
import java.util.Map;

import io.ican.hgl.stackoverflow.R;
import io.ican.hgl.stackoverflow.databinding.MainPageBinding;
import io.ican.hgl.stackoverflow.mvp.p.impl.MainPagePresenter;
import io.ican.hgl.stackoverflow.mvp.v.IView;
import io.ican.hgl.stackoverflow.util.NavUtils;
import rx.functions.Action1;

public class MainPage extends BaseActivity implements IView {

    private static final String TAG = "MainPage";


    MainPageBinding binding;
    MainPagePresenter presenter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new MainPagePresenter(this);
        presenter.bindView(this);
        presenter.setBinding(binding);
        presenter.loadData();
    }


    @Override
    protected void initNavMenuAndView() {
        binding = DataBindingUtil.setContentView(this, R.layout.main_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawer = new BindDrawer();
        listener = new NavListener();
        navUtils = new NavUtils();
        navUtils.setNavBindDrawer(drawer);
        navUtils.setNavItemClickListener(listener);
        navUtils.setNavigationView(navigationView);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer.bindDrawerLayout(), toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.bindDrawerLayout().setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, TestActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void refresh() {
        Log.i(TAG, "onRefresh");
    }

    @Override
    public void update() {
        Log.i(TAG, "update");
    }

    @Override
    public void error(String e) {

    }

}
