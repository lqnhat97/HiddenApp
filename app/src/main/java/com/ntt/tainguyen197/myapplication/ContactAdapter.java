package com.ntt.tainguyen197.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by NGUYENTRUNGTAI on 4/27/2018.
 */

public class ContactAdapter extends BaseAdapter {

    private Context context;
    private List<Contact> contactslist;

    public ContactAdapter(Context context, List<Contact> contactslist) {
        this.context = context;
        this.contactslist = contactslist;
    }

    @Override
    public int getCount() {
        return contactslist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custom_layout_contact,null);

        TextView tvname = (TextView) convertView.findViewById(R.id.tvnamecontact);
        TextView tvstd = (TextView) convertView.findViewById(R.id.tvsdtcontac);

        tvname.setText(contactslist.get(position).getTen());
        tvstd.setText(contactslist.get(position).getSDT());

        return  convertView;
    }
}
