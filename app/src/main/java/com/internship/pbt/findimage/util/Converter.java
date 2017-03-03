package com.internship.pbt.findimage.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by user on 03.03.2017.
 */

public class Converter {

    private Context context;

    public Converter(Context context) {
        this.context = context;
    }

    public String encodeImageTobase64(Bitmap bitmap) {
        String imageEncoded = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }

    public Bitmap decodeBase64(String image) {
        byte[] decodedByte = new byte[0];
        if (image != null)
            decodedByte = Base64.decode(image, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);

    }
}
