package com.apishowdown.discovergreatness;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class SocialFriendsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_social_friends, container, false);

        ArrayList<Friend> friends = new ArrayList<>();
        friends.add(new Friend("Admiral", "Meowmeow", "https://i.imgur.com/T5YiQwO.jpg"));
        friends.add(new Friend("Jane", "Smith", "invalid URL"));
        friends.add(new Friend("John", "Doe", null));
        friends.add(new Friend("John", "Doe", null));
        friends.add(new Friend("John", "Doe", null));
        friends.add(new Friend("John", "Doe", null));
        friends.add(new Friend("John", "Doe", null));
        friends.add(new Friend("John", "Doe", null));
        friends.add(new Friend("John", "Doe", null));
        friends.add(new Friend("John", "Doe", null));
        friends.add(new Friend("John", "Doe", null));
        friends.add(new Friend("John", "Doe", null));
        friends.add(new Friend("John", "Doe", null));
        friends.add(new Friend("John", "Doe", null));
        friends.add(new Friend("John", "Doe", null));
        friends.add(new Friend("John", "Doe", null));

        FriendArrayAdapter adapter = new FriendArrayAdapter(getActivity());
        adapter.setData(friends);

        ListView friendsList = (ListView) rootView.findViewById(R.id.friendsList);
        friendsList.setAdapter(adapter);

        return rootView;
    }
}
