package com.apishowdown.discovergreatness.social;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.apishowdown.discovergreatness.MainActivity;
import com.apishowdown.discovergreatness.R;
import com.apishowdown.discovergreatness.util.PalRequestQueueSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

import static com.apishowdown.discovergreatness.util.WalletUtil.BASE_URL;
import static com.apishowdown.discovergreatness.util.WalletUtil.WALLET_ID;

public class SocialFragment extends Fragment {

    private static final String LOG_TAG = SocialFragment.class.getName();
    public static final String FRAGMENT_TAG = "social";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_social, container, false);

        SocialPagerAdapter adapter = new SocialPagerAdapter(getChildFragmentManager(), getActivity());

        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.pager);
        PagerTabStrip pagerTabStrip = (PagerTabStrip) rootView.findViewById(R.id.pagerTabStrip);
        pagerTabStrip.setTabIndicatorColor(ContextCompat.getColor(getActivity(), R.color.yellow));
        pagerTabStrip.setTextColor(Color.WHITE);

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_social, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addGroupButton:
                return true;
            case R.id.addFriendButton:
                final View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_friend, null);
                new AlertDialog.Builder(getActivity()).setTitle(R.string.add_friend)
                        .setView(dialogView)
                        .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText firstNameInput = (EditText) dialogView.findViewById(R.id.firstNameInput);
                                EditText lastNameInput = (EditText) dialogView.findViewById(R.id.lastNameInput);

                                String url = BASE_URL + "/addFriend";

                                HashMap<String, String> map = new HashMap<>();
                                map.put("first_name", firstNameInput.getText().toString());
                                map.put("last_name", lastNameInput.getText().toString());
                                map.put("wallet_id", WALLET_ID);

                                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(map), new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Log.i(LOG_TAG, "Got response after POSTing add friend request");

                                        if (response != null) {
                                            try {
                                                if (response.has("error")) {
                                                    String error = response.getString("error");
                                                    Log.e(LOG_TAG, "Error adding friend: " + error);
                                                    Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.add_friend_error_message, Snackbar.LENGTH_SHORT).show();
                                                } else {
                                                    String firstName = response.getString("first_name");
                                                    String lastName = response.getString("last_name");
                                                    String imageUrl = response.getString("image_url");

                                                    Log.i(LOG_TAG, "Successfully added friend");
                                                    MainActivity mainActivity = ((MainActivity)getActivity());
                                                    mainActivity.getFriends().add(new Friend(firstName, lastName, imageUrl));
                                                    mainActivity.getFriendArrayAdapter().notifyDataSetChanged();
                                                }

                                                for (Iterator<String> keys = response.keys(); keys.hasNext(); ) {
                                                    String key = keys.next();
                                                    Log.i(LOG_TAG, key + " : " + response.get(key).toString());
                                                }
                                            } catch (JSONException e) {
                                                Log.e(LOG_TAG, "Error adding friend: " + e.getMessage());
                                                Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.add_friend_error_message, Snackbar.LENGTH_SHORT).show();
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Log.e(LOG_TAG, "Error adding friend: " + error.getMessage());
                                        Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.add_friend_error_message, Snackbar.LENGTH_SHORT).show();
                                        error.printStackTrace();
                                    }
                                });

                                PalRequestQueueSingleton.getInstance(getActivity()).addToRequestQueue(request);
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private static class SocialPagerAdapter extends FragmentPagerAdapter {
        private static final int NUM_ITEMS = 2;

        private Context context;

        private SocialPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new SocialGroupsFragment();
                case 1:
                    return new SocialFriendsFragment();
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return context.getResources().getString(R.string.groups);
                case 1:
                    return context.getResources().getString(R.string.friends);
                default:
                    return null;
            }
        }
    }
}
