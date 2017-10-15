package com.apishowdown.discovergreatness.offers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.apishowdown.discovergreatness.R;

import java.util.ArrayList;
import java.util.Date;

public class OffersFragment extends Fragment {

    public static final String FRAGMENT_TAG = "offers";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_offers, container, false);

        ArrayList<Offer> offers = new ArrayList<>();
        offers.add(new Offer("Merchant", "Save 100% off!", new Date()));
        offers.add(new Offer("Lorem Ipsum", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", new Date()));

        OfferArrayAdapter adapter = new OfferArrayAdapter(getActivity());
        adapter.setData(offers);

        ListView offersList = (ListView) rootView.findViewById(R.id.offersList);
        offersList.setAdapter(adapter);
        offersList.setEmptyView(rootView.findViewById(R.id.emptyOffersMessage));

        return rootView;
    }
}
