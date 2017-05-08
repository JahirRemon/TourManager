package com.example.mdjahirulislam.simplemap.sharePreference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mdjahirulislam on 05/04/17.
 */

public class LocationSharedPreference {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    private static final String LOCATION_LATITUDE="latitude";
    private static final String LOCATION_LONGITUDE="longitude";
    private static final String LOCATION_CITY_NAME="city_name";
    private static final String DEFAULT_MSG="null";
    private static final String PREFERENCE_NAME="location authentication";
    private static final String USER_ID="user_unique_id";
//    private static final String PREFERENCE_NAME="location authentication";



    public LocationSharedPreference(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME,context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public boolean setLocation(String latitude,String longitude,String cityName){
        editor.putString(LOCATION_LATITUDE,latitude);
        editor.putString(LOCATION_LONGITUDE,longitude);
        editor.putString(LOCATION_CITY_NAME,cityName);
        editor.commit();
        if (LOCATION_LATITUDE.isEmpty() && LOCATION_LONGITUDE.isEmpty() && LOCATION_CITY_NAME.isEmpty()){
            return false;
        }else {
            return true;
        }

    }


    public String getLocationLatitude() {
        return sharedPreferences.getString(LOCATION_LATITUDE,DEFAULT_MSG);
    }

    public String getLocationLongitude() {
        return sharedPreferences.getString(LOCATION_LONGITUDE,DEFAULT_MSG);
    }

    public String getLocationCityName() {
        return sharedPreferences.getString(LOCATION_CITY_NAME,DEFAULT_MSG);
    }

    public boolean resetLocation(){
        editor.clear();
        editor.commit();
        return true;
    }

}
