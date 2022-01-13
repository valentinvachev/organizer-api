package com.organizer.www.models;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class WeatherInfo {
    private String name;
    private LinkedHashMap<String, Double> main;

    public String getName() {
        return this.name;
    }

    public WeatherInfo setName(String name) {
        this.name = name;
        return this;
    }

    public Map<String, Double> getMain() {
        return Collections.unmodifiableMap(this.main);
    }

    public WeatherInfo setMain(LinkedHashMap<String, Double> main) {
        this.main = main;
        return this;
    }
}
