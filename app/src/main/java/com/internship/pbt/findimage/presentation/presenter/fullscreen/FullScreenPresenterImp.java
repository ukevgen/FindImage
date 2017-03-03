package com.internship.pbt.findimage.presentation.presenter.fullscreen;

import android.graphics.Bitmap;

import com.internship.pbt.findimage.cache.CacheSharedPreferences;
import com.internship.pbt.findimage.util.Converter;
import com.internship.pbt.findimage.view.fragment.FullScreenView;

/**
 * Created by ukevgen on 03.03.2017.
 */

public class FullScreenPresenterImp implements FullScreenPresenter {
    private FullScreenView fullScreenView;
    private CacheSharedPreferences sharedPreferences;
    private Converter converter;

    public FullScreenPresenterImp(FullScreenView fullScreenView,
                                  CacheSharedPreferences sharedPreferences,
                                  Converter converter) {
        this.fullScreenView = fullScreenView;
        this.sharedPreferences = sharedPreferences;
        this.converter = converter;
    }

    @Override
    public void getImage() {
        Bitmap bitmap = converter.decodeBase64(sharedPreferences.getStringImage());
        fullScreenView.showImage(bitmap);
    }

    @Override
    public void onDestroy() {
        sharedPreferences.deleteAllCache();
    }


}
