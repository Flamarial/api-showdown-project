package com.apishowdown.discovergreatness.offers;

public class Offer {
    private String merchantName;
    private String merchantAddress;
    private String message;
    private String expirationMessage;

    public Offer(String merchantName, String merchantAddress, String message, String expirationMessage) {
        this.merchantName = merchantName;
        this.merchantAddress = merchantAddress;
        this.message = message;
        this.expirationMessage = expirationMessage;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public String getMessage() {
        return message;
    }

    public String getExpirationMessage() {
        return expirationMessage;
    }
}
