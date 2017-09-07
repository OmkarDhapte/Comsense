package com.comsense.comsense.thread;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;
import java.util.Locale;

/**
 * Created by lenovo on 8/30/2017.
 */

public class GeocoderAddressThread extends AsyncTask<Object, Object, Object> {

    private final String TAG = GeocoderAddressThread.class.getName();

    private final String googleMapAddress = "Comsense Marketing LLP, Pune, Maharashtra";
    private Handler handler;
    private Context context;
    public GeocoderAddressThread(Context context,Handler handler) {
        this.handler = handler;
        this.context = context;
    }

    @Override
    protected Object doInBackground(Object... params) {
        return getGeoAddress();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Message message = new Message();
        message.obj = o;
        handler.sendMessage(message);
    }

    public LatLng getGeoAddress(){
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> address = geocoder.getFromLocationName(googleMapAddress,1);
            if (address == null || address.size()<=0) {
                return new LatLng(18.559658, 73.779938);   //Pune
            }

            Address add = address.get(0);
            add.getLatitude();
            add.getLongitude();

            return new LatLng(add.getLatitude(), add.getLongitude());
        } catch (Exception e) {
            Log.e(TAG, "getGeoAddress: ",e );
        }
        return new LatLng(18.559658, 73.779938);   //Pune
    }
}
