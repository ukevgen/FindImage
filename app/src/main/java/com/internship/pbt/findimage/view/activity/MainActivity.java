package com.internship.pbt.findimage.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.internship.pbt.findimage.R;
import com.internship.pbt.findimage.presentation.presenter.main.MainPresenterIml;
import com.internship.pbt.findimage.view.MainView;
import com.internship.pbt.findimage.view.fragment.favorites.FavoritesFragment;
import com.internship.pbt.findimage.view.fragment.results.ResultsFragment;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {

    private static final String RESULTS_FRAGMENT_FR_TAG = "11";
    private static final String FAVORITES_FRAGMENT_FR_TAG = "12";
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private TextView mTextOnToolbar;
    private SearchView mSearchView;
    private MenuItem searchMenuItem;
    private MainPresenterIml presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenterIml(this);
        initViews();

        presenter.onResultsTab();


    }

    @Override
    public void onClick(View v) {

    }

    private void initViews() {
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.addTab(mTabLayout.newTab().setText("Results"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Favorites"));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    presenter.onResultsTab();
                }
                if (tab.getPosition() == 1) {
                    presenter.onFavoritesTab();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void showResultsFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(RESULTS_FRAGMENT_FR_TAG);
        if (fragment != null) {
            transaction.replace(R.id.main_screen_container, fragment, RESULTS_FRAGMENT_FR_TAG)
                    .commit();
            return;
        }

        transaction.replace(R.id.main_screen_container, new ResultsFragment(),
                RESULTS_FRAGMENT_FR_TAG)
                .commit();

    }

    @Override
    public void showFavoritesFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(FAVORITES_FRAGMENT_FR_TAG);
        if (fragment != null) {
            transaction.replace(R.id.main_screen_container, fragment, FAVORITES_FRAGMENT_FR_TAG)
                    .commit();
            return;
        }

        transaction.replace(R.id.main_screen_container, new FavoritesFragment(),
                FAVORITES_FRAGMENT_FR_TAG)
                .commit();
    }
}
