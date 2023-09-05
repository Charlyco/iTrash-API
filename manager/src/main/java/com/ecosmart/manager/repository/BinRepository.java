package com.ecosmart.manager.repository;

import com.ecosmart.manager.data.Bin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BinRepository extends JpaRepository<Bin, Integer> {
    List<Bin> findBinsByCustomer_UserId(Integer userId);
}
