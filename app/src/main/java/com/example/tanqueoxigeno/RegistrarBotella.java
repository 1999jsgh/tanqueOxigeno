package com.example.tanqueoxigeno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.tanqueoxigeno.cliente.Cliente;
import com.example.tanqueoxigeno.databinding.ActivityRegistrarBotellaBinding;
import com.example.tanqueoxigeno.estado.Estado;
import com.example.tanqueoxigeno.form.Api;
import com.example.tanqueoxigeno.form.Globalvar;
import com.example.tanqueoxigeno.tamano.Tamano;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrarBotella extends AppCompatActivity {

    private ActivityRegistrarBotellaBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        binding=ActivityRegistrarBotellaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        obtenerDatosEstados();
        obtenerDatosTamano();
        obtenerDatosCiente();
    }

    private void spinnerViewCliente(String nombre, String apellido) {
        ArrayList<String> services =new ArrayList<>();

//        for(int i=0; i<Globalvar.datosCliente.size(); i++){
//            Cliente cliente1=Globalvar.datosCliente.get(i);
//            if(cliente1.getCliente_id() !=0){
//                cliente1.getNombre();
//                cliente1.getApellido();
        Log.e("tamano2","-------------->"+Globalvar.datosCliente.size());
                Log.e("Nombre","-------------->"+nombre);
                Log.e("apellido","-------------->"+apellido);
        services.add(nombre+apellido);

        for (int j = 0; j < Globalvar.datosCliente.size(); j++) {
        }



//            }
//        }

//        services.add("CLARO");
//        services.add("MOVISTAR");
//        services.add("WOM");
//        services.add("TIGO");
//        services.add("MOVIL EXITO");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,services);
        binding.SpinnerclienteId.setAdapter(adapter);
    }

    private void obtenerDatosCiente() {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://orangecodecol.com/Botellasgas/cliente/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api myCall= retrofit.create(Api.class);
        Call<ArrayList<Cliente>> call =myCall.datosCliente();

        call.enqueue(new Callback<ArrayList<Cliente>>() {
            @Override
            public void onResponse(Call<ArrayList<Cliente>> call, Response<ArrayList<Cliente>> response) {
                if(response.isSuccessful()){
                    Globalvar.datosCliente=response.body();
//                    Log.e("BODYCliente",response.body().toString());


                    for(int i=0; i<Globalvar.datosCliente.size(); i++){
                        Cliente cliente1=Globalvar.datosCliente.get(i);
                        cliente1.getNombre();
                        cliente1.getApellido();
                        spinnerViewCliente(cliente1.getNombre(),cliente1.getApellido());
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Cliente>> call, Throwable t) {

            }
        });

    }

    private void obtenerDatosTamano() {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://orangecodecol.com/Botellasgas/tamano/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api myCall= retrofit.create(Api.class);
        Call<ArrayList<Tamano>> call =myCall.datosTamano();

        call.enqueue(new Callback<ArrayList<Tamano>>() {
            @Override
            public void onResponse(Call<ArrayList<Tamano>> call, Response<ArrayList<Tamano>> response) {
                if(response.isSuccessful()){
                    Globalvar.tamano=response.body();
//                    Log.e("BODYtamano",response.body().toString());

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Tamano>> call, Throwable t) {

            }
        });
    }

    private void obtenerDatosEstados() {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://orangecodecol.com/Botellasgas/estado/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Api myCall= retrofit.create(Api.class);
        Call<ArrayList<Estado>> call =myCall.datosEstado();

        call.enqueue(new Callback<ArrayList<Estado>>() {
            @Override
            public void onResponse(Call<ArrayList<Estado>> call, Response<ArrayList<Estado>> response) {
                if(response.isSuccessful()){
                    Globalvar.estado=response.body();
//                    Log.e("BODYestado",response.body().toString());

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Estado>> call, Throwable t) {

            }
        });
    }
}