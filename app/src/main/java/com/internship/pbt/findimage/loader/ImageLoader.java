package com.internship.pbt.findimage.loader;

import android.content.Context;
import android.database.Cursor;

import com.internship.pbt.findimage.net.RetrofitApi;
import com.internship.pbt.findimage.net.response.Response;
import com.internship.pbt.findimage.net.services.SearchService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

/**
 * Created by user on 01.03.2017.
 */

public class ImageLoader extends BaseLoader {
    private String query;

    public ImageLoader(Context context, String query) {
        super(context);
        this.query = query;

    }

    @Override
    protected Response apiCall() throws IOException {
        // TODO implement request like example

        SearchService service = RetrofitApi.getRetrofitApi().getSearchService();
        Call<List<Object>> call = service.airports("as");
        /*AirportsService service = ApiFactory.getAirportsService();
        Call<List<Airport>> call = service.airports(mGps);
        List<Airport> airports = call.execute().body();
        return new AirportsResponse()
                .setRequestResult(RequestResult.SUCCESS)
                .setAnswer(airports);*/


        return null;
    }
}

