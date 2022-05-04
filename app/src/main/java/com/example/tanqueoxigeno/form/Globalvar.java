package com.example.tanqueoxigeno.form;

import com.example.tanqueoxigeno.botella.Botella;
import com.example.tanqueoxigeno.cliente.Cliente;
import com.example.tanqueoxigeno.estado.Estado;
import com.example.tanqueoxigeno.tamano.Tamano;
import com.example.tanqueoxigeno.venta.Venta;

import java.util.ArrayList;

public class Globalvar {

    public static ArrayList<Cliente> datosCliente = new ArrayList<>();
    public static ArrayList<Botella> botella = new ArrayList<>();
    public static ArrayList<Venta> venta = new ArrayList<>();
    public static ArrayList<Tamano> tamano = new ArrayList<>();
    public static ArrayList<Estado> estado = new ArrayList<>();
    public static Integer botella_id = null;
    public static Integer cliente_id = null;


}
