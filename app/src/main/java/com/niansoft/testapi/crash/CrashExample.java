package com.niansoft.testapi.crash;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.niansoft.testapi.R;

import static com.niansoft.utils.CustomLogger.printVerbose;

public class CrashExample extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "CrashExample";
    Button mTriggerCrash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        printVerbose(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash_example);
    }

    @Override
    protected void onStart() {
        printVerbose(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        printVerbose(TAG, "onResume");
        super.onResume();
        init();
        initUiListeners();
    }

    private void init() {
        mTriggerCrash = findViewById(R.id.trigger_crash);
    }

    private void initUiListeners() {
        mTriggerCrash.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.trigger_crash:
                printVerbose(TAG, "Button Clicked :", getResources().getString(R.string.trigger_crash));
                printVerbose(TAG, "Throwing NullPointerException");
                Toast.makeText(this, "NullpointerException thrown", Toast.LENGTH_LONG).show();
                throw new NullPointerException();
        }
    }

    @Override
    protected void onPause() {
        printVerbose(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        printVerbose(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        printVerbose(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        printVerbose(TAG, "onDestroy");
        super.onDestroy();
    }
}
