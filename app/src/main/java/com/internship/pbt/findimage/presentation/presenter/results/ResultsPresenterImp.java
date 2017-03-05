package com.internship.pbt.findimage.presentation.presenter.results;

import android.graphics.Bitmap;

import com.internship.pbt.findimage.adapter.ImageAdapter;
import com.internship.pbt.findimage.cache.CachePhotos;
import com.internship.pbt.findimage.cache.CacheSharedPreferences;
import com.internship.pbt.findimage.net.content.Item;
import com.internship.pbt.findimage.util.Converter;
import com.internship.pbt.findimage.view.fragment.results.ResultsView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import io.realm.Realm;

/**
 * Created by user on 01.03.2017.
 */

public class ResultsPresenterImp implements ResultsPresenter, ImageAdapter.OnImageClickCallback {

    private static final String ADD_IMAGE = "Image added";
    private static final String EMPTY = "Nothing to search";
    private static final String NOTHING_ADD_TO_DB = "Nothing to add, mark image";
    private ResultsView resultsView;
    private ImageAdapter adapter;
    private List<Item> items;
    private CachePhotos cachePhotos;
    private Converter converter;
    private CacheSharedPreferences sharedPreferences;
    private Realm realm;

    public ResultsPresenterImp(ResultsView resultsView, CachePhotos cachePhotos,
                               CacheSharedPreferences sharedPreferences) {
        this.resultsView = resultsView;
        this.cachePhotos = cachePhotos;
        this.sharedPreferences = sharedPreferences;
        realm = Realm.getDefaultInstance();
        converter = new Converter(resultsView.geCurrentContext());
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
            resultsView.showToast(EMPTY);
    }

    @Override
    public void addImageToFavorites() {
        if (adapter.getBitmaps().size() != 0) {
            HashSet<Bitmap> bitmaps = adapter.getBitmaps();
            for (Bitmap b : bitmaps) {
                cachePhotos.savePhoto(b);
            }
            resultsView.showToast(ADD_IMAGE);
        }
    }

    @Override
    public void saveResultsToDb() {
        realm.beginTransaction();
        int count = 0;
        for (Item i : adapter.getItems()) {
            if (i.isChecked()) {
                realm.copyToRealm(i);
                count++;
            }
        }
        if (count == 0)
            resultsView.showToast(NOTHING_ADD_TO_DB);
        realm.commitTransaction();
    }

    @Override
    public void onDestroy() {
        realm.close();
    }


    @Override
    public void onImageClick(int position) {
        String encode = converter.encodeImageTobase64(adapter.getCurrentImage());
        sharedPreferences.putStringImage(encode);

        resultsView.showFullScreenImage();
    }

    public ImageAdapter getAdapter() {
        return adapter;
    }


}
