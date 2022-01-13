package com.organizer.www.controllers;

import com.organizer.www.models.views.WeatherInfoViewDTO;
import com.organizer.www.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("")
    public ResponseEntity<?> getWeatherInfo(@RequestParam String city) {
        WeatherInfoViewDTO weatherInfoViewDTO = this.weatherService.getWeatherInfo(city);
        return ResponseEntity.ok().body(weatherInfoViewDTO);
    }
}
