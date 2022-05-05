package com.example.tanqueoxigeno.form;

import com.example.tanqueoxigeno.botella.Botella;
import com.example.tanqueoxigeno.cliente.Cliente;
import com.example.tanqueoxigeno.estado.Estado;
import com.example.tanqueoxigeno.tamano.Tamano;
import com.example.tanqueoxigeno.venta.Venta;

import java.util.ArrayList;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {
    @POST("login.php")
    @Headers("Content-Type:application/json;charset=UTF-8")
    Call<ArrayList<Cliente>>getDatosUsuario(@Body RequestBody body);

    @POST("botellaCli.php")
    @Headers("Content-Type:application/json;charset=UTF-8")
    Call<ArrayList<Botella>>getDatosBotella(@Body RequestBody body);

    @POST("add.php")
    @Headers("Content-Type:application/json;charset=UTF-8")
    Call<String>registrarCliente(@Body RequestBody body);

    @POST("delete.php")
    @Headers("Content-Type:application/json;charset=UTF-8")
    Call <Integer> borrarBotella(@Body RequestBody body);

    @POST("add.php")
    @Headers("Content-Type:application/json;charset=UTF-8")
    Call <ArrayList<Venta>> registarVenta(@Body RequestBody body);

    @GET("datos.php")
    @Headers("Content-Type:application/json;charset=UTF-8")
    Call <ArrayList<Estado>> datosEstado();

    @GET("datos.php")
    @Headers("Content-Type:application/json;charset=UTF-8")
    Call <ArrayList<Tamano>> datosTamano();

    @GET("datos.php")
    @Headers("Content-Type:application/json;charset=UTF-8")
    Call <ArrayList<Cliente>> datosCliente();

    @POST("add.php")
    @Headers("Content-Type:application/json;charset=UTF-8")
    Call <ArrayList<Botella>> registrarBotella(@Body RequestBody body);

}
