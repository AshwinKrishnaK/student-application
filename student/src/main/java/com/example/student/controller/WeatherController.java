package com.example.student.controller;

import com.example.student.model.Response;
import com.example.student.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.student.constant.SchoolConstants.SUCCESS;

@RestController
@RequestMapping("weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/")
    public ResponseEntity<Response> getWeather(@RequestParam("location") String location,
                                                @RequestParam("airindex") String airIndex){
        return ResponseEntity.ok(new Response(weatherService.testProp(location,airIndex), SUCCESS));
    }
}
