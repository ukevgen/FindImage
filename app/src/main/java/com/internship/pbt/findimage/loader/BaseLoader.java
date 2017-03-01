package com.internship.pbt.findimage.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;

import com.internship.pbt.findimage.net.response.RequestResult;
import com.internship.pbt.findimage.net.response.Response;

import java.io.IOException;

/**
 * Created by user on 01.03.2017.
 */

public abstract class BaseLoader extends AsyncTaskLoader<Response> {

    public BaseLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public Response loadInBackground() {
        try {
            Response response = apiCall();
            if (response.getRequestResult() == RequestResult.SUCCESS) {
                response.save(getContext());
                onSuccess();
            } else {
                onError();
            }
            return response;
        } catch (IOException e) {
            onError();
            return new Response();
        }
    }

    protected void onSuccess() {
    }

    protected void onError() {
    }

    protected abstract Response apiCall() throws IOException;
}
