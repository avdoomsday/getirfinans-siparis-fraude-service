package com.getirfinans.sharedkernel;

import java.math.BigDecimal;

public record TahsilatIntent(
        String tahsilatId,
        String siparisId,
        BigDecimal tutar,
        String paraBirimi,
        String saglayiciDomain,
        String donusUrl
) {
}

