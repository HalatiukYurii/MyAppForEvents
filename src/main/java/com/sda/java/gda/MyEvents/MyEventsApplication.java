package com.sda.java.gda.MyEvents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class MyEventsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyEventsApplication.class, args);
    }

    @Bean
    @Profile("!production")
    public FlywayMigrationStrategy cleanMigrationStrategy(){
        return flyway -> {
            flyway.clean();
            flyway.migrate();
        };
    }
}
