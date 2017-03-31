package com.zly.diycode.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.zly.diycode.R;
import com.zly.diycode.common.Navigation;
import com.zly.diycode.common.UserManager;
import com.zly.diycode.common.feature.BaseActivity;
import com.zly.diycode.common.feature.VoidPresenter;
import com.zly.diycode.data.Callback;
import com.zly.diycode.data.user.UserData;
import com.zly.diycode.data.user.UserRemoteData;
import com.zly.diycode.databinding.ActivityMainBinding;
import com.zly.diycode.databinding.NavHeaderDrawerBinding;
import com.zly.diycode.editor.EditRequester;
import com.zly.diycode.home.NewsFragment;
import com.zly.diycode.home.TopicsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, VoidPresenter>
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavHeaderDrawerBinding mNavHeaderDrawerBinding;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }


    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mDataBinding.setPresenter(this);
        Toolbar toolbar = mDataBinding.views.toolbar;
        ViewCompat.setElevation(toolbar, 0);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final DrawerLayout drawer = mDataBinding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavHeaderDrawerBinding = NavHeaderDrawerBinding.inflate(getLayoutInflater());
        mDataBinding.navView.addHeaderView(mNavHeaderDrawerBinding.getRoot());


        NavigationView navigationView = mDataBinding.navView;
        navigationView.setNavigationItemSelectedListener(this);
        mDataBinding.views.tabLayout.setupWithViewPager(mDataBinding.views.flContent);
        mDataBinding.views.flContent.setAdapter(new ContentPagerAdapter(getSupportFragmentManager()));

        getMeInfo();
    }

    @Override
    protected boolean isNeedInsertToolbar() {
        return false;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@Nullable MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_topics || id == R.id.nav_favorite
                || id == R.id.nav_replies || id == R.id.action_shared) {
            if (!checkLogin(null)) {
                return false;
            }
        }

        if (id == R.id.nav_topics) {
            // Handle the camera action
        } else if (id == R.id.nav_favorite) {

        } else if (id == R.id.nav_replies) {

        } else if (id == R.id.nav_shared) {

        } else if (id == R.id.nav_abort) {

        } else if (id == R.id.nav_setting) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void createPaper() {
        EditRequester editRequester = new EditRequester();
        editRequester.setType(EditRequester.TYPE_CREATE_PAPER);
        Navigation.getInstance().openEditor(this, editRequester);
    }

    private void getMeInfo() {
        if (UserManager.getInstance().isLogin()) {
            UserRemoteData.getInstance().getMeInfo(new Callback<MeModel>() {
                @Override
                public void onSuccess(MeModel meModel) {
                    mNavHeaderDrawerBinding.setMe(meModel);
                }

                @Override
                public void onError(String messgae) {

                }
            });
        }
    }

    private static class ContentPagerAdapter extends FragmentPagerAdapter {

        private List<Content> mFragments = new ArrayList<>(3);

        {
            mFragments.add(new Content("社区", new TopicsFragment()));
            mFragments.add(new Content("新闻", new NewsFragment()));
            mFragments.add(new Content("酷站", new TopicsFragment()));
        }

        ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position).fragment;
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragments.get(position).title;
        }

        private class Content {

            String title;
            Fragment fragment;

            public Content(String title, Fragment fragment) {
                this.title = title;
                this.fragment = fragment;
            }
        }
    }
}
