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

public class SocialGroupsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_social_groups, container, false);

        ListView groupsList = (ListView) rootView.findViewById(R.id.groupsList);
        groupsList.setAdapter(((MainActivity)getActivity()).getFriendGroupArrayAdapter());
        groupsList.setEmptyView(rootView.findViewById(R.id.emptyGroupsMessage));

        return rootView;
    }
}
