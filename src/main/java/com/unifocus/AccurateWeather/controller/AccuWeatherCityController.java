package com.unifocus.AccurateWeather.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unifocus.AccurateWeather.exception.ResourceNotFoundException;
import com.unifocus.AccurateWeather.model.City;
import com.unifocus.AccurateWeather.repository.CityRepository;

@RestController
@RequestMapping("/persistance")
/**City basic operations controller.*/
public class AccuWeatherCityController {
	

		@Autowired
		private CityRepository cityRepository;

		@GetMapping("/cities")
		public List<City> getAllCities() {
			return cityRepository.findAll();
		}

		@GetMapping("/cities/{id}")
		public ResponseEntity<City> getCityById(@PathVariable(value = "id") Long cityId) throws ResourceNotFoundException {
			City city = cityRepository.findById(cityId).orElseThrow(() -> new ResourceNotFoundException("City not found for this id :: " + cityId));
			return ResponseEntity.ok().body(city);
		}

		@PostMapping("/cities")
		public City createCity(@Valid @RequestBody City city) {
			return cityRepository.save(city);
		}

		@PutMapping("/cities/{id}")
		public ResponseEntity<City> updateEmployee(@PathVariable(value = "id") Long cityId,	@Valid @RequestBody City cityDetail) throws ResourceNotFoundException {
			City city = cityRepository.findById(cityId).orElseThrow(() -> new ResourceNotFoundException("City not found for this id :: " + cityId));

			city.setCityName(cityDetail.getCityName());
			final City updatedCity = cityRepository.save(city);
			return ResponseEntity.ok(updatedCity);
		}

		@DeleteMapping("/cities/{id}")
		public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long cityId)	throws ResourceNotFoundException {
			City city = cityRepository.findById(cityId).orElseThrow(() -> new ResourceNotFoundException("City not found for this id :: " + cityId));

			cityRepository.delete(city);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		}

}
