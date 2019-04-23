package com.projetotqs.prettycloud;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(ClientRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Client("Cliente1")));
            log.info("Preloading " + repository.save(new Client("Cliente2")));
        };
    }
}
