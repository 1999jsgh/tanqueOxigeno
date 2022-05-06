package com.example.tanqueoxigeno;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tanqueoxigeno.adapter.ListaBotellaAdapter;
import com.example.tanqueoxigeno.adapter.ListaVentaAdapter;
import com.example.tanqueoxigeno.botella.Botella;
import com.example.tanqueoxigeno.form.Api;
import com.example.tanqueoxigeno.form.Globalvar;
import com.example.tanqueoxigeno.venta.Venta;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FechaVenta extends AppCompatActivity {
    private Button btnConsultaVenta;
    private EditText fechaAntes;
    private EditText fechaDespues;

    private RecyclerView recyclerviewVenta;
    private ListaVentaAdapter listaVentaAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fecha_venta);

        btnConsultaVenta=findViewById(R.id.btnConsultaVenta);
        recyclerviewVenta= findViewById(R.id.recyclerviewVenta);
        fechaAntes = findViewById(R.id.fechaDespues);
        fechaDespues = findViewById(R.id.fechaDespues);

        listaVentaAdapter= new ListaVentaAdapter();
        recyclerviewVenta.setAdapter(listaVentaAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerviewVenta.setLayoutManager(layoutManager);


        btnConsultaVenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerDatosVentaFecha(fechaAntes.getText().toString(),fechaDespues.getText().toString());;
            }
        });

    }




    private void obtenerDatosVentaFecha(String fechaAntes, String fechaDespues) {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://orangecodecol.com/Botellasgas/venta/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONObject jsonObject= new JSONObject();
        try {
            Log.e("fechaAntes", fechaAntes);
            Log.e("fechaDespues", fechaDespues);
            jsonObject.put("fechaAntes", fechaAntes);
            jsonObject.put("fechaDespues", fechaDespues);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("JSONObjectFEcha", String.valueOf(jsonObject));
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Api myCall= retrofit.create(Api.class);
        Call<ArrayList<Venta>> call =myCall.datosVentaFecha(body);

        call.enqueue(new Callback<ArrayList<Venta>>() {
            @Override
            public void onResponse(Call<ArrayList<Venta>> call, Response<ArrayList<Venta>> response) {
                Log.e("RESPONSE", String.valueOf(response.body())+"   "+response.message());
                if(response.isSuccessful()){
                    Globalvar.venta=response.body();
                    Log.e("BODYFechaVenta", String.valueOf(Globalvar.venta));
                    listaVentaAdapter.adicionarListaVenta(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Venta>> call, Throwable t) {

            }
        });
    }
}