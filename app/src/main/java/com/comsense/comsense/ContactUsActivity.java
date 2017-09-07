package com.comsense.comsense;

import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.comsense.comsense.thread.GeocoderAddressThread;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class ContactUsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private final String TAG = ContactUsActivity.class.getName();
    private GoogleMap mMap;
    private Button meetingInsights;

    private final String address = "<b>India Office</b><br>" +
            "Comsense Marketing LLP,<br>" +
            "Office 527, 5th Floor, Sterling Centre,<br>" +
            "MG Road, Pune, Maharashtra, India – 411001<br>" +
            "<br><b>Email</b><br>" +
            "connect@comsense.consulting<br>" +
            "<br><b>Cell Phone</b><br>" +
            "+91 98223 71062";


    private TextView txtAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        try {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            Log.e(TAG, "onCreate: ", e);
        }

        meetingInsights = (Button) findViewById(R.id.meetingInsights);
        meetingInsights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Coming Soon...", Toast.LENGTH_SHORT).show();
            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        txtAddress = (TextView) findViewById(R.id.address);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtAddress.setText(Html.fromHtml(address, Html.FROM_HTML_MODE_COMPACT));
        } else {
            txtAddress.setText(Html.fromHtml(address));
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        new GeocoderAddressThread(getApplicationContext(), new GeocoderHandler()).execute((Object[]) null);
    }

    private class GeocoderHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg != null) {
                LatLng pune = (LatLng) msg.obj;
                mMap.addMarker(new MarkerOptions().position(pune).title("Comsense Marketing LLP"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pune, 14.0f));
            }
        }
    }
}
