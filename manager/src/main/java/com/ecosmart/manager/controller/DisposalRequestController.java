package com.ecosmart.manager.controller;

import com.ecosmart.manager.data.RequestStatus;
import com.ecosmart.manager.dto.DisposalRequestDto;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/disposal")
public interface DisposalRequestController {
    @GetMapping("/status")
    ResponseEntity<List<DisposalRequestDto>> getRequestsByStatus(@RequestParam("status") RequestStatus requestStatus);

    @GetMapping("/location")
    ResponseEntity<List<DisposalRequestDto>> getPendingRequestByLocation(@RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude);

    @GetMapping("/{agentId}")
    ResponseEntity<List<DisposalRequestDto>> getAllRequestHandledByAgent(@PathVariable Integer agentId );

    @GetMapping("/{requestId}")
    ResponseEntity<String> getRequestStatus(@PathVariable("requestId") Integer requestId);

    @PutMapping("/{requestId}")
    ResponseEntity<String> updateRequestStatus(@PathVariable("requestId") Integer requestId, @RequestParam("status") String status);

    @PutMapping("/assign/{requestId}")
    ResponseEntity<Boolean> assignRequestToAgent(@PathVariable("requestId") Integer requestId, @RequestParam("agentId") Integer agentId);

    @PostMapping("/create")
    ResponseEntity<Integer> generateRequest(@RequestBody DisposalRequestDto requestDto) throws FirebaseMessagingException;

    @GetMapping()
    ResponseEntity<DisposalRequestDto> getRequestById(@RequestParam("requestId") Integer requestId);
    @PutMapping("/notify/subscribe")
    ResponseEntity<Integer> subscribeToTopic(@RequestParam List<String> tokens, @RequestParam String topic) throws FirebaseMessagingException;

    @PutMapping("/notify/unsubscribe")
    ResponseEntity<Integer> unsubscribeToTopic(@RequestParam List<String> tokens, @RequestParam String topic) throws FirebaseMessagingException;

}
