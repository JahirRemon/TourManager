package com.example.mdjahirulislam.simplemap.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mdjahirulislam.simplemap.app.AppConfig;
import com.example.mdjahirulislam.simplemap.interfaces.ConnectionApi;
import com.example.mdjahirulislam.simplemap.R;
import com.example.mdjahirulislam.simplemap.modelClass.RegistrationModel;
import com.example.mdjahirulislam.simplemap.pojoClass.RegistrationResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = RegistrationActivity.class.getSimpleName();


    private ConnectionApi connectionApi;
    private RegistrationModel registrationModel;
    private RegistrationResponseModel registrationResponseModel;

    private EditText userFullNameET;
    private EditText userNameET;
    private EditText userMobileNoET;
    private EditText userAddressET;
    private EditText userPasswordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initialization();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        connectionApi = retrofit.create(ConnectionApi.class);
    }

    public void signUp(View view) {

        String fullName = userFullNameET.getText().toString().trim();
        String userName = userNameET.getText().toString().trim();
        String mobileNo = userMobileNoET.getText().toString().trim();
        String address = userAddressET.getText().toString().trim();
        String password = userPasswordET.getText().toString().trim();

        if (fullName.isEmpty()){
            userFullNameET.setError("Please Enter Your Full Name");
        }else if (userName.isEmpty()){
            userNameET.setError("Please Enter Your User Name");
        }else if (mobileNo.isEmpty()){
            userMobileNoET.setError("Please Enter Your Mobile Number");
        }else if (address.isEmpty()){
            userAddressET.setError("Please Enter Your Address");
        }else if (password.isEmpty()){
            userPasswordET.setError("Please Enter Your Password");
        }else {

            registrationModel = new RegistrationModel(fullName,userName,mobileNo,address,password);
            Call<RegistrationResponseModel> registrationResponseModelCall = connectionApi.getRegistrationUser(registrationModel);

            registrationResponseModelCall.enqueue(new Callback<RegistrationResponseModel>() {
                @Override
                public void onResponse(Call<RegistrationResponseModel> call, Response<RegistrationResponseModel> response) {

                    Log.d(TAG,"Response Code : "+response.code());
                    Log.d(TAG,"Response Code : "+response.body().getErrorMsg());
                    if (response.code() == 200){
                        registrationResponseModel = response.body();
                        boolean error = registrationResponseModel.getError();
                        Log.d(TAG,"Error : "+error);
                        if (!error){
                            Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }else {
                            String error_msg = registrationResponseModel.getErrorMsg();
                            Toast.makeText(RegistrationActivity.this, error_msg, Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(RegistrationActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RegistrationResponseModel> call, Throwable t) {

                    Log.e(TAG,t.toString());
                    Toast.makeText(RegistrationActivity.this, "Response Not Found", Toast.LENGTH_SHORT).show();
                }


            });
        }

    }

    public void initialization(){
        userFullNameET = (EditText) findViewById(R.id.regUserFullName);
        userNameET = (EditText) findViewById(R.id.regUserName);
        userMobileNoET = (EditText) findViewById(R.id.regUserMobileNo);
        userAddressET = (EditText) findViewById(R.id.regUserAddress);
        userPasswordET = (EditText) findViewById(R.id.regPassword);


    }

}
