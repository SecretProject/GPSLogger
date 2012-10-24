package com.zerogravity.gpslogger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import android.app.Activity;
import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    Button TurnOff, LogLocationNow;
    PendingIntent sender;
    GPSTracker gps;
    TextView tv;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	TurnOff = (Button) findViewById(R.id.btOff);
	LogLocationNow = (Button) findViewById(R.id.bRib);
	tv = (TextView)findViewById(R.id.t1);
	    gps = new GPSTracker(getApplicationContext());

	    // check if GPS enabled
	    if (gps.canGetLocation()){
		
		//logLocationToFile();
		
	    }else{
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

    public void PlayWithRawFiles() throws IOException {      
	
	File sdcard = Environment.getExternalStorageDirectory();

	//Get the text file
	File file = new File(sdcard,File.separator+Constants.DirectoryName+File.separator+Constants.FileName);

	//Read text from file
	StringBuilder text = new StringBuilder();

	try {
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    String line;

	    while ((line = br.readLine()) != null) {
	        text.append(line);
	        text.append('\n');
	    }
	}
	catch (IOException e) {
	    //You'll need to add proper error handling here
	}

	//Find the view by its id
	TextView tv = (TextView)findViewById(R.id.t1);

	//Set the text
	tv.setText(text);		
    }

}

