package io.ican.hgl.stackoverflow.util;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;


/**
 * Created by hgl on 16-12-2.
 */

public class NavUtils implements NavigationView.OnNavigationItemSelectedListener {

    private NavItemClickListener listener;
    private NavBindDrawer drawer;

    public interface NavItemClickListener {
        void onNavItemClicked(MenuItem item);
    }

    public interface NavBindDrawer {
        DrawerLayout bindDrawerLayout();
    }

    public void setNavItemClickListener(NavItemClickListener listener) {
        this.listener = listener;
    }

    public void setNavBindDrawer(NavBindDrawer drawer) {
        this.drawer = drawer;
    }

    public void setNavigationView(NavigationView view) {
        view.setNavigationItemSelectedListener(this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        listener.onNavItemClicked(item);
        drawer.bindDrawerLayout().closeDrawer(GravityCompat.START);
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);*/
        return true;
    }
}
