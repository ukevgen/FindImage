package com.internship.pbt.findimage.presentation.presenter.favorites;

import com.internship.pbt.findimage.view.fragment.favorites.FavoritesFragment;
import com.internship.pbt.findimage.view.fragment.favorites.FavoritesView;

/**
 * Created by user on 01.03.2017.
 */

public class FavoritesPresenterImp implements FavoritesPresenter {
    private FavoritesView favoritesView;

    public FavoritesPresenterImp(FavoritesView favoritesView) {
        this.favoritesView = favoritesView;
    }
}
