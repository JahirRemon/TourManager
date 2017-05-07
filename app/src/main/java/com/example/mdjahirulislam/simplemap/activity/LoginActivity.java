package com.example.mdjahirulislam.simplemap.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mdjahirulislam.simplemap.R;
import com.example.mdjahirulislam.simplemap.app.AppConfig;
import com.example.mdjahirulislam.simplemap.database.DatabaseSource;
import com.example.mdjahirulislam.simplemap.interfaces.ConnectionApi;
import com.example.mdjahirulislam.simplemap.pojoClass.LogInResponseModel;
import com.example.mdjahirulislam.simplemap.modelClass.RegistrationModel;
import com.example.mdjahirulislam.simplemap.modelClass.TravelEventModel;
import com.example.mdjahirulislam.simplemap.sharePreference.UserSharePreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    private static final String TAG = LoginActivity.class.getSimpleName();

    private UserSharePreference userSharePreference;
    private DatabaseSource databaseSource;

    private ProgressDialog pDialog;

    private ConnectionApi connectionApi;
    private RegistrationModel registrationModel;
    private LogInResponseModel logInResponseModel;

    private TravelEventModel travelEventModel;


    private EditText userNameET;
    private EditText passwordET;

    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialization();

        userId = userSharePreference.getUserId();

        if (!userId.equals("null")){
            Intent intent = new Intent(LoginActivity.this,ShowTravelEventListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(AppConfig.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
        connectionApi = AppConfig.getRetrofit().create(ConnectionApi.class);

    }

    public void initialization(){
        userNameET = (EditText) findViewById(R.id.loginUserName);
        passwordET = (EditText) findViewById(R.id.loginPassword);
        userSharePreference = new UserSharePreference(this);

        databaseSource = new DatabaseSource(this);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

    }


    public void signIn(View view) {
        String userName = userNameET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();

        if (userName.isEmpty()){
            userNameET.setError("Please Enter Your User Name");
        }else if(password.isEmpty()){
            passwordET.setError("Please Enter Your Password");
        }else {

            pDialog.setMessage("Logging in ...");
            showDialog();

            registrationModel = new RegistrationModel(userName,password);
            Call<LogInResponseModel> logInResponseModelCall = connectionApi.logInUser(registrationModel);

            logInResponseModelCall.enqueue(new Callback<LogInResponseModel>() {
                @Override
                public void onResponse(Call<LogInResponseModel> call, Response<LogInResponseModel> response) {

                    hideDialog();

                    Log.d(TAG,"Response Code : "+response.code());
                    Log.d(TAG,"Response Code : "+response.body().getErrorMsg());
                    if (response.code() == 200){
                        logInResponseModel = response.body();
                        boolean error = logInResponseModel.getError();
                        Log.d(TAG,"Error : "+error);
                        if (!error){

                            userId = logInResponseModel.getUser().getUserUniqueId();
                            userSharePreference.setUserId(userId);

                            Log.d(TAG,"Response User_Unique_Id : "+userId);

                            String userUniqueId = logInResponseModel.getUser().getUserUniqueId();
                            String fullName = logInResponseModel.getUser().getUserFullName();
                            String userName = logInResponseModel.getUser().getUserName();
                            String mobile = logInResponseModel.getUser().getUserMobileNo();
                            String address = logInResponseModel.getUser().getUserAddress();
                            String createdAt = logInResponseModel.getUser().getCreatedAt();


                            boolean event_exists = logInResponseModel.getEventExists();

                            if (event_exists){
                                int travelEventListSize = logInResponseModel.getTravelEvent().size();
                                for (int i = 0; i<travelEventListSize; i++ ){
                                    String eventUniqueId = logInResponseModel.getTravelEvent().get(i).getTravelEventUniqueId();
                                    String eventUserUniqueId = logInResponseModel.getTravelEvent().get(i).getUserUniqueId();
                                    String eventDestination = logInResponseModel.getTravelEvent().get(i).getTravelDestination();
                                    String eventBudget = logInResponseModel.getTravelEvent().get(i).getEstimatedBudget();
                                    String eventFromDate = logInResponseModel.getTravelEvent().get(i).getFromDate();
                                    String eventToDate = logInResponseModel.getTravelEvent().get(i).getToDate();
                                    String eventCreatedAt = logInResponseModel.getTravelEvent().get(i).getCreatedAt();

                                    travelEventModel = new TravelEventModel(eventUniqueId,eventUserUniqueId,eventDestination,
                                            eventBudget,eventFromDate,eventToDate,eventCreatedAt);

                                    boolean storeEvent = databaseSource.addTravelEvent(travelEventModel);
                                    if (storeEvent){
                                        Log.d(TAG,"Store Travel Event Successfully in SQLite.");
                                    }else {
                                        Log.d(TAG,"Store Travel Event Unsuccessfully in SQLite.");
                                    }

                                }



                            }else {
                                String eventMsg = logInResponseModel.getEventMsg();
                                Log.d(TAG,"Event Message: "+eventMsg);
                            }





                            registrationModel = new RegistrationModel(userUniqueId,fullName,userName,mobile,address,createdAt);
                            boolean status = databaseSource.addUser(registrationModel);

                            if (status){
                                Intent intent = new Intent(LoginActivity.this,ShowTravelEventListActivity.class);


                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(LoginActivity.this, "User not store in SQLite", Toast.LENGTH_SHORT).show();
                            }


                        }else {
                            String error_msg = logInResponseModel.getErrorMsg();
                            Toast.makeText(LoginActivity.this, error_msg, Toast.LENGTH_SHORT).show();
//                            userNameET.setText("");
                            passwordET.setText("");
                        }

                    }else{
                        Toast.makeText(LoginActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LogInResponseModel> call, Throwable t) {

                    Log.e(TAG,t.toString());
                    Toast.makeText(LoginActivity.this, "Connection Failed", Toast.LENGTH_SHORT).show();
                    hideDialog();
                }


            });

        }
    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}

