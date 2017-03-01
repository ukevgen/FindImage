package com.internship.pbt.findimage.net.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by user on 01.03.2017.
 */

public interface SearchService {

    // TODO  implement my request

    @GET("/v1?")
    Call<List<Object>> findImage(@Query("q") String q,
                                 @Query("num") String count,
                                 @Query("cx") String cx,
                                 @Query("key") String api_key,
                                 @Query("alt") String alt);

}
