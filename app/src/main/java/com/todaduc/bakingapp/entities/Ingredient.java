package com.todaduc.bakingapp.entities;

import android.os.Parcel;
import android.os.Parcelable;



public class Ingredient  implements Parcelable{

    private String quantity;
    private String measure;
    private String description;

    public Ingredient(String quantity, String measure, String description) {
        this.quantity = quantity;
        this.measure = measure;
        this.description = description;
    }

    protected Ingredient(Parcel in) {
        quantity = in.readString();
        measure = in.readString();
        description = in.readString();
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(quantity);
        dest.writeString(measure);
        dest.writeString(description);
    }

    public String getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString(){
        return this.quantity.concat(" "+this.measure.concat(" of ").concat(this.description));
    }
}
