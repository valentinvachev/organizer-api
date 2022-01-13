package com.organizer.www.services;

import com.organizer.www.models.views.WeatherInfoViewDTO;

public interface WeatherService {
    WeatherInfoViewDTO getWeatherInfo(String city);
}
