package com.apishowdown.discovergreatness.creditcard;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.apishowdown.discovergreatness.R;

import java.util.ArrayList;

import io.card.payment.CardIOActivity;
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class CreditCardSelectionFragment extends Fragment {

    public static final String FRAGMENT_TAG = "creditCardSelection";
    public static final int CREDIT_CARD_SCAN_REQUEST_CODE = 9001;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_credit_card_selection, container, false);

        CreditCardArrayAdapter arrayAdapter = new CreditCardArrayAdapter(getActivity());

        FeatureCoverFlow coverFlow = (FeatureCoverFlow) rootView.findViewById(R.id.coverflow);
        coverFlow.setAdapter(arrayAdapter);

        ArrayList<CreditCard> creditCards = new ArrayList<>();
        creditCards.add(new CreditCard("0123 4567 8900 0000", "01/20"));
        creditCards.add(new CreditCard("1111 2222 3333 4444", "01/21"));
        creditCards.add(new CreditCard("8888 8888 8888 8888", "01/22"));
        creditCards.add(new CreditCard("1234 1234 1234 1234", "01/23"));

        arrayAdapter.setData(creditCards);

        coverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO CoverFlow item clicked
            }
        });

        coverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                //TODO CoverFlow stopped to position
            }

            @Override
            public void onScrolling() {
                //TODO CoverFlow began scrolling
            }
        });

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.aboutButton:
                new AlertDialog.Builder(getActivity()).setTitle(R.string.about_this_app)
                        .setView(R.layout.dialog_about)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                return true;
            case R.id.addCreditCardButton:
                Intent scanIntent = new Intent(getActivity(), CardIOActivity.class);
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true); // default: false
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false); // default: false
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false); // default: false
                startActivityForResult(scanIntent, CREDIT_CARD_SCAN_REQUEST_CODE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREDIT_CARD_SCAN_REQUEST_CODE) {
            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                // Use mock data
                // TODO
            }
        }
    }
}
