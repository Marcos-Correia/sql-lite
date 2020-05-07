package controller;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysqlite.CityForm;
import com.example.mysqlite.R;

import java.util.List;

import model.City;
import model.DataStore;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityHolder> {
    private List<City> cities = DataStore.getInstance().getCities();

    @NonNull
    @Override
    public CityAdapter.CityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)  {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_cities, parent, false);
        return new CityHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.CityHolder holder, int position) {
        City city = cities.get(position);
        holder.textViewCityName.setText(city.getName());
        holder.textViewCityPopulation.setText(""+city.getPopulation());
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public class CityHolder extends RecyclerView.ViewHolder  implements View.OnClickListener, View.OnLongClickListener{
        TextView textViewCityName;
        TextView textViewCityPopulation;

        public CityHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            textViewCityName = itemView.findViewById(R.id.textViewCityName);
            textViewCityPopulation = itemView.findViewById(R.id.textViewCityPopulation);

        }

        @Override
        public void onClick(View v) {
            DataStore.getInstance().setEditingCityIndex(getLayoutPosition());
            v.getContext().startActivity(new Intent(v.getContext(),CityForm.class));
            //EDIT
        }

        @Override
        public boolean onLongClick(View v) {
            DataStore.getInstance().removeCity(getLayoutPosition());
            notifyDataSetChanged();
            return false;
        }
    }

}
