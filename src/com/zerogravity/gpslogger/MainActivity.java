package com.zerogravity.gpslogger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
	
	File f = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + File.separator + Constants.FileName);
	String str="";
	StringBuffer buf = new StringBuffer();			
	InputStream is = this.getResources().openRawResource(R.drawable.my_base_data);
	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	if (is!=null) {							
		while ((str = reader.readLine()) != null) {	
			buf.append(str + "\n" );
		}				
	}		
	is.close();	
	Toast.makeText(getBaseContext(), 
			buf.toString(), Toast.LENGTH_LONG).show();				
    }

}
}
