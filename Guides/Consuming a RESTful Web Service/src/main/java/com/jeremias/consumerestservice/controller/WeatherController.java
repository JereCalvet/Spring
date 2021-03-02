package com.jeremias.consumerestservice.controller;

import com.jeremias.consumerestservice.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/weather")
@AllArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping
    public Mono<?> askCurrentWeather(@RequestParam("cityToCheck") String cityToCheck) {
        return weatherService.getCurrentWeather(cityToCheck);
    }
}