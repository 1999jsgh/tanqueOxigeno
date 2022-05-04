package com.example.tanqueoxigeno.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tanqueoxigeno.R;
import com.example.tanqueoxigeno.botella.Botella;
import com.example.tanqueoxigeno.botella.BotellaDetalles;

import java.util.ArrayList;

public class ListaBotellaAdapter extends RecyclerView.Adapter<ListaBotellaAdapter.ViewHolder>{

    private ArrayList<Botella> dataset;
    private Context context;
    private RecyclerViewClickListener listener;
    private Botella b;


    public ListaBotellaAdapter(Context context, RecyclerViewClickListener listener) {
        this.listener=listener;
        this.context=context;
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
         b=dataset.get(position);

        holder.botella_id.setText(String.valueOf(b.getBotella_id()));
        holder.codigo.setText(b.getCodigo());
        holder.valorManometro.setText(b.getValorManometro());
        holder.valorRecarga.setText(b.getValorRecarga());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaBotella(ArrayList<Botella> listaBotella) {

            Log.e("lista","---->"+listaBotella);

        dataset.addAll(listaBotella);
        notifyDataSetChanged();
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
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
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            Intent intent = new Intent(context, BotellaDetalles.class);
            intent.putExtra("botella_id", dataset.get(position).getBotella_id());
            intent.putExtra("tamano_id", dataset.get(position).getTamano_id());
            intent.putExtra("tipoTamano", dataset.get(position).getTipoTamano());
            intent.putExtra("estado_id", dataset.get(position).getEstado_id());
            intent.putExtra("tipoEstado", dataset.get(position).getTipoEstado());
            intent.putExtra("codigo", dataset.get(position).getCodigo());
            intent.putExtra("valorManometro", dataset.get(position).getValorManometro());
            intent.putExtra("valorRecarga", dataset.get(position).getValorRecarga());
            intent.putExtra("fechaRecarga", dataset.get(position).getFechaRecarga());
            intent.putExtra("fechaVencimiento", dataset.get(position).getFechaVencimiento());

            Log.e("ID","------------->"+dataset.get(position).getBotella_id());

            context.startActivity(intent);

        }
    }
}
