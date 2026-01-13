package com.getirfinans.fraud.api;

import com.getirfinans.fraud.model.FraudDecision;
import com.getirfinans.fraud.service.FraudService;
import com.getirfinans.sharedkernel.DomainConstants;
import com.getirfinans.sharedkernel.SiparisRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fraud")
public class FraudController {

    private final FraudService fraudService;

    public FraudController(FraudService fraudService) {
        this.fraudService = fraudService;
    }

    @PostMapping("/evaluate")
    public ResponseEntity<FraudDecision> evaluate(@Valid @RequestBody SiparisRequest siparisRequest) {
        var signal = fraudService.evaluate(siparisRequest);
        String decision = "ALLOW";
        if ("HIGH".equals(signal.riskLevel())) {
            decision = "REVIEW_" + DomainConstants.FRAUD_HOST;
        }
        return ResponseEntity.ok(new FraudDecision(signal.siparisId(), decision, signal.reason()));
    }
}

