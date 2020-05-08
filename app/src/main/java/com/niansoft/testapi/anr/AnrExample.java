package com.niansoft.testapi.anr;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.niansoft.testapi.R;

import static com.niansoft.utils.CustomLogger.printError;
import static com.niansoft.utils.CustomLogger.printVerbose;

public class AnrExample extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AnrExample";
    private static final int ANR_TIMEOUT = 10000;
    Button mAnr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        printVerbose(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anr_example);
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
        mAnr = findViewById(R.id.trigger_anr);
    }

    private void initUiListeners() {
        mAnr.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.trigger_anr:
                printVerbose(TAG, "Button Clicked :", getResources().getString(R.string.trigger_anr));
                try {
                    Thread.sleep(ANR_TIMEOUT);
                } catch (InterruptedException e) {
                    printError(TAG, "InterruptedException:", e.getMessage());
                    e.printStackTrace();
                }
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
