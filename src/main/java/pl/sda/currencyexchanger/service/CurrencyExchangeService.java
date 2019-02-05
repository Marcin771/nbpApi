package pl.sda.currencyexchanger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.sda.currencyexchanger.model.Currency;
import pl.sda.currencyexchanger.exchanger.NbpExchangeRateDownloader;
import pl.sda.currencyexchanger.exchanger.NbpExchangeRateResult;
import pl.sda.currencyexchanger.model.ExchangeResult;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static pl.sda.currencyexchanger.exchanger.NbpExchangeRateExecutionStatus.NEGATIVE;
import static pl.sda.currencyexchanger.exchanger.NbpExchangeRateExecutionStatus.POSITIVE;

@Service
public class CurrencyExchangeService implements ICurrencyExchangeService {

    private NbpExchangeRateDownloader downloader;

    @Autowired
    public CurrencyExchangeService(NbpExchangeRateDownloader downloader) {
        this.downloader = downloader;
    }

    @Override
    public ExchangeResult exchange(BigDecimal value, LocalDate date) {
        NbpExchangeRateResult rateResult = downloader.download(date, Currency.EURO);
        ExchangeResult exchangeResult = new ExchangeResult();
        if (rateResult.getExecutionStatus() == POSITIVE) {
            BigDecimal calculatedValue = value.divide(rateResult.getExchangeRate(), RoundingMode.HALF_UP);
            exchangeResult.setValue(calculatedValue);
            exchangeResult.setHttpStatus(HttpStatus.OK);
        }
        if (rateResult.getExecutionStatus() == NEGATIVE) {
            exchangeResult.setErrorMessage(rateResult.getErrorMessage());
            exchangeResult.setHttpStatus(HttpStatus.BAD_REQUEST);
        }
        return exchangeResult;
    }
}
