package com.internship.pbt.findimage.view.fragment.results;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.internship.pbt.findimage.R;
import com.internship.pbt.findimage.presentation.presenter.favorites.FavoritesPresenterImp;
import com.internship.pbt.findimage.presentation.presenter.results.ResultsPresenterImp;

/**
 * Created by user on 01.03.2017.
 */

public class ResultsFragment extends Fragment implements ResultsView {

    private ResultsPresenterImp presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        presenter = new ResultsPresenterImp(this);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
