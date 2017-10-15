package com.apishowdown.discovergreatness.social;

import java.util.List;

public class FriendGroup {
    private String name;
    private List<Friend> friends;

    public FriendGroup(String name, List<Friend> friends) {
        this.name = name;
        this.friends = friends;
    }

    public String getName() {
        return name;
    }

    public List<Friend> getFriends() {
        return friends;
    }
}
