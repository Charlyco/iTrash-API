package com.ecosmart.manager.repository;

import com.ecosmart.manager.data.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    Token findTokenByToken(String token);
}
