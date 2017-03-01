package com.internship.pbt.findimage.presentation.presenter.main;


import com.internship.pbt.findimage.view.MainView;

/**
 * Created by user on 01.03.2017.
 */

public class MainPresenterIml implements MainPresenter {
    private MainView mainView;

    public MainPresenterIml(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onResultsTab() {
        mainView.showResultsFragment();
    }

    @Override
    public void onFavoritesTab() {
        mainView.showFavoritesFragment();
    }
}
