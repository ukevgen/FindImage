package com.internship.pbt.findimage.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by user on 03.03.2017.
 */

public class Converter {

    private Context context;

    public Converter(Context context) {
        this.context = context;
    }

    public String encodeAvatarTobase64(File file) {
        Bitmap bitmap = null;
        String imageEncoded = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.fromFile(file));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageEncoded;
    }

    public Bitmap decodeBase64(String image) {
        byte[] decodedByte = new byte[0];
        if (image != null)
            decodedByte = Base64.decode(image, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);

    }
}
