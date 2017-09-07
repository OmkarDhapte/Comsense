package com.comsense.comsense;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.comsense.comsense.util.PermissionUtil;
import com.comsense.comsense.util.SharedPreferenceUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getName();
    private final int PERMISSION_REQUEST_CODE = 1;

    private RelativeLayout aboutUs, blog, services, contactUs, marketingCloud;

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        doubleBackPressAction();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "onCreate: Application Started");
        aboutUs = (RelativeLayout) findViewById(R.id.aboutUs);
        blog = (RelativeLayout) findViewById(R.id.blog);
        services = (RelativeLayout) findViewById(R.id.services);
        contactUs = (RelativeLayout) findViewById(R.id.contactUs);
        marketingCloud = (RelativeLayout) findViewById(R.id.marketingCloud);

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });

        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BlogActivity.class);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(),"Coming Soon.",Toast.LENGTH_SHORT).show();
            }
        });

        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServicesActivity.class);
                startActivity(intent);
            }
        });

        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactUsActivity.class);
                startActivity(intent);
            }
        });

        marketingCloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.ibm.com/watson/marketing-automation";
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        final ArrayList<String> permissions = new ArrayList<String>();
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        permissions.add(Manifest.permission.CALL_PHONE);
        PermissionUtil.checkPermission(this, permissions, new PermissionUtil.PermissionAskListener() {
            @Override
            public void onNeedPermission() {
                Log.e(TAG, "onNeedPermission:  call onNeed permission");
                ActivityCompat.requestPermissions(MainActivity.this, permissions.toArray(new String[0]), PERMISSION_REQUEST_CODE);
            }

            @Override
            public void onPermissionPreviouslyDenied() {
                Log.e(TAG, "onPermissionPreviouslyDenied: Previously Denied");
                ActivityCompat.requestPermissions(MainActivity.this,  permissions.toArray(new String[0]), PERMISSION_REQUEST_CODE);
            }

            @Override
            public void onPermissionDisabled() {
                Log.e(TAG, "onPermissionDisabled: Permission Disabled");
                ActivityCompat.requestPermissions(MainActivity.this,  permissions.toArray(new String[0]), PERMISSION_REQUEST_CODE);
                //Toast.makeText(getApplicationContext(), "Permission Disabled.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionGranted() {
                Log.e(TAG, "onPermissionGranted: Permission is already Granted");
            }
        });

        Log.e(TAG, "onCreate: Permission Shared Pref: " + SharedPreferenceUtil.getInstance(getApplicationContext()).isFirstTimeAskingPermission(getApplicationContext(), permissions.get(0)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.showRegDetails) {
            //show dialog
            showRegistrationDetailDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showRegistrationDetailDialog(){
        try {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.lay_reg_details, null);
            alertDialog.setView(view);
            alertDialog.setCancelable(true);
            alertDialog.setTitle("Registration Details");

            TextView txtAppKey, txtSdkRegId, txtUserId;
            txtAppKey = (TextView) view.findViewById(R.id.appKey);
            txtSdkRegId = (TextView) view.findViewById(R.id.sdkRegId);
            txtUserId = (TextView) view.findViewById(R.id.userId);

            SharedPreferenceUtil sharedPref = SharedPreferenceUtil.getInstance(getApplicationContext());
            String userId = sharedPref.getUserId() != null ? sharedPref.getUserId() : "Not Registered";
            String channelId = sharedPref.getChannelId() != null ? sharedPref.getChannelId() : "Not Registered";
            String appKey = sharedPref.getAppKey() != null ? sharedPref.getAppKey() : "Not Registered";

            txtAppKey.setText("App Key: "+appKey);
            txtSdkRegId.setText("Channel ID: "+channelId);
            txtUserId.setText("User ID: "+userId);

            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.setIcon(R.drawable.comsense_logo_1);
            alertDialog.show();
        } catch (Exception e) {
            Log.e(TAG, "showRegistrationDetailDialog: ", e);
        }
    }
    private void doubleBackPressAction() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
