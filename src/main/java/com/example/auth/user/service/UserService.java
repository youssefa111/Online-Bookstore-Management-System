package com.example.auth.user.service;

import com.example.auth.token.service.TokenService;
import com.example.auth.user.dto.LoginDto;
import com.example.auth.user.dto.RegisterDto;
import com.example.auth.user.dto.UserDataResponse;
import com.example.auth.user.entity.User;
import com.example.auth.user.mapper.UserMapper;
import com.example.auth.user.repository.UserRepository;
import com.example.config.JwtService;
import com.example.core.base.BaseResponse;
import com.example.core.exception_handling.exception.DuplicateRecordException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserService  {

    private final TokenService tokenService;
    private final JwtService jwtService;;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Transactional
    public BaseResponse<String> register(RegisterDto request) {
        User user = userMapper.toEntity(request);
        isCivilIdExist(request.getCivilId());
        isUsernameExist(user);
        isEmailExist(user);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return  new BaseResponse<>(null,"User Account Created Successfully!", HttpStatus.CREATED.value());
    }

    private void isUsernameExist(User user) {
        var result = userRepository.findByUsername(user.getUsername());
        if(result.isPresent()){
            throw new DuplicateRecordException("This username already exists, try another one");
        }
    }

    private void isCivilIdExist(String civilId) {
        var result = userRepository.existsByCivilId(civilId);
        if(result){
            throw new DuplicateRecordException("This Civil id already exists, try another one");
        }
    }
    private void isEmailExist(User user) {
        var result = userRepository.findByEmail(user.getEmail());
        if(result.isPresent()){
            throw new DuplicateRecordException("This email already exists, try another one");
        }
    }

    public boolean isUserExist(Long id){
        return userRepository.existsById(id);
    }

    @Transactional
    public UserDataResponse signin(LoginDto request) {
     authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username or Password is incorrect!"));
        var jwtToken = jwtService.generateToken(user);
        tokenService.revokeAllUserTokens(user);
       var tokenResult = tokenService.saveUserToken(user, jwtToken,jwtService.extractExpiration(jwtToken));
        UserDataResponse dto = userMapper.toDto(user);
        dto.setToken(tokenService.toDto(tokenResult));

        return  dto;
    }


    public User getById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("Resource not found: " + userId));
    }
}
