package com.internship.pbt.findimage.presentation.presenter.results;

import com.internship.pbt.findimage.adapter.ImageAdapter;
import com.internship.pbt.findimage.net.content.ImageResponse;
import com.internship.pbt.findimage.net.content.Item;
import com.internship.pbt.findimage.net.response.RequestResult;
import com.internship.pbt.findimage.view.fragment.results.ResultsView;

import java.util.List;

/**
 * Created by user on 01.03.2017.
 */

public class ResultsPresenterImp implements ResultsPresenter, ImageAdapter.OnImageClickCallback {

    private ResultsView resultsView;
    private ImageResponse imageResponse;
    private ImageAdapter adapter;


    public ResultsPresenterImp(ResultsView resultsView) {
        this.resultsView = resultsView;
        adapter = new ImageAdapter();
    }

    public void setImageResponse(ImageResponse imageResponse) {
        this.imageResponse = imageResponse;
    }

    public void setAdapter(List<Item> items) {
        adapter.setItems(items);
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
    public void onImageClick(int position) {
        //TODO save photo to casche
    }
}
