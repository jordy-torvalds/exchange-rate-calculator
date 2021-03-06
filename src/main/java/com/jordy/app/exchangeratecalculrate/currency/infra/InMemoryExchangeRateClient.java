package com.jordy.app.exchangeratecalculrate.currency.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jordy.app.exchangeratecalculrate.currency.dto.ExchangeRateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@Profile("dev")
public class InMemoryExchangeRateClient implements ExchangeRateClient{

    @Autowired
    ObjectMapper objectMapper;


    @Override
    public ExchangeRateResponse retrieveExchangeRate() {
        final String exchangeRateResult = getExchangeRateResult();
        ExchangeRateResponse exchangeRateResponse;
        try {
            exchangeRateResponse = objectMapper.readValue(exchangeRateResult, ExchangeRateResponse.class);

        } catch (JsonProcessingException e) {
            log.info(e.getMessage());
            exchangeRateResponse = new ExchangeRateResponse();
        }
        return exchangeRateResponse;
    }


    private String getExchangeRateResult() {
        return "{\n" +
                "  \"success\": true,\n" +
                "  \"terms\": \"https://currencylayer.com/terms\",\n" +
                "  \"privacy\": \"https://currencylayer.com/privacy\",\n" +
                "  \"timestamp\": 1614996727,\n" +
                "  \"source\": \"USD\",\n" +
                "  \"quotes\": {\n" +
                "    \"USDKRW\": 1128.225039,\n" +
                "    \"USDJPY\": 108.40504,\n" +
                "    \"USDPHP\": 48.617417\n" +
                "  }\n" +
                "}";
    }
}

