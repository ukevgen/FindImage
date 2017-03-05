package com.internship.pbt.findimage.net.services;


import com.internship.pbt.findimage.net.content.ImageResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by user on 01.03.2017.
 */

public interface SearchService {


    @GET("/customsearch/v1")
    Call<ImageResponse> findImage(@Query("q") String q,
                                  @Query("num") String count,
                                  @Query("cx") String cx,
                                  @Query("key") String api_key,
                                  @Query("searchType") String searchType);

}
