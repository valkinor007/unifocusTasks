package com.unifocus.AccurateWeather.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.unifocus.AccurateWeather.services.AccuWeatherService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("vrijeme")
/**Localized controller.*/
public class AccuWeatherControllerCro {
	
	@Autowired
    private AccuWeatherService accuWeatherService;

    @ApiOperation("Return a JSON object that gives the weather averages.")
    @GetMapping(value = "/prognoza", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> weatherForecastAverage(@ApiParam("City name") @RequestParam(required = true) String grad) {
        return accuWeatherService.weatherForecastAverage(grad, "HR");
    }

}
