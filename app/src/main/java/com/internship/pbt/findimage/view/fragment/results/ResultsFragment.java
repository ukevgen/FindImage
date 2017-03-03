package com.internship.pbt.findimage.view.fragment.results;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
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
import com.internship.pbt.findimage.loader.ImageLoader;
import com.internship.pbt.findimage.net.content.ImageResponse;
import com.internship.pbt.findimage.net.response.Response;
import com.internship.pbt.findimage.presentation.presenter.results.ResultsPresenterImp;
import com.internship.pbt.findimage.view.fragment.ImageFragment;

/**
 * Created by user on 01.03.2017.
 */

public class ResultsFragment extends Fragment implements ResultsView,
        LoaderManager.LoaderCallbacks<Response>, View.OnClickListener {

    private static final String IMAGE_FR_TAG = "IMAGE_FR_TAG";
    private ResultsPresenterImp presenter;
    private String mQuery;
    private Button btFind;
    private ProgressBar mProgressBar;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private ImageResponse response;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        presenter = new ResultsPresenterImp(this, CachePhotos.getInstance(getContext()));
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
                if (newText.length() > 3)
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_favorites)
            presenter.addImageToFavorites();
        return super.onOptionsItemSelected(item);
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
                response = (ImageResponse) data.getTypedAnswer();
                if (response.getItems() != null)
                    presenter.setItems(response.getItems());
                recyclerView.setAdapter(presenter.getAdapter());
                btFind.setEnabled(false);
            }
        }
        mProgressBar.setVisibility(View.INVISIBLE);
//        getLoaderManager().destroyLoader(id);
        mQuery = null;
    }

    @Override
    public void onLoaderReset(Loader<Response> loader) {
        getActivity().getLoaderManager().restartLoader(R.id.image_loader, null, this);
    }

    @Override
    public void findImage() {
        getActivity().getLoaderManager().initLoader(R.id.image_loader, null, this);
        mProgressBar.setVisibility(View.VISIBLE);
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
       /* Intent intent = new Intent(getActivity(), FullScreenActivity.class);
        startActivity(intent);*/
        ImageFragment fragment = new ImageFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_screen_container, fragment,
                        IMAGE_FR_TAG)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public Context geCurrentContext() {
        return this.getContext();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find_image:
                checkContent();
                break;

        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}