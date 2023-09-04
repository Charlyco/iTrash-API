package com.ecosmart.manager.data;

import com.ecosmart.manager.dto.AdminDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponseAdmin {
    private String token;
    private AdminDto admin;
}
