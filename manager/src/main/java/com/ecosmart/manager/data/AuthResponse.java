package com.ecosmart.manager.data;

import com.ecosmart.manager.dto.UserDto;
import lombok.Setter;

@Setter
public class AuthResponse {
    private String token;
    private UserDto user;
}
