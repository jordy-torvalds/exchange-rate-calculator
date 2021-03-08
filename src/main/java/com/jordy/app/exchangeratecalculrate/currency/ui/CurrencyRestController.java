package com.jordy.app.exchangeratecalculrate.currency.ui;

import com.jordy.app.exchangeratecalculrate.currency.application.CurrencyService;
import com.jordy.app.exchangeratecalculrate.currency.dto.CurrencyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/currency")
@RestController
@RequiredArgsConstructor
public class CurrencyRestController {

    private final CurrencyService currencyService;

    @GetMapping("/exchange-rate")
    public CurrencyResponse exchangeRate(@RequestParam(value = "currencySpecies", required = false) String currencySpecies) {
        return currencyService.retrieveExchangeRate(currencySpecies);
    }
}
