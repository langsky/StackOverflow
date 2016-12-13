package io.ican.hgl.stackoverflow.mvp.p.impl;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.ican.hgl.stackoverflow.adapter.SummaryAdapter;
import io.ican.hgl.stackoverflow.databinding.MainPageBinding;
import io.ican.hgl.stackoverflow.engineer.JsoupEngineer;
import io.ican.hgl.stackoverflow.engineer.JsoupParser;
import io.ican.hgl.stackoverflow.entity.question.Summary;
import io.ican.hgl.stackoverflow.mvp.p.BasePresenter;
import io.ican.hgl.stackoverflow.mvp.v.IView;
import io.ican.hgl.stackoverflow.util.C;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by hgl on 16-12-12.
 */

public class MainPagePresenter implements BasePresenter {

    private static final String TAG = "MainPagePresenter";

    private Context context;
    private IView view;
    private MainPageBinding binding;
    private Observable<Document> document;
    private Observable<List<Summary>> summaries;

    public MainPagePresenter(Context context) {
        this.context = context;
    }


    @Override
    //load all data
    public void loadData() {
        document = JsoupEngineer.MAIN_PAGE(C.BASE_URL);
        summaries = document.map(new Func1<Document, List<Element>>() {
            @Override
            public List<Element> call(Document document) {
                return document.getElementsByClass(C.QUESTION_SUMMARY_NARROW);
            }
        }).map(new Func1<List<Element>, List<Summary>>() {
            @Override
            public List<Summary> call(List<Element> elements) {
                List<Summary> summaries = new ArrayList<>();
                for (Element e : elements) {
                    summaries.add(JsoupParser.parseQuestionSummary(e));
                }
                return summaries;
            }
        });

        loadCompeted();
    }

    @Override
    public void loadCompeted() {
        summaries.subscribe(new Subscriber<List<Summary>>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompeted");
            }

            @Override
            public void onError(Throwable e) {
                view.error(e.getMessage());
            }

            @Override
            public void onNext(List<Summary> summaries) {
                view.update();
                SummaryAdapter adapter = new SummaryAdapter(summaries, context);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                binding.setAdapter(adapter);
                binding.setLayoutManager(layoutManager);
            }

            @Override
            public void onStart() {
                view.refresh();
            }
        });
    }

    @Override
    public void bindView(IView iView) {
        view = iView;
    }


    public void setBinding(MainPageBinding binding) {
        this.binding = binding;
    }

}
