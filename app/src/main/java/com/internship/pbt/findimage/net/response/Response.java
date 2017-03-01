package com.internship.pbt.findimage.net.response;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by user on 01.03.2017.
 */

public class Response {

    @Nullable
    private Object mAnswer;

    private RequestResult mRequestResult;

    public Response() {
        mRequestResult = RequestResult.ERROR;
    }

    @NonNull
    public RequestResult getRequestResult() {
        return mRequestResult;
    }

    public Response setRequestResult(@NonNull RequestResult requestResult) {
        mRequestResult = requestResult;
        return this;
    }

    @Nullable
    public <T> T getTypedAnswer() {
        if (mAnswer == null) {
            return null;
        }
        //noinspection unchecked
        return (T) mAnswer;
    }

    @NonNull
    public Response setAnswer(@Nullable Object answer) {
        mAnswer = answer;
        return this;
    }

    public void save(@NonNull Context context) {
    }
}

