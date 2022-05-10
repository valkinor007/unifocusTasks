package com.unifocus.AccurateWeather;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.unifocus.AccurateWeather.controller.AccuWeatherCityController;
import com.unifocus.AccurateWeather.controller.AccuWeatherController;
import com.unifocus.AccurateWeather.controller.AccuWeatherUserCityController;
import com.unifocus.AccurateWeather.controller.AccuWeatherUserController;
import com.unifocus.AccurateWeather.dto.AccuWeatherAverageDTO;
import com.unifocus.AccurateWeather.model.City;
import com.unifocus.AccurateWeather.model.User;
import com.unifocus.AccurateWeather.model.UserCity;

@SpringBootTest
class AccurateWeatherApplicationTests {
	
	@Autowired
	AccuWeatherController accuWeatherController;
	
	@Autowired
	AccuWeatherUserController accuWeatherUserController;
	
	@Autowired
	AccuWeatherCityController accuWeatherCityController;
	
	@Autowired
	AccuWeatherUserCityController accuWeatherUserCityController;
	
	@Autowired
	EntityManager entityManager;

	@Test
	void contextLoads() {
		assertTrue(accuWeatherController!=null);
		assertTrue(accuWeatherUserCityController!=null);
	}
	
	@Test
	/**Test getting average temperature for one user, by sending username.*/
	public void testWeatherFetchByUsername() {
		ResponseEntity<?> response = accuWeatherController.weatherForecastAverageFromDbByUserName("mirko_pw");
		List<AccuWeatherAverageDTO> result = (List<AccuWeatherAverageDTO>) response.getBody();
		System.out.println("Result: " + result.get(0).toString());
	}

	
	@Test
	/**Initial DB data.*/
	public void testSaveUserCities() {
		User user1 = new User("mirko_pw", "Mirko", "mirko@gmail.com");
		user1 = accuWeatherUserController.createUser(user1);
		
		City city1 = new City("Zagreb");
		city1 = accuWeatherCityController.createCity(city1);
		
		UserCity userCity1 = new UserCity(user1, city1);
		accuWeatherUserCityController.createUserCity(userCity1);
		
		City city2 = new City("Buzet");
		city2 = accuWeatherCityController.createCity(city2);
		
		UserCity userCity2 = new UserCity(user1, city2);
		accuWeatherUserCityController.createUserCity(userCity2);
		
		City city3 = new City("Rijeka");
		city3 = accuWeatherCityController.createCity(city3);
		
		UserCity userCity3 = new UserCity(user1, city3);
		accuWeatherUserCityController.createUserCity(userCity3);
		
		City city4 = new City("Valpovo");
		city4 = accuWeatherCityController.createCity(city4);
		
		UserCity userCity4 = new UserCity(user1, city4);
		accuWeatherUserCityController.createUserCity(userCity4);
		
		City city5 = new City("Umag");
		city5 = accuWeatherCityController.createCity(city5);
		
		UserCity userCity5 = new UserCity(user1, city5);
		accuWeatherUserCityController.createUserCity(userCity5);
		
		User user2 = new User("slavko_pw", "Slavko", "slavko@gmail.com");
		user2 = accuWeatherUserController.createUser(user2);
		
		City city6 = new City("Osijek");
		city6 = accuWeatherCityController.createCity(city6);
		
		UserCity userCity6 = new UserCity(user2, city6);
		accuWeatherUserCityController.createUserCity(userCity6);
		
		City city7 = new City("Split");
		city7 = accuWeatherCityController.createCity(city7);
		
		UserCity userCity7 = new UserCity(user2, city7);
		accuWeatherUserCityController.createUserCity(userCity7);
		
		City city8 = new City("Pula");
		city8 = accuWeatherCityController.createCity(city8);
		
		UserCity userCity8 = new UserCity(user2, city8);
		accuWeatherUserCityController.createUserCity(userCity8);
		
		City city9 = new City("Zadar");
		city9 = accuWeatherCityController.createCity(city9);
		
		UserCity userCity9 = new UserCity(user2, city9);
		accuWeatherUserCityController.createUserCity(userCity9);
		
		City city10 = new City("Vukovar");
		city10 = accuWeatherCityController.createCity(city10);
		
		UserCity userCity10 = new UserCity(user2, city10);
		accuWeatherUserCityController.createUserCity(userCity10);
	}
	
	@Test
	/**Testing additional saving of data in DB.*/
	public void testSaveUserCitiesNew() {
		User user1 = new User("Luka_pw", "Luka", "Luka@gmail.com");
		user1 = accuWeatherUserController.createUser(user1);
		
		City city1 = new City("Karlovac");
		city1 = accuWeatherCityController.createCity(city1);
		
		UserCity userCity1 = new UserCity(user1, city1);
		accuWeatherUserCityController.createUserCity(userCity1);
	}


}
