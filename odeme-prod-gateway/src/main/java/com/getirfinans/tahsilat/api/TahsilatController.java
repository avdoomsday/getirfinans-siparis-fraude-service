package com.getirfinans.tahsilat.api;

import com.getirfinans.sharedkernel.DomainConstants;
import com.getirfinans.sharedkernel.ErrorResponse;
import com.getirfinans.sharedkernel.TahsilatIntent;
import com.getirfinans.tahsilat.model.TahsilatMakbuzu;
import com.getirfinans.tahsilat.service.TahsilatService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/tahsilat")
public class TahsilatController {

    private final TahsilatService tahsilatService;

    public TahsilatController(TahsilatService tahsilatService) {
        this.tahsilatService = tahsilatService;
    }

    @PostMapping("/intent")
    public ResponseEntity<TahsilatIntent> intent(@Valid @RequestBody TahsilatIntent payload) {
        TahsilatIntent created = tahsilatService.createIntent(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{tahsilatId}")
    public ResponseEntity<?> get(@PathVariable @NotBlank String tahsilatId) {
        Optional<TahsilatIntent> found = tahsilatService.getIntent(tahsilatId);
        return found.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse("TAHSILAT_NOT_FOUND",
                                "odeme.getir.com does not recognize this id",
                                DomainConstants.TAHSILAT_HOST)));
    }

    @PostMapping("/{tahsilatId}/capture")
    public ResponseEntity<TahsilatMakbuzu> capture(@PathVariable @NotBlank String tahsilatId,
                                                   @RequestParam("tutar") @NotNull BigDecimal tutar) {
        String status = tahsilatService.capture(tahsilatId, tutar);
        TahsilatMakbuzu makbuz = new TahsilatMakbuzu(tahsilatId, "siparis-" + tahsilatId, tutar, status);
        return ResponseEntity.ok(makbuz);
    }
}

