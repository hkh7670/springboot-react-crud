package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:application-area-list.yml", "classpath:application.yml"}, factory = YamlPropertySourceFactory.class, ignoreResourceNotFound = true)
public class AppConfig {

}
