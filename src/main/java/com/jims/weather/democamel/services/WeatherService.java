package com.jims.weather.democamel.services;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service
public class WeatherService {

    final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    private static FileWriter file;

    public String fetchWeather() {
        logger.info("========fetchWeather============");
        try {
            HttpGet request = new HttpGet("http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=c33a51e789633c1957461e014e49f977");

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "failed";
    }

    public String saveWeather(String weather) {
        logger.info("=========saveWeatherfetchWeather============");
        try {
            file = new FileWriter("weather.json");
            file.write(weather);
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return weather;
    }
}
