package com.ecosmart.manager.repository;

import com.ecosmart.manager.data.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer> {
    Optional<Agent> findAgentByUserName(String userName);
}
