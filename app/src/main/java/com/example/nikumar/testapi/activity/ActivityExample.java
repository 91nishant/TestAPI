package com.example.nikumar.testapi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.nikumar.testapi.R;
import com.example.nikumar.testapi.commonutility.CustomLogger;

public class ActivityExample extends AppCompatActivity {
    private static final String TAG = "ActivityExample";
    Button mActivityNoUi, mSendMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomLogger.printVerbose(TAG, "onCreate");
        setContentView(R.layout.activity_example);
        mActivityNoUi = findViewById(R.id.activity_no_ui);
        mSendMsg = findViewById(R.id.send_msg);
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
        mActivityNoUi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ActivityExample.this, NoUiActivity.class);
                startActivity(in);
            }
        });
        mSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address","1542654657");
                smsIntent.putExtra("sms_body","This is a sample message to send");
                startActivity(smsIntent);
            }
        });
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
