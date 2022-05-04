package com.example.tanqueoxigeno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class RegistarMantenimiento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_mantenimiento);

        Bundle extras=this.getIntent().getExtras();
        String botellaId= String.valueOf(extras.getString("botella_id"));
        Log.e("IDmantenimiento",botellaId);
//        botella_id.setText(botellaId);
    }
}