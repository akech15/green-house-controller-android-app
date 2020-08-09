package com.nav.greenhousecontoller.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter

public class GreenHouse {

    private BigDecimal temperature;
    private BigDecimal humidity;
    private BigDecimal moisture;
    private BigDecimal light;

}
