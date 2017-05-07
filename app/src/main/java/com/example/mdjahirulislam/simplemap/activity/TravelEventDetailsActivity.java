package com.example.mdjahirulislam.simplemap.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mdjahirulislam.simplemap.R;
import com.example.mdjahirulislam.simplemap.adapter.TabsPagerAdapter;
import com.example.mdjahirulislam.simplemap.database.DatabaseSource;
import com.example.mdjahirulislam.simplemap.interfaces.ConnectionApi;
import com.example.mdjahirulislam.simplemap.modelClass.TravelEventModel;
import com.example.mdjahirulislam.simplemap.sharePreference.UserSharePreference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

public class TravelEventDetailsActivity extends AppCompatActivity {

    private static final String TAG = TravelEventDetailsActivity.class.getSimpleName();


    private DatabaseSource databaseSource;
    private UserSharePreference userSharePreference;

    private LinearLayout linearLayout;
    private ViewPager viewPager;
    private TabsPagerAdapter tabsPagerAdapter;
    private TableLayout tableLayout;


    private ProgressDialog pDialog;
    private Dialog dialog;
    private Calendar calendar;

    private ArrayList<TravelEventModel> travelEventModelArrayList;

    private ConnectionApi connectionApi;

    private TextView eventNameTV;
    private TextView eventBudgetTV;
    private TextView eventFromDateTV;
    private TextView evenToDateTV;
    private String eventUniqueId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_event_detailes);

        initialization();

        tabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabsPagerAdapter);


        userSharePreference= new UserSharePreference(this);

        eventUniqueId = userSharePreference.getTravelUniqueEventId();

        Log.d(TAG,"eventUniqueId : ------------ "+eventUniqueId);
//        databaseSource.deleteTravelEventTable();
        travelEventModelArrayList = databaseSource.getSingleTravelEventDetails(Integer.valueOf(eventUniqueId));

        eventNameTV.setText(travelEventModelArrayList.get(0).getTravel_destination());
        eventBudgetTV.setText(travelEventModelArrayList.get(0).getEstimated_budget());
        eventFromDateTV.setText(travelEventModelArrayList.get(0).getFrom_date());
        evenToDateTV.setText(travelEventModelArrayList.get(0).getTo_date());



    }

    public void initialization(){
        eventNameTV= (TextView) findViewById(R.id.eventNameTv);
        eventBudgetTV= (TextView) findViewById(R.id.eventBudgetTV);
        eventFromDateTV = (TextView) findViewById(R.id.eventFromDateTV);
        evenToDateTV= (TextView) findViewById(R.id.eventToDateTV);

        travelEventModelArrayList = new ArrayList<>();

        linearLayout = (LinearLayout) findViewById(R.id.travelEventDetailsLayout);
        viewPager = (ViewPager) findViewById(R.id.pager);
        userSharePreference = new UserSharePreference(this);

        databaseSource = new DatabaseSource(this);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        dialog = new Dialog(this);

        calendar = Calendar.getInstance();





    }



    public void goToAddExpenseActivity(View view) {

        Intent intent = new Intent(TravelEventDetailsActivity.this,AddExpenseActivity.class);
        startActivity(intent);
    }




    public void goToAddMomentActivity(View view) {
        Toast.makeText(this, "Under Developing", Toast.LENGTH_SHORT).show();
    }




}
