package com.niansoft.testapi.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import static com.niansoft.utils.CustomLogger.printVerbose;

public class NoUiActivity extends AppCompatActivity {
	private static final String TAG = "NoUiActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		printVerbose(TAG, "onCreate");
	}

	@Override
	protected void onStart() {
		super.onStart();
		printVerbose(TAG, "onStart");
	}

	@Override
	protected void onResume() {
		printVerbose(TAG, "onResume");
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
		setResult(1);
		finish();
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
