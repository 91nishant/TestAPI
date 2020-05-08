package com.niansoft.testapi.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import static com.niansoft.utils.CustomLogger.printVerbose;


public class MultiplyService extends Service {
    private static final String TAG = "MultiplyService";
    LocalBinder mBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        printVerbose(TAG, "onCreate");
        mBinder = new LocalBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        printVerbose(TAG, "onStartCommand");
        return super.onStartCommand(intent,flags, startId);
    }

    @Override
    public IBinder onBind(Intent aIntent) {
        printVerbose(TAG, "onBind");
        return mBinder;
    }

    public class LocalBinder extends Binder {

        public MultiplyService getService() {
            printVerbose(TAG, "getService");
            return MultiplyService.this;
        }
    };

    public int multiply(int a, int b) {
        printVerbose(TAG, "multiply");
        return a*b;
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
