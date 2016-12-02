package io.ican.hgl.stackoverflow.engineer;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.ican.hgl.stackoverflow.entity.question.Summary;
import io.ican.hgl.stackoverflow.entity.tab.Tab;
import rx.Observable;

/**
 * Created by hgl on 16-12-1.
 */

public class JsoupParser {

    public static Observable<Map<String, String>> MAIN_MENU(Element element) {
        Elements elements = element.select("a[href]");
        Map<String, String> maps = new HashMap<>();
        for (Element e :
                elements) {
            maps.put(e.text(), e.absUrl("href"));
        }
        return Observable.just(maps);
    }

    public static Observable<ArrayList<Tab>> TABS(Element element) {
        Elements elements = element.children().select("a[data-value=\"*\"]");
        ArrayList<Tab> tabs = new ArrayList<>();
        for (Element e :
                elements) {
            Tab tab = new Tab();
            tab.text = e.text();
            tab.url = e.absUrl("href");
            tabs.add(tab);
        }
        return Observable.just(tabs);
    }

    public static Summary parseQuestionSummary(Element element) {
        Summary question = new Summary();
        question.id = element.id();
        question.votes = element.getElementsByClass("votes").first().text();
        question.answers = parseAnswered(element);
        question.views = parseViews(element);
        question.url = parseSummaryUrl(element);
        question.summary = parseSummaryText(element);
        question.tags = parseTagsName(parseTags(element), question.tags);
        question.reputationScore = parseScore(element);
        question.excerpt = parseExcerpt(element);
        return question;
    }

    private static String parseExcerpt(Element element) {
        Element e = element.getElementsByClass("excerpt").first();
        return e != null ? e.text() : "";
    }

    private static String parseScore(Element element) {
        Element e = element.getElementsByClass("reputation-score").first();
        return e != null ? e.text() : "";
    }

    private static Summary.Answers parseAnswered(Element element) {
        Summary.Answers answers = new Summary.Answers();
        Element e = element.getElementsByClass("status answered-accepted").first();
        if (e != null) {
            answers.state = Summary.ANSWERED_ACCEPTED;
        } else {
            e = element.getElementsByClass("status answered").first();
            if (e != null) {
                answers.state = Summary.ANSWERED;
            } else {
                e = element.getElementsByClass("status unanswered").first();
                answers.state = Summary.UNANSWERED;
            }
        }
        answers.count = e.text();
        return answers;
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
