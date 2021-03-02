package com.jeremias.consumerestservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class Main {

    private Float temp;
    private Integer pressure;
    private Integer humidity;
    @JsonProperty("feels_like")
    private Float feelsLike;
    @JsonProperty("temp_min")
    private Float tempMin;
    @JsonProperty("temp_max")
    private Float tempMax;
}
