package com.deepak.trading.dto.market;

import lombok.Data;

@Data
public class CompanyProfileResponse {

    private String name;
    private String ticker;
    private String exchange;
    private String finnhubIndustry;
    private Double marketCapitalization;
    private String country;
    private String currency;
    private String ipo;
}