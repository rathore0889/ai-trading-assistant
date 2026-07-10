package com.deepak.trading.dto.market;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FinnhubQuoteResponse {

    @JsonProperty("c")
    private Double currentPrice;

    @JsonProperty("h")
    private Double highPrice;

    @JsonProperty("l")
    private Double lowPrice;

    @JsonProperty("o")
    private Double openPrice;

    @JsonProperty("pc")
    private Double previousClose;
}