package com.example.nikumar.testapi.boundservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nikumar.testapi.R;

import static com.example.nikumar.testapi.commonutility.CustomLogger.printVerbose;

public class BoundServiceExample extends Activity implements View.OnClickListener {
    private static final String TAG = "BoundServiceExample";
    EditText mFirstNumber, mSecondNumber;
    Button mShowResult;
    TextView mResult;
    boolean mBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        printVerbose(TAG, "onCreate");
        setContentView(R.layout.bound_service);
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
        printVerbose(TAG, "onClick");
        switch (v.getId()) {
            case R.id.show_result:
                printVerbose(TAG, "Button Clicked :", getResources().getString(R.string.show_result));
                Editable lEditableFirst = mFirstNumber.getText();
                if (lEditableFirst != null && lEditableFirst.length() != 0) {
                    Editable lEditableSecond = mSecondNumber.getText();
                    if (lEditableSecond != null && lEditableSecond.length() != 0) {
                        // Start binding to the service
                        Intent lIntent = new Intent(this, MultiplyService.class);
                        mResult.setText("Start binding to the service");
                        this.bindService(lIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
                    } else {
                        Toast.makeText(this, "Please enter second number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Please enter first number", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            printVerbose(TAG, "onServiceConnected");
            mResult.setText("onServiceConnected");
            mBound = true;
            MultiplyService.LocalBinder  mBinder = (MultiplyService.LocalBinder) service;
            MultiplyService mService = (MultiplyService) mBinder.getService();
            calculateAndUpdateUi(mService);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            printVerbose(TAG, "onServiceDisconnected");

            mBound = false;
        }
    };

    private void calculateAndUpdateUi(MultiplyService aService) {
        printVerbose(TAG, "calculateAndUpdateUi");
        Editable lEditableFirst = mFirstNumber.getText();
        if (lEditableFirst != null && lEditableFirst.length() != 0) {
            Editable lEditableSecond = mSecondNumber.getText();
            if (lEditableSecond != null && lEditableSecond.length() != 0) {
                int firstNumber = Integer.parseInt(lEditableFirst.toString());
                int secondNumber = Integer.parseInt(lEditableSecond.toString());
                int result = aService.multiply(firstNumber, secondNumber);
                mResult.setText(String.valueOf(result));
                //this.unbindService(mServiceConnection);
            } else {
                Toast.makeText(this, "Please enter second number", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter first number", Toast.LENGTH_SHORT).show();
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