package com.example.carlos.paisesapp;

import org.json.JSONException;
import org.json.JSONObject;

public class Pais {
    private String nombre;
    private String alfacode;
    private String url;

    public Pais(JSONObject a) throws JSONException {
        nombre =  a.getString("name").toString();
        alfacode = a.getString("alpha2_code").toString();
        url = "http://www.geognos.com/api/en/countries/flag/"+alfacode+".png";
    }

    public String getNombre() {
        return nombre;
    }

    public String getAlfacode() {
        return alfacode;
    }

    public String getUrl() {
        return url;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAlfacode(String alfacode) {
        this.alfacode = alfacode;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
