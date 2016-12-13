package io.ican.hgl.stackoverflow.mvp.p;

import io.ican.hgl.stackoverflow.mvp.v.IView;

/**
 * Created by hgl on 16-12-12.
 */

public interface IPresenter<T extends IView> {
    void bindView(T t);
}
