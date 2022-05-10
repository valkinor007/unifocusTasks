package com.unifocus.AccurateWeather.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unifocus.AccurateWeather.model.UserCity;

public interface UserCityRepository extends JpaRepository<UserCity, Long> {

	@Query("from UserCity uc where uc.user.username = :username")
	List<UserCity> findByUserName(@Param("username") String userName);

}
