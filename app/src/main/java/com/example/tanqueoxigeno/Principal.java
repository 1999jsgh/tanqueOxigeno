package com.example.tanqueoxigeno;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.tanqueoxigeno.adapter.ListaBotellaAdapter;
import com.example.tanqueoxigeno.botella.Botella;
import com.example.tanqueoxigeno.botella.BotellaDetalles;
import com.example.tanqueoxigeno.estado.Estado;
import com.example.tanqueoxigeno.form.Api;
import com.example.tanqueoxigeno.form.Globalvar;
import com.example.tanqueoxigeno.tamano.Tamano;

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

public class Principal extends AppCompatActivity {

    private RecyclerView recyclerview;
    private Button btnVencimiento;
    private Button btnBotella;
    private ListaBotellaAdapter listaBotellaAdapter;
    private ListaBotellaAdapter.RecyclerViewClickListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        recyclerview= findViewById(R.id.recyclerview);
        btnVencimiento=findViewById(R.id.button);
        btnBotella=findViewById(R.id.btnBotella);


        listaBotellaAdapter=new ListaBotellaAdapter(this, listener);
        recyclerview.setAdapter(listaBotellaAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);


        Bundle parametros=this.getIntent().getExtras();
        if(parametros!=null){
            try {
                obtenerDatosBotella(parametros.getInt("cliente_id"));

            }catch(Exception e){

            }
        }
        btnVencimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarVencimiento();
            }
        });

    btnBotella.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            datosEstadoTamano();

        }
    });



        setOnClickListiner();
    }

    private void datosEstadoTamano() {

        Intent intent = new Intent(this, RegistrarBotella.class);
        startActivity(intent);

    }

    private void registrarVencimiento() {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://orangecodecol.com/Botellasgas/botella/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



    }

    private void setOnClickListiner() {
        listener= new ListaBotellaAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent=new Intent(getApplicationContext(), BotellaDetalles.class);
                intent.putExtra("botella_id",listaBotellaAdapter.getItemCount());
                intent.putExtra("tamano_id",listaBotellaAdapter.getItemCount());
                intent.putExtra("tipoTamano",listaBotellaAdapter.getItemCount());
                intent.putExtra("estado_id",listaBotellaAdapter.getItemCount());
                intent.putExtra("tipoEstado",listaBotellaAdapter.getItemCount());
                intent.putExtra("codigo",listaBotellaAdapter.getItemCount());
                intent.putExtra("valorManometro",listaBotellaAdapter.getItemCount());
                intent.putExtra("valorRecarga",listaBotellaAdapter.getItemCount());
                intent.putExtra("fechaRecarga",listaBotellaAdapter.getItemCount());
                intent.putExtra("fechaVencimiento",listaBotellaAdapter.getItemCount());
            }
        };
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        Bundle parametros=this.getIntent().getExtras();
//        if(parametros!=null){
//            try {
//                obtenerDatosBotella(Globalvar.cliente_id);
//                Log.e("IdCliente","--------->"+Globalvar.cliente_id);
//
//            }catch(Exception e){
//
//            }
//        }
//    }

    private void obtenerDatosBotella(int cliente_id) {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://orangecodecol.com/Botellasgas/botella/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONObject jsonObject= new JSONObject();
        try {
            Log.e("CLIENTEID", String.valueOf(cliente_id));
            jsonObject.put("cliente_id", cliente_id);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Api myCall= retrofit.create(Api.class);
        Call<ArrayList<Botella>> call =myCall.getDatosBotella(body);

        call.enqueue(new Callback<ArrayList<Botella>>() {
            @Override
            public void onResponse(Call<ArrayList<Botella>> call, Response<ArrayList<Botella>> response) {
                if(response.isSuccessful()){
                    Globalvar.botella=response.body();
                    Log.e("Botella",response.body().toString());

                    listaBotellaAdapter.adicionarListaBotella(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Botella>> call, Throwable t) {

            }
        });
    }
}