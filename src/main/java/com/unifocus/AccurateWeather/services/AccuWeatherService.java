package com.unifocus.AccurateWeather.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.alicp.jetcache.anno.Cached;
import com.unifocus.AccurateWeather.dto.AccuWeatherAverageDTO;
import com.unifocus.AccurateWeather.dto.AccuWeatherMapDTO;
import com.unifocus.AccurateWeather.dto.AccuWeatherMapTimeDTO;

import springfox.documentation.spring.web.json.Json;

@Service
/**Service receives data from openweather service and builds ResponseEntity.*/
public class AccuWeatherService {
	
	private final String URI = "http://api.openweathermap.org/data/2.5/forecast";
    private final String API_ID = "801aa7bc488b9c3b55fb42ca7076fa41";

    private final RestTemplate restTemplate;

    public AccuWeatherService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Cached(expire = 10, timeUnit = TimeUnit.MINUTES)
    public ResponseEntity<?> weatherForecastAverage(String city, String locale) {
        List<AccuWeatherAverageDTO> result = new ArrayList<AccuWeatherAverageDTO>();
        try {
            AccuWeatherMapDTO weatherMap = this.restTemplate.getForObject(this.url(city), AccuWeatherMapDTO.class);

            for (LocalDate reference = LocalDate.now();
                 reference.isBefore(LocalDate.now().plusDays(5));
                 reference = reference.plusDays(1)) {
                final LocalDate ref = reference;
                List<AccuWeatherMapTimeDTO> collect = weatherMap.getList().stream().filter(x -> x.getDt().toLocalDate().equals(ref)).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(collect)) {
                    result.add(this.average(collect, locale));
                }

            }
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(new Json(e.getResponseBodyAsString()), e.getStatusCode());
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private AccuWeatherAverageDTO average(List<AccuWeatherMapTimeDTO> list, String locale) {
        AccuWeatherAverageDTO result = new AccuWeatherAverageDTO();

        for (AccuWeatherMapTimeDTO item : list) {
        	if("HR".equalsIgnoreCase(locale)) {
        		result.setDatum(item.getDt().toLocalDate());
        	} else {
        		result.setDate(item.getDt().toLocalDate());
        	}
            result.plusMap(item);
        }

        result.totalize();

        return result;
    }

    private String url(String city) {
        return String.format(URI.concat("?q=%s").concat("&appid=%s").concat("&units=metric"), city, API_ID);
    }
    
    @Cached(expire = 10, timeUnit = TimeUnit.MINUTES)
    public ResponseEntity<?> weatherForecastAverageCities(List<String> cities) {
        List<AccuWeatherAverageDTO> result = new ArrayList<AccuWeatherAverageDTO>();
        try {
        	for (String city : cities) {
				
        		AccuWeatherMapDTO weatherMap = this.restTemplate.getForObject(this.url(city), AccuWeatherMapDTO.class);
        		
        		for (LocalDate reference = LocalDate.now();
        				reference.isBefore(LocalDate.now().plusDays(5));
        				reference = reference.plusDays(1)) {
        			final LocalDate ref = reference;
        			List<AccuWeatherMapTimeDTO> collect = weatherMap.getList().stream().filter(x -> x.getDt().toLocalDate().equals(ref)).collect(Collectors.toList());
        			if (!CollectionUtils.isEmpty(collect)) {
        				result.add(this.average(collect, "EN"));
        			}
        			
        		}
			}
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(new Json(e.getResponseBodyAsString()), e.getStatusCode());
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
