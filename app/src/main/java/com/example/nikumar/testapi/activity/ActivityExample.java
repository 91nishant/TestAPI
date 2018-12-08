package com.example.nikumar.testapi.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.nikumar.testapi.R;
import com.example.nikumar.testapi.commonutility.CustomLogger;

public class ActivityExample extends AppCompatActivity {
    private static final String TAG = "ActivityExample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomLogger.printVerbose(TAG, "onCreate");
        setContentView(R.layout.activity_example);
    }

    @Override
    protected void onStart() {
        super.onStart();
        CustomLogger.printVerbose(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        CustomLogger.printVerbose(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        CustomLogger.printVerbose(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        CustomLogger.printVerbose(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        CustomLogger.printVerbose(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CustomLogger.printVerbose(TAG, "onDestroy");
    }
}
