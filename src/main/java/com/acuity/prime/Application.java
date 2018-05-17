package com.acuity.prime;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Application class starting Spring Boot Prime application.
 * <p>
 * Created by Amit on 15/05/2018.
 */
@SpringBootApplication
@EnableCaching
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).run(args);
    }
}
