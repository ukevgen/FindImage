package com.internship.pbt.findimage.view.fragment.results;

import android.content.Context;

/**
 * Created by user on 01.03.2017.
 */

public interface ResultsView {
    void findImage();

    void checkContent();

    void showToast(String msg);

    void showFullScreenImage();

    Context geCurrentContext();
}
