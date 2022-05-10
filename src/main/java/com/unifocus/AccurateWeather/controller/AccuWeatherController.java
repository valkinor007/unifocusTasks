package com.unifocus.AccurateWeather.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.unifocus.AccurateWeather.exception.ResourceNotFoundException;
import com.unifocus.AccurateWeather.model.UserCity;
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
@RequestMapping("weather")
/**Main controller - returns average weather info by sending a city name or by sending an username.*/
public class AccuWeatherController {
	
	@Autowired
    private AccuWeatherService accuWeatherService;
	
	@Autowired
	private AccuWeatherUserCityController accuWeatherUserCityController;
	

    @ApiOperation("Return a JSON object that gives the weather averages.")
    @GetMapping(value = "/forecast", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> weatherForecastAverage(@ApiParam("City name") @RequestParam(required = true) String cityName) {
        return accuWeatherService.weatherForecastAverage(cityName, "EN");
    }
    
    @ApiOperation("Return a JSON object that gives the weather averages.")
    @GetMapping(value = "/forecastDb", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> weatherForecastAverageFromDbById(@ApiParam("id") @RequestParam(required = true) Long idUserCity) {
    	ResponseEntity<UserCity> userCity = null;
		try {
			userCity = accuWeatherUserCityController.getUserCityById(idUserCity);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
        return accuWeatherService.weatherForecastAverage(userCity.getBody().getCity().getCityName(), "EN");
    }
    
    @ApiOperation("Return a JSON object that gives the weather averages.")
    @GetMapping(value = "/forecastDbUname", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> weatherForecastAverageFromDbByUserName(@ApiParam("username") @RequestParam(required = true) String username) {
    	ResponseEntity<List<UserCity>> userCities = null;
		try {
			userCities = accuWeatherUserCityController.getUserCityByUsername(username);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		
		List<String> cityNames = new ArrayList<String>();
		
		for (UserCity userCity : userCities.getBody()) {
			cityNames.add(userCity.getCity().getCityName());
		}
		
        return accuWeatherService.weatherForecastAverageCities(cityNames);
    }

}
