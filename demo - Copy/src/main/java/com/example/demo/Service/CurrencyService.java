package com.example.demo.Service;

import com.example.demo.Model.Currency;
import com.example.demo.Model.DBCurrency;
import com.example.demo.Model.NBPCurrency;
import com.example.demo.Model.NBPRate;
import com.example.demo.Repository.CurrencyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

@Service
public class CurrencyService {
    private CurrencyRepository currencyRepository;

    private final String apiUrl = "https://api.nbp.pl/api/exchangerates/rates";

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public Currency getAvgCurrencyValue(String code, String startDate, String endDate) {
        RestTemplate restTemplate = new RestTemplate();
        NBPCurrency currency;

        try {
            currency = restTemplate.getForObject(
                    this.apiUrl + "/A/{code}/{startDate}/{endDate}?format=json",
                    NBPCurrency.class,
                    code,
                    startDate,
                    endDate);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Currency not found");
            } else if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request to Currency Service");
            } else {
                throw new ResponseStatusException(e.getStatusCode(), e.getResponseBodyAsString());
            }
        } catch (HttpServerErrorException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Currency Service internal error");
        } catch (ResourceAccessException e) {
            throw new ResponseStatusException(HttpStatus.GATEWAY_TIMEOUT, "Currency Service not reachable");
        }

        double sum = 0;
        assert currency != null;
        ArrayList<NBPRate> rates = currency.getRates();

        for (NBPRate rate : rates) {
            sum += rate.getMid();
        }

        LocalDate currentDate = LocalDate.now();
        DBCurrency dbCurrency = new DBCurrency(
                currency.getCode(),
                startDate,
                endDate,
                sum / rates.size(),
                currentDate.toString(),
                String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)));

        currencyRepository.addCurrency(dbCurrency);

        return new Currency(currency.getCode(), sum / rates.size());
    }
}
