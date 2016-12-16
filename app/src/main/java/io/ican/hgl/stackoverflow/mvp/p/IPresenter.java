package io.ican.hgl.stackoverflow.mvp.p;

import java.util.Map;

import io.ican.hgl.stackoverflow.mvp.m.IModel;
import io.ican.hgl.stackoverflow.mvp.v.IView;

/**
 * Created by hgl on 16-12-12. top presenter interface.
 */

public interface IPresenter<T extends IView, K extends IModel, S extends String> {
    //view
    void bindView(T t);

    //model
    K bindModel(String url);

    K bindModelWithParams(String url, Map<S, S> params);
}
