package com.zerogravity.gpslogger;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class WriteToFile {

    static File FilePath;
    Context main;

    public WriteToFile() {
	// TODO Auto-generated constructor stub
	// check if media is writable
	checkExternalMedia();

	File root = android.os.Environment.getExternalStorageDirectory();
	FilePath = new File(root.getAbsolutePath() + "/a");
	FilePath.mkdirs();
    }

    public void logLocationToFile(GPSTracker gps) {

	double latitude = gps.getLatitude();
	double longitude = gps.getLongitude();
	float accuracy = gps.getAccuracy();
	float speed = gps.getSpeed();
	long time = gps.getTime();

	String data = System.currentTimeMillis() + " " + latitude + " "
		+ longitude + " " + accuracy + " " + speed + " " + time + "\n";
	writeToSDFile(Constants.FileName, data);
	
    }

    private void checkExternalMedia() {
	String state = Environment.getExternalStorageState();
	if (Environment.MEDIA_MOUNTED.equals(state)) {
	    // Can read and write the media
	    Log.i("Media","can read and write media");
	} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
	    // Can only read the media
	    Log.i("Media","can only read the media");
	} else {
	    // Can't read or write
	    Log.i("Media","cant read or write");
	}

    }

    public static void writeToSDFile(String FileName, String data) {
	File file = new File(FilePath, FileName);
	try {
	    FileWriter pw = new FileWriter(file, true);
	    pw.write(data);
	    pw.flush();
	    pw.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	    Log.i("WriteToDSFile",
		    "******* File not found. Did you"
			    + " add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
	} catch (IOException e) {
	    e.printStackTrace();
	}
	Log.i("writetoSD", data);
    }

}