package com.getirfinans.fraud.service;

import com.getirfinans.sharedkernel.FraudSignal;
import com.getirfinans.sharedkernel.SiparisRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class FraudService {

    public FraudSignal evaluate(SiparisRequest siparisRequest) {
        String riskLevel = siparisRequest.toplamTutar().doubleValue() > 5000 ? "HIGH" : "LOW";
        String reason = "scanned: " + siparisRequest.kaynakDomain() + " @ " + Instant.now();
        return new FraudSignal(siparisRequest.siparisId(), riskLevel, reason, siparisRequest.kaynakDomain());
    }
}
