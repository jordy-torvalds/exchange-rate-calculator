package com.jordy.app.exchangeratecalculrate.currency.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@NoArgsConstructor
@ToString
public class ExchangeRateResponse {
    private static final String SENDING_CURRENCY_KIND = "USD";
    private static final int DECIMAL_POINT = 2;

    // 응답 성공 여부
    private boolean success;

    // 사이트 이용 약관
    private String terms;

    // 사이트 개인정보 정책
    private String privacy;

    // 요청시간
    private int timestamp;

    // 기준 화폐 종류
    private String source;

    // 기준 화폐 대비 환율
    private Map<String, BigDecimal> quotes = new HashMap<>();

    @Builder
    public ExchangeRateResponse(boolean success, String terms, String privacy, int timestamp, String source, Map<String, BigDecimal> quotes) {
        this.success = success;
        this.terms = terms;
        this.privacy = privacy;
        this.timestamp = timestamp;
        this.source = source;
        this.quotes = quotes;
    }

    public BigDecimal retrieveExchangeRate(String currencyKind) {
        Optional<BigDecimal> exchangeRateOptional = Optional.ofNullable(quotes.get(SENDING_CURRENCY_KIND + currencyKind));
        if (exchangeRateOptional.isEmpty() == true)
            return BigDecimal.ZERO;
        return exchangeRateOptional.get().setScale(DECIMAL_POINT, RoundingMode.FLOOR);
    }
}
