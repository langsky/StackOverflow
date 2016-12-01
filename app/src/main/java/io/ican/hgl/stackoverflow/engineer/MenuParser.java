package io.ican.hgl.stackoverflow.engineer;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import io.tcl.hgl.workoverflow.entity.Tab;
import rx.Observable;

/**
 * Created by hgl on 16-12-1.
 */

public class MenuParser {

    public static Observable<List<Tab>> MAIN_MENU(Element element) {
        Elements elements = element.getElementsByClass("js-gps-track");
        List<Tab> tabs = new ArrayList<>();
        for (Element e :
                elements) {
            Tab tab = new Tab();
            tab.text = e.text();
            tab.url = e.absUrl("href");
            tabs.add(tab);
        }
        return Observable.just(tabs);
    }

    public static Observable<List<Tab>> TABS(Element element){
        Elements elements = element.children().select("a[data-value=\"*\"]");
        List<Tab> tabs = new ArrayList<>();
        for (Element e :
                elements) {
            Tab tab = new Tab();
            tab.text = e.text();
            tab.url = e.absUrl("href");
            tabs.add(tab);
        }
        return Observable.just(tabs);
    }
}
