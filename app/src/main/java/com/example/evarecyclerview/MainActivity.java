package com.example.evarecyclerview;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements  Asynchtask{
    private Spinner spC;
    private Spinner spSC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spC = findViewById(R.id.spCat);
        spSC = findViewById(R.id.spSubCat);

        Map<String, String> datos = new HashMap<>();
        WebService ws = new WebService("https://uealecpeterson.net/turismo/categoria/getlistadoCB",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        List<String> ct = new ArrayList<>();
        List<String> sbct = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(result);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String id = jsonObject.getString("id");
            String descripcion = jsonObject.getString("descripcion");
            ct.add(descripcion);
        }
        ArrayAdapter<String> ctAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ct);
        ctAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spC.setAdapter(ctAdapter);

        ArrayAdapter<String> sbctAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sbct);
        sbctAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSC.setAdapter(sbctAdapter);
    }
}