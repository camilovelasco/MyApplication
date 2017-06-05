package com.example.camilov.myapplication.finalapi;

import com.example.camilov.myapplication.models.Restaurante;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Camilo V on 4/06/2017.
 */



public interface ApiService {
    @GET("jkwf-2qh6.json")
    Call<ArrayList<Restaurante>> obtenerListaRestaurante();
}
