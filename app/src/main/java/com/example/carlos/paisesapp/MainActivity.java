package com.example.carlos.paisesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mindorks.placeholderview.PlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    ArrayList<Pais> listaPaises;
    PlaceHolderView phvPaises;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("http://www.json-generator.com/api/json/get/coVvSGEWKW?indent=2", datos,this, this);
        ws.execute("");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONObject jsonObj = new JSONObject(result);
        JSONObject datos = jsonObj.getJSONObject("RestResponse");
        JSONArray paises= datos.getJSONArray("result");

        listaPaises = new ArrayList<>();
        phvPaises = (PlaceHolderView)findViewById(R.id.phvPaises);
        phvPaises.getBuilder().setLayoutManager(new GridLayoutManager(this.getApplicationContext(), 3));
        for (int i = 0; i < paises.length(); i++) {
            JSONObject p = paises.getJSONObject(i);
            listaPaises.add(new Pais(p));
        }

        AdaptadorPaises adaptador = new AdaptadorPaises(listaPaises,this);
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, detalle_pais.class);
                Bundle b = new Bundle();
                String cod = listaPaises.get(phvPaises.getChildAdapterPosition(v)).getAlfacode();
                Toast.makeText(MainActivity.this, cod, Toast.LENGTH_SHORT).show();
                b.putString("CODIGO", cod);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        phvPaises.setAdapter(adaptador);
    }
    }
