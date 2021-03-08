package com.jordy.app.exchangeratecalculrate.currency.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("CurrencyRestController 클래스 테스트")
class CurrencyRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @ParameterizedTest
    @ValueSource(strings = {"KRW", "JPY", "PHP"}) /* Given */
    @DisplayName("수취대상 별 환율 조회 테스트")
    void exchangeRateByCase(String currencySpecies) throws Exception {

        /* When, Then */
        mockMvc.perform(get("/api/currency/exchange-rate").param("currencySpecies", currencySpecies))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("유효하지 않은 환율 조회 요청")
    void exchangeRateByCase_IllegalArgument() throws Exception {
        // Given
        final String currencySpecies = "ZZZ";
        final int DECIMAL_POINT = 2;

        /* When, Then */
        mockMvc.perform(get("/api/currency/exchange-rate").param("currencySpecies", currencySpecies))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.amount").value(BigDecimal.ZERO.setScale(DECIMAL_POINT, RoundingMode.FLOOR)))
                .andDo(print());
    }
}