package com.niansoft.testapi.intentservice;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.niansoft.testapi.R;

import static com.niansoft.utils.CustomLogger.printVerbose;

public class IntentServiceExample extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "StartedServiceExample";
    EditText mFirstNumber, mSecondNumber;
    Button mShowResult;
    TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        printVerbose(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_service);
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
        mFirstNumber = findViewById(R.id.first_number);
        mSecondNumber = findViewById(R.id.second_number);
        mShowResult = findViewById(R.id.show_result);
        mResult = findViewById(R.id.result);
    }

    private void initUiListeners() {
        mShowResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_result:
                printVerbose(TAG, "Button Clicked :", getResources().getString(R.string.show_result));
                Editable lEditableFirst = mFirstNumber.getText();
                if (lEditableFirst != null && lEditableFirst.length() != 0) {
                    Editable lEditableSecond = mSecondNumber.getText();
                    if (lEditableSecond != null && lEditableSecond.length() != 0) {
                        Intent lIntent = new Intent(this, AdditionService.class);
                        lIntent.putExtra("firstnumber", Integer.parseInt(lEditableFirst.toString()));
                        lIntent.putExtra("secondnumber", Integer.parseInt(lEditableSecond.toString()));
                        lIntent.putExtra("messenger", new Messenger(mHandler));
                        startService(lIntent);
                    } else {
                        Toast.makeText(this, "Please enter second number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Please enter first number", Toast.LENGTH_SHORT).show();
                }
                break;
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

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            Bundle lData = msg.getData();
            printVerbose(TAG, "result : ", lData.getInt("result", -1));
            mResult.setText(String.valueOf(lData.getInt("result", -1)));
        }
    };
}
