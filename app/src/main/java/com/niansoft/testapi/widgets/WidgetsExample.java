package com.niansoft.testapi.widgets;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.niansoft.testapi.R;

import static com.niansoft.utils.CustomLogger.printVerbose;

public class WidgetsExample extends AppCompatActivity implements View.OnClickListener {
	private static final String TAG = "WidgetsExample";
	Button mButton;
	ImageView mImageView;
	Switch mSwitch;
	TextView mTextView;
	EditText mEditText;
	CheckBox mCheckBox;
	RadioButton mRadioButton;
	ToggleButton mToggleButton;
	FloatingActionButton mFab;
	ProgressBar mProgressBar;
	SeekBar mSeekBar;
	RatingBar mRatingBar;
	SearchView mSearchView;
	WebView mWebView;

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
		super.onResume();
		printVerbose(TAG, "onResume");
		setContentView(R.layout.activity_widgets_example);
		init();
		setListeners();
	}

	private void init() {
		mButton = findViewById(R.id.button);
		mImageView = findViewById(R.id.imageView);
		mSwitch = findViewById(R.id.switch_);
		mTextView = findViewById(R.id.textView);
		mEditText = findViewById(R.id.editText);
		mCheckBox = findViewById(R.id.checkBox);
		mRadioButton = findViewById(R.id.radioButton);
		mToggleButton = findViewById(R.id.toggleButton);
		mFab = findViewById(R.id.floatingActionButton);
		mProgressBar = findViewById(R.id.progressBar);
		mSeekBar = findViewById(R.id.seekBar);
		mRatingBar = findViewById(R.id.ratingBar);
		mSearchView = findViewById(R.id.searchView);
		mWebView = findViewById(R.id.webView);
	}

	private void setListeners() {
		mButton.setOnClickListener(this);
		mImageView.setOnClickListener(this);
		mSwitch.setOnClickListener(this);
		mTextView.setOnClickListener(this);
		mEditText.setOnClickListener(this);
		mCheckBox.setOnClickListener(this);
		mRadioButton.setOnClickListener(this);
		mToggleButton.setOnClickListener(this);
		mFab.setOnClickListener(this);
		mProgressBar.setOnClickListener(this);
		mSeekBar.setOnClickListener(this);
		mRatingBar.setOnClickListener(this);
		mSearchView.setOnClickListener(this);
		mWebView.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.button:
				Toast.makeText(this, getResources().getString(R.string.button) + " clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.imageView:
				Toast.makeText(this, "ImageView clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.switch_:
				Toast.makeText(this, getResources().getString(R.string.switch_) + " clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.textView:
				Toast.makeText(this, getResources().getString(R.string.textview) + " clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.editText:
				Toast.makeText(this, getResources().getString(R.string.edittext) + " clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.checkBox:
				Toast.makeText(this, getResources().getString(R.string.checkbox) + " clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.radioButton:
				Toast.makeText(this, getResources().getString(R.string.radio_button) + " clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.toggleButton:
				Toast.makeText(this, getResources().getString(R.string.toggle_button) + " clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.floatingActionButton:
				Toast.makeText(this, "floatingActionButton clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.progressBar:
				Toast.makeText(this, "progressBar clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.seekBar:
				Toast.makeText(this, "seekBar clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.ratingBar:
				Toast.makeText(this, "ratingBar clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.searchView:
				Toast.makeText(this, "searchView clicked", Toast.LENGTH_SHORT).show();
				break;
			case R.id.webView:
				Toast.makeText(this, "webView clicked", Toast.LENGTH_SHORT).show();
				break;
		}
	}
}
