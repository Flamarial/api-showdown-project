package com.apishowdown.discovergreatness.social;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.apishowdown.discovergreatness.R;

import java.util.ArrayList;

public class SocialGroupsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_social_groups, container, false);

        ArrayList<Friend> friends = new ArrayList<>();
        friends.add(new Friend("John", "Doe", null));
        friends.add(new Friend("John", "Doe", null));
        friends.add(new Friend("John", "Doe", null));
        friends.add(new Friend("John", "Doe", null));
        friends.add(new Friend("John", "Doe", null));

        ArrayList<FriendGroup> friendGroups = new ArrayList<>();
        friendGroups.add(new FriendGroup("Group Name", friends));
        friendGroups.add(new FriendGroup("Group Name", friends));
        friendGroups.add(new FriendGroup("Group Name", friends));
        friendGroups.add(new FriendGroup("Group Name", friends));
        friendGroups.add(new FriendGroup("Group Name", friends));

        FriendGroupArrayAdapter adapter = new FriendGroupArrayAdapter(getActivity());
        adapter.setData(friendGroups);

        ListView groupsList = (ListView) rootView.findViewById(R.id.groupsList);
        groupsList.setAdapter(adapter);

        return rootView;
    }
}
