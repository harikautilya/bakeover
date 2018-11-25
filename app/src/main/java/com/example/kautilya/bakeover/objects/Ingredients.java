package com.example.kautilya.bakeover.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ingredients {


    @Expose
    @SerializedName("ingredient")
    private String ingredient;
    @Expose
    @SerializedName("measure")
    private String measure;
    @Expose
    @SerializedName("quantity")
    private float quantity;

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}
