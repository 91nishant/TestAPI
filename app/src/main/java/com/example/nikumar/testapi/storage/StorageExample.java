package com.example.nikumar.testapi.storage;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nikumar.testapi.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.example.nikumar.testapi.commonutility.CustomLogger.printError;
import static com.example.nikumar.testapi.commonutility.CustomLogger.printVerbose;

public class StorageExample extends Activity {
    private static final String TAG = "StorageExample";
    Button mAppPrivateStorage, mInternalSdCard, mExternalSdCard, mResolvedPath;
    TextView mShowAppPrivateStorage, mShowInternalSdCard, mShowExternalSdCard, mShowResolvedPath;
    EditText mEnterPath;
    Activity self;
    int fileNumber = 1;
    enum StoragePath {INTERNAL, EXTERNAL}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        printVerbose(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_example);
        init();
        registerOnClickListeners();
        setTextViewScrollable();
    }

    private void init() {
        self = this;
        mAppPrivateStorage = findViewById(R.id.app_private_storage);
        mInternalSdCard = findViewById(R.id.internal_sd_card);
        mExternalSdCard = findViewById(R.id.external_sd_card);
        mResolvedPath = findViewById(R.id.resolved_path);
        mShowAppPrivateStorage = findViewById(R.id.show_app_private_storage);
        mShowInternalSdCard = findViewById(R.id.show_internal_sd_card);
        mShowExternalSdCard = findViewById(R.id.show_external_sd_card);
        mShowResolvedPath = findViewById(R.id.show_resolved_path);
        mEnterPath = findViewById(R.id.enter_path);
    }

    private void registerOnClickListeners() {
        UiListenersImpl mUiListenersImpl = new UiListenersImpl();
        mAppPrivateStorage.setOnClickListener(mUiListenersImpl);
        mInternalSdCard.setOnClickListener(mUiListenersImpl);
        mExternalSdCard.setOnClickListener(mUiListenersImpl);
        mResolvedPath.setOnClickListener(mUiListenersImpl);

        // To be removed
        String path = "/storage/sdcard0";
        mEnterPath.setText(path);
    }

    private void setTextViewScrollable() {
        mShowAppPrivateStorage.setMovementMethod(new ScrollingMovementMethod());
        mShowInternalSdCard.setMovementMethod(new ScrollingMovementMethod());
        mShowExternalSdCard.setMovementMethod(new ScrollingMovementMethod());
        mShowResolvedPath.setMovementMethod(new ScrollingMovementMethod());
    }

    class UiListenersImpl implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.app_private_storage:
                    printVerbose(TAG, "Button Clicked :", getResources().getString(R.string.app_private_storage));
                    showAppPrivateStorage();
                    break;
                case R.id.internal_sd_card:
                    printVerbose(TAG, "Button Clicked :", getResources().getString(R.string.internal_sd_card));
                    showInternalSdCard();
                    break;
                case R.id.external_sd_card:
                    printVerbose(TAG, "Button Clicked :", getResources().getString(R.string.external_sd_card));
                    showExternalSdCard();
                    break;
                case R.id.resolved_path:
                    printVerbose(TAG, "Button Clicked :", getResources().getString(R.string.resolved_path));
                    showResolvedPath();
                    break;
            }
        }

        private void showAppPrivateStorage() {

        }

        private void showInternalSdCard() {
            StringBuilder internalSdCardPath = new StringBuilder();
            internalSdCardPath.append("</b>Environment.getExternalStorageDirectory().getPath():</b><br>")
                    .append(Environment.getExternalStorageDirectory().getPath()).append("<br><br>");
            mShowInternalSdCard.setText(Html.fromHtml(internalSdCardPath.toString()));
        }

        private void showExternalSdCard() {
            StringBuilder externalSdCardPath = new StringBuilder();
            externalSdCardPath.append("<b>getApplicationContext().getFilesDir().getPath():</b><br>")
                    .append(getApplicationContext().getFilesDir().getPath()).append("<br><br>")
                    .append("<b>getExternalFilesDir().getPath():</b><br>")
                    .append(getExternalFilesDir(null).getPath()).append("<br><br>");
            mShowExternalSdCard.setText(Html.fromHtml(externalSdCardPath.toString()));
        }

        private void showResolvedPath() {
            String enteredPath = mEnterPath.getText().toString();
            String resolvedPath = null;
            if (!enteredPath.isEmpty()) {
                resolvedPath = resolveModulePath(enteredPath);
                if (null != resolvedPath) {
                    mShowResolvedPath.setText(resolvedPath);
                    //createFile(resolvedPath);
                }
                else Toast.makeText(self, "Null resolved path", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(self, "Enter path first", Toast.LENGTH_LONG).show();
            }
        }

        private void createFile(String resolvedPath) {
            String fileName = "CustomFile" + fileNumber++;
            printVerbose(TAG, "createFile, filename:", resolvedPath + fileName);
            File file = new File(resolvedPath, fileName);
            FileOutputStream stream = null;
            try {
                stream = new FileOutputStream(file);
                stream.write(fileName.getBytes());
                stream.close();
            } catch (FileNotFoundException e) {
                printError(TAG, "FileNotFoundException:", e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                printError(TAG, "IOException:", e.getMessage());
                e.printStackTrace();
            } finally {

            }
        }

    }

    public String resolveModulePath(String modulePath) {
        if (null != modulePath) {
            String[] paths= modulePath.split(File.separator);
            if (paths.length>2 && "".equals(paths[0]) && "storage".equals(paths[1])
                    && ("sdcard0".equals(paths[2]) || "sdcard1".equals(paths[2]))) {
                StringBuilder lPath = new StringBuilder();
                String basePath = pathConversion(modulePath);
                if (null != basePath) {
                    lPath.append(basePath);
                    if (paths.length > 3) {
                        for (int i = 3; i < paths.length; i++) {
                            lPath = lPath.append(paths[i]).append(File.separator);
                        }
                    }
                    modulePath = lPath.toString();
                }
            }
        }
        return modulePath;
    }

    private String pathConversion(String actualPath) {
            File[] files = getApplicationContext().getExternalFilesDirs("null");
            String[] paths;

            if (null != files) {
                try {
                    //printVerbose(TAG, "files[0]:", files[0].toString());
                    //printVerbose(TAG, "files[1]:", files[1].toString());
                    if (actualPath.contains("sdcard0")) {
                        paths = files[0].toString().split(File.separator);
                        return File.separator + paths[1] + File.separator + paths[2] + File.separator + paths[3] + File.separator;
                    } else if (actualPath.contains("sdcard1")) {
                        paths = files[1].toString().split(File.separator);
                        return File.separator + paths[1] + File.separator + paths[2] + File.separator;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    printError(TAG, "ArrayIndexOutOfBoundsException:", e.getMessage());
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    printError(TAG, "NullPointerException:", e.getMessage());
                    e.printStackTrace();
                }
            }

        return null;
    }

}
