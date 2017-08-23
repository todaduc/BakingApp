package com.todaduc.bakingapp.Entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Themener on 8/22/17.
 */

public class Recipe implements Parcelable{

    private Recipe(Parcel in){

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
    }
}
