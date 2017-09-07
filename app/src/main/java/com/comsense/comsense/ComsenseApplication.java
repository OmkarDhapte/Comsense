package com.comsense.comsense;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.comsense.comsense.receiver.SampleGcmBroadcastReceiver;
import com.comsense.comsense.util.ResourcesHelper;
import com.comsense.comsense.util.SharedPreferenceUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.ibm.mce.sdk.api.MceApplication;
import com.ibm.mce.sdk.api.MceSdk;

/**
 * Created by lenovo on 9/7/2017.
 */

public class ComsenseApplication extends MceApplication {

    private final String TAG = ComsenseApplication.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();

        ResourcesHelper resourcesHelper = new ResourcesHelper(getResources(), getPackageName());

        /**
         * Custom layout
         */

        MceSdk.getNotificationsClient().setCustomNotificationLayout(this,
                resourcesHelper.getString("expandable_layout_type"),
                resourcesHelper.getLayoutId("custom_notification"),
                resourcesHelper.getId("bigText"),
                resourcesHelper.getId("bigImage"), resourcesHelper.getId("action1"),
                resourcesHelper.getId("action2"),
                resourcesHelper.getId("action3"));

        MceSdk.getNotificationsClient().getNotificationsPreference().setSoundEnabled(getApplicationContext(), true);
        MceSdk.getNotificationsClient().getNotificationsPreference().setSound(getApplicationContext(), resourcesHelper.getRawId("notification_sound"));
        MceSdk.getNotificationsClient().getNotificationsPreference().setVibrateEnabled(getApplicationContext(), true);
        long[] vibrate = {0, 100, 200, 300};
        MceSdk.getNotificationsClient().getNotificationsPreference().setVibrationPattern(getApplicationContext(), vibrate);
        MceSdk.getNotificationsClient().getNotificationsPreference().setIcon(getApplicationContext(), resourcesHelper.getDrawableId("comsense_logo_1"));
        MceSdk.getNotificationsClient().getNotificationsPreference().setLightsEnabled(getApplicationContext(), true);
        int ledARGB = 0x00a2ff;
        int ledOnMS = 300;
        int ledOffMS = 1000;
        MceSdk.getNotificationsClient().getNotificationsPreference().setLights(getApplicationContext(), new int[]{ledARGB, ledOnMS, ledOffMS});


        if (SampleGcmBroadcastReceiver.SENDER_ID != null) {
            String registeredSenderId = SharedPreferenceUtil.getInstance(getApplicationContext()).getSenderId();
            if (!SampleGcmBroadcastReceiver.SENDER_ID.equals(registeredSenderId)) {
                (new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                            String regid = gcm.register(SampleGcmBroadcastReceiver.SENDER_ID);
                            Log.i(TAG, "GCM registration id: " + regid);
                            SharedPreferenceUtil.getInstance(getApplicationContext()).setSenderId(SampleGcmBroadcastReceiver.SENDER_ID);
                        } catch (Exception e) {
                            Log.e(TAG, "Failed to register GCM", e);
                        }
                    }
                })).start();
            }
        }
    }
}
