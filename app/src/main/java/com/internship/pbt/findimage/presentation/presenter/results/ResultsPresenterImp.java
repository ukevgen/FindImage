package com.internship.pbt.findimage.presentation.presenter.results;

import com.internship.pbt.findimage.view.fragment.results.ResultsFragment;
import com.internship.pbt.findimage.view.fragment.results.ResultsView;

/**
 * Created by user on 01.03.2017.
 */

public class ResultsPresenterImp implements ResultsPresenter {

    private ResultsView resultsView;

    public ResultsPresenterImp(ResultsView resultsView) {
        this.resultsView = resultsView;
    }
}
