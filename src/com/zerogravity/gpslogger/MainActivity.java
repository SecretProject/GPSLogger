package com.zerogravity.gpslogger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import android.app.Activity;
import android.app.PendingIntent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    Button TurnOff, LogLocationNow;
    PendingIntent sender;
    GPSTracker gps;
    TextView tv;

    LocationListener mlocListener;
    LocationManager mlocManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	TurnOff = (Button) findViewById(R.id.btOff);
	LogLocationNow = (Button) findViewById(R.id.bRib);
	tv = (TextView) findViewById(R.id.t1);
	tv.setMovementMethod(new ScrollingMovementMethod());
	gps = new GPSTracker(getApplicationContext());

	// check if GPS enabled
	if (gps.canGetLocation()) {

	    // logLocationToFile();

	} else {
	    // can't get location
	    // GPS or Network is not enabled
	    // Ask user to enable GPS/network in settings
	    gps.showSettingsAlert();
	}

	LogLocationNow.setOnClickListener(new OnClickListener() {
	    @Override
	    public void onClick(View v) {
		// TODO Auto-generated method stub
		WriteToFile wtf = new WriteToFile();
		wtf.logLocationToFile(gps);
		PlayWithRawFiles();
	    }
	});

	TurnOff.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		// TODO Auto-generated method stub
		gps.stopUsingGPS();
	    }
	});
    }

    public void PlayWithRawFiles() {

	File sdcard = Environment.getExternalStorageDirectory();

	// Get the text file
	File file = new File(sdcard, File.separator + Constants.DIRECTORY_NAME
		+ File.separator + Constants.FILE_NAME);

	// Read text from file
	StringBuilder text = new StringBuilder();

	try {
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    String line;

	    while ((line = br.readLine()) != null) {
		text.append(line);
		text.append('\n');
	    }
	} catch (IOException e) {
	    // You'll need to add proper error handling here
	}

	// Find the view by its id
	TextView tv = (TextView) findViewById(R.id.t1);

	// Set the text
	tv.setText(text);
    }

}
