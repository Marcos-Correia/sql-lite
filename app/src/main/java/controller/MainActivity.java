package controller;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.mysqlite.CityForm;
import com.example.mysqlite.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Comparator;

import model.City;
import model.DataStore;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerViewCities;
    FloatingActionButton addButton;
    CityAdapter cityAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Cities");
        recyclerViewCities = findViewById(R.id.recyclerViewCities);
        addButton = findViewById(R.id.addButton);
        DataStore.getInstance().setContext(this);
        cityAdapter = new CityAdapter();
        recyclerViewCities.setAdapter(cityAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewCities.setLayoutManager(manager);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CityForm.class);
                startActivity(intent);
            }
        });

    }

    public void sortAZName(View v){
        DataStore.getInstance().getCities().sort(new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        this.cityAdapter.notifyDataSetChanged();
    }
    public void sortZAName(View v){
        DataStore.getInstance().getCities().sort(new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return o2.getName().compareTo(o1.getName());
            }
        });
        this.cityAdapter.notifyDataSetChanged();
    }

    public void sortAZPopulation(View v){
        DataStore.getInstance().getCities().sort(new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return o1.getPopulation()==o2.getPopulation()?0:(o1.getPopulation()>o2.getPopulation()?1:-1);
            }
        });
        this.cityAdapter.notifyDataSetChanged();
    }
    public void sortZAPopulation(View v){
        DataStore.getInstance().getCities().sort(new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return o1.getPopulation()==o2.getPopulation()?0:(o1.getPopulation()>o2.getPopulation()?-1:1);
            }
        });
        this.cityAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.cityAdapter.notifyDataSetChanged();
    }


}
