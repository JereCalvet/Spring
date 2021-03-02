package com.jeremias.consumerestservice.controller;

import com.jeremias.consumerestservice.domain.WeatherDTO;
import com.jeremias.consumerestservice.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/v1/weather")
@AllArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping
    public WeatherDTO askCurrentWeather(@RequestParam("cityToCheck") String cityToCheck) {
        final ResponseEntity<WeatherDTO> currentWeatherResponse = weatherService.getCurrentWeather(cityToCheck);

        if (currentWeatherResponse.getStatusCode() == HttpStatus.OK) {
            return currentWeatherResponse.getBody();
        } else {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }
}