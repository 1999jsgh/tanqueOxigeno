package com.example.tanqueoxigeno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tanqueoxigeno.form.Api;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistarCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_cliente);
        Button b_registros = findViewById(R.id.btnRegistrar);
        EditText nombre=findViewById(R.id.nombre);
        EditText apellido=findViewById(R.id.apellido);
        EditText cedula=findViewById(R.id.cedula);
        EditText direccion=findViewById(R.id.direccion);
        EditText telefono=findViewById(R.id.telefono);
        EditText correo=findViewById(R.id.correo);
        EditText contrasena=findViewById(R.id.contrasena);


        b_registros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarCliente(
                        nombre.getText().toString(),
                        apellido.getText().toString(),
                        cedula.getText().toString(),
                        direccion.getText().toString(),
                        telefono.getText().toString(),
                        correo.getText().toString(),
                        contrasena.getText().toString()
                );
            }
        });
    }

    private void registrarCliente(String nombre, String apellido, String cedula, String direccion, String telefono, String correo, String contrasena) {

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://orangecodecol.com/Botellasgas/cliente/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("nombre", nombre);
            jsonObject.put("apellido", apellido);
            jsonObject.put("cedula", cedula);
            jsonObject.put("direccion", direccion);
            jsonObject.put("telefono", telefono);
            jsonObject.put("correo", correo);
            jsonObject.put("contrasena", contrasena);

        }catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Api myCall= retrofit.create(Api.class);
        Call<String> call= myCall.registrarCliente(body);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Log.e("cliente", response.body());
                }
                Log.e("", response.message());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("Error","Error al Registrar Datos"+t.getMessage());
                mensaje("Error al Registrar Cliente");
            }
        });
    }
    private void mensaje(String mensaje) {
        Toast toast1 = Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT);
        toast1.show();
    }

}