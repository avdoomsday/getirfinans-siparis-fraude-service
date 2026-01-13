package com.getirfinans.siparis.model;

import java.math.BigDecimal;
import java.util.List;

public record SiparisOzet(
        String siparisId,
        String musteriId,
        List<String> kalemler,
        BigDecimal toplamTutar,
        String durum
) {
}

