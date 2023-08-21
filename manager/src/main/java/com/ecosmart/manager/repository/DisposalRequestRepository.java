package com.ecosmart.manager.repository;

import com.ecosmart.manager.data.DisposalRequest;
import com.ecosmart.manager.data.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DisposalRequestRepository extends JpaRepository<DisposalRequest, Integer> {
    List<DisposalRequest> findAllByCustomer_UserId(Integer userId);
    List<DisposalRequest> findAllByAgent_UserId(Integer userId);
    List<DisposalRequest> findAllByRequestStatus(RequestStatus requestStatus);
}
