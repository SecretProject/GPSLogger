/**
 * 
 */
package com.zerogravity.gpslogger;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/**
 * @author Ash
 * 
 */
public class MyLocationListener extends Service implements LocationListener {

    Location location; // location
    double latitude; // latitude
    double longitude; // longitude
    float speed;
    float accuracy;
    long time;

    // Declaring a Location Manager
    protected LocationManager locationManager;
    private boolean canGetLocation;

    @Override
    public void onLocationChanged(Location loc) {
	// TODO Auto-generated method stub
	Log.i("MLL", "onLocationChanged...");

    }

    @Override
    public void onProviderDisabled(String provider) {
	// TODO Auto-generated method stub
	Log.i("MLL", "Provider Disabled...");
    }

    @Override
    public void onProviderEnabled(String provider) {
	// TODO Auto-generated method stub
	Log.i("MLL", "Provider Enabled...");

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
	// TODO Auto-generated method stub
	Log.i("MLL", "onStatusChanged...");
    }

    @Override
    public IBinder onBind(Intent intent) {
	// TODO Auto-generated method stub
	return null;
    }
    /**
     * Stop using GPS listener Calling this function will stop using GPS in your
     * app
     * */
    public void stopUsingGPS() {
	if (locationManager != null) {
	    locationManager.removeUpdates(MyLocationListener.this);
	}
    }

    /**
     * Function to get speed
     * */
    public float getAccuracy() {
	if (location != null) {
	    accuracy = location.getAccuracy();
	}
	// return latitude
	return accuracy;
    }
    
    /**
     * Function to get speed
     * */
    public long getTime() {
	if (location != null) {
	    time = location.getTime();
	}

	// return latitude
	return time;
    }
    
    
    /**
     * Function to get speed
     * */
    public float getSpeed() {
	if (location != null) {
	    speed = location.getSpeed();
	}

	// return latitude
	return speed;
    }
    
    /**
     * Function to get latitude
     * */
    public double getLatitude() {
	if (location != null) {
	    latitude = location.getLatitude();
	}

	// return latitude
	return latitude;
    }

    /**
     * Function to get longitude
     * */
    public double getLongitude() {
	if (location != null) {
	    longitude = location.getLongitude();
	}

	// return longitude
	return longitude;
    }

    /**
     * Function to check GPS/wifi enabled
     * 
     * @return boolean
     * */
    public boolean canGetLocation() {
	return this.canGetLocation;
    }

}
