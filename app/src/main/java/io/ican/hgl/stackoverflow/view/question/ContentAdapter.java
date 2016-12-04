package io.ican.hgl.stackoverflow.view.question;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import io.ican.hgl.stackoverflow.entity.question.SortType;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class ContentAdapter extends FragmentPagerAdapter {

    public ContentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        String tab = SortType.Question.values()[position].getText();
        String tag = "";
        String page = "1";
        return ContentPage.newInstance(tab, tag, page);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return SortType.Question.values().length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return SortType.Question.values()[position].getText();
    }
}
