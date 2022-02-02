package com.jims.weather.democamel.routes;

import com.jims.weather.democamel.services.WeatherService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SaveWeatherRoute  extends RouteBuilder {

    @Override
    public void configure() {
        from("direct:saveWeather")
                .bean(WeatherService.class, "saveWeather")
                .end();
    }

}
