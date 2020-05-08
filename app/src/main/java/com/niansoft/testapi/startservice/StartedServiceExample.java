package com.niansoft.testapi.startservice;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.niansoft.testapi.R;

import static com.niansoft.utils.CustomLogger.printVerbose;


public class StartedServiceExample extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "StartedServiceExample";
    EditText mFirstNumber, mSecondNumber;
    Button mShowResult;
    TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        printVerbose(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.started_service);
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_result:
                printVerbose(TAG, "Button Clicked :", getResources().getString(R.string.show_result));
                Editable lEditableFirst = mFirstNumber.getText();
                if (lEditableFirst != null && lEditableFirst.length() != 0) {
                    Editable lEditableSecond = mSecondNumber.getText();
                    if (lEditableSecond != null && lEditableSecond.length() != 0) {
                        // start the service
                        Intent intent = new Intent(this, SubtractionService.class);
                        intent.putExtra("first_number", Integer.parseInt(lEditableFirst.toString()));
                        intent.putExtra("second_number", Integer.parseInt(lEditableSecond.toString()));
                        intent.putExtra("result_receiver", new CustomResultReceiver(null));
                        mShowResult.setText("Starting Service");
                        startService(intent);
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

    public class CustomResultReceiver extends ResultReceiver {

        private CustomResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            printVerbose("onReceiveResult, resultCode : ", resultCode);
            mShowResult.setText(String.valueOf(resultData.getInt("result")));
        }
    }

}
