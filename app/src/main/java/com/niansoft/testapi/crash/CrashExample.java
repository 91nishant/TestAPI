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
    Button mTrJavaCrash, mTrNativeCrash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        printVerbose(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash_example);
        init();
        initUiListeners();
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
    }

    private void init() {
        mTrJavaCrash = findViewById(R.id.trigger_java_crash);
        mTrNativeCrash = findViewById(R.id.trigger_native_crash);
    }

    private void initUiListeners() {
        mTrJavaCrash.setOnClickListener(this);
        mTrNativeCrash.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.trigger_java_crash:
                printVerbose(TAG, "Button Clicked :", getResources().getString(R.string.trigger_java_crash));
                printVerbose(TAG, "Throwing NullPointerException");
                Toast.makeText(this, "NullpointerException thrown", Toast.LENGTH_LONG).show();
                throw new NullPointerException();
            case R.id.trigger_native_crash:
                printVerbose(TAG, "Button Clicked :", getResources().getString(R.string.trigger_native_crash));
                printVerbose(TAG, "Crashing native");
                Toast.makeText(this, "Crashing native", Toast.LENGTH_LONG).show();

                // Pass code flow towards native -> tombstones will be generated once successfully crashed

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