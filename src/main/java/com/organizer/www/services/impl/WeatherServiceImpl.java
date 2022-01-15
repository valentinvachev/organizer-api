package com.organizer.www.services.impl;

import com.organizer.www.models.WeatherInfo;
import com.organizer.www.models.views.WeatherInfoViewDTO;
import com.organizer.www.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final String API_KEY = "e98c93793ea03f874a632f3f4cd1fb1a";
    private final String WEATHER_URL =
            "https://api.openweathermap.org/data/2.5/weather?q={city}&mode=json&units=metric&appid={apiKey}";
    private final WebClient.Builder webClientBuilder;

    @Autowired
    public WeatherServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public WeatherInfoViewDTO getWeatherInfo(String city) {
        WeatherInfo responseApiObject = this.webClientBuilder.build()
                .get()
                .uri(new UriTemplate(WEATHER_URL).expand(city, this.API_KEY))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(WeatherInfo.class)
                .block();

        WeatherInfoViewDTO weatherInfoViewDTO = new WeatherInfoViewDTO();

        if (responseApiObject != null) {
            weatherInfoViewDTO.setCityName(responseApiObject.getName());
            weatherInfoViewDTO.setTemperature(responseApiObject.getMain().get("temp"));
        }

        return weatherInfoViewDTO;
    }
}
