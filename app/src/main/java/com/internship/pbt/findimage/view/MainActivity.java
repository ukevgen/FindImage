package com.internship.pbt.findimage.view;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.internship.pbt.findimage.R;
import com.internship.pbt.findimage.presentation.presenter.main.MainPresenterIml;
import com.internship.pbt.findimage.view.fragment.favorites.FavoritesFragment;
import com.internship.pbt.findimage.view.fragment.results.ResultsFragment;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener,
        SearchView.OnQueryTextListener {

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
        mTextOnToolbar = (TextView) findViewById(R.id.chat_toolbar_title);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        searchMenuItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) searchMenuItem.getActionView();
        mSearchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // newText is text entered by user to SearchView
        Toast.makeText(getApplicationContext(), newText, Toast.LENGTH_LONG).show();
        return false;
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
