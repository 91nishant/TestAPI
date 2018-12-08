package com.example.nikumar.testapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nikumar.testapi.activity.ActivityExample;
import com.example.nikumar.testapi.asynctask.AsyncTaskExample;
import com.example.nikumar.testapi.boundservice.BoundServiceExample;
import com.example.nikumar.testapi.fingerprint.FingerPrintExample;
import com.example.nikumar.testapi.intentservice.IntentServiceExample;
import com.example.nikumar.testapi.startservice.StartedServiceExample;
import com.example.nikumar.testapi.swipegesture.SwipeGestureExample;

import static com.example.nikumar.testapi.commonutility.CustomLogger.printVerbose;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    Button mAsyncTask, mHandler, mIntentService, mFingerPrint, mSwipeGesture, mStartedService,
            mBoundService, mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        printVerbose(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        init();
        registerOnClickListeners();
    }

    private void init() {
        mAsyncTask = findViewById(R.id.async_task);
        mHandler = findViewById(R.id.handler);
        mIntentService = findViewById(R.id.intent_service);
        mFingerPrint = findViewById(R.id.finger_print);
        mSwipeGesture = findViewById(R.id.swipe_gesture);
        mStartedService = findViewById(R.id.started_service);
        mBoundService = findViewById(R.id.bound_service);
        mActivity = findViewById(R.id.activity);

    }

    private void registerOnClickListeners() {
        UiListenersImpl mUiListenersImpl = new UiListenersImpl();
        mAsyncTask.setOnClickListener(mUiListenersImpl);
        mHandler.setOnClickListener(mUiListenersImpl);
        mIntentService.setOnClickListener(mUiListenersImpl);
        mFingerPrint.setOnClickListener(mUiListenersImpl);
        mSwipeGesture.setOnClickListener(mUiListenersImpl);
        mStartedService.setOnClickListener(mUiListenersImpl);
        mBoundService.setOnClickListener(mUiListenersImpl);
        mActivity.setOnClickListener(mUiListenersImpl);
    }

    class UiListenersImpl implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.async_task:
                    printVerbose(TAG, "Button Clicked :", getResources().getString(R.string.async_task));
                    startActivity(new Intent(MainActivity.this, AsyncTaskExample.class));
                    break;
                case R.id.handler:
                    printVerbose(TAG, "Button Clicked :", getResources().getString(R.string.handler));
                    break;
                case R.id.intent_service:
                    printVerbose(TAG, "Button Clicked :", getResources().getString(R.string.intent_service));
                    startActivity(new Intent(MainActivity.this, IntentServiceExample.class));
                    break;
                case R.id.finger_print:
                    printVerbose(TAG, "Button Clicked :", getResources().getString(R.string.finger_print));
                    startActivity(new Intent(MainActivity.this, FingerPrintExample.class));
                    break;
                case R.id.swipe_gesture:
                    printVerbose(TAG, "Button Clicked :", getResources().getString(R.string.swipe_gesture));
                    startActivity(new Intent(MainActivity.this, SwipeGestureExample.class));
                    break;
                case R.id.started_service:
                    printVerbose(TAG, "Button Clicked :", getResources().getString(R.string.started_service));
                    startActivity(new Intent(MainActivity.this, StartedServiceExample.class));
                    break;
                case R.id.bound_service:
                    printVerbose(TAG, "Button Clicked :", getResources().getString(R.string.bound_service));
                    startActivity(new Intent(MainActivity.this, BoundServiceExample.class));
                    break;
                case R.id.activity:
                    printVerbose(TAG, "Button Clicked :", getResources().getString(R.string.activity));
                    startActivity(new Intent(MainActivity.this, ActivityExample.class));
                    break;
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        printVerbose(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        printVerbose(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        printVerbose(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        printVerbose(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        printVerbose(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        printVerbose(TAG, "onDestroy");
    }
}