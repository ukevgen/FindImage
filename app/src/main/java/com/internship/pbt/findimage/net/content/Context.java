
package com.internship.pbt.findimage.net.content;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Context implements Serializable
{

    @SerializedName("title")
    @Expose
    private String title;
    private final static long serialVersionUID = -624726757615094298L;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
