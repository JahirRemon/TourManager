package com.example.mdjahirulislam.simplemap.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mdjahirulislam.simplemap.activity.ShowTravelEventListActivity;
import com.example.mdjahirulislam.simplemap.modelClass.ExpenseModel;
import com.example.mdjahirulislam.simplemap.modelClass.RegistrationModel;
import com.example.mdjahirulislam.simplemap.modelClass.TravelEventModel;

import java.util.ArrayList;

/**
 * Created by Trainer on 3/29/2017.
 */

public class DatabaseSource {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private RegistrationModel userModel;
    private TravelEventModel travelEventModel;

    private static final String TAG = DatabaseSource.class.getSimpleName();


    public DatabaseSource(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void open(){
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }
    public void close(){
        sqLiteDatabase.close();
    }



    public boolean addUser(RegistrationModel users){
        this.open();
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.COL_USER_UNIQUE_ID,users.getUser_unique_id());
        values.put(DatabaseHelper.COL_USER_FULL_NAME,users.getUser_full_name());
        values.put(DatabaseHelper.COL_USER_NAME,users.getUser_name());
        values.put(DatabaseHelper.COL_USER_MOBILE_NO,users.getUser_mobile_no());
        values.put(DatabaseHelper.COL_USER_ADDRESS,users.getUser_address());
        values.put(DatabaseHelper.COL_USER_CREATED_AT,users.getCreated_at());



        long id = sqLiteDatabase.insert(DatabaseHelper.TABLE_USER_REGISTRATION,null,values);
        this.close();
        if(id > 0){
            return true;
        }else{
            return false;
        }
    }



    public boolean addTravelEvent(TravelEventModel events){
        this.open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_TRAVEL_EVENT_UNIQUE_ID,events.getTravel_event_unique_id());
        values.put(DatabaseHelper.COL_TRAVEL_EVENT_USER_UNIQUE_ID,events.getUser_unique_id());
        values.put(DatabaseHelper.COL_TRAVEL_EVENT_DESTINATION,events.getTravel_destination());
        values.put(DatabaseHelper.COL_TRAVEL_EVENT_ESTIMATED_BUDGET,events.getEstimated_budget());
        values.put(DatabaseHelper.COL_TRAVEL_EVENT_FROM_DATE,events.getFrom_date());
        values.put(DatabaseHelper.COL_TRAVEL_EVENT_TO_DATE,events.getTo_date());
        values.put(DatabaseHelper.COL_TRAVEL_EVENT_CREATED_AT,events.getCreated_at());



        long id = sqLiteDatabase.insert(DatabaseHelper.TABLE_TRAVEL_EVENT,null,values);
        this.close();
        if(id > 0){
            Log.d(TAG,"AddAllEvent ----- Event Name-------"+events.getTravel_event_unique_id());
            Log.d(TAG,"AddAllEvent ----- Event unique id-------"+events.getTravel_destination());
            return true;
        }else{
            return false;
        }
    }



    public boolean addTravelEventExpense(ExpenseModel expenseModel){
        this.open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_TRAVEL_EVENT_EXPENSE_UNIQUE_ID,expenseModel.getExpense_unique_id());
        values.put(DatabaseHelper.COL_TRAVEL_EVENT_EXPENSE_EVENT_UNIQUE_ID,expenseModel.getTravel_event_unique_id());
        values.put(DatabaseHelper.COL_TRAVEL_EVENT_EXPENSE_DETAILS,expenseModel.getExpense_details());
        values.put(DatabaseHelper.COL_TRAVEL_EVENT_EXPENSE_AMOUNT,expenseModel.getExpense_amount());
        values.put(DatabaseHelper.COL_TRAVEL_EVENT_EXPENSE_DATE,expenseModel.getExpense_date());
        values.put(DatabaseHelper.COL_TRAVEL_EVENT_EXPENSE_VOUCHER,expenseModel.getExpense_voucher());
        values.put(DatabaseHelper.COL_TRAVEL_EVENT_EXPENSE_CREATED_AT,expenseModel.getCreated_at());



        long id = sqLiteDatabase.insert(DatabaseHelper.TABLE_TRAVEL_EVENT_EXPENSE,null,values);
        this.close();
        if(id > 0){
            Log.d(TAG,"-------AddAllEventExpense ----- Expense Name-------"+expenseModel.getExpense_details());
            Log.d(TAG,"-------AddAllEventExpense ----- Expense unique id-------"+expenseModel.getExpense_unique_id());
            Log.d(TAG,"-------AddAllEventExpense ----- Expense voucher-------"+expenseModel.getExpense_voucher());
            return true;
        }else{
            return false;
        }
    }



//    public ArrayList<RegistrationModel> getAllDrInfo(){
//        ArrayList<DrModel>drArrayList = new ArrayList<>();
//        this.open();
//        /*Cursor cursor = sqLiteDatabase.rawQuery("select * from "+DatabaseHelper.TABLE_USER_REGISTRATION,null);*/
//
//        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_USER_REGISTRATION,null,null,null,null,null,null);
//        cursor.moveToFirst();
//        if(cursor != null && cursor.getCount() > 0){
//            for(int i = 0; i < cursor.getCount(); i++){
//                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_USER_UNIQUE_ID));
//                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_USER_FULL_NAME));
//                String details = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_USER_MOBILE_NO));
//                String appointment = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_USER_ADDRESS));
//                String phone = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_USER_CREATED_AT));
//                String email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_USER_NAME));
//
//                userModel = new DrModel(id,name,details,appointment,phone,email);
//                drArrayList.add(userModel);
//                cursor.moveToNext();
//            }
//        }
//        cursor.close();
//        this.close();
//        return drArrayList;
//
//    }


    public ArrayList<TravelEventModel> getAllTravelEvent(String  userId){
        ArrayList<TravelEventModel> travelEventModelArrayList = new ArrayList<>();
        this.open();
        /*Cursor cursor = sqLiteDatabase.rawQuery("select * from "+DatabaseHelper.TABLE_USER_REGISTRATION,null);*/

        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_TRAVEL_EVENT,null,DatabaseHelper.COL_TRAVEL_EVENT_USER_UNIQUE_ID +" =? ",new String[] {String.valueOf(userId)},null,null,null);
        cursor.moveToFirst();
        if(cursor != null && cursor.getCount() > 0){
            for(int i = 0; i < cursor.getCount(); i++){
                String eventUniqueId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TRAVEL_EVENT_UNIQUE_ID));
//                int userId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_TRAVEL_EVENT_USER_UNIQUE_ID));
                String destination = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TRAVEL_EVENT_DESTINATION));
                String budget = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TRAVEL_EVENT_ESTIMATED_BUDGET));
                String fromDate = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TRAVEL_EVENT_FROM_DATE));
                String toDate = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TRAVEL_EVENT_TO_DATE));
                String createdAt = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TRAVEL_EVENT_CREATED_AT));

//                travelEventModel = new MedicalHistoryModel(id,dr_id,prescription,name,details,date);

                Log.d(TAG,"getAllEvent ----- Event Name-------"+destination);
                Log.d(TAG,"getAllEvent ----- Event unique id-------"+eventUniqueId);
                Log.d(TAG,"getAllEvent ----- Event unique id-------"+eventUniqueId);

                travelEventModel = new TravelEventModel(eventUniqueId, userId, destination, budget, fromDate, toDate);
                travelEventModelArrayList.add(travelEventModel);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.close();
        return travelEventModelArrayList;

    }

    public ArrayList<TravelEventModel> getSingleTravelEventDetails(int eventId){
        ArrayList<TravelEventModel>travelEventModelArrayList = new ArrayList<>();
        this.open();
        /*Cursor cursor = sqLiteDatabase.rawQuery("select * from "+DatabaseHelper.TABLE_USER_REGISTRATION,null);*/

        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_TRAVEL_EVENT,null,DatabaseHelper.COL_TRAVEL_EVENT_UNIQUE_ID +" =? ",new String[] {String.valueOf(eventId)},null,null,null);
        cursor.moveToFirst();
        if(cursor != null && cursor.getCount() > 0){

            int eventUniqueId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_TRAVEL_EVENT_UNIQUE_ID));
            int userId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_TRAVEL_EVENT_USER_UNIQUE_ID));
            String destination = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TRAVEL_EVENT_DESTINATION));
            String budget = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TRAVEL_EVENT_ESTIMATED_BUDGET));
            String fromDate = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TRAVEL_EVENT_FROM_DATE));
            String toDate = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TRAVEL_EVENT_TO_DATE));
            String createdAt = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TRAVEL_EVENT_CREATED_AT));

            Log.d(TAG,"Event id-------"+destination);
            travelEventModel = new TravelEventModel(String.valueOf(eventUniqueId),String.valueOf(userId), destination, budget, fromDate, toDate);
                travelEventModelArrayList.add(travelEventModel);
                cursor.moveToNext();

        }
        cursor.close();
        this.close();
        return travelEventModelArrayList;

    }



    public ArrayList<RegistrationModel> getSingleUserDetails(int userId){
        ArrayList<RegistrationModel> registrationModelArrayList= new ArrayList<>();

        this.open();
        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_USER_REGISTRATION, null, DatabaseHelper.COL_USER_UNIQUE_ID +" =? ",new String[] { String.valueOf(userId) },null,null,null);
        cursor.moveToFirst();

                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_USER_ID));
                String userUniqueId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_USER_UNIQUE_ID));
                String fullName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_USER_FULL_NAME));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_USER_NAME));
                String mobile = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_USER_MOBILE_NO));
                String address = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_USER_ADDRESS));
                String created = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_USER_CREATED_AT));

                userModel = new RegistrationModel(userUniqueId, fullName, name, mobile, address, created);
                registrationModelArrayList.add(userModel);




        cursor.close();
        this.close();

        return registrationModelArrayList;
    }



    public ArrayList<ExpenseModel> getAllTravelEventExpense(String  travelEventUniqueId){
        Log.d(TAG,"getAllTravelEventExpense : ******************** "+travelEventUniqueId);
        ArrayList<ExpenseModel> expenseModelArrayList = new ArrayList<>();
        this.open();
        /*Cursor cursor = sqLiteDatabase.rawQuery("select * from "+DatabaseHelper.TABLE_USER_REGISTRATION,null);*/

        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_TRAVEL_EVENT_EXPENSE,null,DatabaseHelper.COL_TRAVEL_EVENT_EXPENSE_EVENT_UNIQUE_ID +" =? ",new String[] {String.valueOf(travelEventUniqueId)},null,null,null);
        cursor.moveToFirst();
        if(cursor != null && cursor.getCount() > 0){
            for(int i = 0; i < cursor.getCount(); i++){
                String eventExpenseUniqueId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TRAVEL_EVENT_EXPENSE_UNIQUE_ID));
                String expenseDetails = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TRAVEL_EVENT_EXPENSE_DETAILS));
                String expenseAmount = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TRAVEL_EVENT_EXPENSE_AMOUNT));
                String expenseDate = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TRAVEL_EVENT_EXPENSE_DATE));
                String expenseVoucher = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TRAVEL_EVENT_EXPENSE_VOUCHER));
                String createdAt = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TRAVEL_EVENT_EXPENSE_CREATED_AT));

//                travelEventModel = new MedicalHistoryModel(id,dr_id,prescription,name,details,date);

                Log.d(TAG,"getAllEventExpense -----  expense id ------- "+cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TRAVEL_EVENT_EXPENSE_ID)));
                Log.d(TAG,"getAllEventExpense ----- event expense uid ------- "+eventExpenseUniqueId);
                Log.d(TAG,"getAllEventExpense ----- event expense details ------- "+expenseDetails);
//                Log.d(TAG,"getAllEvent ----- Event unique id-------"+eventUniqueId);

                ExpenseModel expenseModel = new ExpenseModel(eventExpenseUniqueId, travelEventUniqueId, expenseDetails, expenseAmount, expenseDate, expenseVoucher,createdAt);
                expenseModelArrayList.add(expenseModel);
                cursor.moveToNext();
            }
        }else {
            Log.d(TAG,"Data not found");
        }
        cursor.close();
        this.close();
        return expenseModelArrayList;

    }




    // -----------------DELETE--------------------//


//    public boolean deleteDoctor(int userId){
//        this.open();
//        int deleteDrId = sqLiteDatabase.delete(DatabaseHelper.TABLE_USER_REGISTRATION,DatabaseHelper.COL_USER_UNIQUE_ID +" = ?",new String[]{Long.toString(userId)});
//
//        if (deleteDrId > 0){
//            return true;
//        }else {
//            return false;
//        }
//    }



    // -----------------DELETE--------------------//


//    public boolean updateDrDetailes(DrModel drs,long dr_id){
//        this.open();
//        ContentValues values = new ContentValues();
//        values.put(DatabaseHelper.COL_USER_FULL_NAME,drs.getDrName());
//        values.put(DatabaseHelper.COL_USER_MOBILE_NO,drs.getDrDetails());
//        values.put(DatabaseHelper.COL_USER_ADDRESS,drs.getDrAppointment());
//        values.put(DatabaseHelper.COL_USER_CREATED_AT,drs.getDrPhone());
//        values.put(DatabaseHelper.COL_USER_NAME,drs.getDrEmail());
//
//
//        long id = sqLiteDatabase.update(DatabaseHelper.TABLE_USER_REGISTRATION,values,DatabaseHelper.COL_USER_UNIQUE_ID +" = ?",new String[]{Long.toString(dr_id)});
//        this.close();
//        if(id > 0){
//            return true;
//        }else{
//            return false;
//        }
//    }


    public void whenLogoutUser(){
        this.open();
        sqLiteDatabase.delete(DatabaseHelper.TABLE_TRAVEL_EVENT,null,null);
        sqLiteDatabase.delete(DatabaseHelper.TABLE_USER_REGISTRATION,null,null);
        sqLiteDatabase.delete(DatabaseHelper.TABLE_TRAVEL_EVENT_EXPENSE,null,null);
        sqLiteDatabase.delete(DatabaseHelper.TABLE_TRAVEL_EVENT_MOMENT,null,null);
        this.close();
        Log.d(TAG, "Deleted all Table info from SQLite");
    }

    public void deleteTravelEventTable(){
        this.open();
        sqLiteDatabase.delete(DatabaseHelper.TABLE_TRAVEL_EVENT,null,null);
        this.close();
        Log.d(TAG, "Deleted Travel Event Table info from SQLite");
    }

    public void deleteTravelEventExpenseTable(){
        this.open();
        sqLiteDatabase.delete(DatabaseHelper.TABLE_TRAVEL_EVENT_EXPENSE,null,null);
        this.close();
        Log.d(TAG, "Deleted Event Expense Table event expense info from SQLite");
    }


}
