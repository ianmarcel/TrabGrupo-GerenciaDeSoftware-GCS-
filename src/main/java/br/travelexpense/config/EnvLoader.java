package br.travelexpense.config;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvBuilder;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvLoader {

    @PostConstruct
    public void init() {
        Dotenv dotenv = Dotenv.configure().load();
        System.getenv().forEach((name, value) -> {
            if (value == null) {
                String envValue = dotenv.get(name);
                if (envValue != null) {
                    System.setProperty(name, envValue);
                }
            }
        });
    }
}
