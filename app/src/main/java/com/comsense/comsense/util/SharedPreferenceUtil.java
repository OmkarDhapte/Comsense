package com.comsense.comsense.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lenovo on 9/6/2017.
 */

public class SharedPreferenceUtil {

    private static SharedPreferenceUtil sharePref = new SharedPreferenceUtil();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private final String SENDER_ID = "senderId";
    private final String USER_ID = "userId";
    private final String CHANNEL_ID = "channelId";
    private final String APP_KEY = "appKey";

    private SharedPreferenceUtil() {} //prevent creating multiple instances by making the constructor private

    public static SharedPreferenceUtil getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
        return sharePref;
    }

    public void setUserId(String userId) {
        editor.putString(USER_ID, userId).apply();
    }
    public String getUserId(){
        return sharedPreferences.getString(USER_ID, null);
    }

    public void setChannelId(String channelId) {
        editor.putString(CHANNEL_ID, channelId).apply();
    }
    public String getChannelId(){
        return sharedPreferences.getString(CHANNEL_ID, null);
    }

    public void setAppKey(String appKey) {
        editor.putString(APP_KEY, appKey).apply();
    }
    public String getAppKey(){
        return sharedPreferences.getString(APP_KEY, null);
    }

    public void firstTimeAskingPermission(Context context, String permission, boolean isFirstTime){
        editor.putBoolean(permission, isFirstTime).apply();
    }

    public boolean isFirstTimeAskingPermission(Context context, String permission){
        return sharedPreferences.getBoolean(permission, true);
    }

    public String getSenderId(){
        return sharedPreferences.getString(SENDER_ID,null);
    }

    public void setSenderId(String senderId) {
        editor.putString(SENDER_ID,senderId).apply();
    }

    public void clearAll() {
        editor.clear();
        editor.commit();
    }
}
