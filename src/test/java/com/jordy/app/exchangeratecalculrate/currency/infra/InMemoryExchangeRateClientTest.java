package com.jordy.app.exchangeratecalculrate.currency.infra;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("dev")
class InMemoryExchangeRateClientTest {
    @Autowired
    ExchangeRateClient exchangeRateClient;

    @Test
    @DisplayName("테스트(dev) 프로필일 때 InMemoryExchangeRateClient가 활성화 되는지 테스트")
    public void checkInMemoryExchangeRateClient(){
        assertEquals(exchangeRateClient.getClass(), InMemoryExchangeRateClient.class);
    }
}