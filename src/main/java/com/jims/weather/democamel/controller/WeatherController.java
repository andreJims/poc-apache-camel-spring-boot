package com.jims.weather.democamel.controller;

import com.jims.weather.democamel.model.ResponseApi;
import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final ProducerTemplate producerTemplate;

    public WeatherController(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    @GetMapping("/weather")
    @ResponseBody
    public ResponseApi handleSaveWeather(){
        producerTemplate.start();
        ResponseApi responseApi = producerTemplate.requestBody((Object) "direct:saveWeather", ResponseApi.class);
        producerTemplate.stop();
        return responseApi;
    }

}
