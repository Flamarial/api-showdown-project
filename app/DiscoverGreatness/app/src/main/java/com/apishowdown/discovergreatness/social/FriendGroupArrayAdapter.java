package com.apishowdown.discovergreatness.social;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apishowdown.discovergreatness.R;

import java.util.ArrayList;
import java.util.List;

public class FriendGroupArrayAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<FriendGroup> friendGroups;

    public FriendGroupArrayAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<FriendGroup> friends) {
        this.friendGroups = friends;
    }

    @Override
    public int getCount() {
        return friendGroups.size();
    }

    @Override
    public Object getItem(int pos) {
        return friendGroups.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_friend_group, null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.groupName = (TextView) convertView.findViewById(R.id.groupName);
            viewHolder.groupUsersList = (LinearLayout) convertView.findViewById(R.id.groupUsersList);
            convertView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) convertView.getTag();

        holder.groupName.setText(friendGroups.get(position).getName());

        holder.groupUsersList.removeAllViews();
        List<Friend> friends = friendGroups.get(position).getFriends();
        if (friends != null) {
            for (int i = 0; i < friends.size(); i++) {
                Friend friend = friends.get(i);
                View userView = inflater.inflate(R.layout.item_group_user, null);
                TextView groupUserName = (TextView) userView.findViewById(R.id.groupUserName);
                groupUserName.setText(friend.getFirstName() + " " + friend.getLastName());
                holder.groupUsersList.addView(userView);
            }
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView groupName;
        LinearLayout groupUsersList;
    }
}
