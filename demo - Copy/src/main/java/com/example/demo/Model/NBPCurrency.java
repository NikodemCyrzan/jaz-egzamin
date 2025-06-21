package com.example.demo.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class NBPCurrency implements Serializable {
    private String table;
    private String currency;
    private String code;
    private ArrayList<NBPRate> rates;

    public String getTable() {
        return table;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

    public ArrayList<NBPRate> getRates() {
        return rates;
    }
}
