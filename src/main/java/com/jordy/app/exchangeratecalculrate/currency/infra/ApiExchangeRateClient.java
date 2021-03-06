package com.jordy.app.exchangeratecalculrate.currency.infra;

import com.jordy.app.exchangeratecalculrate.currency.dto.ExchangeRateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("prod")
public class ApiExchangeRateClient implements ExchangeRateClient{
    @Override
    public ExchangeRateResponse retrieveExchangeRate() {

        return null;
    }
}
