package com.apishowdown.discovergreatness.social;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.apishowdown.discovergreatness.MainActivity;
import com.apishowdown.discovergreatness.R;

public class SocialFriendsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_social_friends, container, false);


        ListView friendsList = (ListView) rootView.findViewById(R.id.friendsList);
        friendsList.setAdapter(((MainActivity)getActivity()).getFriendArrayAdapter());
        friendsList.setEmptyView(rootView.findViewById(R.id.emptyFriendsMessage));

        return rootView;
    }
}
