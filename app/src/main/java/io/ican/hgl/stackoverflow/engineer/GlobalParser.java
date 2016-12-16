package io.ican.hgl.stackoverflow.engineer;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

import io.ican.hgl.stackoverflow.util.C;

/**
 * Created by swd1 on 16-12-14.
 */

public class GlobalParser {

    public static String parsePageUrl(Document document) {
        return document.head().select("meta[property=\"og:url\"]").first().absUrl("content");
    }

    public static Map<String, String> parseMenus(Document document) {
        Elements elements = document.getElementById(C.HEADER_MENU_ID).select("a[href]");
        Map<String, String> maps = new HashMap<>();
        for (Element e :
                elements) {
            maps.put(e.text(), e.absUrl("href"));
        }
        return maps;
    }

    public static Map<String, String> parseTabs(Document document) {
        Elements elements = document.getElementById(C.TABS_ID).children().select("a[data-value=\"*\"]");
        Map<String, String> maps = new HashMap<>();
        for (Element e :
                elements) {
            maps.put(e.text(), e.absUrl("href"));
        }
        return maps;
    }

    public static String parseMenu(Document document) {
        Element element = document.getElementById(C.HEADER_MENU_ID).getElementsByClass(C.YOU_ARE_HERE).first();
        return element.text();
    }

    public static String parseTab(Document document) {
        Element element = document.getElementById(C.TABS_ID).getElementsByClass(C.YOU_ARE_HERE).first();
        return element.text();
    }

    public static String parsePageNumber(Document document) {
        Element element = document.getElementsByClass(C.PAGE_NUMBERS_CURRENT).first();
        return element.text();
    }

}
