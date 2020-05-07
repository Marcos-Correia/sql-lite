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

import java.util.Random;

import model.City;
import model.DataStore;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerViewCities;
    Button addButton;
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
//                cityAdapter.notifyDataSetChanged();
            }
        });
//        recyclerViewCities.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                System.out.println("um olho no peixe outro no gato");
//                return false;
//            }
//        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        this.cityAdapter.notifyDataSetChanged();
    }


}
