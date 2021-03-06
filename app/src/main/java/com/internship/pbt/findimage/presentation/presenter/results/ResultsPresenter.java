package com.internship.pbt.findimage.presentation.presenter.results;

/**
 * Created by user on 01.03.2017.
 */

public interface ResultsPresenter {
    void onFindImage();

    void checkSearchRequest(String query);

    void addImageToFavorites();

    void saveResultsToDb();

    void onDestroy();
}
