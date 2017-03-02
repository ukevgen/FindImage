package com.internship.pbt.findimage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.internship.pbt.findimage.R;
import com.internship.pbt.findimage.net.content.CseThumbnail;
import com.internship.pbt.findimage.net.content.ImageResponse;
import com.internship.pbt.findimage.net.content.Item;

import java.util.List;

/**
 * Created by user on 02.03.2017.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {

    private List<Item> items;
    private OnImageClickCallback onImageClickCallback;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false);

        return new ImageHolder(v, this);
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {

        holder.setPosition(position);
        // TODO src information about photo
        CseThumbnail cseThumbnail = items.get(position).getPagemap().getCseThumbnail().get(0);

        String describe = items.get(position).getSnippet();
        holder.mName.setText(describe);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnImageClickCallback {
        void onImageClick(int position);
    }


    public ImageAdapter setOnImageClickCallback(OnImageClickCallback onImageClickCallback) {
        this.onImageClickCallback = onImageClickCallback;
        return this;
    }

    public static class ImageHolder extends RecyclerView.ViewHolder {

        Item item;
        ImageAdapter adapter;
        ImageView mImageView;
        CheckBox mCheckBox;
        int position;
        TextView mName;


        public ImageHolder(View itemView, final ImageAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            mImageView = (ImageView) itemView.findViewById(R.id.current_image);
            mName = (TextView) itemView.findViewById(R.id.image_name);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.check_image);

            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.onImageClickCallback.onImageClick(position);
                }
            });
        }


        public ImageHolder setPosition(int position) {
            this.position = position;
            return this;
        }

        public void setItem(Item item) {
            this.item = item;
        }
    }
}
