package com.internship.pbt.findimage.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.internship.pbt.findimage.R;
import com.internship.pbt.findimage.net.imgcontent.Item;
import com.squareup.picasso.Picasso;

import java.util.HashSet;
import java.util.List;

/**
 * Created by user on 02.03.2017.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {

    private List<Item> items;
    private OnImageClickCallback onImageClickCallback;
    private Bitmap currentImage;
    private Context context;
    private HashSet<Bitmap> bitmaps = new HashSet<>();


    public HashSet<Bitmap> getBitmaps() {
        return bitmaps;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public ImageAdapter(List<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false);

        return new ImageHolder(v, this);
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {
        final Item item = items.get(position);
        holder.setPosition(position);
        // TODO src information about photo
        //if (items.get(position).getPagemap() != null)
        //    cseThumbnail = items.get(position).getPagemap().getCseThumbnail().get(0);
        Picasso.with(context)
                .load(item.getLink())
                .error(R.drawable.default_image)
                .resize(50, 50)
                .into(holder.mImageView);

        BitmapDrawable drawable = (BitmapDrawable) holder.mImageView.getDrawable();
        final Bitmap currentImage = drawable.getBitmap();

        String describe = item.getTitle();
        holder.mName.setText(describe);

        holder.mCheckBox.setOnCheckedChangeListener(null);
        holder.mCheckBox.setChecked(item.isChecked());
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setChecked(isChecked);
                if (item.isChecked())
                    bitmaps.add(currentImage);
                else
                    bitmaps.remove(currentImage);
            }
        });

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
