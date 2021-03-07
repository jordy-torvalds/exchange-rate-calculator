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
    @DisplayName("ApiExchangeRateClient로 정상적으로 조회되는지 확인")
    void checkCommunicationWithExchangeRateServer() throws UnsupportedEncodingException{

        // Given (skip)
        ApiExchangeRateClient apiExchangeRateClient = (ApiExchangeRateClient) exchangeRateClient;

        // When
        ResponseEntity<ExchangeRateResponse> responseEntity = apiExchangeRateClient.requestToCurrencyLayer();

        // Then
        assertThat(responseEntity.getBody().isSuccess()).isTrue();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        log.info(responseEntity.getBody().toString());
    }
}