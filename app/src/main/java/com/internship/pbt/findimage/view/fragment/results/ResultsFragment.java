package com.internship.pbt.findimage.view.fragment.results;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.internship.pbt.findimage.R;
import com.internship.pbt.findimage.cache.CachePhotos;
import com.internship.pbt.findimage.cache.CacheSharedPreferences;
import com.internship.pbt.findimage.loader.ImageLoader;
import com.internship.pbt.findimage.net.content.ImageResponse;
import com.internship.pbt.findimage.net.response.Response;
import com.internship.pbt.findimage.presentation.presenter.results.ResultsPresenterImp;
import com.internship.pbt.findimage.view.fragment.FullScreenImageFragment;

/**
 * Created by user on 01.03.2017.
 */

public class ResultsFragment extends Fragment implements ResultsView,
        LoaderManager.LoaderCallbacks<Response>, View.OnClickListener {

    private static final String CHECK_CONNECTION = "Check your network connection";
    private ResultsPresenterImp presenter;
    private Button btFind;
    private ProgressBar mProgressBar;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private ImageResponse response;
    private SearchView mSearch;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        presenter = new ResultsPresenterImp(this,
                CachePhotos.getInstance(getContext()),
                CacheSharedPreferences.getInstance(getContext()));

        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        btFind = (Button) view.findViewById(R.id.find_image);
        recyclerView = (RecyclerView) view.findViewById(R.id.image_container);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        btFind.setOnClickListener(this);
        setHasOptionsMenu(true);
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
        mSearch = (SearchView) MenuItemCompat.getActionView(searchViewItem);

        mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                checkContent();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 1) {
                    btFind.setBackgroundColor(getResources().getColor(R.color.cardSignBackground));
                    btFind.setEnabled(true);
                } else {
                    btFind.setBackgroundColor(getResources().getColor(R.color.separator));
                    btFind.setEnabled(false);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_favorites)
            presenter.addImageToFavorites();
        if (item.getItemId() == R.id.save_to_database)
            presenter.saveResultsToDb();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Response> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case R.id.image_loader:
                return new ImageLoader(getContext(), String.valueOf(mSearch.getQuery()));
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Response> loader, Response data) {
        int id = loader.getId();
        if (id == R.id.image_loader) {
            if (data.getTypedAnswer() instanceof ImageResponse) {
                response = (ImageResponse) data.getTypedAnswer();
                if (response.getItems() != null) {
                    presenter.setItems(response.getItems());
                    recyclerView.setAdapter(presenter.getAdapter());
                } else {
                    Toast.makeText(getContext(), getString(R.string.incorrectly_request),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
        mProgressBar.setVisibility(View.INVISIBLE);
        getActivity().getLoaderManager().destroyLoader(id);
    }

    @Override
    public void onLoaderReset(Loader<Response> loader) {
    }

    @Override
    public void findImage() {
        getActivity().getLoaderManager().initLoader(R.id.image_loader, null, this);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    public void checkContent() {
        presenter.checkSearchRequest(String.valueOf(mSearch.getQuery()));
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFullScreenImage() {
        FullScreenImageFragment fragment = new FullScreenImageFragment();
        fragment.show(getFragmentManager(), "Image");
    }

    @Override
    public Context geCurrentContext() {
        return this.getContext();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find_image:
                if (isNetworkConnected()) {
                    checkContent();
                } else {
                    showToast(CHECK_CONNECTION);
                }
                break;

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}