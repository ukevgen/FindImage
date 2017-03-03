package com.internship.pbt.findimage.cache;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ukevgen on 03.03.2017.
 */

public class CacheSharedPreferences {

    private static final String CURRENT_IMAGE_STRING = "CURRENT_IMAGE_STRING";
    private static volatile CacheSharedPreferences INSTANCE;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String FILE_NAME = "CURRENT_IMAGE";

    private CacheSharedPreferences(Context context) {
        mSharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public static CacheSharedPreferences getInstance(Context context) {
        CacheSharedPreferences local = INSTANCE;
        if (local == null) {
            synchronized (CacheSharedPreferences.class) {
                local = INSTANCE;
                if (local == null) {
                    INSTANCE = local = new CacheSharedPreferences(context);
                }
            }
        }
        return local;
    }

    public void putStringImage(String s) {
        mEditor.putString(CURRENT_IMAGE_STRING, s);
        mEditor.apply();
    }

    public String getStringImage() {
        return mSharedPreferences.getString(CURRENT_IMAGE_STRING, null);
    }

    public void deleteAllCache() {
        mEditor.clear();
        mEditor.apply();
    }
}
