package com.internship.pbt.findimage.loader;

import android.content.Context;

import com.internship.pbt.findimage.net.ApiConstants;
import com.internship.pbt.findimage.net.RetrofitApi;
import com.internship.pbt.findimage.net.content.ImageResponse;
import com.internship.pbt.findimage.net.response.RequestResult;
import com.internship.pbt.findimage.net.response.Response;
import com.internship.pbt.findimage.net.services.SearchService;

import java.io.IOException;

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

        SearchService service = RetrofitApi.getSerchService();
        Call<ImageResponse> call = service.findImage(query,
                ApiConstants.COUNT,
                ApiConstants.CX,
                ApiConstants.API_KEY);

        ImageResponse imageResponse = call.execute().body();

        return new Response()
                .setRequestResult(RequestResult.SUCCESS)
                .setAnswer(imageResponse);
    }
}

