
package com.internship.pbt.findimage.net.content;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Url implements Serializable
{

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("template")
    @Expose
    private String template;
    private final static long serialVersionUID = -1419089132818660631L;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

}
