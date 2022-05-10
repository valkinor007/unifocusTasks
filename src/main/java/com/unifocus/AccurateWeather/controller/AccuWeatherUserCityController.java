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
import com.unifocus.AccurateWeather.model.UserCity;
import com.unifocus.AccurateWeather.repository.UserCityRepository;

@RestController
@RequestMapping("/persistance")
/**UserCity basic operations controller.*/
public class AccuWeatherUserCityController {
	

		@Autowired
		private UserCityRepository userCityRepository;

		@GetMapping("/userCities")
		public List<UserCity> getAllUserCities() {
			return userCityRepository.findAll();
		}

		@GetMapping("/userCities/{id}")
		public ResponseEntity<UserCity> getUserCityById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
			UserCity userCity = userCityRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
			return ResponseEntity.ok().body(userCity);
		}
		
		@GetMapping("/userCities/{userName}")
		public ResponseEntity<List<UserCity>> getUserCityByUsername(@PathVariable(value = "userName") String userName) throws ResourceNotFoundException {
			List<UserCity> userCities = userCityRepository.findByUserName(userName); //.orElseThrow(() -> new ResourceNotFoundException("User not found for this username :: " + usernuserNameame));
			return ResponseEntity.ok().body(userCities);
		}

		@PostMapping("/userCities")
		public UserCity createUserCity(@Valid @RequestBody UserCity userCity) {
			return userCityRepository.save(userCity);
		}

		@PutMapping("/userCities/{id}")
		public ResponseEntity<UserCity> updateUserCity(@PathVariable(value = "id") Long userCityId,	@Valid @RequestBody UserCity userCityDetail) throws ResourceNotFoundException {
			UserCity userCity = userCityRepository.findById(userCityId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userCityId));

			userCity.setUser(userCityDetail.getUser());
			userCity.setCity(userCityDetail.getCity());
			final UserCity updateUserCity = userCityRepository.save(userCity);
			return ResponseEntity.ok(updateUserCity);
		}

		@DeleteMapping("/userCities/{id}")
		public Map<String, Boolean> deleteUserCity(@PathVariable(value = "id") Long userCityId)	throws ResourceNotFoundException {
			UserCity userCity = userCityRepository.findById(userCityId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userCityId));

			userCityRepository.delete(userCity);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		}

}
