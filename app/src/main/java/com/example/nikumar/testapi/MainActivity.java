package com.example.nikumar.testapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nikumar.testapi.activity.ActivityExample;
import com.example.nikumar.testapi.anr.AnrExample;
import com.example.nikumar.testapi.asynctask.AsyncTaskExample;
import com.example.nikumar.testapi.boundservice.BoundServiceExample;
import com.example.nikumar.testapi.crash.CrashExample;
import com.example.nikumar.testapi.fingerprint.FingerPrintExample;
import com.example.nikumar.testapi.intentservice.IntentServiceExample;
import com.example.nikumar.testapi.startservice.StartedServiceExample;
import com.example.nikumar.testapi.swipegesture.SwipeGestureExample;

import static com.example.nikumar.testapi.commonutility.CustomLogger.printVerbose;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    Button mAsyncTask, mHandler, mIntentService, mStartedService,
            mBoundService, mActivity, mCrash, mAnr, mNextSet;

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
        mStartedService = findViewById(R.id.started_service);
        mBoundService = findViewById(R.id.bound_service);
        mActivity = findViewById(R.id.activity);
        mCrash = findViewById(R.id.crash);
        mAnr = findViewById(R.id.anr);
        mNextSet = findViewById(R.id.next_set);
    }

    private void registerOnClickListeners() {
        UiListenersImpl mUiListenersImpl = new UiListenersImpl();
        mAsyncTask.setOnClickListener(mUiListenersImpl);
        mHandler.setOnClickListener(mUiListenersImpl);
        mIntentService.setOnClickListener(mUiListenersImpl);
        mStartedService.setOnClickListener(mUiListenersImpl);
        mBoundService.setOnClickListener(mUiListenersImpl);
        mActivity.setOnClickListener(mUiListenersImpl);
        mCrash.setOnClickListener(mUiListenersImpl);
        mAnr.setOnClickListener(mUiListenersImpl);
        mNextSet.setOnClickListener(mUiListenersImpl);
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
                case R.id.crash:
                    printVerbose(TAG, "Button Clicked :", getResources().getString(R.string.crash));
                    startActivity(new Intent(MainActivity.this, CrashExample.class));
                    break;
                case R.id.anr:
                    printVerbose(TAG, "Button Clicked :", getResources().getString(R.string.anr));
                    startActivity(new Intent(MainActivity.this, AnrExample.class));
                    break;
                case R.id.next_set:
                    printVerbose(TAG, "Button Clicked : ", getResources().getString(R.string.next_set));
                    startActivity(new Intent(MainActivity.this, SecondMainActivity.class));
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