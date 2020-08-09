package com.nav.greenhousecontoller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class GreenHouseSystemInf {

    private long id;
    private int lightOn;
    private int conditioningOn;
    private int irrigationSystemOn;

}
