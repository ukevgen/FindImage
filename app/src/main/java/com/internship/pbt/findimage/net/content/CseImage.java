
package com.internship.pbt.findimage.net.content;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CseImage implements Serializable
{

    @SerializedName("src")
    @Expose
    private String src;
    private final static long serialVersionUID = -7505883436521677335L;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

}
