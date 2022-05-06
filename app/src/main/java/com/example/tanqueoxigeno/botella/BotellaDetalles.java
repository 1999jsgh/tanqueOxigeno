package com.example.tanqueoxigeno.botella;

import static com.example.tanqueoxigeno.form.Globalvar.botella;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tanqueoxigeno.Principal;
import com.example.tanqueoxigeno.R;
import com.example.tanqueoxigeno.RegistarMantenimiento;
import com.example.tanqueoxigeno.RegistrarVenta;
import com.example.tanqueoxigeno.adapter.ListaBotellaAdapter;
import com.example.tanqueoxigeno.cliente.Cliente;
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

public class BotellaDetalles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botella_detalles);

        Button btnBorrar = findViewById(R.id.btnBorrar);
        Button btnVenta = findViewById(R.id.btnVenta);
        Button btnMantenimiento=findViewById(R.id.btnMantenimiento);
        TextView botella_id=findViewById(R.id.botella_id);
        TextView tipoTamano=findViewById(R.id.tipoTamano);
        TextView tipoEstado=findViewById(R.id.tipoEstado);
        TextView codigo=findViewById(R.id.codigo);
        TextView valorManometro=findViewById(R.id.valorManometro);
        TextView valorRecarga=findViewById(R.id.valorRecarga);
        TextView fechaRecarga=findViewById(R.id.fechaRecarga);
        TextView fechaVencimiento=findViewById(R.id.fechaVencimiento);

        Bundle extras=this.getIntent().getExtras();
        String botellaId= String.valueOf(extras.getInt("botella_id"));
        String tamanoId= String.valueOf(extras.getInt("tamano_id"));
        String tipo_Tamano= extras.getString("tipoTamano");
        String estadoId= String.valueOf(extras.getInt("estado_id"));
        String tipo_Estado= extras.getString("tipoEstado");
        String Codigo= extras.getString("codigo");
        String valor_Manometro= extras.getString("valorManometro");
        String valor_Recarga= extras.getString("valorRecarga");
        String fecha_Recarga= extras.getString("fechaRecarga");
        String fecha_Vencimiento= extras.getString("fechaVencimiento");

        Log.e("tamanoId",tamanoId);
        Log.e("estadoId",estadoId);

        botella_id.setText(botellaId);
        tipoTamano.setText(tipo_Tamano);
        tipoEstado.setText(tipo_Estado);
        codigo.setText(Codigo);
        valorManometro.setText(valor_Manometro);
        valorRecarga.setText(valor_Recarga);
        fechaRecarga.setText(fecha_Recarga);
        fechaVencimiento.setText(fecha_Vencimiento);

        Bundle parametros=this.getIntent().getExtras();

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(parametros!=null){
                    try {
                        Eliminar(botellaId);
                      finish();
                    }catch(Exception e){

                    }
                }
            }
        });

        btnVenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(parametros!=null){
                    try {
                        vender(botellaId);
                    }catch(Exception e){

                    }
                }
            }
        });
        btnMantenimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(parametros!=null){
                    try {
                        mantenimiento(botellaId);
                    }catch(Exception e){

                    }
                }
            }
        });


    }

    private void mantenimiento(String botellaId) {
        Intent intent = new Intent(this, RegistarMantenimiento.class);
        intent.putExtra("botella_id",botellaId);
        startActivity(intent);
    }

    private void vender(String botellaId) {
        Intent intent = new Intent(this, RegistrarVenta.class);
        intent.putExtra("botella_id",botellaId);
        startActivity(intent);
    }

    private void Eliminar(String botellaId) {

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://orangecodecol.com/Botellasgas/botella/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONObject jsonObject= new JSONObject();
        try {
            Log.e("botella_id", String.valueOf(botellaId));
            jsonObject.put("botella_id", Integer.valueOf(botellaId));
        }catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Api myCall= retrofit.create(Api.class);
        Call<Integer> call= myCall.borrarBotella(body);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()){
                    Globalvar.botella_id=response.body();
                    Toast toast = Toast.makeText(getApplicationContext(),"Eliminado",Toast.LENGTH_LONG);
                }

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e("Error","Error al traer Datos"+t.getMessage());
            }
        });
    }
}