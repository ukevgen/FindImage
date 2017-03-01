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

    @GET("/places/coords_to_places_ru.json")
    Call<List<Object>> airports(@Query("coords") String gps);

}
