package com.example.mdjahirulislam.simplemap.sharePreference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mdjahirulislam on 28/04/17.
 */

public class UserSharePreference {


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    private static final String USER_ID="user_unique_id";
    private static final String TRAVEL_UNIQUE_EVENT_ID ="event_unique_id";
    private static final String DEFAULT_MSG="null";
    private static final String PREFERENCE_NAME="user authentication";





    public UserSharePreference(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME,context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public boolean setUserId(String userId){

        editor.putString(USER_ID,userId);
        editor.commit();
        if (USER_ID.isEmpty()){
            return false;
        }else {
            return true;
        }

    }

    public boolean setTravelEventUniqueId(String eventId){

        editor.putString(TRAVEL_UNIQUE_EVENT_ID,eventId);
        editor.commit();
        if (TRAVEL_UNIQUE_EVENT_ID.isEmpty()){
            return false;
        }else {
            return true;
        }

    }


    public String getUserId() {
        return sharedPreferences.getString(USER_ID,DEFAULT_MSG);
    }

    public String getTravelUniqueEventId() {
        return sharedPreferences.getString(TRAVEL_UNIQUE_EVENT_ID,DEFAULT_MSG);
    }


    public boolean resetUserId(){
        editor.clear();
        editor.commit();
        return true;
    }
}
