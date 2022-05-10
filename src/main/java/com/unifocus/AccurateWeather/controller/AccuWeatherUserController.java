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
import com.unifocus.AccurateWeather.model.User;
import com.unifocus.AccurateWeather.repository.UserRepository;

@RestController
@RequestMapping("/persistance")
/**User basic operations controller.*/
public class AccuWeatherUserController {
	

		@Autowired
		private UserRepository userRepository;

		@GetMapping("/users")
		public List<User> getAllUsers() {
			return userRepository.findAll();
		}

		@GetMapping("/users/{id}")
		public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
			User employee = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
			return ResponseEntity.ok().body(employee);
		}

		@PostMapping("/users")
		public User createUser(@Valid @RequestBody User user) {
			return userRepository.save(user);
		}

		@PutMapping("/users/{id}")
		public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,	@Valid @RequestBody User userDetail) throws ResourceNotFoundException {
			User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

			user.setEmail(userDetail.getEmail());
			user.setName(userDetail.getName());
			user.setUsername(userDetail.getUsername());
			final User updatedEmployee = userRepository.save(user);
			return ResponseEntity.ok(updatedEmployee);
		}

		@DeleteMapping("/employees/{id}")
		public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long employeeId)	throws ResourceNotFoundException {
			User user = userRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + employeeId));

			userRepository.delete(user);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		}

}
