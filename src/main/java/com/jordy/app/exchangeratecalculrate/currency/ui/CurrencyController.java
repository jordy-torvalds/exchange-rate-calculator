package com.jordy.app.exchangeratecalculrate.currency.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CurrencyController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("currency", "20000");
        return "index";
    }
}
