package com.example.tanqueoxigeno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.tanqueoxigeno.botella.Botella;
import com.example.tanqueoxigeno.cliente.Cliente;
import com.example.tanqueoxigeno.databinding.ActivityRegistrarBotellaBinding;
import com.example.tanqueoxigeno.estado.Estado;
import com.example.tanqueoxigeno.form.Api;
import com.example.tanqueoxigeno.form.Globalvar;
import com.example.tanqueoxigeno.tamano.Tamano;
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

public class RegistrarBotella extends AppCompatActivity {

    private Spinner Spinnertamano_id;
    private Spinner Spinnercliente_id;
    private Spinner Spinnerestado_id;
    private int IdTamano;
    private int IdEstado;
    private int IdCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_botella);

        Button btnGuardarBotella=findViewById(R.id.btnGuardarBotella);
        EditText codigo=findViewById(R.id.codigo);
        EditText valorManometro=findViewById(R.id.valorManometro);
        EditText valorRecarga=findViewById(R.id.valorRecarga);
        EditText fechaRecarga=findViewById(R.id.fechaRecarga);
        EditText fechaVencimiento=findViewById(R.id.fechaVencimiento);

        obtenerDatosEstados();
        obtenerDatosTamano();
        obtenerDatosCiente();

        btnGuardarBotella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("entreOnclick","-------->");
                    try {
                        guardarBotella(IdCliente,
                                IdTamano,
                                IdEstado,
                                codigo.getText().toString(),
                                valorManometro.getText().toString(),
                                valorRecarga.getText().toString(),
                                fechaRecarga.getText().toString(),
                                fechaVencimiento.getText().toString());
                    }catch(Exception e){

                    }
                }


        });
    }

    private void guardarBotella( int idCliente,int idTamano, int idEstado, String codigo,
                                String valorManometro, String valorRecarga, String fechaRecarga, String fechaVencimiento) {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://orangecodecol.com/Botellasgas/botella/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.e("entreBoton","------------->");
        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("cliente_id", idCliente);
            jsonObject.put("tamano_id", idTamano);
            jsonObject.put("estado_id", idEstado);
            jsonObject.put("codigo", codigo);
            jsonObject.put("valorManometro", valorManometro);
            jsonObject.put("valorRecarga", valorRecarga);
            jsonObject.put("fechaRecarga", fechaRecarga);
            jsonObject.put("fechaVencimiento", fechaVencimiento);

        }catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("CUERPOENVIO", String.valueOf(jsonObject));
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Api myCall= retrofit.create(Api.class);
        Call<ArrayList<Botella>> call =myCall.registrarBotella(body);


      call.enqueue(new Callback<ArrayList<Botella>>() {
          @Override
          public void onResponse(Call<ArrayList<Botella>> call, Response<ArrayList<Botella>> response) {

              if(response.isSuccessful()){
                  Globalvar.botella=response.body();
                    Log.e("BODYBotella", String.valueOf(response.body()));
              }
          }

          @Override
          public void onFailure(Call<ArrayList<Botella>> call, Throwable t) {

          }
      });
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
                        spinnerViewCliente();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Cliente>> call, Throwable t) {

            }
        });
    }

    private void spinnerViewCliente() {
        Spinnercliente_id = findViewById(R.id.Spinnercliente_id);
        ArrayList<String> services =new ArrayList<>();

        for (int j = 0; j < Globalvar.datosCliente.size(); j++) {
            Cliente cliente2=Globalvar.datosCliente.get(j);
            cliente2.getNombre();
            cliente2.getApellido();
            services.add(cliente2.getNombre()+cliente2.getApellido());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,services);
        Spinnercliente_id.setAdapter(adapter);
        Spinnercliente_id.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int cliente= (int) Spinnercliente_id.getItemIdAtPosition(Spinnercliente_id.getSelectedItemPosition());
                IdCliente=cliente+2;
                Log.e("IDCliente","-------->"+ IdCliente);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
                    spinnerViewTamano();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Tamano>> call, Throwable t) {

            }
        });
    }

    private void spinnerViewTamano() {
        Spinnertamano_id = findViewById(R.id.Spinnertamano_id);

        ArrayList<String> tamanos =new ArrayList<>();
        for (int j = 0; j < Globalvar.tamano.size(); j++) {
            Tamano tamano2=Globalvar.tamano.get(j);
            tamano2.getTipoTamano();
            tamanos.add(tamano2.getTipoTamano());
        }
        ArrayAdapter<String> adapterTamano=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,tamanos);
        Spinnertamano_id.setAdapter(adapterTamano);
        Spinnertamano_id.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int tamano= (int) Spinnertamano_id.getItemIdAtPosition(Spinnertamano_id.getSelectedItemPosition());
                if(tamano==0){
                     IdTamano =1;
                }else{
                     IdTamano =2;
                }
                Log.e("IDTamano","-------->"+ IdTamano);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
                    spinnerViewEstado();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Estado>> call, Throwable t) {

            }
        });
    }
    private void spinnerViewEstado() {
        Spinnerestado_id = findViewById(R.id.Spinnerestado_id);

        ArrayList<String> estados =new ArrayList<>();
        for (int j = 0; j < Globalvar.estado.size(); j++) {
            Estado estado2=Globalvar.estado.get(j);
            estado2.getTipoEstado();
            estados.add(estado2.getTipoEstado());
        }
        ArrayAdapter<String> adapterEstado=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,estados);
        Spinnerestado_id.setAdapter(adapterEstado);
        Spinnerestado_id.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 IdEstado= (int) Spinnerestado_id.getItemIdAtPosition(Spinnerestado_id.getSelectedItemPosition());
                 IdEstado=IdEstado+1;

                Log.e("IDEstado", String.valueOf(IdEstado));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}