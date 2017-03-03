
package com.internship.pbt.findimage.net.content;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Queries implements Serializable
{

    @SerializedName("request")
    @Expose
    private List<Request> request = null;
    private final static long serialVersionUID = -6625725694075398509L;

    public List<Request> getRequest() {
        return request;
    }

    public void setRequest(List<Request> request) {
        this.request = request;
    }

}
