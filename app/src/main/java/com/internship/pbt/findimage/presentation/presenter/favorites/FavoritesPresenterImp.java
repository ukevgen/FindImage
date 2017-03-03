package com.internship.pbt.findimage.presentation.presenter.favorites;

import com.internship.pbt.findimage.adapter.FavImageAdapter;
import com.internship.pbt.findimage.view.fragment.favorites.FavoritesView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 01.03.2017.
 */

public class FavoritesPresenterImp implements FavoritesPresenter {

    private FavoritesView favoritesView;
    private FavImageAdapter adapter;
    private List<String> urls;

    public FavoritesPresenterImp(FavoritesView favoritesView) {
        this.favoritesView = favoritesView;
        urls = new ArrayList<>();
        adapter = new FavImageAdapter(favoritesView.geCurrentContext());
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
        setAdapter();
    }

    private void setAdapter() {
        adapter.setUrls(urls);
        adapter.notifyDataSetChanged();
    }

    public FavImageAdapter getAdapter() {
        return adapter;
    }
}
