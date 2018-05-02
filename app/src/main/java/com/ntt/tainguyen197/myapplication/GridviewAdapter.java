package com.ntt.tainguyen197.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NGUYENTRUNGTAI on 4/16/2018.
 */

public class GridviewAdapter extends BaseAdapter {

    private List<HinhAnh> hinhAnhList;
    private Context context;

    public GridviewAdapter(ArrayList<HinhAnh> hinhAnhList, Context context) {
        this.hinhAnhList = hinhAnhList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return hinhAnhList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custom_layout_gridview,null);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imgview);

        Glide.with(context)
                .load(hinhAnhList.get(position).getUrlhinh())
                .into(imageView);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = position;
                Intent intent = new Intent(context, PopupImageActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra("loadpos",position);
                bundle.putParcelableArrayList("mylist", (ArrayList<? extends Parcelable>) hinhAnhList);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        return convertView;

        /*ImageView picturesView;
        if (convertView == null) {
            picturesView = new ImageView(context);
            picturesView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            picturesView
                    .setLayoutParams(new GridView.LayoutParams(270, 270));

        } else {
            picturesView = (ImageView) convertView;
        }

        Glide.with(context).load(hinhAnhList.get(position))
                .into(imageView);

        return picturesView;*/
    }
}