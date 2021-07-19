package com.seng3150.flightpub;

import com.seng3150.flightpub.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FlightpubApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightpubApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(UserRepository userRepository) {
//        return args -> {
//            UserEntity test = new UserEntity(
//                    "Bill",
//                    "Roger",
//                    "billIsBadAss@habbohotel.com",
//                    "123"
//            );
//            userRepository.save(test);
//        };
//
//
//
//    }



}

