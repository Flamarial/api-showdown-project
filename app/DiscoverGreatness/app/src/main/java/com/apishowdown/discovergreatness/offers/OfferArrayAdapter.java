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

import java.text.SimpleDateFormat;
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
            viewHolder.offerMessage = (TextView) convertView.findViewById(R.id.offerMessage);
            viewHolder.offerExpirationDate = (TextView) convertView.findViewById(R.id.offerExpirationDate);
            convertView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) convertView.getTag();

        holder.merchantName.setText(offers.get(position).getMerchantName());
        holder.offerMessage.setText(offers.get(position).getMessage());

        String date = new SimpleDateFormat("MMMM dd, yyyy", context.getResources().getConfiguration().locale).format(offers.get(position).getExpirationDate());
        holder.offerExpirationDate.setText(context.getString(R.string.expiration_date_message, date));

        return convertView;
    }

    private static class ViewHolder {
        TextView merchantName;
        TextView offerMessage;
        TextView offerExpirationDate;
    }
}
