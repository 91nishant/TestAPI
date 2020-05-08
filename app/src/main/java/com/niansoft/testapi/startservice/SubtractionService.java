package com.niansoft.testapi.startservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;

import static com.niansoft.utils.CustomLogger.printVerbose;


public class SubtractionService extends Service {

    private static final String TAG = "SubtractionService";

    @Override
    public void onCreate() {
        super.onCreate();
        printVerbose(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        printVerbose(TAG, "onStartCommand");
        int firstNumber = intent.getIntExtra("first_number", -1);
        int secondNumber = intent.getIntExtra("second_number", -1);
        int result = subtract(firstNumber, secondNumber);
        final Bundle resultData = new Bundle();
        resultData.putInt("result", result);
        new Thread(new Runnable() {
            @Override
            public void run() {
                printVerbose(TAG, "calling stopSelf()");
                stopSelf();
            }
        }).start();
        printVerbose(TAG, "sending result back to the caller");
        ((ResultReceiver)intent.getExtras().get("result_receiver")).send(1221, resultData);
        return super.onStartCommand(intent,flags, startId);
    }

    @Override
    public IBinder onBind(Intent aIntent) {
        printVerbose(TAG, "onBind");
        return null;
    }

    private int subtract(int a, int b) {
        printVerbose(TAG, "subtract");
        return a-b;
    }

    @Override
    public void onRebind(Intent aIntent) {
        printVerbose(TAG, "onRebind");
    }

    @Override
    public boolean onUnbind(Intent aIntent) {
        printVerbose(TAG, "onUnbind");
        return true;
    }

    @Override
    public void onDestroy() {
        printVerbose(TAG, "onDestroy");
    }

}
