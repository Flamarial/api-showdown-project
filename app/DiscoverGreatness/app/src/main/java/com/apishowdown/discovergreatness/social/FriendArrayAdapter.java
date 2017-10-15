package com.apishowdown.discovergreatness.social;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apishowdown.discovergreatness.R;

import java.util.ArrayList;

public class FriendArrayAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Friend> friends;

    public FriendArrayAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Friend> friends) {
        this.friends = friends;
    }

    @Override
    public int getCount() {
        return friends.size();
    }

    @Override
    public Object getItem(int pos) {
        return friends.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_friend, null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.friendName = (TextView) convertView.findViewById(R.id.friendName);
            convertView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) convertView.getTag();

        holder.friendName.setText(friends.get(position).getFirstName() + " " + friends.get(position).getLastName());

        return convertView;
    }

    private static class ViewHolder {
        TextView friendName;
    }
}
