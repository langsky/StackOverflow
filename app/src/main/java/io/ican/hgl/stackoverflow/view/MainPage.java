package io.ican.hgl.stackoverflow.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import io.ican.hgl.stackoverflow.R;
import io.ican.hgl.stackoverflow.adapter.SummaryAdapter;
import io.ican.hgl.stackoverflow.databinding.MainPageBinding;
import io.ican.hgl.stackoverflow.engineer.JsoupEngineer;
import io.ican.hgl.stackoverflow.engineer.JsoupParser;
import io.ican.hgl.stackoverflow.entity.menu.MenuType;
import io.ican.hgl.stackoverflow.entity.question.Summary;
import io.ican.hgl.stackoverflow.mvp.p.impl.MainPagePresenter;
import io.ican.hgl.stackoverflow.mvp.v.IView;
import io.ican.hgl.stackoverflow.util.C;
import io.ican.hgl.stackoverflow.util.NavUtils;
import io.ican.hgl.stackoverflow.view.question.QuestionPage;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainPage extends AppCompatActivity implements IView {

    private static final String TAG = "MainPage";

    NavUtils navUtils;
    BindDrawer drawer;
    NavListener listener;

    MainPageBinding binding;

    MainPagePresenter presenter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new MainPagePresenter(this);
        presenter.bindView(this);
        presenter.setBinding(binding);
        presenter.loadData();

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

    }

    @Override
    public void update() {

    }

    @Override
    public void error(String e) {

    }


    private class BindDrawer implements NavUtils.NavBindDrawer {
        @Override
        public DrawerLayout bindDrawerLayout() {
            return (DrawerLayout) findViewById(R.id.drawer_layout);
        }
    }

    private class NavListener implements NavUtils.NavItemClickListener {
        @Override
        public void onNavItemClicked(final MenuItem item) {
            presenter.getMenuUrls().subscribe(new Action1<EnumMap<MenuType, String>>() {
                @Override
                public void call(EnumMap<MenuType, String> menu) {
                    Log.i(TAG, menu.get(MenuType.valueOf(item.getTitle().toString())));
                }
            });
        }
    }


}
