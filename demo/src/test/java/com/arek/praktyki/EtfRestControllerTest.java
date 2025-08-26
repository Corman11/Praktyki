package com.arek.praktyki;

import com.arek.praktyki.Controllers.EtfRestController;
import com.arek.praktyki.JsonDTO.EtfProfileResponse;
import com.arek.praktyki.JsonDTO.Holding;
import com.arek.praktyki.JsonDTO.SectorAllocation;
import com.arek.praktyki.Services.AlphaWebClientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;

@WebFluxTest(EtfRestController.class)
public class EtfRestControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private AlphaWebClientService alphaVantageService;

    private EtfProfileResponse createMockEtfProfile() {
        EtfProfileResponse response = new EtfProfileResponse();
        response.setNetAssets("1B");
        response.setNetExpenseRatio("0.1%");
        response.setPortfolioTurnover("5%");
        response.setDividendYield("2%");
        response.setInceptionDate("2000-01-01");
        response.setLeveraged("No");

        SectorAllocation techSector = new SectorAllocation();
        techSector.setSector("Tech");
        techSector.setWeight("50%");

        Holding appleHolding = new Holding();
        appleHolding.setSymbol("AAPL");
        appleHolding.setDescription("Apple Inc.");
        appleHolding.setWeight("10%");

        response.setSectors(List.of(techSector));
        response.setHoldings(List.of(appleHolding));

        return response;
    }

    @Test
    void getEtfProfile_ShouldReturnOk() {
        String symbol = "SPY";

        // Mockowanie zwrotu serwisu
        EtfProfileResponse mockProfile = createMockEtfProfile();
        Mockito.when(alphaVantageService.getEtfProfile(symbol))
                .thenReturn(Mono.just(mockProfile));

        webTestClient.get()
                .uri("/api/etf/{symbol}", symbol)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void getEtfProfile_ShouldReturnBadRequest() {
        String symbol = "INVALID_SYMBOL";

        Mockito.when(alphaVantageService.getEtfProfile(symbol))
                .thenReturn(Mono.error(new IllegalArgumentException("Invalid symbol")));

        webTestClient.get()
                .uri("/api/etf/{symbol}", symbol)
                .exchange()
                .expectStatus().isBadRequest();
    }
}
