package com.android.hkh.ad08_weather;

/**
 * Created by hieu.truongvan on 5/25/2017.
 */

public class Weather {
    private String idImage, cityName, weather, temperature;

    public Weather(String cityName, String weather, String temperature, String idImage) {
        this.idImage = idImage;
        this.cityName = cityName;
        this.weather = weather;
        this.temperature = temperature;
    }
    public Weather() {
        this.idImage = "";
        this.cityName = "";
        this.weather = "";
        this.temperature = "";
    }

    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
