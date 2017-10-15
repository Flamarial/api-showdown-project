package com.apishowdown.discovergreatness.offers;

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

public class OfferArrayAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Offer> offers;

    public OfferArrayAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public int getCount() {
        return offers.size();
    }

    @Override
    public Object getItem(int pos) {
        return offers.get(pos);
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
            convertView = inflater.inflate(R.layout.item_offer, null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.merchantName = (TextView) convertView.findViewById(R.id.merchantName);
            viewHolder.merchantAddress = (TextView) convertView.findViewById(R.id.merchantAddress);
            viewHolder.offerMessage = (TextView) convertView.findViewById(R.id.offerMessage);
            viewHolder.offerExpirationDate = (TextView) convertView.findViewById(R.id.offerExpirationDate);
            convertView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) convertView.getTag();

        holder.merchantName.setText(offers.get(position).getMerchantName());
        holder.merchantAddress.setText(offers.get(position).getMerchantAddress());
        holder.offerMessage.setText(offers.get(position).getMessage());
        holder.offerExpirationDate.setText(offers.get(position).getExpirationMessage());

        return convertView;
    }

    private static class ViewHolder {
        TextView merchantName;
        TextView merchantAddress;
        TextView offerMessage;
        TextView offerExpirationDate;
    }
}
