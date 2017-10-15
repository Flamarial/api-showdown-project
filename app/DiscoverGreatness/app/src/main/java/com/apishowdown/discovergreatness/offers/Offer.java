package com.apishowdown.discovergreatness.offers;

import java.util.Date;

public class Offer {
    private String merchantName;
    private String message;
    private Date expirationDate;

    public Offer(String merchantName, String message, Date expirationDate) {
        this.merchantName = merchantName;
        this.message = message;
        this.expirationDate = expirationDate;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getMessage() {
        return message;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }
}
