package com.jordy.app.exchangeratecalculrate.currency.infra;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("prod")
class ApiExchangeRateClientTest {

    @Autowired
    ExchangeRateClient exchangeRateClient;

    @Test
    @DisplayName("운영(prod) 프로필일 때 ApiExchnageRateClient가 활성화되는지 확인")
    public void checkApiExchangeRateClient(){
        assertEquals(exchangeRateClient.getClass(), ApiExchangeRateClient.class);
    }


}