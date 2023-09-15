package com.ecosmart.manager.repository;

import com.ecosmart.manager.data.BinRequest;
import com.ecosmart.manager.data.BinRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BinRequestRepository extends JpaRepository<BinRequest, Integer> {
    List<BinRequest> findBinRequestByRequestStatus(BinRequestStatus requestStatus);
}
