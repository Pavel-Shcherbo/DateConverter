package org.date.dateconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class DateConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(DateConverterApplication.class, args);
    }
}
