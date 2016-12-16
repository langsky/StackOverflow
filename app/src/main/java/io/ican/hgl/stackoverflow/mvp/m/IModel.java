package io.ican.hgl.stackoverflow.mvp.m;

import rx.Observable;

/**
 * Created by swd1 on 16-12-13. this interface is to bind Presenter.
 */

public interface IModel {
    /**
     * This method returns the url of page
     * @return URL of page.
     */
    Observable<String> getUrl();
}
