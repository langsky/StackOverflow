package io.ican.hgl.stackoverflow.engineer;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import io.ican.hgl.stackoverflow.entity.menu.MenuType;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by swd1 on 16-11-30.
 * JsoupEngineer
 */

public class JsoupEngineer {

    //issue 778
    public static Element removeAttr(Element element, String... attr) {
        for (Iterator<Attribute> iterator = element.attributes().iterator(); iterator.hasNext(); ) {
            Attribute a = iterator.next();
            for (String anAttr : attr) {
                if (a.getKey().equals(anAttr))
                    iterator.remove();
            }

        }
        return element;
    }

    //获得主页信息
    public static Observable<Document> MAIN_PAGE(String baseUrl) {
        return Observable.just(baseUrl)
                .map(new Func1<String, Document>() {
                    @Override
                    public Document call(String s) {
                        try {
                            return Jsoup.connect(s).userAgent("Chrome/54.0.2840.100").get();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io());
    }

    //对post方法进行封装
    public static Observable<Document> POST_FORM(String baseUrl, final String menuType, final Map<String, String> content) {
        return Observable.just(baseUrl)
                .map(new Func1<String, Connection.Response>() {
                    @Override
                    public Connection.Response call(String s) {
                        try {
                            return Jsoup.connect(s + "/" + menuType).userAgent("Chrome/54.0.2840.100").data(content).timeout(100000).method(Connection.Method.GET).execute();
                        } catch (IOException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }
                })
                .map(new Func1<Connection.Response, Document>() {
                    @Override
                    public Document call(Connection.Response response) {
                        try {
                            return response.parse();
                        } catch (IOException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }
                })
                .subscribeOn(Schedulers.io());
    }


}
