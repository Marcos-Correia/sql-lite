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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DataStore ds = DataStore.getInstance();
        if(ds.getEditingCityIndex()!=null){
            ds.getCity(ds.getEditingCityIndex()).setName(this.cityName.getText().toString());
            ds.getCity(ds.getEditingCityIndex()).setPopulation(Integer.parseInt(this.cityPopulation.getText().toString()));
        ds.editCity(ds.getCity(ds.getEditingCityIndex()),ds.getEditingCityIndex());
        ds.setEditingCityIndex(null);
    }else{
        ds.addCity(new City(this.cityName.getText().toString(),Integer.parseInt(this.cityPopulation.getText().toString())));
    }
    }
}
