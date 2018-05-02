package com.ntt.tainguyen197.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.List;

/**
 * Created by NGUYENTRUNGTAI on 4/30/2018.
 */

public class AdapterVideo extends BaseAdapter {
    public AdapterVideo(List<Video> videoList, Context context) {
        this.videoList = videoList;
        this.context = context;
    }

    private ImageView imgvideo;
    private List<Video> videoList;
    private Context context;

    @Override
    public int getCount() {
        return videoList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custom_layout_video,null);

       imgvideo = (ImageView) convertView.findViewById(R.id.imgvideo);


        Bitmap thumb = ThumbnailUtils.createVideoThumbnail(videoList.get(position).getUrl(),
                MediaStore.Video.Thumbnails.MINI_KIND);

        BitmapDrawable bitmapDrawable = new BitmapDrawable(thumb);
        imgvideo.setBackgroundDrawable(bitmapDrawable);

        imgvideo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(context,PopupVideoActivity.class);
                intent.putExtra("popupvideo",videoList.get(position).getUrl());
                context.startActivity(intent);
                return false;
            }
        });

        return convertView;
    }
}
