package com.niansoft.testapi.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.niansoft.testapi.R;

import static com.niansoft.utils.CustomLogger.printVerbose;

public class ActivityExample extends AppCompatActivity {
    private static final String TAG = "ActivityExample";
    private static final String MSG_ERR_NO_ACTIVITY = "No activity found to send messages";
    private static final String MSG_CALLING_NO_UI_ACTIVITY = "Calling activity with no UI";
    private static final String MSG_CALLING_ACTIVITY_SUCCESSFUL = "Calling activity with no UI successful";
    Button mActivityNoUi, mSendMsg;
    TextView mMessageBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        printVerbose(TAG, "onCreate");
        setContentView(R.layout.activity_example);
        mActivityNoUi = findViewById(R.id.activity_no_ui);
        mSendMsg = findViewById(R.id.send_msg);
        mMessageBar = findViewById(R.id.message_bar);
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
        mActivityNoUi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printVerbose(TAG, "mActivityNoUi, onClick method");
                Intent in = new Intent(ActivityExample.this, NoUiActivity.class);
                mMessageBar.setText(MSG_CALLING_NO_UI_ACTIVITY);
                startActivityForResult(in, 1);
            }
        });
        mSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printVerbose(TAG, "mSendMsg, onClick method");
                Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
                PackageManager pm = getApplication().getPackageManager();
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", "1542654657");
                smsIntent.putExtra("sms_body", "This is a sample message to send");
                if (null != smsIntent.resolveActivity(pm)) {
                    startActivity(smsIntent);
                } else {
                    mMessageBar.setText(MSG_ERR_NO_ACTIVITY);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        printVerbose(TAG, "onActivityResult");
        if (requestCode == 1) {
            mMessageBar.setText(MSG_CALLING_ACTIVITY_SUCCESSFUL);
        }
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
