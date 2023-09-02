package com.example.auth.user.controller;


import com.example.auth.user.dto.LoginDto;
import com.example.auth.user.dto.RegisterDto;
import com.example.auth.user.service.UserService;
import com.example.core.base.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${baseUrl}/user")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService service;

    @PostMapping("/auth/register")
    public ResponseEntity<BaseResponse<String>> register(@RequestBody  @Valid RegisterDto request){

        return  ResponseEntity.status(HttpStatus.CREATED).body(service.register(request));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<RegisterDto> login(@RequestBody @Valid LoginDto request){

        return  ResponseEntity.ok(service.signin(request));
    }
}
