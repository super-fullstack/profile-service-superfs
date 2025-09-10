package com.elu.profileservicesuperfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProfileServiceSuperfsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProfileServiceSuperfsApplication.class, args);
    }

}
