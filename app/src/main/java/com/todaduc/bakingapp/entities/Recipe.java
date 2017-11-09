package com.todaduc.bakingapp.entities;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class Recipe implements Parcelable{

    private int id;
    private String name;
    private int serving;
    private String image;
    private List<Ingredient> ingredientList;
    private List<BakingStep> backingSteps;

    public Recipe(int id, String name, int serving, String image, List<Ingredient> ingredientList, List<BakingStep> backingSteps) {
        this.id = id;
        this.name = name;
        this.serving = serving;
        this.image = image;
        this.ingredientList = ingredientList;
        this.backingSteps = backingSteps;
    }

    public Recipe(){}

    protected Recipe(Parcel in) {
        id = in.readInt();
        name = in.readString();
        serving = in.readInt();
        image = in.readString();
        ingredientList = in.createTypedArrayList(Ingredient.CREATOR);
        backingSteps = in.createTypedArrayList(BakingStep.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(serving);
        dest.writeString(image);
        dest.writeTypedList(ingredientList);
        dest.writeTypedList(backingSteps);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getServing() {
        return serving;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public List<BakingStep> getBackingSteps() {
        return backingSteps;
    }

    public String getImage() {
        return image;
    }


}
