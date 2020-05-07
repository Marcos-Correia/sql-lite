package com.example.mysqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import model.City;
import model.DataStore;

public class CityForm extends AppCompatActivity {
    EditText cityName;
    EditText cityPopulation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_form);
        cityName = findViewById(R.id.cityName);
        cityPopulation = findViewById(R.id.cityPopulation);
        DataStore ds = DataStore.getInstance();
        if(ds.getEditingCityIndex()!=null){
            City city = ds.getCity(ds.getEditingCityIndex());
            cityName.setText(city.getName());
            cityPopulation.setText(city.getPopulation()+"");
        }

    }

    public void onSaveForm(View v){
        DataStore ds = DataStore.getInstance();
        if(ds.getEditingCityIndex()!=null){
            // fix
            ds.editCity(ds.getCity(ds.getEditingCityIndex()),ds.getEditingCityIndex());
        }
    }
}
