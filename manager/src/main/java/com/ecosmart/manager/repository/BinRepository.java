package com.ecosmart.manager.repository;

import com.ecosmart.manager.data.Bin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BinRepository extends JpaRepository<Bin, Integer> {
}
