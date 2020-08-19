package com.nav.greenhousecontoller.model;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GreenHouseLimits {

    private int downTemperatureLimit;
    private int upTemperatureLimit;
    private int downMoistureLimit;
    private int upMoistureLimit;
    private int downLightLimit;
    private int upLightLimit;

}
