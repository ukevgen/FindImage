package com.internship.pbt.findimage.view.fragment.favorites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.internship.pbt.findimage.R;
import com.internship.pbt.findimage.cache.CachePhotos;
import com.internship.pbt.findimage.presentation.presenter.favorites.FavoritesPresenterImp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 01.03.2017.
 */

public class FavoritesFragment extends Fragment implements FavoritesView {

    private FavoritesPresenterImp presenter;
    private CachePhotos cachePhotos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        presenter = new FavoritesPresenterImp(this);
        cachePhotos = CachePhotos.getInstance(getContext());

        chekPhoto();

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private void chekPhoto() {
        ArrayList<String> allPhotosPath = cachePhotos.getAllPhotosPath();
        for (String s : allPhotosPath) {
            Log.d("TAG", s);
        }
    }
}
