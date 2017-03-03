package com.internship.pbt.findimage.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.internship.pbt.findimage.R;
import com.internship.pbt.findimage.adapter.ViewPagerAdapter;
import com.internship.pbt.findimage.presentation.presenter.main.MainPresenterIml;
import com.internship.pbt.findimage.view.MainView;
import com.internship.pbt.findimage.view.fragment.favorites.FavoritesFragment;
import com.internship.pbt.findimage.view.fragment.results.ResultsFragment;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener,
        TabLayout.OnTabSelectedListener {

    private static final String FR_TAG = "FV_TAG";
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MainPresenterIml presenter;
    private ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenterIml(this);
        initViews();
    }

    @Override
    public void onClick(View v) {

    }

    private void initViews() {
        adapter.addFragment(new ResultsFragment(), "Results");
        adapter.addFragment(new FavoritesFragment(), "Favorites");
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setOnTabSelectedListener(this);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getPosition() == 1) {
            adapter.getItem(1).onResume();
        }

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
