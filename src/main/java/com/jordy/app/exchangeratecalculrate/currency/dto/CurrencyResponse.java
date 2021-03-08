package com.jordy.app.exchangeratecalculrate.currency.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Getter
@NoArgsConstructor
public class CurrencyResponse {

    private boolean success;

    private BigDecimal amount;

    public String getAmount() {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return decimalFormat.format(amount);
    }

    public CurrencyResponse(boolean success, BigDecimal amount) {
        this.success = success;
        this.amount = amount;
    }
}
