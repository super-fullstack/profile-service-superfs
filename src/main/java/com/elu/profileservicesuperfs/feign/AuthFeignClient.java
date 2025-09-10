package com.elu.profileservicesuperfs.feign;

import com.elu.profileservicesuperfs.UserResonseDto;
import com.elu.profileservicesuperfs.config.FeignClientConfig;
import com.elu.profileservicesuperfs.config.UserClientFallback;
import com.elu.profileservicesuperfs.dto.UserDtoOpenFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(name = "auth-service-superfs",
        configuration = FeignClientConfig.class,
        fallback = UserClientFallback.class)
public interface AuthFeignClient {

    @GetMapping("/open-feign/get-users")
    List<UserDtoOpenFeign> getUsers();

    @PostMapping("open-feign/update-user")
    ResponseEntity<Map<String, Object>> updateUser(@RequestBody UserDtoOpenFeign request);

}
