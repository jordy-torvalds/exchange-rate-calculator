package com.jordy.app.exchangeratecalculrate.currency.infra;

import com.jordy.app.exchangeratecalculrate.currency.dto.ExchangeRateResponse;

import java.io.UnsupportedEncodingException;

public interface ExchangeRateClient {
    ExchangeRateResponse retrieveExchangeRate() throws UnsupportedEncodingException;
}
