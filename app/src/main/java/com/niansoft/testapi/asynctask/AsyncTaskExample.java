package com.niansoft.testapi.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.niansoft.testapi.R;

import static com.niansoft.utils.CustomLogger.printVerbose;

public class AsyncTaskExample extends Activity {
    private static final String TAG = "AsyncTaskExample";
    CustomAsyncTask mCustomAsyncTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        printVerbose(TAG, "onCreate");
        super.onCreate(savedInstanceState);

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
        setContentView(R.layout.async_task);
        mCustomAsyncTask = new CustomAsyncTask();
    }

    static class CustomAsyncTask extends AsyncTask<Object, Integer, Integer> {

        @Override
        protected Integer doInBackground(Object... objects) {
            return 0;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(Integer result) {

        }

        @Override
        protected void onProgressUpdate(Integer... result) {

        }
    }
}
