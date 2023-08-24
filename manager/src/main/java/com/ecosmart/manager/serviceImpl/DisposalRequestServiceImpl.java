package com.ecosmart.manager.serviceImpl;

import com.ecosmart.manager.data.Agent;
import com.ecosmart.manager.data.DisposalRequest;
import com.ecosmart.manager.data.Location;
import com.ecosmart.manager.data.RequestStatus;
import com.ecosmart.manager.dto.DisposalRequestDto;
import com.ecosmart.manager.repository.AgentRepository;
import com.ecosmart.manager.repository.BinRepository;
import com.ecosmart.manager.repository.CustomerRepository;
import com.ecosmart.manager.repository.DisposalRequestRepository;
import com.ecosmart.manager.service.DisposalRequestService;
import com.ecosmart.manager.service.EntityDtoConverter;
import com.google.firebase.messaging.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DisposalRequestServiceImpl implements DisposalRequestService {
    private final DisposalRequestRepository requestRepository;
    private final AgentRepository agentRepository;
    private  final BinRepository binRepository;
    private final CustomerRepository customerRepository;
    private final EntityDtoConverter entityDtoConverter;

    public DisposalRequestServiceImpl(DisposalRequestRepository requestRepository,
                                      AgentRepository agentRepository,
                                      BinRepository binRepository,
                                      CustomerRepository customerRepository,
                                      EntityDtoConverter entityDtoConverter) {
        this.requestRepository = requestRepository;
        this.agentRepository = agentRepository;
        this.binRepository = binRepository;
        this.customerRepository = customerRepository;
        this.entityDtoConverter = entityDtoConverter;
    }

    @Override
    public List<DisposalRequestDto> getRequestsByStatus(RequestStatus requestStatus) {
        List<DisposalRequestDto> availableRequests = new ArrayList<>();
        requestRepository.findAllByRequestStatus(requestStatus).forEach(request ->
                availableRequests.add(entityDtoConverter.convertRequestToDto(request))
                );
        return availableRequests;
    }

    @Override
    public List<DisposalRequestDto> getAllRequestHandledByAgent(Integer agentId) {
        List<DisposalRequestDto> availableRequests = new ArrayList<>();
        requestRepository.findAllByAgent_UserId(agentId).forEach(request ->
                availableRequests.add(entityDtoConverter.convertRequestToDto(request))
        );
        return availableRequests;
    }

    @Override
    public String getRequestStatus(Integer requestId) {
        DisposalRequest request = requestRepository.findById(requestId).orElseThrow();
        return request.getRequestStatus().name();
    }

    @Override
    public String updateRequestStatus(Integer requestId, RequestStatus requestStatus) {
        if (requestRepository.findById(requestId).isPresent()) {
            DisposalRequest request = requestRepository.findById(requestId).orElseThrow();
            request.setRequestStatus(requestStatus);
            requestRepository.save(request);
            return requestRepository.findById(requestId).orElseThrow().getRequestStatus().name();
        }
        return "Disposal Request not found";
    }

    @Override
    public String assignRequestToAgent(Integer requestId, Integer agentId) {
        if (requestRepository.findById(requestId).isPresent()) {
            DisposalRequest request = requestRepository.findById(requestId).orElseThrow();
            request.setAgent(agentRepository.findById(agentId).orElseThrow());
            requestRepository.save(request);
            return requestRepository.findById(requestId).orElseThrow().getAgent().getUsername();
        }
        return "Disposal Request not found";
    }

    @Override
    public String generateRequest(DisposalRequestDto requestDto) throws FirebaseMessagingException {
        DisposalRequest disposalRequest = new DisposalRequest();
        disposalRequest.setRequestStatus(RequestStatus.SENT);
        disposalRequest.setBin(binRepository.findById(requestDto.getBinId()).orElseThrow());
        disposalRequest.setCustomer(customerRepository.findById(requestDto.getCustomerId()).orElseThrow());
        DisposalRequest savedRequest = requestRepository.save(disposalRequest);
        notifyAvailableAgents(savedRequest);
        return savedRequest.getRequestStatus().name();
    }

    private void notifyAvailableAgents(DisposalRequest requestDto) throws FirebaseMessagingException {
        List<Agent> agentList = agentRepository.findAll();
        List<Agent> availableAgents = new ArrayList<>();
        agentList.forEach(agent -> {
            if (calculateDistance(agent.getCurrentLocation(), requestDto.getBin().getLocation())) {
                availableAgents.add(agent);
            }
        });
        sendNotification(availableAgents, entityDtoConverter.convertRequestToDto(requestDto));
    }

    private boolean calculateDistance(Location agentLocation, Location binLocation) {
        double earthRadius = 6371.0;

        // Convert degrees to radians
        double lat1Rad = Math.toRadians(agentLocation.getLatitude());
        double lon1Rad = Math.toRadians(agentLocation.getLongitude());
        double lat2Rad = Math.toRadians(binLocation.getLatitude());
        double lon2Rad = Math.toRadians(binLocation.getLongitude());

        // Haversine formula
        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;
        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.pow(Math.sin(dLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;

        return distance < 10;
    }

    private void sendNotification(List<Agent> availableAgents, DisposalRequestDto requestDto) throws FirebaseMessagingException {
        //SMS will be sent to available agent but push notification will be sent to all devices registered for agents
        sendMessage("disposal_request", requestDto);
    }

    @Override
    public void sendMessage(String topic, DisposalRequestDto requestDto) throws FirebaseMessagingException {
        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setImage("New Disposal Request")
                        .setBody("Bin with ID: " + requestDto.getBinId() + "if filled. Click to see details")
                        .setImage("url to app icon")
                        .build())
                .setTopic(topic)
                .build();

        FirebaseMessaging.getInstance().send(message);
    }

    @Override
    public Integer subScribeClientDevice(List<String> tokens, String topic) throws FirebaseMessagingException {
        TopicManagementResponse response = FirebaseMessaging.getInstance().subscribeToTopic(tokens, topic);
        return response.getSuccessCount();
    }

    @Override
    public Integer unsubscribeClientDevice(List<String> tokens, String topic) throws FirebaseMessagingException {
        TopicManagementResponse response = FirebaseMessaging.getInstance().unsubscribeFromTopic(tokens, topic);
        return response.getSuccessCount();
    }
}
