package com.unifocus.AccurateWeather.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@JacksonXmlRootElement(localName = "weather")
public class AccuWeatherMapDTO implements Serializable {

	private static final long serialVersionUID = 1253320017739887653L;

	private String cod;

	private BigDecimal message;

	private Integer cnt;

	@JacksonXmlProperty(localName = "list")
	@JacksonXmlElementWrapper(localName = "list", useWrapping = true)
	private List<AccuWeatherMapTimeDTO> list;

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public BigDecimal getMessage() {
		return message;
	}

	public void setMessage(BigDecimal message) {
		this.message = message;
	}

	public Integer getCnt() {
		return cnt;
	}

	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}

	public List<AccuWeatherMapTimeDTO> getList() {
		return list;
	}

	public void setList(List<AccuWeatherMapTimeDTO> list) {
		this.list = list;
	}

}
