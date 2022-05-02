package com.example.tanqueoxigeno.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tanqueoxigeno.R;
import com.example.tanqueoxigeno.botella.Botella;

import java.util.ArrayList;

public class ListaBotellaAdapter extends RecyclerView.Adapter<ListaBotellaAdapter.ViewHolder>{

    private ArrayList<Botella> dataset;

    public ListaBotellaAdapter() {
        dataset=new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_botella,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Botella b=dataset.get(position);
        holder.botella_id.setText(b.getBotella_id());
        holder.codigo.setText(b.getCodigo());
        holder.valorManometro.setText(b.getValorManometro());
        holder.valorRecarga.setText(b.getValorRecarga());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private TextView botella_id;
        private TextView codigo;
        private TextView valorManometro;
        private TextView valorRecarga;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            botella_id=itemView.findViewById(R.id.botella_id);
            codigo=itemView.findViewById(R.id.codigo);
            valorManometro=itemView.findViewById(R.id.valorManometro);
            valorRecarga=itemView.findViewById(R.id.valorRecarga);

        }
    }
}
