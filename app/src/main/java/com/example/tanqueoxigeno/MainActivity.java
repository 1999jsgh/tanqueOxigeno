package com.example.tanqueoxigeno;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tanqueoxigeno.botella.Botella;
import com.example.tanqueoxigeno.cliente.Cliente;
import com.example.tanqueoxigeno.form.Api;
import com.example.tanqueoxigeno.form.Globalvar;
import com.google.android.material.textfield.TextInputEditText;

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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b_registros = findViewById(R.id.botonRegistar);
        Button b_iniciarSesion = findViewById(R.id.botonIniciarSesion);
        EditText e_correo = findViewById(R.id.editCorreo);
        EditText e_contra = findViewById(R.id.editContra);

        b_iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            obtenerDatos(e_correo.getText().toString(),e_contra.getText().toString());
            }
        });

    }

    private void obtenerDatos(String user, String contrasena) {

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://orangecodecol.com/Botellasgas/cliente/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("user", user);
            jsonObject.put("contrasena", contrasena);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Api myCall= retrofit.create(Api.class);
        Call<ArrayList<Cliente>> call= myCall.getDatosUsuario(body);

        call.enqueue(new Callback<ArrayList<Cliente>>() {
            @Override
            public void onResponse(Call<ArrayList<Cliente>> call, Response<ArrayList<Cliente>> response) {
                if(response.isSuccessful()) {
                    Globalvar.datosCliente= response.body();
                    Log.e("CLIENTE", String.valueOf(response.body()));
                    for(int i=0; i<Globalvar.datosCliente.size(); i++){
                        Cliente cliente1=Globalvar.datosCliente.get(i);
                        if(cliente1.getCliente_id() !=0){
                        datosBotellasCliente(cliente1.getCliente_id());
                        }
                    }

                }else{
                    Log.e("entreElse", "-------->");
                   // mensaje("else wey");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Cliente>> call, Throwable t) {
                mensaje("Cliente desconocido");
                Log.e("Error","Error al traer Datos"+t.getMessage());

            }
        });

    }

    private void datosBotellasCliente(int idCliente) {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://orangecodecol.com/Botellasgas/botella/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Intent intent = new Intent(this, Principal.class);
        intent.putExtra("cliente_id",idCliente);
        startActivity(intent);

        JSONObject jsonObject= new JSONObject();
        try {
            Log.e("CLIENTEID", String.valueOf(idCliente));
            jsonObject.put("cliente_id", idCliente);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Api myCall= retrofit.create(Api.class);
        Call<ArrayList<Botella>>call =myCall.getDatosBotella(body);

        call.enqueue(new Callback<ArrayList<Botella>>() {
            @Override
            public void onResponse(Call<ArrayList<Botella>> call, Response<ArrayList<Botella>> response) {
                if(response.isSuccessful()){
                    Globalvar.botella=response.body();
                    Log.e("Botella",response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Botella>> call, Throwable t) {

            }
        });

    }

    private void mensaje(String mensaje) {
        Toast toast1 = Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT);
        toast1.show();
    }
}