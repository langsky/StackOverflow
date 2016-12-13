package io.ican.hgl.stackoverflow.mvp.p.impl;

import android.content.Context;

import org.jsoup.nodes.Document;

import java.util.List;

import io.ican.hgl.stackoverflow.databinding.MainPageBinding;
import io.ican.hgl.stackoverflow.entity.question.Summary;
import io.ican.hgl.stackoverflow.mvp.p.BasePresenter;
import io.ican.hgl.stackoverflow.mvp.v.IView;
import rx.Observable;

/**
 * Created by swd1 on 16-12-13.
 */

public class QuestionsPresenter implements BasePresenter {

    private String tab;
    private Context context;
    private IView view;

    private Observable<Document> document;
    private Observable<List<Summary>> summaries;

    public QuestionsPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void loadData() {

    }

    @Override
    public void loadCompeted() {

    }

    @Override
    public void bindView(IView iView) {

    }
}
