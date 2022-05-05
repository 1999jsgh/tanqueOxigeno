package com.example.tanqueoxigeno;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.tanqueoxigeno.adapter.ListaBotellaAdapter;
import com.example.tanqueoxigeno.adapter.ListaVentaAdapter;

public class FechaVenta extends AppCompatActivity {
    private Button btnConsultaVenta;
    private RecyclerView recyclerviewVenta;
    private ListaVentaAdapter listaVentaAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fecha_venta);

        Button btnConsultaVenta=findViewById(R.id.btnConsultaVenta);
        recyclerviewVenta= findViewById(R.id.recyclerviewVenta);


    }
}