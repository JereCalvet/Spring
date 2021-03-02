package com.jeremias.consumerestservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Sys {

    private Integer type;
    private Integer id;
    private String country;
    private Float message;
    private Integer sunrise;
    private Integer sunset;
}