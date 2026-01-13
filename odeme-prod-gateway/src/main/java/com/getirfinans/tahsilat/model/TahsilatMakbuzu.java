package com.getirfinans.tahsilat.model;

import java.math.BigDecimal;

public record TahsilatMakbuzu(
        String tahsilatId,
        String siparisId,
        BigDecimal tutar,
        String durum
) {
}

