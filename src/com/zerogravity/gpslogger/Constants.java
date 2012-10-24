package com.zerogravity.gpslogger;

public class Constants {

    // Logging parameters
    public static String FILE_NAME = "m8.txt";
    public static String DIRECTORY_NAME = "a";
    
    // The minimum distance to change Updates in meters
    public static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = -1; // 10 meters

    // The minimum time between updates in milliseconds
    public static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 2; // 1 minute

    //Location manager
    public static final int ACCURACY_THRESHOLD = 5;
    
}
