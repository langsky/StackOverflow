package io.ican.hgl.stackoverflow;

import android.app.Application;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Map;

import io.ican.hgl.stackoverflow.engineer.JsoupEngineer;
import io.ican.hgl.stackoverflow.engineer.JsoupParser;
import io.ican.hgl.stackoverflow.util.C;
import rx.Observable;
import rx.functions.Func1;

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

    //get menu url
    public static Observable<Map<String, String>> getMenuUrls() {
        return JsoupEngineer.MAIN_PAGE(BASE_URL).flatMap(new Func1<Document, Observable<Element>>() {
            @Override
            public Observable<Element> call(Document document) {
                return Observable.just(document.getElementById(C.HEADER_MENU_ID));
            }
        }).flatMap(new Func1<Element, Observable<Map<String, String>>>() {
            @Override
            public Observable<Map<String, String>> call(Element element) {
                return JsoupParser.MAIN_MENU(element);
            }
        });
    }
}
