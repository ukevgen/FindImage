
package com.internship.pbt.findimage.net.imgcontent;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.internship.pbt.findimage.net.content.*;

public class ImageResponse implements Serializable
{

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("url")
    @Expose
    private Url url;
    @SerializedName("queries")
    @Expose
    private Queries queries;
    @SerializedName("context")
    @Expose
    private com.internship.pbt.findimage.net.content.Context context;
    @SerializedName("searchInformation")
    @Expose
    private SearchInformation searchInformation;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    private final static long serialVersionUID = -3301954705980571766L;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

    public Queries getQueries() {
        return queries;
    }

    public void setQueries(Queries queries) {
        this.queries = queries;
    }

    public com.internship.pbt.findimage.net.content.Context getContext() {
        return context;
    }

    public void setContext(com.internship.pbt.findimage.net.content.Context context) {
        this.context = context;
    }

    public SearchInformation getSearchInformation() {
        return searchInformation;
    }

    public void setSearchInformation(SearchInformation searchInformation) {
        this.searchInformation = searchInformation;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
