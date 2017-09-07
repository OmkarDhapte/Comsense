package com.comsense.comsense.mce;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.comsense.comsense.R;
import com.comsense.comsense.util.SharedPreferenceUtil;
import com.ibm.mce.sdk.api.MceBroadcastReceiver;
import com.ibm.mce.sdk.api.MceSdk;
import com.ibm.mce.sdk.api.attribute.Attribute;
import com.ibm.mce.sdk.api.attribute.AttributesOperation;
import com.ibm.mce.sdk.api.broadcast.EventBroadcastUtil;
import com.ibm.mce.sdk.api.event.Event;

import com.ibm.mce.sdk.api.notification.NotificationDetails;
import com.ibm.mce.sdk.api.registration.RegistrationDetails;
import com.ibm.mce.sdk.location.MceLocation;
import com.ibm.mce.sdk.plugin.inapp.InAppManager;
import com.ibm.mce.sdk.util.Logger;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by lenovo on 8/10/2017.
 */

public class MyMceBroadcastReceiver extends MceBroadcastReceiver {

    private final String TAG = MyMceBroadcastReceiver.class.getName();

    public static String ACTION_KEY = "action";
    public static String ACTION_SDK_REGISTRATION = "sdkreg";
    public static String ACTION_GCM_REGISTRATION = "gcmreg";

    private SharedPreferenceUtil sharedPref;

    @Override
    public void onSdkRegistered(Context context) {
        // Handle the SDK registration event
        // context - The application context
        Log.e(TAG, "onSdkRegistered: SDK is registered");
        RegistrationDetails registrationDetails = MceSdk.getRegistrationClient().getRegistrationDetails(context);
        Log.e(TAG, "-- SDK registered");
        Log.e(TAG, "Channel ID is: " + registrationDetails.getChannelId());
        Log.e(TAG, "User ID is: " + registrationDetails.getUserId());

        sharedPref = SharedPreferenceUtil.getInstance(context);
        sharedPref.setChannelId(registrationDetails.getChannelId());
        sharedPref.setUserId(registrationDetails.getUserId());
        sharedPref.setAppKey(MceSdk.getRegistrationClient().getAppKey(context));
        //showNotification(context,  "SDK Registration",  "Channel ID"+": "+registrationDetails.getChannelId(), ACTION_SDK_REGISTRATION);
        //showNotification(context,  "Device Registration",  "Registration ID: "+registrationDetails.getChannelId(), ACTION_SDK_REGISTRATION);
    }

    @Override
    public void onDeliveryChannelRegistered(Context context) {
        // Handle the GCM registration event
        // context - The application context
        RegistrationDetails registrationDetails = MceSdk.getRegistrationClient().getRegistrationDetails(context);
        Log.e(TAG, "-- SDK delivery channel registered");
        Log.e(TAG, "Registration ID  is: " + registrationDetails.getPushToken());
        Log.e(TAG, "-- SDK registered");
        Log.e(TAG, "Channel ID is: " + registrationDetails.getChannelId());
        Log.e(TAG, "User ID is: " + registrationDetails.getUserId());

        sharedPref = SharedPreferenceUtil.getInstance(context);
        sharedPref.setChannelId(registrationDetails.getChannelId());
        sharedPref.setUserId(registrationDetails.getUserId());
        sharedPref.setAppKey(MceSdk.getRegistrationClient().getAppKey(context));
        //showNotification(context, "GCM Registration", registrationDetails.getPushToken(), ACTION_GCM_REGISTRATION);
    }

    @Override
    public void onSdkRegistrationChanged(Context context) {
        // context - The application context
        RegistrationDetails registrationDetails = MceSdk.getRegistrationClient().getRegistrationDetails(context);
        Log.e(TAG, "-- SDK registration changed");
        Log.e(TAG, "Channel ID is: " + registrationDetails.getChannelId());
        Log.e(TAG, "User ID is: " + registrationDetails.getUserId());

        sharedPref = SharedPreferenceUtil.getInstance(context);
        sharedPref.setChannelId(registrationDetails.getChannelId());
        sharedPref.setUserId(registrationDetails.getUserId());
        sharedPref.setAppKey(MceSdk.getRegistrationClient().getAppKey(context));
        //showNotification(context, "SDK Registration", "Channel ID" + ": " + registrationDetails.getChannelId(), ACTION_SDK_REGISTRATION);
    }

    @Override
    public void onMessage(Context context, NotificationDetails notificationDetails, Bundle bundle) {
        //Handle the notification received event
        // context - The application context
        // notificationDetails - The received notification
        // extraPayload- Additional payload that arrived with the notification
        if (notificationDetails != null) {
            Log.e(TAG, "-- SDK delivery channel message received");
            Log.e(TAG, "Subject is: " + notificationDetails.getSubject());
            Log.e(TAG, "Message is: " + notificationDetails.getMessage());
        }
        String attribution = null;
        if (notificationDetails != null && notificationDetails.getMceNotificationPayload() != null) {
            attribution = notificationDetails.getMceNotificationPayload().getAttribution();
        }
        String mailingId = null;
        if (notificationDetails != null && notificationDetails.getMceNotificationPayload() != null) {
            mailingId = notificationDetails.getMceNotificationPayload().getMailingId();
            Log.e(TAG, "onMessage: MailingID: " + mailingId);
        }
        if (notificationDetails.isSoundEnabled()) {
            Log.e(TAG, "onMessage: Sound enable");
        } else {
            Log.e(TAG, "onMessage: Sound Disable");
        }
        //showNotification(context,  notificationDetails.getSubject(),  notificationDetails.getMessage(), ACTION_KEY);
        //InAppManager.handleNotification(context, bundle, attribution, mailingId);
    }

    @Override
    public void onC2dmError(Context context, String s) {
        // Handle the error
        // context - The application context
        // errorCode- The GCM error code
    }

    @Override
    public void onSessionStart(Context context, Date date) {
        // sessionStartDate- The new session start time
        Log.e(TAG, "-- SDK session started");
        Log.e(TAG, "Date is: " + date);
    }

    @Override
    public void onSessionEnd(Context context, Date date, long sessionDurationInMinutes) {
        // context - The application context
        //sessionEndDate- The session end time
        // sessionDurationInMinutes - The session duration in minutes
        Log.e(TAG, "-- SDK session ended");
        Log.e(TAG, "Date is: " + date);
        Log.e(TAG, "Session duration is: " + sessionDurationInMinutes);
    }

    @Override
    public void onNotificationAction(Context context, Date actionTime, String pushType,
                                     String actionType, String actionValue) {
        // actionTime- The time the action was clicked on
        // pushType - always "simple"
        // actionType - The type of the action
        // actionValue - the value of the "value" key in the payload.
        Log.e(TAG, "-- SDK notification clicked");
        Log.e(TAG, "Date is: " + actionTime);
        Log.e(TAG, "Push type is: " + pushType);
        Log.e(TAG, "Action type is: " + actionType);
        Log.e(TAG, "Action values is: " + actionValue);
    }

    @Override
    public void onAttributesOperation(Context context, AttributesOperation attributesOperation) {
        // attributesOperation - The operation that was executed
        Log.e(TAG, "-- Attributes operation performed");
        Log.e(TAG, "Type is: " + attributesOperation.getType());
        if(attributesOperation.getAttributeKeys() != null) {
            Log.e(TAG, "Keys: "+attributesOperation.getAttributeKeys());
        } else if(attributesOperation.getAttributes() != null) {
            String attributesStr = getAttributesString(attributesOperation.getAttributes());
            Log.e(TAG, "Attributes: "+attributesStr);
        }
    }

    private String getAttributesString(List<Attribute> attributes) {
        if(attributes == null) {
            return "null";
        }
        StringBuilder builder = new StringBuilder("{");
        if(!attributes.isEmpty()) {
            Attribute attribute = attributes.get(0);
            builder.append("{type = ").append(attribute.getType())
                    .append(", key = ").append(attribute.getValue())
                    .append(", value = ").append(attribute.getValue()).append("}");
            for(int i = 1 ; i < attributes.size() ; ++i) {
                attribute = attributes.get(i);
                builder.append(", {type = ").append(attribute.getType())
                        .append(", key = ").append(attribute.getValue())
                        .append(", value = ").append(attribute.getValue()).append("}");
            }
        }
        builder.append("}");
        return builder.toString();
    }


    @Override
    public void onEventsSend(Context context, List<Event> events) {
        // events- The events that were sent
        Log.e(TAG, "-- Events were sent");
        StringBuilder builder = new StringBuilder("{");
        if(events!=null && !events.isEmpty()) {
            Event event = events.get(0);
            builder.append("{type = ").append(event.getType())
                    .append(", name = ").append(event.getName())
                    .append(", timestamp = ").append(event.getTimestamp())
                    .append(", attributes = ").append(getAttributesString(event.getAttributes()))
                    .append(", attribution = ").append(event.getAttribution()).append("}");
            for(int i = 1 ; i < events.size() ; ++i) {
                event = events.get(i);
                builder.append("{type = ").append(event.getType())
                        .append(", name = ").append(event.getName())
                        .append(", timestamp = ").append(event.getTimestamp())
                        .append(", attributes = ").append(getAttributesString(event.getAttributes()))
                        .append(", attribution = ").append(event.getAttribution()).append("}");
            }
        }
        builder.append("}");
        Log.e(TAG, "Events: " + builder.toString());
    }

    @Override
    public void onIllegalNotification(Context context, Intent intent) {
        // intent- The intent that contains the illegal notification
        Log.e(TAG, "-- Illegal SDK notification received");
    }

    @Override
    public void onNonMceBroadcast(Context context, Intent intent) {
        // intent- The intent that contains the non MCE broadcast
        Log.e(TAG, "-- Non SDK broadcast received");
    }

    /**
     * This method is called when a location event occurs
     *
     * @param location          The related location
     * @param locationType      The related location type
     * @param locationEventType The related location event type
     */
    //public abstract void onLocationEvent(Context context, MceLocation location, LocationType locationType, LocationEventType locationEventType);


    @Override
    public void onLocationEvent(Context context, MceLocation location, LocationType locationType, LocationEventType locationEventType) {
        Log.d(TAG, "Location event:  "+locationType.name()+" "+locationEventType.name()+" id = "+location.getId());
        showNotification(context, locationType.name()+" "+locationEventType.name(), location.getId(), locationType.name());
    }

    /**
     * This method is called when the device location is updated
     *
     * @param context  The application's context
     * @param location The device location
     */
//    public abstract void onLocationUpdate(Context context, Location location);


    @Override
    public void onLocationUpdate(Context context, Location location) {
        Log.d(TAG, "Location was updated "+location);
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        try {
            EventBroadcastUtil.handleBroadcast(context, intent, this);
        } catch (Throwable t) {
            Logger.e(TAG, "Unexpected error", t);
        }
    }

    protected void showNotification(Context context, String subject, String message, String action) {
        //ResourcesHelper resourcesHelper = new ResourcesHelper(context.getResources(), context.getPackageName());
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent actionIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        actionIntent.putExtra(ACTION_KEY, action);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(subject)
                .setContentText(message)
                .setContentIntent(PendingIntent.getActivity(context, action.hashCode(), actionIntent, 0));
        Notification notification = builder.build();
        setNotificationPreferences(context, notification);
        UUID notifUUID = UUID.randomUUID();
        notificationManager.notify(notifUUID.hashCode(), notification);

    }
    protected void setNotificationPreferences(Context context,
                                              Notification notification) {
        if (MceSdk.getNotificationsClient().getNotificationsPreference().isSoundEnabled(context)) {
            Integer sound = MceSdk.getNotificationsClient().getNotificationsPreference().getSound(context);
            if (sound == null) {
                notification.defaults |= Notification.DEFAULT_SOUND;
            } else {
                notification.sound = Uri.parse("android.resource://" + context.getPackageName() + "/" + sound);
            }
        }
        if (MceSdk.getNotificationsClient().getNotificationsPreference().isVibrateEnabled(context)) {
            long[] vibrationPattern = MceSdk.getNotificationsClient().getNotificationsPreference().getVibrationPattern(context);
            if (vibrationPattern == null || vibrationPattern.length == 0) {
                notification.defaults |= Notification.DEFAULT_VIBRATE;
            } else {
                notification.vibrate = vibrationPattern;
            }
        }
        if (MceSdk.getNotificationsClient().getNotificationsPreference().isLightsEnabled(context)) {
            notification.flags |= Notification.FLAG_SHOW_LIGHTS;
            int[] lightsPref = MceSdk.getNotificationsClient().getNotificationsPreference().getLights(context);
            if (lightsPref == null || lightsPref.length < 3) {
                notification.defaults |= Notification.DEFAULT_LIGHTS;
            } else {
                notification.ledARGB = lightsPref[0];
                notification.ledOnMS = lightsPref[1];
                notification.ledOffMS = lightsPref[2];
            }
        }

        notification.flags |= Notification.FLAG_AUTO_CANCEL;
    }
}
