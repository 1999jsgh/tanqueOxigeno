package com.example.tanqueoxigeno;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b_registros = findViewById(R.id.botonRegistar);
        EditText e_correo = findViewById(R.id.editCorreo);
        EditText e_contra = findViewById(R.id.editContra);

        b_registros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            obtenerDatos(e_correo.getText().toString(),e_contra.getText().toString());
            }
        });

    }

    private void obtenerDatos(String user, String pass) {

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://localhost/Botellasgas/cliente/edit.php")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("user", user);
            jsonObject.put("contrasena", pass);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Log.e("", "------>" + jsonObject.toString());

    }
}