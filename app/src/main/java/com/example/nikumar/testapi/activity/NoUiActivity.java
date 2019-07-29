package com.example.nikumar.testapi.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nikumar.testapi.commonutility.CustomLogger;

import static com.example.nikumar.testapi.commonutility.CustomLogger.printVerbose;

public class NoUiActivity extends AppCompatActivity {
	private static final String TAG = "NoUiAcitivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		CustomLogger.printVerbose(TAG, "onCreate");
	}

	@Override
	protected void onStart() {
		super.onStart();
		CustomLogger.printVerbose(TAG, "onStart");
	}

	@Override
	protected void onResume() {
		CustomLogger.printVerbose(TAG, "onResume");
		super.onResume();
		/*try {
			printVerbose(TAG, "Going to sleep for 5 second on UI thread");
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			printVerbose(TAG, "Sleep completed, finishing the current activity");
			finish();
		}*/
		finish();
	}

	@Override
	protected void onPause() {
		super.onPause();
		CustomLogger.printVerbose(TAG, "onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		CustomLogger.printVerbose(TAG, "onStop");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		CustomLogger.printVerbose(TAG, "onRestart");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		CustomLogger.printVerbose(TAG, "onDestroy");
	}

}
