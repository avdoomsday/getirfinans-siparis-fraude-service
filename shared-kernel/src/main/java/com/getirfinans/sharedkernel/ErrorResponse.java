package com.getirfinans.sharedkernel;

public record ErrorResponse(
        String code,
        String message,
        String domain
) {
}

