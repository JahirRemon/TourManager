package com.example.mdjahirulislam.simplemap.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mdjahirulislam.simplemap.R;
import com.example.mdjahirulislam.simplemap.sharePreference.LocationSharedPreference;
import com.example.mdjahirulislam.simplemap.sharePreference.UserSharePreference;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.List;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Geocoder geocoder;
    private List<Address> addressList;
    private Button ok;

    private Double currentLatitude;
    private Double currentLongitude;

    private LocationSharedPreference locationSharedPreference;
    private UserSharePreference userSharePreference;

    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userSharePreference = new UserSharePreference(this);
        userId = userSharePreference.getUserId();

        if (!userId.equals("null")){
            Intent intent = new Intent(MainActivity.this,ShowTravelEventListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }

        locationSharedPreference = new LocationSharedPreference(this);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();



    }

    @Override
    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onPause() {
        googleApiClient.disconnect();
        super.onPause();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationRequest = LocationRequest.create()
                .setInterval(1000*60*5).setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();
        boolean status = locationSharedPreference.setLocation(String.valueOf(currentLatitude),
                String.valueOf(currentLongitude));
        if (status){
            Toast.makeText(this, "Location Store Successful", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, "Location Store Not Successful", Toast.LENGTH_SHORT).show();

        }

//        Log.d(TAG,"currentLatitude: "+ currentLatitude +" currentLongitude: "+ currentLongitude);
//        Toast.makeText(this, "getLatitude; "+ currentLatitude +"\ngetLongitude: "+ currentLongitude, Toast.LENGTH_SHORT).show();
    }

    public void goToSignIn(View view) {
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
//        finish();
    }

    public void goToSignUp(View view) {
        Intent intent = new Intent(MainActivity.this,RegistrationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
//        finish();
    }

    public void goToLocationNearBy(View view) {
        Toast.makeText(this, "lat: "+locationSharedPreference.getLocationLatitude()
                +"log: "+locationSharedPreference.getLocationLongitude(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this,MapsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
//        finish();
    }

    public void goToWeatherCondition(View view) {
    }
}
