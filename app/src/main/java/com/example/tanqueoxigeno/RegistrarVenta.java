package com.example.tanqueoxigeno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class RegistrarVenta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_venta);
        TextView botella_id=findViewById(R.id.botella_id);
        EditText monto=findViewById(R.id.monto);
        EditText fecha=findViewById(R.id.fecha);
        Button btnGuardarVenta = findViewById(R.id.btnGuardarVenta);

        Bundle extras=this.getIntent().getExtras();
        String botellaId= String.valueOf(extras.getString("botella_id"));
        botella_id.setText(botellaId);


        Bundle parametros=this.getIntent().getExtras();

        btnGuardarVenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(parametros!=null){
                    try {

                        obtenerDatosBotella(Integer.valueOf(botellaId),monto.getText().toString(),fecha.getText().toString());

                    }catch(Exception e){

                    }
                }
            }
        });

    }

    private void obtenerDatosBotella(Integer botella_id, String monto, String fecha) {

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://orangecodecol.com/Botellasgas/venta/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("botella_id", botella_id);
            jsonObject.put("monto", monto);
            jsonObject.put("fecha", fecha);

        }catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Api myCall= retrofit.create(Api.class);
        Call<ArrayList<Venta>> call =myCall.registarVenta(body);


      call.enqueue(new Callback<ArrayList<Venta>>() {
          @Override
          public void onResponse(Call<ArrayList<Venta>> call, Response<ArrayList<Venta>> response) {
              if(response.isSuccessful()){
                  Globalvar.venta=response.body();
                  Toast toast = Toast.makeText(getApplicationContext(),"guardado",Toast.LENGTH_LONG);
              }

          }

          @Override
          public void onFailure(Call<ArrayList<Venta>> call, Throwable t) {

          }
      });


    }
}