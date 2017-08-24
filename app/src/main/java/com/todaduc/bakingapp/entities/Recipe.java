package com.todaduc.bakingapp.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Themener on 8/22/17.
 */

public class Recipe implements Parcelable{

    private int id;
    private String name;
    private int serving;
    private String image;

    public Recipe(int id, String name, int serving, String image) {
        this.id = id;
        this.name = name;
        this.serving = serving;
        this.image = image;
    }

    private Recipe(Parcel in){
        id = in.readInt();
        name = in.readString();
        serving = in.readInt();
        image = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(serving);
        parcel.writeString(image);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getServing() {
        return serving;
    }

    public String getImage() {
        return image;
    }
}
