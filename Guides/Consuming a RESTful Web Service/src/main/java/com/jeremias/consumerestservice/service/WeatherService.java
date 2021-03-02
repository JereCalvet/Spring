package com.jeremias.consumerestservice.service;

import com.jeremias.consumerestservice.domain.WeatherDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class WeatherService {

    private static final Logger LOG = LoggerFactory.getLogger(WeatherService.class);
    private static final String API_KEY = "RAPIDAPIKEYOPENWEATHERMAP";

    public Mono<?> getCurrentWeather(String cityToCheck) {
        //create web client
        WebClient client = WebClient.builder()
                .baseUrl("https://community-open-weather-map.p.rapidapi.com/weather?q=" + cityToCheck)
                .defaultHeader("x-rapidapi-key", System.getenv(API_KEY))
                .defaultHeader("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com")
                //.defaultUriVariables(Collections.singletonMap("q", cityToCheck))
                .build();


        //getting response
        return client.get().exchangeToMono(httpResponse -> {
            if (httpResponse.statusCode().equals(HttpStatus.OK)) {
                return httpResponse.bodyToMono(WeatherDTO.class);
            } else if (httpResponse.statusCode().is4xxClientError()) {
                return Mono.just(String.format("Error %d: %s not found.", httpResponse.statusCode().value(), cityToCheck));
            } else {
                return httpResponse.createException().flatMap(Mono::error);
            }
        });
        //TODO: figure out how to pass the exception to the controller to throw the right status code
    }
}