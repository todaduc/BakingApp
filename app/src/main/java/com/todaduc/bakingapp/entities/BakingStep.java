package com.todaduc.bakingapp.entities;

/**
 * Created by ddjankou on 8/24/2017.
 */

public class BakingStep {

    private int id;
    private String shortDescription;
    private String description;
    private String videoUrl;
    private String thumbnailURL;

    public BakingStep(int id, String shortDescription, String description, String videoUrl, String thumbnailURL) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoUrl = videoUrl;
        this.thumbnailURL = thumbnailURL;
    }

    public int getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }
}
