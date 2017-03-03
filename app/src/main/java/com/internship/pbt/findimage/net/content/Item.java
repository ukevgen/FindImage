
package com.internship.pbt.findimage.net.content;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;

@Entity(nameInDb = "Images")
public class Item implements Serializable {

    @Property(nameInDb = "image_kind")
    @SerializedName("kind")
    @Expose
    private String kind;

    @Property(nameInDb = "image_title")
    @SerializedName("title")
    @Expose
    private String title;

    @Property(nameInDb = "image_htmlTitle")
    @SerializedName("htmlTitle")
    @Expose
    private String htmlTitle;

    @Property(nameInDb = "image_link")
    @SerializedName("link")
    @Expose
    private String link;

    @Property(nameInDb = "image_displayLink")
    @SerializedName("displayLink")
    @Expose
    private String displayLink;

    @Property(nameInDb = "image_snippet")
    @SerializedName("snippet")
    @Expose
    private String snippet;

    @Property(nameInDb = "image_htmlSnippet")
    @SerializedName("htmlSnippet")
    @Expose
    private String htmlSnippet;

    @Property(nameInDb = "image_mime")
    @SerializedName("mime")
    @Expose
    private String mime;

    @Property(nameInDb = "image_image")
    @SerializedName("image")
    @Expose
    private Image image;

    @Property(nameInDb = "image_fileFormat")
    @SerializedName("fileFormat")
    @Expose
    private String fileFormat;

    @Generated()
    public Item(String kind, String title, String htmlTitle, String link, String displayLink,
                String snippet, String htmlSnippet, String mime, Image image, String fileFormat) {
        this.kind = kind;
        this.title = title;
        this.htmlTitle = htmlTitle;
        this.link = link;
        this.displayLink = displayLink;
        this.snippet = snippet;
        this.htmlSnippet = htmlSnippet;
        this.mime = mime;
        this.image = image;
        this.fileFormat = fileFormat;
    }

    @Generated()
    public Item() {
    }

    private boolean checked = false;
    private final static long serialVersionUID = -47778820414660594L;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHtmlTitle() {
        return htmlTitle;
    }

    public void setHtmlTitle(String htmlTitle) {
        this.htmlTitle = htmlTitle;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDisplayLink() {
        return displayLink;
    }

    public void setDisplayLink(String displayLink) {
        this.displayLink = displayLink;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getHtmlSnippet() {
        return htmlSnippet;
    }

    public void setHtmlSnippet(String htmlSnippet) {
        this.htmlSnippet = htmlSnippet;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
