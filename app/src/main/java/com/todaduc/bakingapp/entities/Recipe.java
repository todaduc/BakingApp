package com.todaduc.bakingapp.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Themener on 8/22/17.
 */

public class Recipe implements Parcelable{

    private long id;
    private String name;


    public Recipe(long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Recipe(Parcel in){
        id = in.readLong();
        name = in.readString();
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
        parcel.writeLong(id);
        parcel.writeString(name);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
