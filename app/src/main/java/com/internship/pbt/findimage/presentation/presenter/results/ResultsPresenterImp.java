package com.internship.pbt.findimage.presentation.presenter.results;

import android.graphics.Bitmap;

import com.internship.pbt.findimage.adapter.ImageAdapter;
import com.internship.pbt.findimage.cache.CachePhotos;
import com.internship.pbt.findimage.net.content.ImageResponse;
import com.internship.pbt.findimage.net.content.Item;
import com.internship.pbt.findimage.view.fragment.results.ResultsView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by user on 01.03.2017.
 */

public class ResultsPresenterImp implements ResultsPresenter, ImageAdapter.OnImageClickCallback {

    private ResultsView resultsView;
    private ImageResponse imageResponse;
    private ImageAdapter adapter;
    private List<Item> items;
    private CachePhotos cachePhotos;


    public ResultsPresenterImp(ResultsView resultsView, CachePhotos cachePhotos) {
        this.resultsView = resultsView;
        this.cachePhotos = cachePhotos;
        items = new ArrayList<>();
        adapter = new ImageAdapter(items, resultsView.geCurrentContext());
        adapter.setOnImageClickCallback(this);
    }

    public void setItems(List<Item> items) {
        this.items = items;
        setAdapter();
    }


    public void setAdapter() {
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onFindImage() {
        resultsView.findImage();
    }

    @Override
    public void checkSearchRequest(String query) {
        if (query != null)
            resultsView.findImage();
        else
            resultsView.showToast("Nothing to search");
    }

    @Override
    public void addImageToFavorites() {
        if (adapter.getBitmaps().size() != 0) {
            HashSet<Bitmap> bitmaps = adapter.getBitmaps();
            for (Bitmap b : bitmaps) {
                cachePhotos.savePhoto(b);
            }
            resultsView.showToast("image added");
        }
    }


    @Override
    public void onImageClick(int position) {
        //TODO save photo to casche
        resultsView.showFullScreenImage();
        resultsView.showToast(String.valueOf(position));
    }

    public ImageAdapter getAdapter() {
        return adapter;
    }


}
