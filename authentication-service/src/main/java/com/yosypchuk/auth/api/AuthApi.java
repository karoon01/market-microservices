package com.yosypchuk.auth.api;

import com.yosypchuk.auth.model.AuthRequest;
import com.yosypchuk.auth.model.RegisterRequest;
import com.yosypchuk.auth.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@Api("Auth management API")
public interface AuthApi {
    @ApiOperation("Login user into system")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    User login(@RequestBody @Valid AuthRequest authRequest);

    @ApiOperation("Register user in system")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 409, message = "Conflict")
    })
    User register(@RequestBody @Valid RegisterRequest registerRequest);
}
