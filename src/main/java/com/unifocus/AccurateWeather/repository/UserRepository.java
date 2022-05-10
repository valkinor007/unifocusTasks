package com.unifocus.AccurateWeather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unifocus.AccurateWeather.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
