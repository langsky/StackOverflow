package io.ican.hgl.stackoverflow.engineer;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import io.ican.hgl.stackoverflow.entity.question.Question;
import io.ican.hgl.stackoverflow.entity.tab.Tab;
import rx.Observable;

/**
 * Created by hgl on 16-12-1.
 */

public class JsoupParser {

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

    public static Question parseQSN(Element element) {
        Question qSN = new Question();
        qSN.id = element.id();
        qSN.votes = element.getElementsByClass("votes").first().text();
        qSN.answers = parseAnswered(element);
        qSN.views = parseViews(element);
        qSN.url = parseSummaryUrl(element);
        qSN.summary = parseSummaryText(element);
        qSN.tags = parseTagsName(parseTags(element), qSN.tags);
        qSN.reputationScore = parseScore(element);
        qSN.excerpt = parseExcerpt(element);
        return qSN;
    }

    private static String parseExcerpt(Element element) {
        Element e = element.getElementsByClass("excerpt").first();
        return e != null ? e.text() : "";
    }

    private static String parseScore(Element element) {
        Element e = element.getElementsByClass("reputation-score").first();
        return e != null ? e.text() : "";
    }

    private static String parseAnswered(Element element) {
        Element e = element.getElementsByClass("status answered-accepted").first() != null ?
                element.getElementsByClass("status answered-accepted").first() : element.getElementsByClass("status answered").first() != null ?
                element.getElementsByClass("status answered").first() : element.getElementsByClass("status unanswered").first();
        return e != null ? e.text() : "";
    }

    private static String parseViews(Element element) {
        Element e = element.getElementsByClass("views").first();
        return e != null ? e.text() : "";
    }

    private static String parseSummaryUrl(Element element) {
        return element.getElementsByClass("summary").first().select("a[abs:href]").first().attr("abs:href");
    }

    private static String parseSummaryText(Element element) {
        return element.getElementsByClass("summary").first().select("a[abs:href]").first().text();
    }

    private static ArrayList<Element> parseTags(Element element) {
        return element.getElementsByClass("post-tag");
    }

    private static ArrayList<String> parseTagsName(ArrayList<Element> tags, ArrayList<String> names) {
        for (Element tag : tags
                ) {
            names.add(tag.text());
        }
        return names;
    }

}
