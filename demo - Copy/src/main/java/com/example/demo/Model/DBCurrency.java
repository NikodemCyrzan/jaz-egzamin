package com.example.demo.Model;

public class DBCurrency {
    private String code;
    private String startDate;
    private String endDate;
    private double rate;
    private String requestDate;
    private String requestHour;

    public DBCurrency(String code, String startDate, String endDate, double rate, String requestDate, String requestHour) {
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rate = rate;
        this.requestDate = requestDate;
        this.requestHour = requestHour;
    }
}
