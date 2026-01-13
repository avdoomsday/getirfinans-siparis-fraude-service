package com.getirfinans.sharedkernel;

public record FraudSignal(
        String siparisId,
        String riskLevel,
        String reason,
        String scannedDomain
) {
}

