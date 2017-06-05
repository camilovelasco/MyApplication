package com.example.camilov.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.camilov.myapplication.finalapi.ApiService;
import com.example.camilov.myapplication.models.Restaurante;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class    MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private static final String TAG = "RESTAURANTE";
    private RecyclerView recyclerView;
    private boolean aptoParaCargar;
    private adaptadorRestaurante adaptadorRestaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adaptadorRestaurante = new adaptadorRestaurante(this);
        recyclerView.setAdapter(adaptadorRestaurante);

        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (aptoParaCargar) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, " Final.");

                            aptoParaCargar = false;
                            obtenerLista();
                        }
                    }
                }
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.datos.gov.co/resource/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        aptoParaCargar = true;
        obtenerLista();

    }

    private void obtenerLista() {

        ApiService service = retrofit.create(ApiService.class);
        Call<ArrayList<Restaurante>> autoRespuestaCall = service.obtenerListaRestaurante();

        autoRespuestaCall.enqueue(new Callback<ArrayList<Restaurante>>() {

            @Override
            public void onResponse(Call<ArrayList<Restaurante>> call, Response<ArrayList<Restaurante>> response) {

                if (response.isSuccessful()) {
                    List lista = response.body();

                    for (int i = 0; i < lista.size(); i++)
                    {
                        Restaurante p = (Restaurante) lista.get(i);
                      //  Log.i(TAG, " nombre: " + p.setRestaurante());
                    }

                } else {
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Restaurante>> call, Throwable t) {
                Log.e(TAG," onFailure: "+t.getMessage());
            }
        });
    }
}
