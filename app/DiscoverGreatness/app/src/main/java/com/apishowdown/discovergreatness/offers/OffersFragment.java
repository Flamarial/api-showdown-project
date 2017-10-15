package com.apishowdown.discovergreatness.offers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.apishowdown.discovergreatness.MainActivity;
import com.apishowdown.discovergreatness.R;

public class OffersFragment extends Fragment {

    public static final String FRAGMENT_TAG = "offers";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_offers, container, false);

        ListView offersList = (ListView) rootView.findViewById(R.id.offersList);
        offersList.setAdapter(((MainActivity)getActivity()).getOfferArrayAdapter());
        offersList.setEmptyView(rootView.findViewById(R.id.emptyOffersMessage));

        return rootView;
    }
}
