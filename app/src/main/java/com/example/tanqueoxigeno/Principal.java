package com.example.tanqueoxigeno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Bundle parametros=this.getIntent().getExtras();
        if(parametros!=null){
            Log.e("llegoID","------->"+parametros.getInt("cliente_id"));
        }
    }
}