package com.getirfinans.fraud.model;

public record FraudDecision(
        String siparisId,
        String decision,
        String reason
) {
}

