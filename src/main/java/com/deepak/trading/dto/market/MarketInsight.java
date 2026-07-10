package com.deepak.trading.dto.market;

import lombok.Data;

import java.util.List;

@Data
public class MarketInsight {

    private StockQuoteResponse quote;

    private CompanyProfileResponse profile;

    private List<CompanyNewsResponse> news;

}