package com.internship.pbt.findimage.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.internship.pbt.findimage.R;
import com.internship.pbt.findimage.presentation.presenter.main.MainPresenterIml;
import com.internship.pbt.findimage.view.MainView;
import com.internship.pbt.findimage.view.fragment.TabsFragment;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {


    private Toolbar mToolbar;
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


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new TabsFragment())
                .commit();
    }


}
