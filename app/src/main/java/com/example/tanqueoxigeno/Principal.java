package com.example.tanqueoxigeno;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.tanqueoxigeno.adapter.ListaBotellaAdapter;
import com.example.tanqueoxigeno.botella.Botella;
import com.example.tanqueoxigeno.form.Api;
import com.example.tanqueoxigeno.form.Globalvar;

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
    private ListaBotellaAdapter listaBotellaAdapter;
    private ListaBotellaAdapter.RecyclerViewClickListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        recyclerview= findViewById(R.id.recyclerview);
        listaBotellaAdapter=new ListaBotellaAdapter(this, listener);
        recyclerview.setAdapter(listaBotellaAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);


        Log.e("SAQUENNNNNME","------------------------>");

        Bundle parametros=this.getIntent().getExtras();
        if(parametros!=null){
            Log.e("llegoID","------->"+parametros.getInt("cliente_id"));
            try {
                obtenerDatosBotella(parametros.getInt("cliente_id"));
            }catch(Exception e){


            }
        }
    }

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


//                    ArrayList<Botella> listaBotella=botellaRes.getResults();
                    listaBotellaAdapter.adicionarListaBotella(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Botella>> call, Throwable t) {

            }
        });
    }
}