package com.example.demo.Model;

public class Currency {
    private final String code;
    private final double rates;

    public String getCode() {
        return code;
    }

    public double getRates() {
        return rates;
    }

    public Currency(String code, double rates) {
        this.code = code;
        this.rates = rates;
    }
}
