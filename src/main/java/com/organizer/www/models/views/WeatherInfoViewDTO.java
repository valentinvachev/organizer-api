package com.organizer.www.models.views;

public class WeatherInfoViewDTO {
    private String cityName;
    private double temperature;

    public String getCityName() {
        return this.cityName;
    }

    public WeatherInfoViewDTO setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public WeatherInfoViewDTO setTemperature(double temperature) {
        this.temperature = temperature;
        return this;
    }
}
