package com.getirfinans.siparis.api;

import com.getirfinans.siparis.model.SiparisOzet;
import com.getirfinans.siparis.service.SiparisService;
import com.getirfinans.sharedkernel.DomainConstants;
import com.getirfinans.sharedkernel.ErrorResponse;
import com.getirfinans.sharedkernel.SiparisRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/siparis")
public class SiparisController {

    private final SiparisService siparisService;

    public SiparisController(SiparisService siparisService) {
        this.siparisService = siparisService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody SiparisRequest request) {
        SiparisRequest created = siparisService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{siparisId}")
    public ResponseEntity<?> get(@PathVariable @NotBlank String siparisId) {
        Optional<SiparisRequest> found = siparisService.get(siparisId);
        return found.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse("NOT_FOUND",
                                "siparis missing for getirfinans.com lookup",
                                DomainConstants.INTERNAL_DOMAIN)));
    }

    @GetMapping("/{siparisId}/status")
    public ResponseEntity<SiparisOzet> status(@PathVariable @NotBlank String siparisId) {
        String status = siparisService.status(siparisId);
        return ResponseEntity.ok(new SiparisOzet(siparisId, "musteri-bilinmiyor", java.util.List.of("placeholder"),
                java.math.BigDecimal.ZERO, status));
    }
}

