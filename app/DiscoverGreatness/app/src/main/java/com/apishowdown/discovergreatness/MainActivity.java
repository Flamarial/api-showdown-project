package com.apishowdown.discovergreatness;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.apishowdown.discovergreatness.creditcard.CreditCardSelectionFragment;
import com.apishowdown.discovergreatness.gcm.PalGcmListenerService;
import com.apishowdown.discovergreatness.gcm.RegistrationIntentService;
import com.apishowdown.discovergreatness.offers.Offer;
import com.apishowdown.discovergreatness.offers.OfferArrayAdapter;
import com.apishowdown.discovergreatness.offers.OffersFragment;
import com.apishowdown.discovergreatness.social.Friend;
import com.apishowdown.discovergreatness.social.FriendArrayAdapter;
import com.apishowdown.discovergreatness.social.FriendGroup;
import com.apishowdown.discovergreatness.social.FriendGroupArrayAdapter;
import com.apishowdown.discovergreatness.social.SocialFragment;
import com.apishowdown.discovergreatness.util.ImageStorage;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.ArrayList;

import static com.apishowdown.discovergreatness.gcm.PalGcmListenerService.KEY_OPEN_OFFERS;

public class MainActivity extends AppCompatActivity {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private ImageStorage imageStorage = new ImageStorage();
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private TextView homeButton;
    private TextView offersButton;
    private TextView socialButton;

    private ArrayList<Friend> friends = new ArrayList<>();
    private FriendArrayAdapter friendArrayAdapter;

    private ArrayList<FriendGroup> friendGroups = new ArrayList<>();
    private FriendGroupArrayAdapter friendGroupArrayAdapter;

    private ArrayList<Offer> offers = new ArrayList<>();
    private OfferArrayAdapter offerArrayAdapter;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String merchantName = intent.getStringExtra(PalGcmListenerService.KEY_MERCHANT_NAME);
            String merchantAddress = intent.getStringExtra(PalGcmListenerService.KEY_MERCHANT_ADDRESS);
            String offerMessage = intent.getStringExtra(PalGcmListenerService.KEY_OFFER_MESSAGE);
            String expirationMessage = intent.getStringExtra(PalGcmListenerService.KEY_EXPIRATION_MESSAGE);
            offers.add(new Offer(merchantName, merchantAddress, offerMessage, expirationMessage));
            offerArrayAdapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        friends.add(new Friend("Admiral", "Meowmeow", "https://i.imgur.com/T5YiQwO.jpg"));
        friends.add(new Friend("Captain", "Whiskers", "https://i.imgur.com/RUgjaNk.jpg"));
        friends.add(new Friend("Lieutenant", "Fluffers", "https://i.imgur.com/Y0dztKF.jpg"));
        friends.add(new Friend("General", "Paws", "https://i.imgur.com/3gyCiwy.jpg"));
        friends.add(new Friend("Jane", "Smith", null));
        friends.add(new Friend("John", "Doe", null));
        friendArrayAdapter = new FriendArrayAdapter(this, imageStorage);
        friendArrayAdapter.setData(friends);

        ArrayList<Friend> catsGroup = new ArrayList<>();
        catsGroup.add(new Friend("Admiral", "Meowmeow", "https://i.imgur.com/T5YiQwO.jpg"));
        catsGroup.add(new Friend("Captain", "Whiskers", "https://i.imgur.com/RUgjaNk.jpg"));
        friendGroups.add(new FriendGroup("Navy Kittens", catsGroup));
        friendGroupArrayAdapter = new FriendGroupArrayAdapter(this);
        friendGroupArrayAdapter.setData(friendGroups);

        ArrayList<Friend> dogsGroup = new ArrayList<>();
        dogsGroup.add(new Friend("Lieutenant", "Fluffers", "https://i.imgur.com/Y0dztKF.jpg"));
        dogsGroup.add(new Friend("General", "Paws", "https://i.imgur.com/3gyCiwy.jpg"));
        friendGroups.add(new FriendGroup("Army Puppies", dogsGroup));
        friendGroupArrayAdapter = new FriendGroupArrayAdapter(this);
        friendGroupArrayAdapter.setData(friendGroups);

        offers.add(new Offer("Merchant Name", "123 Address St", "Save 100% off!", "Expires October 16, 2017"));
        offers.add(new Offer("Lorem Ipsum", "1600 Pennsylvania Ave", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Expires September 21, 2020"));

        offerArrayAdapter = new OfferArrayAdapter(this);
        offerArrayAdapter.setData(offers);

        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            startService(new Intent(this, RegistrationIntentService.class));
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.actionBar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        homeButton = (TextView) findViewById(R.id.homeButton);
        offersButton = (TextView) findViewById(R.id.offersButton);
        socialButton = (TextView) findViewById(R.id.socialButton);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(new CreditCardSelectionFragment(), CreditCardSelectionFragment.FRAGMENT_TAG);
            }
        });
        offersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(new OffersFragment(), OffersFragment.FRAGMENT_TAG);
            }
        });
        socialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(new SocialFragment(), SocialFragment.FRAGMENT_TAG);
            }
        });

        if (getIntent().getBooleanExtra(KEY_OPEN_OFFERS, false)) {
            navigateToFragment(new OffersFragment(), OffersFragment.FRAGMENT_TAG);
        } else {
            navigateToFragment(new CreditCardSelectionFragment(), CreditCardSelectionFragment.FRAGMENT_TAG);
        }

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter(PalGcmListenerService.BROADCAST_OFFER));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void navigateToFragment(Fragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainer, fragment, tag)
                .addToBackStack(null)
                .commit();
        drawerLayout.closeDrawers();
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                finish();
            }
            return false;
        }
        return true;
    }

    public ImageStorage getImageStorage() {
        return imageStorage;
    }

    public ArrayList<Friend> getFriends() {
        return friends;
    }

    public ArrayList<FriendGroup> getFriendGroups() {
        return friendGroups;
    }

    public FriendArrayAdapter getFriendArrayAdapter() {
        return friendArrayAdapter;
    }

    public FriendGroupArrayAdapter getFriendGroupArrayAdapter() {
        return friendGroupArrayAdapter;
    }

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public OfferArrayAdapter getOfferArrayAdapter() {
        return offerArrayAdapter;
    }
}
