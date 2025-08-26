package com.arek.praktyki.JsonDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class EtfProfileResponse {
    @JsonProperty("net_assets")
    private String netAssets;

    @JsonProperty("net_expense_ratio")
    private String netExpenseRatio;

    @JsonProperty("portfolio_turnover")
    private String portfolioTurnover;

    @JsonProperty("dividend_yield")
    private String dividendYield;

    @JsonProperty("inception_date")
    private String inceptionDate;

    private String leveraged;

    private List<SectorAllocation> sectors;

    private List<Holding> holdings;
}
