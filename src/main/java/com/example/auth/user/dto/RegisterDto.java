package com.example.auth.user.dto;

import com.example.auth.role.dto.RoleDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterDto {

    @NotBlank
    @Size(min = 3 , max = 30)
    private String firstName;
    @NotBlank
    @Size(min = 3 , max = 30)
    private String lastName;
    @NotBlank
    @Size(min = 3 , max = 30)
    private String address;
    @NotBlank
    @Size(min = 11 , max = 11)
    private String phone;
    @NotBlank
    @Size(min = 3 , max = 30)
    private String username;
    @NotBlank
    @Size(min = 12 , max = 12)
    @Pattern(regexp = "^[0-9]+$", message = "civil id must contain only digits")
    private String civilId;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 7 , max = 30)
    private String password;
    @NotNull
    private RoleDto role;

}
