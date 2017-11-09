package com.todaduc.bakingapp.entities;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * This class describes and sets the fields of a parcelable BakingStep Object.
 */
public class BakingStep implements Parcelable{

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

    protected BakingStep(Parcel in) {
        id = in.readInt();
        shortDescription = in.readString();
        description = in.readString();
        videoUrl = in.readString();
        thumbnailURL = in.readString();
    }

    public static final Creator<BakingStep> CREATOR = new Creator<BakingStep>() {
        @Override
        public BakingStep createFromParcel(Parcel in) {
            return new BakingStep(in);
        }

        @Override
        public BakingStep[] newArray(int size) {
            return new BakingStep[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(shortDescription);
        dest.writeString(description);
        dest.writeString(videoUrl);
        dest.writeString(thumbnailURL);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {

        if(!(obj instanceof  BakingStep))
            return false;

        BakingStep eltToCompare = (BakingStep)obj;
        if(this.id == eltToCompare.id && this.shortDescription.equals(eltToCompare.shortDescription)
            && this.description.equals(eltToCompare.description) && this.thumbnailURL.equals(eltToCompare.thumbnailURL)
            && this.videoUrl.equals(eltToCompare.videoUrl)
        ){
            return true;
        }
        return false;
    }
}
