package com.yosypchuk.auth.client;

import com.yosypchuk.auth.model.RegisterRequest;
import com.yosypchuk.auth.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(
        name = "user-service",
        url = "/user"
)
public interface UserFeignClient {
    @PostMapping
    User createUser(@RequestBody @Valid RegisterRequest registerRequest);

    @GetMapping("/{email}")
    User getUserByEmail(@PathVariable String email);
}
