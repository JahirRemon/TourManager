package com.example.mdjahirulislam.simplemap.interfaces;

import com.example.mdjahirulislam.simplemap.pojoClass.AddTravelEventResponseModel;
import com.example.mdjahirulislam.simplemap.pojoClass.ExpenseResponseModel;
import com.example.mdjahirulislam.simplemap.pojoClass.LogInResponseModel;
import com.example.mdjahirulislam.simplemap.modelClass.RegistrationModel;
import com.example.mdjahirulislam.simplemap.pojoClass.RegistrationResponseModel;
import com.example.mdjahirulislam.simplemap.modelClass.TravelEventModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by mdjahirulislam on 27/04/17.
 */

public interface ConnectionApi {
    @POST("TourApp/user_registration.php")
    Call<RegistrationResponseModel> getRegistrationUser(@Body RegistrationModel registrationModel);

    @POST("TourApp/user_login.php")
    Call<LogInResponseModel> logInUser(@Body RegistrationModel registrationModel);

    @POST("TourApp/create_travel_event.php")
    Call<AddTravelEventResponseModel> addTravelEvent(@Body TravelEventModel travelEventModel);

    @Multipart
    @POST("TourApp/add_expense_multipart.php")
    Call<ExpenseResponseModel> addExpense(@Part("travel_event_unique_id") RequestBody travel_event_unique_id);

    @Multipart
    @POST("TourApp/add_expense_multipart.php")
    Call<ExpenseResponseModel> uploadPhotoWithText(
            @Part("travel_event_unique_id") RequestBody travel_event_unique_id,
            @Part("expense_details") RequestBody expense_details,
            @Part("expense_amount") RequestBody expense_amount,
            @Part("expense_date") RequestBody expense_date,
            @Part MultipartBody.Part image

    );


}
