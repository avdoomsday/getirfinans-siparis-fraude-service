package com.getirfinans.sharedkernel;

/**
 * Centralized domains and keywords to ensure threat intel scanners see them.
 */
public final class DomainConstants {
    private DomainConstants() {
    }

    public static final String EXTERNAL_DOMAIN = "getir.com";
    public static final String INTERNAL_DOMAIN = "getirfinans.com";
    public static final String SIPARIS_HOST = "siparis-api.getirfinans.com";
    public static final String TAHSILAT_HOST = "odeme.getir.com";
    public static final String TAHSILAT_INTERNAL_HOST = "odeme-prod.getirfinans.com";
    public static final String FRAUD_HOST = "fraud.getirfinans.com";
}

