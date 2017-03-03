package com.internship.pbt.findimage.view.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.internship.pbt.findimage.R;
import com.internship.pbt.findimage.cache.CacheSharedPreferences;
import com.internship.pbt.findimage.presentation.presenter.fullscreen.FullScreenPresenterImp;
import com.internship.pbt.findimage.util.Converter;

/**
 * Created by user on 01.03.2017.
 */

public class FullScreenImageFragment extends DialogFragment implements FullScreenView {

    private ImageView mImageView;
    private FullScreenPresenterImp presenter;
    private DisplayMetrics displayMetrics;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);

        mImageView = (ImageView) view.findViewById(R.id.full_screen_image);

        presenter = new FullScreenPresenterImp(this,
                CacheSharedPreferences.getInstance(getActivity()),
                new Converter(getActivity()));

        displayMetrics = getActivity().getResources().getDisplayMetrics();

        return view;
    }


    @Override
    public void showImage(Bitmap bitmap) {
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
        mImageView.setImageBitmap(bitmap);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getImage();
    }
}
