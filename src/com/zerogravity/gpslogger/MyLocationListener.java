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
import android.util.Log;

/**
 * @author Ash
 * 
 */
public class MyLocationListener extends Service implements LocationListener {

  

    @Override
    public void onLocationChanged(Location loc) {
	// TODO Auto-generated method stub
	Log.i("MLL","onLocationChanged...");


    }


    @Override
    public void onProviderDisabled(String provider) {
	// TODO Auto-generated method stub
	Log.i("MLL","Provider Disabled...");
    }

    @Override
    public void onProviderEnabled(String provider) {
	// TODO Auto-generated method stub
	Log.i("MLL","Provider Enabled...");

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
	// TODO Auto-generated method stub
	Log.i("MLL","onStatusChanged...");
    }

    @Override
    public IBinder onBind(Intent intent) {
	// TODO Auto-generated method stub
	return null;
    }

}
