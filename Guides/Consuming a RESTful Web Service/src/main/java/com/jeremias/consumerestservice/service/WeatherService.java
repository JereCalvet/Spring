package com.jeremias.consumerestservice.service;

import com.jeremias.consumerestservice.domain.WeatherDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class WeatherService {

    private static final Logger LOG = LoggerFactory.getLogger(WeatherService.class);
    private static final String API_KEY = "RAPIDAPIKEYOPENWEATHERMAP";

    public ResponseEntity<WeatherDTO> getCurrentWeather(String cityToCheck) {
        final RestTemplate restTemplate = new RestTemplateBuilder().build();
        final HttpHeaders headers = createHttpHeaders();
        final HttpEntity<?> request = new HttpEntity<>(headers);
        String urlTargetAPI = "https://community-open-weather-map.p.rapidapi.com/weather?q=" + cityToCheck;

        ResponseEntity<WeatherDTO> response = null;
        try {
             response = restTemplate.exchange(urlTargetAPI,
                    HttpMethod.GET,
                    request,
                    WeatherDTO.class);
        } catch (RestClientException e) {
            LOG.error(String.format("Error: %s Searched city: %s.", e, cityToCheck));
        }
        return response;
    }

    private HttpHeaders createHttpHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", System.getenv(API_KEY));
        headers.set("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com");
        return headers;
    }
}