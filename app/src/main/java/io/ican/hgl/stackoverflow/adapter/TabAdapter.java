package io.ican.hgl.stackoverflow.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import io.ican.hgl.stackoverflow.mvp.m.BaseModel;
import io.ican.hgl.stackoverflow.util.C;
import io.ican.hgl.stackoverflow.view.fragment.MainPageFragment;

/**
 * Created by swd1 on 16-12-13.
 */

public class TabAdapter extends FragmentStatePagerAdapter {

    private BaseModel model;

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setModel(BaseModel model) {
        this.model = model;
    }

    @Override
    public Fragment getItem(int position) {
        String tabName = model.getTabName();
        String pageNumber = model.getPageNumber();
        return MainPageFragment.newInstance(tabName, pageNumber);
    }

    @Override
    public int getCount() {
        return model.getTabCount();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return C.MAIN_PAGE_TABS.get(position);
    }
}
