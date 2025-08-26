package com.arek.praktyki.Services;

import com.arek.praktyki.JsonDTO.EtfProfileResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AlphaWebClientService {
    private final WebClient webClient;
    private final String apiKey;

    public AlphaWebClientService(WebClient.Builder builder,
                                 @Value("${alpha.vantage.api.key}") String apiKey,
                                 @Value("${alpha.vantage.base.url}") String baseURL) {
        this.apiKey = apiKey;
        this.webClient = builder.baseUrl(baseURL).build();
    }

    public Mono<EtfProfileResponse> getEtfProfile(String symbol) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("function", "ETF_PROFILE")
                        .queryParam("symbol", symbol)
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(EtfProfileResponse.class);
    }
}
