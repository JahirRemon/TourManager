package com.example.mdjahirulislam.simplemap.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.mdjahirulislam.simplemap.R;
import com.example.mdjahirulislam.simplemap.sharePreference.LocationSharedPreference;
import com.example.mdjahirulislam.simplemap.utilities.Place;
import com.example.mdjahirulislam.simplemap.utilities.PlacesService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private LocationSharedPreference locationSharedPreference;

    private double lat;
    private double log;

    private String[] places;
    LatLng myPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        locationSharedPreference = new LocationSharedPreference(this);
        lat = Double.valueOf(locationSharedPreference.getLocationLatitude());
        log = Double.valueOf(locationSharedPreference.getLocationLongitude());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        -----------------------------Marge--------------------------
        places = getResources().getStringArray(R.array.places);

        Spinner spnNearByType = (Spinner) findViewById(R.id.spnNearByType);
        spnNearByType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if( position > 0 ){
                    new GetPlaces(MapsActivity.this, places[position].toLowerCase().replace("-", "_").replace(" ", "_")).execute();
                } else{
                    initMap(mMap);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //        -----------------------------end Marge--------------------------

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
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(lat, log);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in current Location"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 12));
//        mMap.getUiSettings().setZoomControlsEnabled(true);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        mMap.setMyLocationEnabled(true);
//        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        initMap(googleMap);
    }




    private void initMap(GoogleMap googleMap){
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        myPosition = new LatLng(lat, log);
        mMap.addMarker(new MarkerOptions().position(myPosition).title("My Position"));
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(myPosition , 17.5f) );
        mMap.setMapType(1);
        mMap.setMaxZoomPreference(21.5f);
        mMap.setMinZoomPreference(12.5f);
        mMap.resetMinMaxZoomPreference();
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
    }

    private class GetPlaces extends AsyncTask<Void, Void, ArrayList<Place>> {

        private ProgressDialog dialog;
        private Context context;
        private String places;

        public GetPlaces(Context context, String places) {
            this.context = context;
            this.places = places;
        }

        @Override
        protected void onPostExecute(ArrayList<Place> result) {
            super.onPostExecute(result);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }

            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(myPosition).title("My Position"));

            for (int i = 0; i < result.size(); i++) {
                mMap.addMarker(new MarkerOptions()
                        .title(result.get(i).getName())
                        .position(
                                new LatLng(result.get(i).getLatitude(), result
                                        .get(i).getLongitude()))
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.pin))
                        .snippet(result.get(i).getVicinity()));
            }

            mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(myPosition , 15.5f) );
            mMap.setMapType(1);
            mMap.setMaxZoomPreference(21.5f);
            mMap.setMinZoomPreference(12.5f);
            mMap.resetMinMaxZoomPreference();
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setZoomGesturesEnabled(true);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(context);
            dialog.setCancelable(false);
            dialog.setMessage("Loading..");
            dialog.isIndeterminate();
            dialog.show();
        }

        @Override
        protected ArrayList<Place> doInBackground(Void... arg0) {
            PlacesService service = new PlacesService("AIzaSyD9e0bMh0Ufy0DR4ampiLEuJQL8MfsDhRc");
            ArrayList<Place> findPlaces = service.findPlaces(lat, log, places);

            for (int i = 0; i < findPlaces.size(); i++) {

                Place placeDetail = findPlaces.get(i);
                Log.e("PLACE: ", "places : " + placeDetail.getName());
            }
            return findPlaces;
        }

    }







}
