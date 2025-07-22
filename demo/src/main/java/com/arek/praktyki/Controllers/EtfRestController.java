package com.arek.praktyki.Controllers;

import com.arek.praktyki.JsonDTO.EtfProfileResponse;
import com.arek.praktyki.Services.AlphaWebClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/etf")
@RequiredArgsConstructor
public class EtfRestController {
    private final AlphaWebClientService alphaVantageService;

    @GetMapping("/{symbol}")
    public Mono<ResponseEntity<EtfProfileResponse>> getEtfProfile(@PathVariable String symbol) {
        return alphaVantageService.getEtfProfile(symbol)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
    }
}
