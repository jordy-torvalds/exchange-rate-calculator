package com.jordy.app.exchangeratecalculrate.currency.infra;

import com.jordy.app.exchangeratecalculrate.currency.dto.ExchangeRateResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
@ActiveProfiles("prod")
class ApiExchangeRateClientTest {

    @Autowired
    ExchangeRateClient exchangeRateClient;

    @Test
    @DisplayName("운영(prod) 프로필일 때 ApiExchangeRateClient가 활성화되는지 확인")
    public void checkApiExchangeRateClient(){
        assertEquals(ApiExchangeRateClient.class, exchangeRateClient.getClass());
    }

    @Test
    @DisplayName("ApiExchangeRateClient 로 조회한 값의 정상 여부 확인")
    void retrieveExchangeRate() throws UnsupportedEncodingException {
        // Given (skip)

        // When
        ExchangeRateResponse exchangeRateResponse = exchangeRateClient.retrieveExchangeRate();
        Map<String, BigDecimal> quotes = exchangeRateResponse.getQuotes();

        // Then
        assertThat(exchangeRateResponse.isSuccess()).isTrue();
        assertThat(exchangeRateResponse.getSource()).isEqualTo("USD");

        Set<String> quotesKeySet= quotes.keySet();
        assertThat(quotesKeySet.contains("USDKRW")).isTrue();
        assertThat(quotesKeySet.contains("USDJPY")).isTrue();
        assertThat(quotesKeySet.contains("USDPHP")).isTrue();
    }

    @Test
    @DisplayName("ApiExchangeRateClient 의 api 통신이 정상적으로 되는지 확인")
    void checkCommunicationWithExchangeRateServer() throws UnsupportedEncodingException{

        // Given
        ApiExchangeRateClient apiExchangeRateClient = (ApiExchangeRateClient) exchangeRateClient;

        // When
        ResponseEntity<String> responseEntity = apiExchangeRateClient.requestToCurrencyLayer();

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        log.info(responseEntity.getBody().toString());
    }
}