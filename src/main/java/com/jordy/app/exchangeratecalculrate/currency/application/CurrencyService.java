package com.jordy.app.exchangeratecalculrate.currency.application;

import com.jordy.app.exchangeratecalculrate.currency.dto.CurrencyRequest;
import com.jordy.app.exchangeratecalculrate.currency.dto.CurrencyResponse;
import com.jordy.app.exchangeratecalculrate.currency.dto.ExchangeRateResponse;
import com.jordy.app.exchangeratecalculrate.currency.infra.ExchangeRateClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyService {

    final ExchangeRateClient exchangeRateClient;

    public CurrencyResponse getCalculatedCurrency() throws UnsupportedEncodingException {
        ExchangeRateResponse exchangeRateResponse = exchangeRateClient.retrieveExchangeRate();


        return null;
    }

    private ExchangeRateResponse calculateCurrencyWithExchangeRate(CurrencyRequest currencyRequest) {
        ExchangeRateResponse exchangeRateResponse = exchangeRateClient.retrieveExchangeRate();

        return null;
    }

    public CurrencyResponse retrieveExchangeRate(String currencyKinds) {
        ExchangeRateResponse exchangeRateResponse = exchangeRateClient.retrieveExchangeRate();
        BigDecimal exchangeRate = exchangeRateResponse.retrieveExchangeRate(currencyKinds);

        if(exchangeRateResponse.isSuccess() == false)
            return new CurrencyResponse();
        else if ( exchangeRate.equals(BigDecimal.ZERO))
            return new CurrencyResponse(false, exchangeRate);

        return new CurrencyResponse(true, exchangeRateResponse.retrieveExchangeRate(currencyKinds));
    }
}
