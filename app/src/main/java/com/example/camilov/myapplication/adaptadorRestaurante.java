package com.example.camilov.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.camilov.myapplication.models.Restaurante;

import java.util.ArrayList;

/**
 * Created by Camilo V on 4/06/2017.
 */

public class adaptadorRestaurante extends RecyclerView.Adapter<adaptadorRestaurante.ViewHolder> {
    private ArrayList<Restaurante> dataset;

    private Context context;

    public adaptadorRestaurante(Context context) {
        this.context = context;
        dataset = new ArrayList<>();

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurante, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Restaurante a = dataset.get(position);
        holder.restaurante.setText(a.getRestaurante());


    }

    @Override
    public int getItemCount() {

        return dataset.size();
    }

    public void listaRestaurantes(ArrayList<Restaurante> listaRestaurante) {
        dataset.addAll(listaRestaurante);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView restaurante;

        public ViewHolder(View itemView) {

            super(itemView);


            restaurante = (TextView) itemView.findViewById(R.id.restaurante);
        }
    }
}
