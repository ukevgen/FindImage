package com.internship.pbt.findimage.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.internship.pbt.findimage.R;
import com.internship.pbt.findimage.adapter.ViewPagerAdapter;
import com.internship.pbt.findimage.presentation.presenter.main.MainPresenterIml;
import com.internship.pbt.findimage.view.MainView;
import com.internship.pbt.findimage.view.fragment.favorites.FavoritesFragment;
import com.internship.pbt.findimage.view.fragment.results.ResultsFragment;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {

    private static final String FR_TAG = "FV_TAG";
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TextView mTextOnToolbar;
    private MainPresenterIml presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenterIml(this);
        initViews();
        getSupportFragmentManager().addOnBackStackChangedListener(getListener());
    }

    @Override
    public void onClick(View v) {

    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                  //  presenter.onFavoritesTab();
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

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ResultsFragment(), "Results");
        adapter.addFragment(new FavoritesFragment(), "Favorites");
        viewPager.setAdapter(adapter);
    }

    private FragmentManager.OnBackStackChangedListener getListener() {
        FragmentManager.OnBackStackChangedListener result = new FragmentManager.OnBackStackChangedListener() {
            public void onBackStackChanged() {
                FragmentManager manager = getSupportFragmentManager();
                if (manager != null) {
                    int backStackEntryCount = manager.getBackStackEntryCount();
                    if (backStackEntryCount == 0) {
                        finish();
                    }
                    Fragment fragment = manager.getFragments()
                            .get(backStackEntryCount - 1);
                    fragment.onResume();
                }
            }
        };
        return result;
    }


    @Override
    public void showFavoritesFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(FR_TAG);
        if (fragment != null) {
            transaction.replace(R.id.main_screen_container, fragment, FR_TAG)
                    .commit();
            return;
        }

        transaction.replace(R.id.main_screen_container, new FavoritesFragment(),
                FR_TAG)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (fragments == 1) {
            finish();
            return;
        }

        super.onBackPressed();
    }
}
