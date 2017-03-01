package com.internship.pbt.findimage.view.fragment.favorites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.internship.pbt.findimage.R;
import com.internship.pbt.findimage.presentation.presenter.favorites.FavoritesPresenterImp;

/**
 * Created by user on 01.03.2017.
 */

public class FavoritesFragment extends Fragment implements FavoritesView {

    private FavoritesPresenterImp presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        presenter = new FavoritesPresenterImp(this);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
