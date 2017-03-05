package com.internship.pbt.findimage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.internship.pbt.findimage.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ukevgen on 03.03.2017.
 */

public class FavImageAdapter extends RecyclerView.Adapter<FavImageAdapter.VH> {

    private List<String> urls = new ArrayList<>();
    private Context context;


    public FavImageAdapter(Context context) {
        this.context = context;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorites, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.setPosition(position);
        String uri = urls.get(position);
        File f = new File(uri);

        Picasso.with(context)
                .load(f)
                .resize(200, 200)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return urls == null ? 0 : urls.size();
    }

    public static class VH extends RecyclerView.ViewHolder {

        int position;
        ImageView mImageView;

        public VH(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.fav_image);
        }

        public VH setPosition(int position) {
            this.position = position;
            return this;
        }


    }
}
