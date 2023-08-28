package com.example.auth.user.mapper;

import com.example.auth.role.mapper.RoleMapper;
import com.example.auth.token.mapper.TokenMapper;
import com.example.auth.user.dto.RegisterDto;
import com.example.auth.user.dto.UserDataResponse;
import com.example.auth.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
              RoleMapper.class,
                TokenMapper.class
        }

)
public interface UserMapper {


    @Mapping(target = "password",ignore = true)
    UserDataResponse toDto(User user);

    User toEntity (RegisterDto registerDto);

//    RegisterDto mapUserEntityToRegisterDto(User user);

}
