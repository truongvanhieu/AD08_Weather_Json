package com.android.hkh.ad08_weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DownloadJsonAsyncTaskListener{
    private ListView myListView;
    private MyArrayAdapter myAdapter;
    private ArrayList<Weather> weatherDataSource;
    private String idCity[] = {"1583992","1905516","1580541","1581129","1559969","1905577","1568758","1569805","1559977","1562798","1572151", "1559976", "1905475", "1566338", "1566557", "1559972", "1582562","1559971"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Step 1:
        myListView = (ListView) findViewById(R.id.mylistview);
        //step 2: data source
        weatherDataSource = new ArrayList<>();
        //(String idImage, String cityName, String weather, String temperature)
        /*weatherDataSource.add(new Weather("Hà Nội", "Sunny", "32°C", "sunny"));
        weatherDataSource.add(new Weather("Quảng Bình", "Cloudy", "25°C", "cloudy"));
        weatherDataSource.add(new Weather("Huế", "Rainy", "23°C", "rainy"));
        weatherDataSource.add(new Weather("Đà Nẵng", "Sunny", "32°C", "sunny"));
        weatherDataSource.add(new Weather("Quảng Nam", "Sunny", "30°C", "sunny"));
        weatherDataSource.add(new Weather("Quảng Ngãi", "Thunder", "24°C", "thunder"));
        weatherDataSource.add(new Weather("Đà Lạt", "Cloudy", "20°C", "cloudy"));
        weatherDataSource.add(new Weather("Phan Thiết", "Thunder", "24°C", "thunder"));
        weatherDataSource.add(new Weather("Vũng Tàu", "Rainy", "25°C", "rainy"));
        weatherDataSource.add(new Weather("Hồ Chính Minh", "Sunny", "33°C", "sunny"));*/
        for(int i = 0; i < idCity.length; i++) {
            String linkjson = "http://api.openweathermap.org/data/2.5/weather?id="+idCity[i]+"&appid=973134294693f3ea1279678dc953329c";
            Log.i("LinkJson", linkjson);
            DownloadJsonAsyncTask downloadJsonAsyncTask = new DownloadJsonAsyncTask();
            downloadJsonAsyncTask.SetLister(this);
            downloadJsonAsyncTask.execute(linkjson);
        }
        //step 3: create adapter
        myAdapter = new MyArrayAdapter(MainActivity.this, R.layout.layout_wather, weatherDataSource);
        //step 4:
        myListView.setAdapter(myAdapter);
    }
    @Override
    public void OnFinisheDownload(String result) {
        Log.i("hehe", result);
        ParseJson(result);
    }
    public void ParseJson(String jsonString) {
        try{
            JSONObject jsonRootObject = new JSONObject(jsonString);
            JSONArray jsonObjArrayWeather = jsonRootObject.getJSONArray("weather");
            String temp = jsonRootObject.getJSONObject("main").getString("temp");
            String cityname = jsonRootObject.getString("name");
            String main=null, description=null, icon=null;
            for (int i=0; i < jsonObjArrayWeather.length(); i++) {
                JSONObject jsonObject = jsonObjArrayWeather.getJSONObject(i);
                main = jsonObject.getString("main");
                description = jsonObject.getString("description");
                icon = jsonObject.getString("icon");
                Log.i("Icon", icon);
            }
            weatherDataSource.add(new Weather(cityname, main, temp, icon+".png"));//"icon"+icon));
            myAdapter.notifyDataSetChanged();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
