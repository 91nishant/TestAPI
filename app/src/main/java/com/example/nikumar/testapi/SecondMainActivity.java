package com.example.nikumar.testapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nikumar.testapi.googlemap.GoogleMapExample;

import static com.example.nikumar.testapi.commonutility.CustomLogger.printVerbose;

public class SecondMainActivity extends AppCompatActivity {
    private static final String TAG = "SecondMainActivity";
    Button mGoogleMap, mFingerPrint, mSwipeGesture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        printVerbose(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_second);
        init();
        registerOnClickListeners();
    }

    private void init() {
        mGoogleMap = findViewById(R.id.google_map);
        mFingerPrint = findViewById(R.id.finger_print);
        mSwipeGesture = findViewById(R.id.swipe_gesture);
    }

    private void registerOnClickListeners() {
        UiListenersImpl mUiListenersImpl = new UiListenersImpl();
        mGoogleMap.setOnClickListener(mUiListenersImpl);
        mFingerPrint.setOnClickListener(mUiListenersImpl);
        mSwipeGesture.setOnClickListener(mUiListenersImpl);
    }

    class UiListenersImpl implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.google_map:
                    printVerbose(TAG, "Button Clicked :", getResources().getString(R.string.google_map));
                    startActivity(new Intent(SecondMainActivity.this, GoogleMapExample.class));
                    break;
            }
        }
    }
}
