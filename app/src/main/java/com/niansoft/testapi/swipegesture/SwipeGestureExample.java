package com.niansoft.testapi.swipegesture;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.niansoft.testapi.R;

public class SwipeGestureExample extends AppCompatActivity {
    ImageView mSwipeGestureImageView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_gesture_example);
        mSwipeGestureImageView = findViewById(R.id.swipe_gesture_image_view);
        mSwipeGestureImageView.setOnTouchListener(new OnSwipeTouchListener(SwipeGestureExample.this) {
            public void onSwipeTop() {
                Toast.makeText(SwipeGestureExample.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                Toast.makeText(SwipeGestureExample.this, "right", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft() {
                Toast.makeText(SwipeGestureExample.this, "left", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeBottom() {
                Toast.makeText(SwipeGestureExample.this, "bottom", Toast.LENGTH_SHORT).show();
            }

        });
    }

}
