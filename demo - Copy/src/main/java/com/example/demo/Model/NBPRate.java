package com.example.demo.Model;

import java.io.Serializable;

public class NBPRate implements Serializable {
    private String no;
    private String effectiveDate;
    private double mid;

    public String getNo() {
        return no;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public double getMid() {
        return mid;
    }
}
