package com.ecosmart.manager.data;

import com.ecosmart.manager.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String token;
    private UserDto user;
}
