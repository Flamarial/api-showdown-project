package com.apishowdown.discovergreatness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CreditCardArrayAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CreditCard> creditCards = new ArrayList<>();

    public CreditCardArrayAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    @Override
    public int getCount() {
        return creditCards.size();
    }

    @Override
    public Object getItem(int pos) {
        return creditCards.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_credit_card, null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.creditCardNumber = (TextView) convertView.findViewById(R.id.creditCardNumber);
            viewHolder.creditCardExpirationDate = (TextView) convertView.findViewById(R.id.creditCardExpirationDate);
            convertView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) convertView.getTag();

        holder.creditCardNumber.setText(creditCards.get(position).getNumber());
        holder.creditCardExpirationDate.setText(creditCards.get(position).getExpirationDate());

        return convertView;
    }

    private static class ViewHolder {
        TextView creditCardNumber;
        TextView creditCardExpirationDate;
    }
}