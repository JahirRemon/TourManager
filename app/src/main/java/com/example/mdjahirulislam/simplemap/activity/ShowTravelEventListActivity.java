package com.example.mdjahirulislam.simplemap.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mdjahirulislam.simplemap.R;
import com.example.mdjahirulislam.simplemap.adapter.TravelEventCustomAdapterForRecyclerView;
import com.example.mdjahirulislam.simplemap.app.AppConfig;
import com.example.mdjahirulislam.simplemap.database.DatabaseSource;
import com.example.mdjahirulislam.simplemap.interfaces.ConnectionApi;
import com.example.mdjahirulislam.simplemap.modelClass.ExpenseModel;
import com.example.mdjahirulislam.simplemap.pojoClass.AddTravelEventResponseModel;
import com.example.mdjahirulislam.simplemap.modelClass.RegistrationModel;
import com.example.mdjahirulislam.simplemap.modelClass.TravelEventModel;
import com.example.mdjahirulislam.simplemap.pojoClass.ExpenseResponseModel;
import com.example.mdjahirulislam.simplemap.sharePreference.UserSharePreference;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowTravelEventListActivity extends AppCompatActivity{

    private static final String TAG = ShowTravelEventListActivity.class.getSimpleName();

    private UserSharePreference userSharePreference;
    private ConnectionApi getResponse;

    private DatabaseSource databaseSource;

    private ProgressDialog pDialog;


    private TextView userFullNameTV;
    private TextView userMobileNoTV;
    private TextView noEventTV;
//    private ListView travelEventListLV;
    private RecyclerView travelEventRV;


    private String userFullName;
    private String userMobile;
    private String userId;

    private Dialog dialog;
    private TravelEventModel travelEventModel;
    private AddTravelEventResponseModel addTravelEventResponseModel;
    private ExpenseResponseModel expenseResponseModel;
    private ExpenseModel expenseModel;


    private ArrayList<AddTravelEventResponseModel.TravelEvent> addTravelEventResponseModelArrayList;
    private ArrayList<TravelEventModel> travelEventModelArrayList;
    private ArrayList<ExpenseResponseModel.TravelEventExpense> travelEventExpenseArrayList;
    private ArrayList<ExpenseModel> expenseModelArrayList;


    private ArrayList<RegistrationModel> userDetailsArrayList;

//    private TravelEventCustomAdapter travelEventCustomAdapter;

    private TravelEventCustomAdapterForRecyclerView travelEventCustomAdapter;

    private Calendar calendar;

    private SimpleDateFormat simpleDateFormat;
    private LinearLayoutManager linearLayoutManager;

    private String eventId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_travel_event_list);
        initialization();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitle("Home");

        userId = userSharePreference.getUserId();

        userDetailsArrayList = databaseSource.getSingleUserDetails(Integer.valueOf(userId));

        userFullName = userDetailsArrayList.get(0).getUser_full_name();
        userMobile = userDetailsArrayList.get(0).getUser_mobile_no();

        userFullNameTV.setText(userFullName);
        userMobileNoTV.setText(userMobile);

        travelEventModelArrayList = databaseSource.getAllTravelEvent(userId);
//        travelEventCustomAdapter = new TravelEventCustomAdapter(this,travelEventModelArrayList);
        Log.d(TAG,"event size : "+travelEventModelArrayList.size());
        travelEventCustomAdapter = new TravelEventCustomAdapterForRecyclerView(travelEventModelArrayList);
//        travelEventRV.setHasFixedSize(true);
//        travelEventRV.setLayoutManager(linearLayoutManager);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        travelEventRV.setLayoutManager(layoutManager);
        travelEventRV.setItemAnimator(new DefaultItemAnimator());



        travelEventRV.setAdapter(travelEventCustomAdapter);

        travelEventRV.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), travelEventRV, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                eventId = travelEventModelArrayList.get(position).getTravel_event_unique_id();
                Log.d(TAG,"position : "+position+" \neventId : "+eventId);



                userSharePreference.setTravelEventUniqueId(eventId);

                addEventExpense(eventId);
//                Intent intent = new Intent(ShowTravelEventListActivity.this,TravelEventDetailsActivity.class);
//
//                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

//        travelEventListLV.setAdapter(travelEventCustomAdapter);
//        travelEventRV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String eventId = travelEventModelArrayList.get(position).getTravel_event_unique_id();
//                Log.d(TAG,"position : "+position+" \nid : "+id+" \neventId : "+eventId);
//                userSharePreference.setTravelEventUniqueId(eventId);
//                Intent intent = new Intent(ShowTravelEventListActivity.this,TravelEventDetailsActivity.class);
//
//                startActivity(intent);
//            }
//        });

        if (travelEventModelArrayList.size()>0){

            noEventTV.setVisibility(View.GONE);

        }else {
            noEventTV.setVisibility(View.VISIBLE);
        }

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(AppConfig.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        connectionApi = retrofit.create(ConnectionApi.class);
        getResponse = AppConfig.getRetrofit().create(ConnectionApi.class);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.user_menu,menu);

        return true;
    }

    private void initialization() {
        userFullNameTV = (TextView) findViewById(R.id.userFullName);
        userMobileNoTV = (TextView) findViewById(R.id.userMobileNo);
        noEventTV = (TextView) findViewById(R.id.noEventTV);

//        travelEventListLV = (ListView) findViewById(R.id.travelEventListLV);
        travelEventRV = (RecyclerView) findViewById(R.id.travelEventListLV);

        userSharePreference = new UserSharePreference(this);
        dialog = new Dialog(this);

        databaseSource = new DatabaseSource(this);
        pDialog = new ProgressDialog(this);

        addTravelEventResponseModelArrayList = new ArrayList<>();

        travelEventModelArrayList = new ArrayList<>();

        userDetailsArrayList = new ArrayList<>();


        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd/mm/yy");

        travelEventExpenseArrayList = new ArrayList<>();
        expenseModelArrayList = new ArrayList<>();

    }



    public void logout(MenuItem item) {

        boolean status = userSharePreference.resetUserId();
        if (status){

            databaseSource.whenLogoutUser();

            Intent intent = new Intent(ShowTravelEventListActivity.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();

        }else {
            Toast.makeText(this, "Logout not successfully", Toast.LENGTH_SHORT).show();
        }


    }
    public void goToWeatherConditionActivity(MenuItem item) {
        Intent intent = new Intent(ShowTravelEventListActivity.this,WeatherConditionActivity.class);
        startActivity(intent);
    }

    public void goToNearBy(MenuItem item) {

        Intent intent = new Intent(ShowTravelEventListActivity.this,MapsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
//        finish();

    }

//    private DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//        }
//    };
    public void showAddEventDialog(View view) {

        dialog.setContentView(R.layout.add_travel_event_dialog);
        dialog.setTitle("Travel Event Info");


        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int date = calendar.get(Calendar.DAY_OF_MONTH);


        final EditText destinationET = (EditText) dialog.findViewById(R.id.event_destination);
        final EditText budgetET = (EditText) dialog.findViewById(R.id.event_budget);
        final TextView fromDateTV = (TextView) dialog.findViewById(R.id.event_from_date);
        final TextView toDateTV = (TextView) dialog.findViewById(R.id.event_to_date);
        Button addEventBtn = (Button) dialog.findViewById(R.id.addEventBtn);



        fromDateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                DatePickerDialog datePickerDialog=new DatePickerDialog(ShowTravelEventListActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        calendar.set(Calendar.MONTH,month+1);
                        calendar.set(Calendar.YEAR,year);
//                        String dateString = sdf.format(calendar.getTimeInMillis());
                        fromDateTV.setText(calendar.get(Calendar.DAY_OF_MONTH)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR));

                    }
                },year,month,date);
                datePickerDialog.show();
            }
        });


        toDateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(ShowTravelEventListActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        calendar.set(Calendar.MONTH,month+1);
                        calendar.set(Calendar.YEAR,year);
//                        String dateString = sdf.format(calendar.getTimeInMillis());
                        toDateTV.setText(calendar.get(Calendar.DAY_OF_MONTH)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR));

                    }
                },year,month,date);
                datePickerDialog.show();
            }
        });





        addEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String destination = destinationET.getText().toString().trim();
                String budget = budgetET.getText().toString().trim();
                String fromDate = fromDateTV.getText().toString().trim();
                String toDate = toDateTV.getText().toString().trim();

                if (destination.isEmpty()){
                    destinationET.setError("Please Enter Your Destination");
                }else if(budget.isEmpty()){
                    budgetET.setError("Please Enter Your Budget");
                }else if (fromDate.isEmpty()){
                    fromDateTV.setError("Required Field");
                }else if (toDate.isEmpty()){
                    toDateTV.setError("Required Field");
                }else {

                    pDialog.setMessage("Adding Travel Event ...");
                    showDialog();

                    Log.d(TAG,userId+"\n"+destination+"\n"+budget+"\n"+fromDate+"\n"+toDate);
                    travelEventModel = new TravelEventModel(userId,destination,budget,fromDate,toDate);


                    final Call<AddTravelEventResponseModel> addTravelEventResponseModelCall = getResponse.addTravelEvent(travelEventModel);

                    addTravelEventResponseModelCall.enqueue(new Callback<AddTravelEventResponseModel>() {
                        @Override
                        public void onResponse(Call<AddTravelEventResponseModel> call, Response<AddTravelEventResponseModel> response) {

                            hideDialog();

                            Log.d(TAG,"Response Code : "+response.code());
                            Log.d(TAG,"Response Code : "+response.body().getErrorMsg());
                            if (response.code() == 200){
                                addTravelEventResponseModel = response.body();
                                boolean error = addTravelEventResponseModel.getError();
                                Log.d(TAG,"Error : "+error);
                                if (!error){

                                    databaseSource.deleteTravelEventTable();

                                    addTravelEventResponseModelArrayList = (ArrayList<AddTravelEventResponseModel.TravelEvent>) addTravelEventResponseModel.getTravelEvent();

                                    Log.d(TAG, String.valueOf(addTravelEventResponseModelArrayList.size()));

                                    travelEventModelArrayList.clear();

                                    for (int i = 0; i < addTravelEventResponseModelArrayList.size(); i++){

                                        String eventUniqueId = addTravelEventResponseModelArrayList.get(i).getTravelEventUniqueId();
                                        String destination = addTravelEventResponseModelArrayList.get(i).getTravelDestination();
                                        String budget = addTravelEventResponseModelArrayList.get(i).getEstimatedBudget();
                                        String fromDate = addTravelEventResponseModelArrayList.get(i).getFromDate();
                                        String toDate = addTravelEventResponseModelArrayList.get(i).getToDate();
                                        String createdAt = addTravelEventResponseModelArrayList.get(i).getCreatedAt();

                                        travelEventModel = new TravelEventModel(eventUniqueId,userId,destination,budget,fromDate,toDate,createdAt);

                                        boolean storeEvent = databaseSource.addTravelEvent(travelEventModel);
                                        if (storeEvent){
                                            travelEventModelArrayList.add(travelEventModel);
                                            Log.d(TAG,i+": Store Travel Event Successfully in SQLite.");
                                        }else {
                                            Log.d(TAG,i+": Store Travel Event Unsuccessfully in SQLite.");
                                        }


                                    }


                                    travelEventCustomAdapter.notifyDataSetChanged();

                                }else {
                                    String error_msg = addTravelEventResponseModel.getErrorMsg();
                                    Toast.makeText(ShowTravelEventListActivity.this, error_msg, Toast.LENGTH_SHORT).show();
                                }

                            }else{
                                Toast.makeText(ShowTravelEventListActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<AddTravelEventResponseModel> call, Throwable t) {

                            Log.e(TAG,t.toString());
                            Toast.makeText(ShowTravelEventListActivity.this, "Connection Failed", Toast.LENGTH_SHORT).show();
                            hideDialog();
                        }


                    });

                }

                dialog.cancel();


            }
        });

        dialog.show();
    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void addEventExpense(final String eventId){

        RequestBody travel_event_unique_id = RequestBody.create(MultipartBody.FORM,eventId);

        pDialog.setMessage("Loading.....");
        showDialog();

        Call<ExpenseResponseModel> call = getResponse.addExpense(travel_event_unique_id);

        call.enqueue(new Callback<ExpenseResponseModel>() {
            @Override
            public void onResponse(Call<ExpenseResponseModel> call, Response<ExpenseResponseModel> response) {
                if (response.code() == 200) {
                    expenseResponseModel = response.body();
                    boolean error = expenseResponseModel.getError();

                    if (!error){

                        databaseSource.deleteTravelEventExpenseTable();
                        travelEventExpenseArrayList = (ArrayList<ExpenseResponseModel.TravelEventExpense>) expenseResponseModel.getTravelEventExpense();

                        for (int i = 0; i<travelEventExpenseArrayList.size(); i++){

                            String expenseUniqueId = travelEventExpenseArrayList.get(i).getExpenseUniqueId();
                            String expenseDetails = travelEventExpenseArrayList.get(i).getExpenseDetails();
                            String expenseAmount =travelEventExpenseArrayList.get(i).getExpenseAmount();
                            String expenseDate = travelEventExpenseArrayList.get(i).getExpenseDate();
                            String expenseVoucher = travelEventExpenseArrayList.get(i).getExpenseVoucher();
                            String createdAt = travelEventExpenseArrayList.get(i).getCreatedAt();

                            expenseModel = new ExpenseModel(expenseUniqueId,eventId,expenseDetails,expenseAmount,expenseDate,expenseVoucher,createdAt);

                            boolean addExpenseInSQLite = databaseSource.addTravelEventExpense(expenseModel);
                            if (addExpenseInSQLite){
                                Log.d(TAG,"add expense info in SQLite from showTravelEventListActivity  "+expenseUniqueId);

                            }else {
                                Toast.makeText(ShowTravelEventListActivity.this, "Expense not store in SQLite", Toast.LENGTH_SHORT).show();
                            }
                        }



                    }else {
                        Log.d(TAG,"Error : True ---- msg : "+expenseResponseModel.getErrorMsg());
                    }


                    Intent intent = new Intent(ShowTravelEventListActivity.this,TravelEventDetailsActivity.class);
                    startActivity(intent);

                    Log.d(TAG,"Response: "+expenseResponseModel.getErrorMsg());
                    hideDialog();
                }
            }

            @Override
            public void onFailure(Call<ExpenseResponseModel> call, Throwable t) {
                Intent intent = new Intent(ShowTravelEventListActivity.this,TravelEventDetailsActivity.class);
                startActivity(intent);
                Log.e(TAG,t.toString());
                Toast.makeText(ShowTravelEventListActivity.this, "Connection Failed", Toast.LENGTH_SHORT).show();
                hideDialog();
            }
        });
    }



}
