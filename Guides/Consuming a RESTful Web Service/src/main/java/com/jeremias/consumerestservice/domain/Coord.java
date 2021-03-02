package com.jeremias.consumerestservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@JsonPropertyOrder({
        "lon",
        "lat"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class Coord {

    @JsonProperty("lon")
    private Float lon;
    @JsonProperty("lat")
    private Float lat;

}