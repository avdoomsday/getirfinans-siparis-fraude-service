package com.getirfinans.sharedkernel;

import java.math.BigDecimal;
import java.util.List;

public record SiparisRequest(
        String siparisId,
        String musteriId,
        List<String> kalemler,
        BigDecimal toplamTutar,
        String paraBirimi,
        String kaynakDomain,
        String callbackUrl
) {
}

