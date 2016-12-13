package io.ican.hgl.stackoverflow.mvp.v;

/**
 * Created by hgl on 16-12-12.
 */

public interface IView {

    void refresh();

    void update();

    void error(String e);
}
