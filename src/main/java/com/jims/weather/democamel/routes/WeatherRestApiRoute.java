package com.jims.weather.democamel.routes;

import com.jims.weather.democamel.services.WeatherService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class WeatherRestApiRoute extends RouteBuilder {

    private final Environment env;

    public WeatherRestApiRoute(Environment env) {
        this.env = env;
    }

    @Override
    public void configure(){
        restConfiguration()
                .contextPath("/weatherapp")
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "REST API")
                .apiProperty("api.version", "1.0")
                .apiProperty("cors", "true")
                .apiContextRouteId("doc-api")
                .port(env.getProperty("server.port", "8080"))
                .bindingMode(RestBindingMode.json);

        rest("/weather/process")
                .get("/").description("Storage weather")
                .route().routeId("fetch-weather-api")
                .bean(WeatherService.class, "fetchWeather")
                .to("direct:saveWeather")
                .endRest();
    }

}
