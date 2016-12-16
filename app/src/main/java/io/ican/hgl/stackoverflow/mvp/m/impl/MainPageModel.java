package io.ican.hgl.stackoverflow.mvp.m.impl;

import org.jsoup.nodes.Document;

import java.util.Map;

import io.ican.hgl.stackoverflow.engineer.GlobalParser;
import io.ican.hgl.stackoverflow.engineer.JsoupEngineer;
import io.ican.hgl.stackoverflow.mvp.m.BaseModel;
import io.ican.hgl.stackoverflow.util.C;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by swd1 on 16-12-13.
 */

public class MainPageModel implements BaseModel {

    private Observable<Document> document;

    private Map<String, String> params;

    private int tabCount;
    private int menuCount;

    /**
     * construct method, default page.
     *
     * @param url URL of page.
     */
    public MainPageModel(String url) {
        document = JsoupEngineer.MAIN_PAGE(url);
        initData();
    }

    /**
     * construct method, including params.
     *
     * @param url    URL
     * @param params params, such as "sort","page" and so on.
     */
    public MainPageModel(String url, Map<String, String> params) {
        document = JsoupEngineer.PAGE_POST(url, "", params);
        this.params = params;
        initData();
    }

    private void initData() {
        setMenuCount();
        setTabCount();
    }

    /**
     * There is no menu in main page.
     *
     * @return null.
     */
    @Override
    public String getMenuName() {
        return null;
    }

    /**
     * get tab name, the default is "interesting".
     *
     * @return tab name.
     */
    @Override
    public String getTabName() {
        return params != null ? params.get("sort") != null ? params.get("sort") : C.MAIN_PAGE_INTERESTING : C.MAIN_PAGE_INTERESTING;
    }

    /**
     * get page number
     *
     * @return the default is 1.
     */
    @Override
    public String getPageNumber() {
        return params != null ? params.get("page") != null ? params.get("page") : "1" : "1";
    }

    /**
     * It is always a constant number.
     *
     * @return 6
     */
    @Override
    public int getTabCount() {
        return tabCount;
    }

    private void setTabCount() {
        document.map(new Func1<Document, Map<String, String>>() {
            @Override
            public Map<String, String> call(Document document) {
                return GlobalParser.parseTabs(document);
            }
        }).subscribe(new Action1<Map<String, String>>() {
            @Override
            public void call(Map<String, String> map) {
                tabCount = map.size();
            }
        });
    }

    private void setMenuCount() {
        document.map(new Func1<Document, Map<String, String>>() {
            @Override
            public Map<String, String> call(Document document) {
                return GlobalParser.parseMenus(document);
            }
        }).subscribe(new Action1<Map<String, String>>() {
            @Override
            public void call(Map<String, String> map) {
                menuCount = map.size();
            }
        });
    }

    /**
     * There needn't menu count
     *
     * @return always 0
     */
    @Override
    public int getMenuCount() {
        return menuCount;
    }

    @Override
    public Observable<String> getUrl() {
        return document.map(new Func1<Document, String>() {
            @Override
            public String call(Document document) {
                return GlobalParser.parsePageUrl(document);
            }
        });
    }
}
