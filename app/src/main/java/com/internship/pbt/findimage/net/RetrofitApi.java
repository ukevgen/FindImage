package com.internship.pbt.findimage.net;

import android.support.annotation.NonNull;
import android.util.Log;

import com.internship.pbt.findimage.net.services.SearchService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by user on 01.03.2017.
 */

public class RetrofitApi {

    private static final int TIMEOUT = 60;
    private static final int WRITE_TIMEOUT = 120;
    private static final int CONNECT_TIMEOUT = 10;

    private volatile static RetrofitApi INSTANCE;
    private static OkHttpClient sClient;
    private SearchService serchService;


    public static RetrofitApi getRetrofitApi() {
        if (INSTANCE == null) {
            synchronized (RetrofitApi.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RetrofitApi();
                }
            }
        }
        return INSTANCE;
    }


    private RetrofitApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.API_END_POINT)
                .client(getClient())
                .addConverterFactory(new QualifiedTypeConverterFactory(
                        GsonConverterFactory.create(),
                        SimpleXmlConverterFactory.create()
                ))
                .build();

        buildServices(retrofit);

    }

    @NonNull
    private void buildServices(Retrofit retrofit) {
        //TODO create this class
        serchService = retrofit.create(SearchService.class);
    }

    public SearchService getSerchService() {
        return serchService;
    }

    @NonNull
    private static OkHttpClient getClient() {
        OkHttpClient client = sClient;
        if (client == null) {
            synchronized (RetrofitApi.class) {
                client = sClient;
                if (client == null) {
                    client = sClient = buildClient();
                }
            }
        }
        return client;
    }


    @NonNull
    private static OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor())
                .build();
    }

}
