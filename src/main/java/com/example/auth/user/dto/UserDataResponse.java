package com.example.auth.user.dto;

import com.example.auth.token.dto.TokenDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDataResponse  extends RegisterDto{

    private TokenDto token;
}