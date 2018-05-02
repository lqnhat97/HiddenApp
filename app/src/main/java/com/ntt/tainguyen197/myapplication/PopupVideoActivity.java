package com.ntt.tainguyen197.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

public class PopupVideoActivity extends AppCompatActivity {

    VideoView videoView;
    String videoUrl;
    MediaController mMediaController;
    boolean isPlay = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_video);
        mMediaController = new MediaController(this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width * 1),(int) (height * 1));

        videoView = (VideoView) findViewById(R.id.popupvideo);
        Intent intent = getIntent();
        videoUrl = intent.getStringExtra("popupvideo");
        videoView.setVideoPath(videoUrl);
        videoView.setMediaController(mMediaController);
        videoView.start();

        videoView.setOnTouchListener(new OnSwipeTouchListener(PopupVideoActivity.this) {
            public void onSwipeTop() {
                finish();
            }
            public void onSwipeRight() {

            }
            public void onSwipeLeft() {

            }
            public void onSwipeBottom() {

            }

            public boolean onTouch(View v, MotionEvent event) {
                if(videoView.isPlaying()) {
                    videoView.pause();
                    mMediaController.hide();
                }
                else {
                    videoView.start();
                    mMediaController.show(0);
                }
                return false;
            }
        });
    }




}
