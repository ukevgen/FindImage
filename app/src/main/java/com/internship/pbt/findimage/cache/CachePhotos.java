package com.internship.pbt.findimage.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by user on 03.03.2017.
 */

public class CachePhotos {

    private static volatile CachePhotos INSTANCE;
    private String pathToPhotos;

    private CachePhotos(Context context) {
        /*pathToPhotos = context.getCacheDir().getAbsolutePath()
                + File.separator + "mysearch" + File.separator;
        new File(pathToPhotos).mkdirs();*/
        pathToPhotos = android.os.Environment.getExternalStorageDirectory() + File.separator
                + "mysearch" + File.separator;
        new File(pathToPhotos).mkdirs();
    }

    public static CachePhotos getInstance(Context context) {
        CachePhotos local = INSTANCE;
        if (local == null) {
            synchronized (CachePhotos.class) {
                local = INSTANCE;
                if (local == null) {
                    INSTANCE = local = new CachePhotos(context);
                }
            }
        }
        return local;
    }

    public ArrayList<String> getAllPhotosPath() {
        ArrayList<String> result = new ArrayList<>();
        File file = new File(pathToPhotos);
        File[] listFile;

        if (file.isDirectory()) {
            listFile = file.listFiles();
            for (int i = 0; i < listFile.length; i++) {
                result.add(listFile[i].getAbsolutePath());
            }
        }
        return result;
    }

    public void savePhoto(Bitmap bitmap) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inSampleSize = 8;
        String uuid = UUID.randomUUID().toString();
        //Bitmap result = BitmapFactory.decodeStream(responseBody.byteStream(), null, opt);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

        saveToFile(new ByteArrayInputStream(outputStream.toByteArray()), uuid);

    }

    private void saveToFile(InputStream inputStream, String uuid) {
        File fileToWrite = new File(pathToPhotos + uuid + ".jpg");
        byte[] buffer = new byte[4096];
        OutputStream outputStream = null;
        int byteRead;
        try {

            try {
                outputStream = new FileOutputStream(fileToWrite);
                while ((byteRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, byteRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (outputStream != null)
                outputStream.close();
            if (inputStream != null)
                inputStream.close();
        } catch (IOException e) {
            Log.d("TAG", e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
