package pl.sda.currencyexchanger.service;

import pl.sda.currencyexchanger.model.ExchangeResult;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ICurrencyExchangeService {
    ExchangeResult exchange(BigDecimal value, LocalDate date);
}
