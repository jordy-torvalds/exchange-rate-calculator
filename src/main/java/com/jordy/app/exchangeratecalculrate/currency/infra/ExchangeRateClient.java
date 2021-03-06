package com.jordy.app.exchangeratecalculrate.currency.infra;

import com.jordy.app.exchangeratecalculrate.currency.dto.ExchangeRateResponse;

public interface ExchangeRateClient {
    ExchangeRateResponse retrieveExchangeRate();
}
