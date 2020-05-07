package model;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

public class DataStore {
    private static DataStore instance =null;


    public List<City> getCities() {
        return cities;
    }
    public City getCity(int index) {
        return cities.get(index);
    }

    private  DataStore(){

    }
    public static DataStore getInstance(){
        if (instance==null)
                instance = new DataStore();
            return instance;
    }
    private CityDataBase dataBase;
    private List<City> cities;

    public Integer getEditingCityIndex() {
        return editingCityIndex;
    }

    public void setEditingCityIndex(Integer editingCityIndex) {
        this.editingCityIndex = editingCityIndex;
    }

    private Integer editingCityIndex;
    private Context context;

    public void setContext(Context context){
        this.context = context;
        dataBase  =new CityDataBase(context);
        cities = dataBase.retriveCitiesFromDatabase();

    }

    public void addCity(City city){
        if(dataBase.createCityInDataBase(city)>0){
            cities.add(city);
        }else {
            Toast.makeText(context,"Problem in creation", Toast.LENGTH_SHORT).show();
        }
    }

    public void removeCity(int position){
        int count = dataBase.deleteCityFromDataBase(cities.get(position));
        if (count>0){
            cities.remove(position);
        }
    }

    public int getCitySize(){
        return cities.size();
    }

    public void editCity(City city, int position){
        int count = dataBase.updateCity(city);
        if (count>0){
            cities.set(position,city);
        }
    }

}
