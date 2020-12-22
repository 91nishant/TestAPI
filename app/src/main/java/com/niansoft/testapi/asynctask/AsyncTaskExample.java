package com.niansoft.testapi.asynctask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.niansoft.testapi.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static com.niansoft.utils.CustomLogger.printVerbose;

public class AsyncTaskExample extends Activity {
    private static final String TAG = "AsyncTaskExample";
    CustomAsyncTask mCustomAsyncTask;
    Button mTrAsyncTask;
    String url1, url2;
    static ImageView mImageView1, mImageView2;
    static ProgressBar mProgressBar;
    static AsyncTaskExample mInstance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        printVerbose(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async_task);
        init();
        initUiListeners();
    }

    private void init() {
        mTrAsyncTask = findViewById(R.id.trigger_async_task);
        mImageView1 = findViewById(R.id.imageView1);
        mImageView2 = findViewById(R.id.imageView2);
        mProgressBar = findViewById(R.id.progressBar);
        url1 = "https://wallpaperaccess.com/full/493220.jpg";
        url2 = "https://i.pinimg.com/originals/08/0b/9f/080b9f240d6394ad224aae612cfae18b.jpg";
        mInstance = this;
    }

    private void initUiListeners() {
        mTrAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomAsyncTask = new CustomAsyncTask();
                mCustomAsyncTask.execute(url1, url2);
            }
        });
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
    }

    static class CustomAsyncTask extends AsyncTask<Object, Integer, Integer> {
        Bitmap bitmap;
        int imageId = 0;

        @Override
        protected Integer doInBackground(Object... objects) {
            printVerbose(TAG, "doInBackground, objects: " + objects[1].toString());

            for (Object requestUrl: objects) {
                printVerbose(TAG, "imageId: " + imageId);
                URL url;
                try {
                    url = new URL(requestUrl.toString());
                    URLConnection conn = url.openConnection();
                    bitmap = BitmapFactory.decodeStream(conn.getInputStream());
                    imageId++;
                    publishProgress();
                } catch (IOException e) {
                    e.printStackTrace();
                    return 1;
                }
            }
            return 0;
        }

        @Override
        protected void onPreExecute() {
            printVerbose(TAG, "onPreExecute");
        }

        @Override
        protected void onPostExecute(Integer result) {
            printVerbose(TAG, "onPostExecute, result: " + result);
            Toast.makeText(mInstance, "All images downloaded", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onProgressUpdate(Integer... result) {
            printVerbose(TAG, "onProgressUpdate, imageId: " + imageId);
            switch (imageId) {
                case 1:
                    mImageView1.setImageBitmap(bitmap);
                    mProgressBar.setProgress(50);
                    break;
                case 2:
                    mImageView2.setImageBitmap(bitmap);
                    mProgressBar.setProgress(100);
                    break;
            }
        }

        private void sleep(int timeInMilliseconds) {
            try {
                Thread.sleep(timeInMilliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
