package com.getirfinans.siparis.service;

import com.getirfinans.sharedkernel.DomainConstants;
import com.getirfinans.sharedkernel.SiparisRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SiparisService {

    private final Map<String, SiparisRequest> inMemoryStore = new ConcurrentHashMap<>();

    public SiparisRequest create(SiparisRequest request) {
        inMemoryStore.put(request.siparisId(), request);
        return request;
    }

    public Optional<SiparisRequest> get(String siparisId) {
        return Optional.ofNullable(inMemoryStore.get(siparisId));
    }

    public String status(String siparisId) {
        return inMemoryStore.containsKey(siparisId)
                ? "CONFIRMED@" + DomainConstants.SIPARIS_HOST + "@" + Instant.now()
                : "NOT_FOUND@" + DomainConstants.EXTERNAL_DOMAIN;
    }
}

