package com.example.tanqueoxigeno.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tanqueoxigeno.R;
import com.example.tanqueoxigeno.botella.Botella;
import com.example.tanqueoxigeno.venta.Venta;

import java.util.ArrayList;

public class ListaVentaAdapter extends RecyclerView.Adapter<ListaVentaAdapter.ViewHolder>{

    private ArrayList<Venta> dataset;
    private Venta v;
    private Context context;
    private ListaBotellaAdapter.RecyclerViewClickListener listener;

    public ListaVentaAdapter() {
        dataset=new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_venta,parent,false);
        return new ListaVentaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        v=dataset.get(position);
        holder.venta_id.setText(String.valueOf(v.getVenta_id()));
        holder.botella_id.setText(String.valueOf(v.getBotella_id()));
        holder.monto.setText(v.getMonto());
        holder.fecha.setText(v.getFecha());

        Log.e("FECHA",v.getFecha());

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaVenta(ArrayList<Venta> listaVentaFecha) {
        dataset.addAll(listaVentaFecha);
        notifyDataSetChanged();
    }

    public interface RecyclerViewClickListener {
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView venta_id;
        private TextView botella_id;
        private TextView monto;
        private TextView fecha;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        venta_id=itemView.findViewById(R.id.venta_id);
        botella_id=itemView.findViewById(R.id.botella_id);
        monto=itemView.findViewById(R.id.monto);
        fecha=itemView.findViewById(R.id.fecha);
    }
}
}
