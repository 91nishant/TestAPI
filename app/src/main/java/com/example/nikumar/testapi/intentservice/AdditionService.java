package com.example.nikumar.testapi.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import static com.example.nikumar.testapi.commonutility.CustomLogger.printVerbose;

public class AdditionService extends IntentService {

    private static final String TAG = "AdditionService";

    public AdditionService() {
        super("AdditionService");
    }

    @Override
    public void onCreate() {
        printVerbose(TAG, "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        printVerbose(TAG, "onStartCommand");
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int result = intent.getIntExtra("firstnumber", -1) +
                intent.getIntExtra("secondnumber", -1);
        Bundle lBundle = new Bundle();
        lBundle.putInt("result", result);
        printVerbose(TAG, "onHandleIntent, result : ", result);
        Bundle mBundle = intent.getExtras();

        Messenger lMessenger = (Messenger) mBundle.get("messenger");
        Message msg = Message.obtain();
        msg.setData(lBundle);
        try {
            lMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        printVerbose(TAG, "onDestroy");
        super.onDestroy();
    }

}
