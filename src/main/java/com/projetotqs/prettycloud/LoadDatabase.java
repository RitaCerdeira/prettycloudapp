package com.projetotqs.prettycloud;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class to load database and, if necessary, preload it
 */

@Configuration
@Slf4j
public class LoadDatabase {

    /**
     * Method to initialize database and preload it, if necessary
     *
     * @param repository API's repository
     * @return data preloaded
     */

    @Bean
    CommandLineRunner initDatabase(ClientRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Client("Cliente1")));
            log.info("Preloading " + repository.save(new Client("Cliente2")));
        };
    }
}
