package com.elu.profileservicesuperfs.config;

import com.elu.profileservicesuperfs.UserResonseDto;
import com.elu.profileservicesuperfs.dto.UserDtoOpenFeign;
import com.elu.profileservicesuperfs.feign.AuthFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserClientFallback implements AuthFeignClient{

    @Override
    public List<UserDtoOpenFeign> getUsers() {
        return List.of();
    }



    @Override
    public ResponseEntity<Map<String, Object>> updateUser(UserDtoOpenFeign request) {
        return null;
    }

    @Override
    public ResponseEntity<UserDtoOpenFeign> getUser(String email) {
        return null;
    }
}

