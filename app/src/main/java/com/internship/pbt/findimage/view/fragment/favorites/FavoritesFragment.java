package com.internship.pbt.findimage.view.fragment.favorites;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.internship.pbt.findimage.R;
import com.internship.pbt.findimage.cache.CachePhotos;
import com.internship.pbt.findimage.presentation.presenter.favorites.FavoritesPresenterImp;

import java.util.List;

/**
 * Created by user on 01.03.2017.
 */

public class FavoritesFragment extends Fragment implements FavoritesView {

    private FavoritesPresenterImp presenter;
    private CachePhotos cachePhotos;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private List<String> allPhotosPath;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        presenter = new FavoritesPresenterImp(this);
        cachePhotos = CachePhotos.getInstance(getContext());

        recyclerView = (RecyclerView) view.findViewById(R.id.favorites_recycler);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private void checkPhoto() {
        if (cachePhotos.getAllPhotosPath().size() != 0) {
            allPhotosPath = cachePhotos.getAllPhotosPath();
        }
    }

    private void setUrls() {
        presenter.setUrls(allPhotosPath);
      //  recyclerView.setAdapter(presenter.getAdapter());
    }

    @Override
    public void onResume() {
        super.onResume();
        checkPhoto();
        setUrls();
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }

    @Override
    public Context geCurrentContext() {
        return this.getContext();
    }
}
