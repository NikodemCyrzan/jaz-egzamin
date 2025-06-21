package com.example.demo.Service;

import com.example.demo.Model.Currency;
import com.example.demo.Model.NBPCurrency;
import com.example.demo.Model.NBPRate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class CurrencyService {
    private final String apiUrl = "https://api.nbp.pl/api/exchangerates/rates";

    public Currency getAvgCurrencyValue(String code, String startDate, String endDate) {
        RestTemplate restTemplate = new RestTemplate();

        NBPCurrency currency = restTemplate.getForObject(
            this.apiUrl + "/{code}/{startDate}/{endDate}?format=json",
                NBPCurrency.class,
                code,
                startDate,
                endDate);

        double sum = 0;
        assert currency != null;
        ArrayList<NBPRate> rates = currency.getRates();

        for (NBPRate rate : rates) {
            sum += rate.getMid();
        }

        return new Currency(currency.getCode(), sum / rates.size());
    }
}
