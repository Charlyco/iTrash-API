package com.ecosmart.manager.repository;

import com.ecosmart.manager.data.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
