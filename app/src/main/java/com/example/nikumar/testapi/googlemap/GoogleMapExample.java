package com.example.nikumar.testapi.googlemap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.nikumar.testapi.R;

import static com.example.nikumar.testapi.commonutility.CustomLogger.printVerbose;

public class GoogleMapExample extends AppCompatActivity {
    private static final String TAG = "GoogleMapExample";

    Button mPostOnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map_example);
        mPostOnMap = findViewById(R.id.post_on_map);
        mPostOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printVerbose(TAG, "Button Clicked :", getResources().getString(R.string.post_on_map));
                postOnMap();
            }
        });
    }

    private void postOnMap() {
        double latitude  = 23.369612;
        double longitude =  85.862112;
        printVerbose(TAG, "postOnMap, latitude :", latitude, ", longitude :", longitude);
        Intent nt = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + latitude + "," + longitude));
        startActivity(nt);
    }

}
