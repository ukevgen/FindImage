package com.internship.pbt.findimage.view.fragment.results;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;

import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.internship.pbt.findimage.R;
import com.internship.pbt.findimage.loader.ImageLoader;
import com.internship.pbt.findimage.net.content.ImageResponse;
import com.internship.pbt.findimage.net.response.Response;
import com.internship.pbt.findimage.presentation.presenter.results.ResultsPresenterImp;

/**
 * Created by user on 01.03.2017.
 */

public class ResultsFragment extends Fragment implements ResultsView,
        LoaderManager.LoaderCallbacks<Response>, View.OnClickListener {

    private ResultsPresenterImp presenter;
    private String mQuery;
    private Button btFind;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        presenter = new ResultsPresenterImp(this);
        btFind = (Button) view.findViewById(R.id.find_image);
        setHasOptionsMenu(true);
        //getActivity().getLoaderManager().initLoader(R.id.image_loader, null, this);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        final SearchView searchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchViewAndroidActionBar.clearFocus();
                mQuery = null;
                btFind.setEnabled(true);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 0)
                    btFind.setEnabled(true);
                else
                    btFind.setEnabled(false);
                mQuery = newText;
                Log.d("TAG", newText);
                return false;
            }
        });
    }


    @Override
    public Loader<Response> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case R.id.image_loader:
                return new ImageLoader(getContext(), mQuery);
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Response> loader, Response data) {
        int id = loader.getId();
        if (id == R.id.image_loader) {
            if (data.getTypedAnswer() instanceof ImageResponse) {
                ImageResponse response = (ImageResponse) data.getTypedAnswer();
                presenter.setAdapter(response.getItems());

            }
        }
        getLoaderManager().destroyLoader(id);
        mQuery = null;
    }

    @Override
    public void onLoaderReset(Loader<Response> loader) {
        getActivity().getLoaderManager().restartLoader(R.id.image_loader, null, this);
    }

    @Override
    public void findImage() {
        getActivity().getLoaderManager().initLoader(R.id.image_loader, null, this);
    }

    @Override
    public void checkContent() {
        presenter.checkSearchRequest(mQuery);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG);
    }

    @Override
    public void showFullScreenImage() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find_image:
                checkContent();
                break;

        }
    }
}