package com.example.demo.Repository;

import com.example.demo.Model.DBCurrency;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class CurrencyRepository {
    private final ArrayList<DBCurrency> currencies = new ArrayList<>();

    public void addCurrency(DBCurrency currency) {
        currencies.add(currency);
    }
}
