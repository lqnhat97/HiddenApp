package com.ntt.tainguyen197.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PopupImageActivity extends AppCompatActivity {

    ImageView imageView;
    ArrayList<HinhAnh> arraylist;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_image);

        Intent intent=getIntent();
        pos = intent.getIntExtra("loadpos",4);

        Bundle bundle = intent.getExtras();
         arraylist = bundle.getParcelableArrayList("mylist");
        imageView = (ImageView) findViewById(R.id.popupimg);

        Glide.with(this)
                .load(arraylist.get(pos).getUrlhinh())
                .into(imageView);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width * 1),(int) (height * 1));

        imageView.setOnTouchListener(new OnSwipeTouchListener(PopupImageActivity.this) {
            public void onSwipeTop() {
                finish();
            }
            public void onSwipeRight() {
                pos = pos - 1;
                Glide.with(PopupImageActivity.this)
                        .load(arraylist.get(pos).getUrlhinh())
                        .into(imageView);
            }
            public void onSwipeLeft() {
                pos = pos + 1;
                Glide.with(PopupImageActivity.this)
                        .load(arraylist.get(pos).getUrlhinh())
                        .into(imageView);
            }
            public void onSwipeBottom() {

            }

            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
    }
}
