/**
 * 
 */
package com.zerogravity.gpslogger;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

/**
 * @author Ash
 * 
 */
public class MyLocationListener extends Service implements LocationListener {

    float[] resultss = new float[2];

    @Override
    public void onLocationChanged(Location loc) {
	// TODO Auto-generated method stub
	if (MainActivity.gps_state == Constants.GPS_State.GPS_Not_Acquired) {

	    getStartingLocation(loc);

	} else {
	    /*
	     * GPS is acquired and waiting for more accurate position
	     */
	    if (MainActivity.counter_state == Counter_State.Counter_ON) {
		if (loc.getAccuracy() < Constants.accuracy_threshold) {

		    // Update the moving coordinates
		    MainActivity.stop_longitude = loc.getLongitude();
		    MainActivity.stop_latitude = loc.getLatitude();
		    Location.distanceBetween(MainActivity.start_latitude,
			    MainActivity.start_longitude,
			    MainActivity.stop_latitude,
			    MainActivity.stop_longitude, resultss);

		    if (resultss[0] >= 50) {
			MainActivity.gps_TV.setText("Reached 50Mts...!");
			String Text = "You reached 50mts!" + "Accuracy-"
				+ loc.getAccuracy() + "Distance-" + resultss[0];
			Toast.makeText(MainActivity.getAppContext(), Text,
				Toast.LENGTH_LONG).show();
			MainActivity.stopCounter();
		    } else {
			MainActivity.gps_TV.setText("Accuracy-"
				+ loc.getAccuracy() + " Distance-"
				+ resultss[0]);
		    }

		} else {
		    MainActivity.gps_TV.setText("Accuracy-" + loc.getAccuracy()
			    + " Distance-" + resultss[0]);
		}
	    } else {
		// Update the starting point until the counter is pressed
		getStartingLocation(loc);
	    }

	}

    }

    public void getStartingLocation(Location loc) {
	/*
	 * GPS is acquired and waiting for more accurate position
	 */
	if (loc.getAccuracy() < Constants.ACCURACY_THRESHOLD) {
	    MainActivity.gps_TV.setText("GPS Acquired...READY!");
	    MainActivity.start_longitude = loc.getLongitude();
	    MainActivity.start_latitude = loc.getLatitude();
	    MainActivity.gps_state = Constants.GPS_State.GPS_Acquired;
	} else {
	    if (MainActivity.gps_state == Constants.GPS_State.GPS_Acquired) {
		MainActivity.gps_TV.setText("GPS Acquired..READY!");
	    } else {
		MainActivity.gps_TV.setText("Acquiring GPS...");
	    }
	}
    }

    @Override
    public void onProviderDisabled(String provider) {
	// TODO Auto-generated method stub
	MainActivity.gps_TV.setText("Provider Disabled...");
	Toast.makeText(MainActivity.getAppContext(), "Gps Disabled",
		Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderEnabled(String provider) {
	// TODO Auto-generated method stub
	MainActivity.gps_TV.setText("Provider Enabled...");
	Toast.makeText(MainActivity.getAppContext(), "Gps Enabled",
		Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
	// TODO Auto-generated method stub
	MainActivity.gps_TV.setText("Status Changed..." + status);
    }

    @Override
    public IBinder onBind(Intent intent) {
	// TODO Auto-generated method stub
	return null;
    }

}
