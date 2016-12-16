package io.ican.hgl.stackoverflow.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import io.ican.hgl.stackoverflow.mvp.p.impl.MainPagePresenter;
import io.ican.hgl.stackoverflow.mvp.v.IView;


public class MainPageFragment extends Fragment implements IView {

    private static final String ARG_PAGES = "pageNumber";
    private static final String ARG_TABS = "tabName";

    protected String pageNumber;
    protected String tabName;

    MainPagePresenter presenter;

    public MainPageFragment() {
        // Required empty public constructor
    }


    public static MainPageFragment newInstance(String tabName, String pageNumber) {
        MainPageFragment fragment = new MainPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TABS, tabName);
        args.putString(ARG_PAGES, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tabName = getArguments().getString(ARG_TABS);
            pageNumber = getArguments().getString(ARG_PAGES);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter = new MainPagePresenter(getContext());
        presenter.bindView(this);
        presenter.setBinding(binding);
        presenter.loadPage();
    }


    @Override
    public void refresh() {

    }

    @Override
    public void update() {

    }

    @Override
    public void error(String e) {

    }
}
