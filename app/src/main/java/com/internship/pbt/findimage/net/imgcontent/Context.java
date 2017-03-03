
package com.internship.pbt.findimage.net.imgcontent;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Context implements Serializable
{

    @SerializedName("title")
    @Expose
    private String title;
    private final static long serialVersionUID = 6110195063576262580L;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
