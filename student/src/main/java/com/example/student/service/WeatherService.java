package com.example.student.service;

import com.example.student.config.SchoolProperties;
import com.example.student.exception.WeatherApiException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class WeatherService {

    @Autowired
    private SchoolProperties schoolProperties;

    @Autowired
    private RestTemplate restTemplate;

    public Map<String, String> testProp(String location, String airIndex){
        Map<String,String> weatherData = new HashMap<>();
        try{
            var response = getWeatherResponse(location,airIndex);
            if(!ObjectUtils.isEmpty(response)) {
                JsonElement jsonElement = JsonParser.parseString(response);
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                var currentData = jsonObject.get("current").getAsJsonObject();
                weatherData.put("Temperature",currentData.get("temp_c").toString());
                weatherData.put("feelsLike",currentData.get("feelslike_c").toString());
                weatherData.put("uv",currentData.get("uv").toString());
                weatherData.put("windKph",currentData.get("wind_kph").toString());
            }
        }catch (WeatherApiException e){
            log.error(e.getMessage());
        }
        return weatherData;
    }

    private String getWeatherResponse(String location, String airIndex) throws WeatherApiException{
        String weatherApiUrl = schoolProperties.getApi().get("url");
        String weatherApiKey = schoolProperties.getApi().get("key");
        var url = String.format("%s?key=%s&q=%s&aqi=%s",weatherApiUrl,weatherApiKey,location,airIndex);
        var response = restTemplate.getForEntity(url, String.class);
        if(response.getStatusCode().equals(HttpStatus.OK)){
            return response.getBody();
        }else {
            throw new WeatherApiException("Failed to fetch weather data.");
        }
    }

}
