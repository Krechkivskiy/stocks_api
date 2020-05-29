package com.test.task.stocks.stocks_api;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class StocksApiApplication implements CommandLineRunner {

    private final Runner runner;

    public StocksApiApplication(Runner runner) {
        this.runner = runner;
    }
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(StocksApiApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.setBannerMode(Banner.Mode.OFF);
        app.setAdditionalProfiles("development");
        app.run(args);
    }
    @Override
    public void run(String... args) {
        runner.run();
    }
}


