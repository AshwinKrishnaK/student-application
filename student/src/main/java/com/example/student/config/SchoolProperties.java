package com.example.student.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@Component(value = "SchoolProperties")
@ConfigurationProperties(prefix = "weather")
@Data
@Validated
public class SchoolProperties {

    private Map<String,String> api;

}
