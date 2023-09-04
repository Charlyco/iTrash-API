package com.ecosmart.manager.data;

import com.ecosmart.manager.dto.AgentDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponseAgent {
    private String token;
    private AgentDto agent;
}
