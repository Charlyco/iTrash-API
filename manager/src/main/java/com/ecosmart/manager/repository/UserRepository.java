package com.ecosmart.manager.repository;

import com.ecosmart.manager.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUserName(String userName);
}
