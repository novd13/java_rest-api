package cz.enehano.training.demoapp.restapi.configuration;

import cz.enehano.training.demoapp.restapi.model.UserEntity;
import cz.enehano.training.demoapp.restapi.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
  //  private final BCryptPasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            log.info("Preloading " + userRepository.save(new UserEntity("Severus", "Snape"
                    ,"severus",passwordEncoder.encode("password"), "iHate@harry.potter", "123123123")));
            log.info("Preloading " + userRepository.save(new UserEntity("Albus", "Dumbledore"
                    ,"albus", passwordEncoder.encode("password"), "iLove@harry.potter", "321321321")));


            };
    }
}
