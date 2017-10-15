package com.apishowdown.discovergreatness.creditcard;

public class CreditCard {
    private String number;
    private String expirationDate;

    public CreditCard(String number, String expirationDate){
        this.number = number;
        this.expirationDate = expirationDate;
    }

    public String getNumber() {
        return number;
    }

    public String getExpirationDate() {
        return expirationDate;
    }
}