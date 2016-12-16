package io.ican.hgl.stackoverflow.mvp.p;

import java.util.Map;

/**
 * Created by hgl on 16-12-12.
 *
 */

public interface BasePresenter extends IPresenter {
    void loadDefaultPage();
    void loadPage(String baseUrl, final String menuType, final Map<String, String> content);
    void loadCompeted();
}
