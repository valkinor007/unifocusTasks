package com.unifocus.AccurateWeather.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class AccuWeatherMapTimeDTO {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty("dt_txt")
	private LocalDateTime dt;

	private AccuWeatherMapTimeMainDTO main;

	public LocalDateTime getDt() {
		return dt;
	}

	public void setDt(LocalDateTime dt) {
		this.dt = dt;
	}

	public AccuWeatherMapTimeMainDTO getMain() {
		return main;
	}

	public void setMain(AccuWeatherMapTimeMainDTO main) {
		this.main = main;
	}

	@JsonIgnore
	public Boolean isDaily() {
		return (this.dt.getHour() >= 6 && this.dt.getHour() < 18);
	}

}
