package pl.sda.currencyexchanger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.currencyexchanger.model.ExchangeResult;
import pl.sda.currencyexchanger.service.ICurrencyExchangeService;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
public class ExchangeController {

    private ICurrencyExchangeService exchangeService;

    @Autowired
    public ExchangeController(ICurrencyExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/exchange/{value}/{date}")
    public ResponseEntity<ExchangeResult> getExchangedValue(@PathVariable(name = "value") BigDecimal value,
                                                            @PathVariable(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate exchangeDate) {

        ExchangeResult exchangeResult = exchangeService.exchange(value, exchangeDate);
        return new ResponseEntity<>(exchangeResult, exchangeResult.getHttpStatus());
    }
}
