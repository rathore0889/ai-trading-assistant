package com.deepak.trading.dto.market;

import lombok.Data;

@Data
public class CompanyNewsResponse {

    private String headline;

    private String summary;

    private String source;

    private String datetime;

    private String url;
}