package com.example.camilov.myapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Camilo V on 4/06/2017.
 */

public class Restaurante {

    @SerializedName("restaurante")
    @Expose
    private String restaurante;

    public String getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }


}