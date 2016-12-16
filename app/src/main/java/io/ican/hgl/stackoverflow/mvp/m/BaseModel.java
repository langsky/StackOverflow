package io.ican.hgl.stackoverflow.mvp.m;


/**
 * Created by swd1 on 16-12-13.
 */

public interface BaseModel extends IModel {

    /**
     * get the menu name of this page.
     * @return menu name
     */
    String getMenuName();

    /**
     * get the tab name of this page.
     * @return tab name
     */
    String getTabName();

    /**
     * get page number of this page.
     * @return page number
     */
    String getPageNumber();

    /**
     * get tab count of page
     * @return tab count.
     */
    int getTabCount();

    /**
     * get menu count of page
     * @return menu count.
     */
    int getMenuCount();
}
