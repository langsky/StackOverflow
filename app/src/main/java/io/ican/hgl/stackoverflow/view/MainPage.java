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
import java.util.List;
import java.util.Map;

import io.ican.hgl.stackoverflow.R;
import io.ican.hgl.stackoverflow.adapter.SummaryAdapter;
import io.ican.hgl.stackoverflow.databinding.MainPageBinding;
import io.ican.hgl.stackoverflow.engineer.JsoupEngineer;
import io.ican.hgl.stackoverflow.engineer.JsoupParser;
import io.ican.hgl.stackoverflow.entity.question.Summary;
import io.ican.hgl.stackoverflow.util.NavUtils;
import io.ican.hgl.stackoverflow.view.question.QuestionPage;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainPage extends AppCompatActivity {

    NavUtils navUtils;
    BindDrawer drawer;
    NavListener listener;

    Observable<Document> document;
    String baseUrl = "http://stackoverflow.com";

    MainPageBinding binding;
    SummaryAdapter adapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        document = JsoupEngineer.MAIN_PAGE(baseUrl);

        document.map(new Func1<Document, List<Element>>() {
            @Override
            public List<Element> call(Document document) {
                return document.getElementsByClass("question-summary narrow");
            }
        }).map(new Func1<List<Element>, List<Summary>>() {
            @Override
            public List<Summary> call(List<Element> elements) {
                List<Summary> summaries = new ArrayList<>();
                for (Element e : elements) {
                    summaries.add(JsoupParser.parseQuestionSummary(e));
                }
                Log.e("HUHUHU", summaries.size() + "       size");
                return summaries;
            }
        }).subscribe(new Action1<List<Summary>>() {
            @Override
            public void call(List<Summary> summaries) {
                adapter = new SummaryAdapter(summaries, MainPage.this);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainPage.this);
                binding.setAdapter(adapter);
                binding.setLayoutManager(layoutManager);
            }
        });


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


    private class BindDrawer implements NavUtils.NavBindDrawer {

        @Override
        public DrawerLayout bindDrawerLayout() {
            return (DrawerLayout) findViewById(R.id.drawer_layout);
        }
    }

    private class NavListener implements NavUtils.NavItemClickListener {

        @Override
        public void onNavItemClicked(final MenuItem item) {
            if (item.getGroupId() == R.id.main_menu) {
                getMenuUrl(item.getTitle().toString()).subscribe(subscriber());
            }
        }
    }


    private Observable<String> getMenuUrl(final String menuName) {
        return document.flatMap(new Func1<Document, Observable<Element>>() {
            @Override
            public Observable<Element> call(Document document) {
                return Observable.just(document.getElementById("hmenus"));
            }
        }).flatMap(new Func1<Element, Observable<Map<String, String>>>() {
            @Override
            public Observable<Map<String, String>> call(Element element) {
                return JsoupParser.MAIN_MENU(element);
            }
        }).map(new Func1<Map<String, String>, String>() {
            @Override
            public String call(Map<String, String> map) {
                return map.get(menuName);
            }
        });
    }

    private Subscriber<String> subscriber() {
        return new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                startActivity(new Intent(MainPage.this, QuestionPage.class));
            }
        };
    }

    private void startNewPage(String url) {

    }

}
