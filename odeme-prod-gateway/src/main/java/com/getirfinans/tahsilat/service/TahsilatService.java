package com.getirfinans.tahsilat.service;

import com.getirfinans.sharedkernel.DomainConstants;
import com.getirfinans.sharedkernel.TahsilatIntent;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TahsilatService {

    private final Map<String, TahsilatIntent> intents = new ConcurrentHashMap<>();

    public TahsilatIntent createIntent(TahsilatIntent intent) {
        intents.put(intent.tahsilatId(), intent);
        return intent;
    }

    public Optional<TahsilatIntent> getIntent(String tahsilatId) {
        return Optional.ofNullable(intents.get(tahsilatId));
    }

    public String capture(String tahsilatId, BigDecimal tutar) {
        return intents.containsKey(tahsilatId)
                ? "TAHSIL_EDILDI@" + DomainConstants.TAHSILAT_INTERNAL_HOST + "@" + Instant.now() + "@tutar=" + tutar
                : "MISSING@" + DomainConstants.EXTERNAL_DOMAIN;
    }
}

