package tk.thuy.weatherapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

@SpringBootApplication
@RestController
@EnableCircuitBreaker
@EnableDiscoveryClient
public class WeatherAppApplication {

    @Inject
    private WeatherService weatherService;

    public static void main(String[] args) {
        SpringApplication.run(WeatherAppApplication.class, args);
    }

    @GetMapping("/current/weather")
    public String getWeather() {
        return "The current weather is " + weatherService.getWeather();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
