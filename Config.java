package com.parking.builder;

import com.parking.builder.model.Rate;
import com.parking.builder.model.Spot;
import com.parking.builder.model.SpotType;
import com.parking.builder.repo.RateRepository;
import com.parking.builder.repo.SpotRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    CommandLineRunner seedData(SpotRepository spots, RateRepository rates) {
        return args -> {
            if (spots.count() == 0) {
                long id = 1;
                for (int level = 1; level <= 3; level++) {
                    for (int i = 1; i <= 4; i++) spots.save(new Spot("L" + level + "-C" + i, level, SpotType.COMPACT));
                    for (int i = 1; i <= 4; i++) spots.save(new Spot("L" + level + "-S" + i, level, SpotType.STANDARD));
                }
            }
            if (rates.count() == 0) {
                rates.save(new Rate(SpotType.COMPACT, 50.0, 30.0, 500.0));
                rates.save(new Rate(SpotType.STANDARD, 70.0, 40.0, 700.0));
            }
        };
    }
}
