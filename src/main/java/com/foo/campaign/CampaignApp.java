package com.foo.campaign;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CampaignApp implements ApplicationRunner {

    public static void main(final String... args) {
        SpringApplication.run(CampaignApp.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }

}
