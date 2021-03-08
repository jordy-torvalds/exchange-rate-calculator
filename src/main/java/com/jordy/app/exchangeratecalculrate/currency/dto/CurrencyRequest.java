package com.jordy.app.exchangeratecalculrate.currency.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
public class CurrencyRequest {

    @NotBlank(message = "수취화폐종을 입력 해주세요.")
    private String receivedCurrencySpecies;

    @Positive(message = "송금액은 $0 ~ $10,000 를 입력해주세요.")
    @Max(value = 10000, message = "송금액은 $0 ~ $10,000 를 입력해주세요.")
    private BigDecimal amount;
}
