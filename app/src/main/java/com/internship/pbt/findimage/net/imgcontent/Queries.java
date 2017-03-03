
package com.internship.pbt.findimage.net.imgcontent;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.internship.pbt.findimage.net.content.Request;

public class Queries implements Serializable
{

    @SerializedName("request")
    @Expose
    private List<Request> request = null;
    private final static long serialVersionUID = 8839282448483404429L;

    public List<Request> getRequest() {
        return request;
    }

    public void setRequest(List<Request> request) {
        this.request = request;
    }

}
