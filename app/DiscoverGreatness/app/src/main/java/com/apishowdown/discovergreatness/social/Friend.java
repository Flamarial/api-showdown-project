package com.apishowdown.discovergreatness.social;

public class Friend {
    private String firstName;
    private String lastName;
    private String imageUrl;

    public Friend(String firstName, String lastName, String imageUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageUrl = imageUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
