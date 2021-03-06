package io.ican.hgl.stackoverflow.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.ican.hgl.stackoverflow.TheApp;
import io.ican.hgl.stackoverflow.adapter.SummaryAdapter;
import io.ican.hgl.stackoverflow.databinding.SubQuestionPageBinding;
import io.ican.hgl.stackoverflow.engineer.JsoupEngineer;
import io.ican.hgl.stackoverflow.engineer.SummaryParser;
import io.ican.hgl.stackoverflow.entity.question.Summary;
import io.ican.hgl.stackoverflow.util.C;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * A placeholder fragment containing a simple view.
 */
public class ContentPage extends Fragment {

    private static final String ARG_TABS_NAME = "tab";
    private static final String ARG_TAGS_NAME = "tag";
    private static final String ARG_PAGES_NUM = "page";

    private SubQuestionPageBinding binding;

    private String tabName;
    private String tagName;
    private String pageNumber;

    private SummaryAdapter adapter;

    public ContentPage() {
    }

    public static ContentPage newInstance(String... name) {
        ContentPage fragment = new ContentPage();
        Bundle args = new Bundle();
        args.putString(ARG_TABS_NAME, name[0]);
        args.putString(ARG_TAGS_NAME, name[1]);
        args.putString(ARG_PAGES_NUM, name[2]);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        tabName = bundle.getString(ARG_TABS_NAME);
        tagName = bundle.getString(ARG_TAGS_NAME);
        pageNumber = bundle.getString(ARG_PAGES_NUM);
        binding = SubQuestionPageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Map<String, String> data = new HashMap<>();
        data.put(ARG_TABS_NAME, tabName);
        data.put(ARG_TAGS_NAME, tagName);
        data.put(ARG_PAGES_NUM, pageNumber);
        JsoupEngineer.PAGE_POST(C.BASE_URL, C.MENU_QUESTIONS, data)
                .map(new Func1<Document, List<Element>>() {
                    @Override
                    public List<Element> call(Document document) {
                        return document.getElementsByClass("question-summary");
                    }
                }).map(new Func1<List<Element>, List<Summary>>() {
            @Override
            public List<Summary> call(List<Element> elements) {
                List<Summary> summaries = new ArrayList<>();
                for (Element e : elements) {
                    summaries.add(SummaryParser.parseQuestionSummary(e));
                }
                return summaries;
            }
        }).subscribe(new Action1<List<Summary>>() {
            @Override
            public void call(List<Summary> summaries) {
                adapter = new SummaryAdapter(summaries, TheApp.instance);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TheApp.instance);
                binding.setAdapter(adapter);
                binding.setLayoutManager(layoutManager);
            }
        });
    }
}
