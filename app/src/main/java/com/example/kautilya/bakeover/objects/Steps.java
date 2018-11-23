package com.example.kautilya.bakeover.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class Steps {

    @Expose
    @SerializedName("thumbnailURL")
    private String thumbnailurl;
    @Expose
    @SerializedName("videoURL")
    private String videourl;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("shortDescription")
    private String shortdescription;
    @Expose
    @SerializedName("id")
    private int id;

    public String getThumbnailurl() {
        return thumbnailurl;
    }

    public void setThumbnailurl(String thumbnailurl) {
        this.thumbnailurl = thumbnailurl;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
