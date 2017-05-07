package com.example.mdjahirulislam.simplemap.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mdjahirulislam.simplemap.R;
import com.example.mdjahirulislam.simplemap.app.AppConfig;
import com.example.mdjahirulislam.simplemap.database.DatabaseSource;
import com.example.mdjahirulislam.simplemap.interfaces.ConnectionApi;
import com.example.mdjahirulislam.simplemap.modelClass.ExpenseModel;
import com.example.mdjahirulislam.simplemap.pojoClass.ExpenseResponseModel;
import com.example.mdjahirulislam.simplemap.sharePreference.UserSharePreference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddExpenseActivity extends AppCompatActivity {

    private static final String TAG = AddExpenseActivity.class.getSimpleName();


    private DatabaseSource databaseSource;
    private UserSharePreference userSharePreference;
    private ConnectionApi getResponse;

    private ExpenseResponseModel expenseResponseModel;
    private ExpenseModel expenseModel;
    private ArrayList<ExpenseResponseModel.TravelEventExpense> travelEventExpenseArrayList;
    private ArrayList<ExpenseModel> expenseModelArrayList;

    private ProgressDialog progressDialog;
    private Dialog dialog;
    private Calendar calendar;



    private ConnectionApi connectionApi;

    private EditText expenseDetailsET;
    private EditText expenseAmountET;
    private TextView expenseDateTV;
    private ImageView voucherIV;
    private Button voucherBtn;
    private Button addEexpenseBtn;

    private String eventUniqueId;
    private String mediaPath;
    private String voucher = "no voucher";


    private Uri selectedImage = null;
    private static int flag;


    private static final String IMAGE_DIRECTORY = "/tourApp";
    private int GALLERY = 1, CAMERA = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        initialization();


//        Log.d(TAG,"goToAddExpenseActivity");

        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int date = calendar.get(Calendar.DAY_OF_MONTH);

        flag =0;

        eventUniqueId = userSharePreference.getTravelUniqueEventId();
        expenseDateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                DatePickerDialog datePickerDialog=new DatePickerDialog(AddExpenseActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        calendar.set(Calendar.MONTH,month+1);
                        calendar.set(Calendar.YEAR,year);
//                        String dateString = sdf.format(calendar.getTimeInMillis());
                        expenseDateTV.setText(calendar.get(Calendar.DAY_OF_MONTH)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR));

                    }
                },year,month,date);
                datePickerDialog.show();
            }
        });


        voucherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
//                selectImage();
//                voucherBtn.setText(fileUri.toString());

            }
        });

        getResponse = AppConfig.getRetrofit().create(ConnectionApi.class);

    }

    public void initialization(){



        expenseDetailsET = (EditText) findViewById(R.id.expenseDetailsET);
        expenseAmountET = (EditText) findViewById(R.id.expenseAmountET);
        expenseDateTV = (TextView) findViewById(R.id.expenseDateTV);
        voucherIV = (ImageView) findViewById(R.id.voucherIV);

        voucherBtn = (Button) findViewById(R.id.voucherBtn);
        addEexpenseBtn = (Button) findViewById(R.id.addExpenseBtn);

        userSharePreference = new UserSharePreference(this);

        databaseSource = new DatabaseSource(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        dialog = new Dialog(this);

        calendar = Calendar.getInstance();

        travelEventExpenseArrayList = new ArrayList<>();
        expenseModelArrayList = new ArrayList<>();


    }

    public void addExpenseBtn(View view) {
//        expenseModel.

        String details = expenseDetailsET.getText().toString().trim();
        String amount = expenseAmountET.getText().toString().trim();
        String date = expenseDateTV.getText().toString().trim();





        if (details.isEmpty()){
            expenseDetailsET.setError("Required Field");
        }else if (amount.isEmpty()){
            expenseAmountET.setError("Required Field");
        }else if (date.isEmpty()){
            expenseDateTV.setError("Required Field");
        }
        else if (flag ==0){
            Log.d(TAG, "flag status : " + flag);
            uploadFile();

        } else {
            Log.d(TAG, "flag status : " + flag);
            uploadFile();

        }

    }


//    private void expenseMethod(ExpenseModel expenseModel){
//        ConnectionApi connectionApi = AppConfig.getRetrofit().create(ConnectionApi.class);
//
//        final Call<ExpenseResponseModel> expenseResponseModelCall = connectionApi.addExpense(expenseModel);
//        expenseResponseModelCall.enqueue(new Callback<ExpenseResponseModel>() {
//            @Override
//            public void onResponse(Call<ExpenseResponseModel> call, Response<ExpenseResponseModel> response) {
//                if (response.code() == 200) {
//                    expenseResponseModel = response.body();
//                    boolean status = expenseResponseModel.getError();
//                    if (!status) {
//                        for (int i = 0; i < expenseResponseModel.getTravelEventExpense().size(); i++) {
//
//                            Log.d(TAG, expenseResponseModel.getErrorMsg() + "\nExpense details : " + expenseResponseModel.getTravelEventExpense().get(i).getExpenseDetails());
//                        }
//                    } else {
//                        Log.d(TAG, "Error status : " + status);
//                    }
//                } else {
//                    Log.d(TAG, "Error response code : " + response.code());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ExpenseResponseModel> call, Throwable t) {
//
//                Log.d(TAG, "Error Failure : " + t.toString());
//            }
//        });
//    }




    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallery();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                selectedImage = data.getData();
                try {

                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    mediaPath = cursor.getString(columnIndex);

                    Bitmap bitmap = getResizedBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage),400,400);
                    String path = saveImage(bitmap);
                    Toast.makeText(AddExpenseActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();



                    voucherIV.setImageBitmap(bitmap);

                    flag = 2;
                    cursor.close();

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(AddExpenseActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            voucherIV.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(AddExpenseActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {

        int width = bm.getWidth();

        int height = bm.getHeight();

        float scaleWidth = ((float) newWidth) / width;

        float scaleHeight = ((float) newHeight) / height;

// CREATE A MATRIX FOR THE MANIPULATION

        Matrix matrix = new Matrix();

// RESIZE THE BIT MAP

        matrix.postScale(scaleWidth, scaleHeight);

// RECREATE THE NEW BITMAP

        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);

        return resizedBitmap;

    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }






    private void uploadFile(){


//
        RequestBody travel_event_unique_id = RequestBody.create(MultipartBody.FORM,eventUniqueId);

        RequestBody expense_details = RequestBody.create(MultipartBody.FORM,
                expenseDetailsET.getText().toString());

        RequestBody expense_amount = RequestBody.create(MultipartBody.FORM,
                expenseAmountET.getText().toString());

        RequestBody expense_date = RequestBody.create(MultipartBody.FORM,
                expenseDateTV.getText().toString());

        progressDialog.setMessage("Uploading.....");
        progressDialog.show();

        // Map is used to multipart the file using okhttp3.RequestBody

        MultipartBody.Part fileToUpload = null;
        Call<ExpenseResponseModel> call = null;
//        ConnectionApi getResponse = AppConfig.getRetrofit().create(ConnectionApi.class);


        if (flag==2){
            File file = new File(mediaPath);

            // Parsing any Media type file
            RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
             fileToUpload = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
            call = getResponse.uploadPhotoWithText(travel_event_unique_id,expense_details,expense_amount,expense_date,fileToUpload);

        }else {
            call = getResponse.uploadPhotoWithText(travel_event_unique_id,expense_details,expense_amount,expense_date,fileToUpload);
        }



        call.enqueue(new Callback<ExpenseResponseModel>() {
            @Override
            public void onResponse(Call<ExpenseResponseModel> call, Response<ExpenseResponseModel> response) {
                if (response.code() == 200) {
                    expenseResponseModel = response.body();
                    boolean error = expenseResponseModel.getError();

                    if (!error){

                        travelEventExpenseArrayList.clear();
//                        databaseSource.deleteTravelEventExpenseTable();
                        travelEventExpenseArrayList = (ArrayList<ExpenseResponseModel.TravelEventExpense>) expenseResponseModel.getTravelEventExpense();

//                        for (int i = 0; i<travelEventExpenseArrayList.size(); i++){

                            String expenseUniqueId = travelEventExpenseArrayList.get(0).getExpenseUniqueId();
                            String expenseDetails = travelEventExpenseArrayList.get(0).getExpenseDetails();
                            String expenseAmount =travelEventExpenseArrayList.get(0).getExpenseAmount();
                            String expenseDate = travelEventExpenseArrayList.get(0).getExpenseDate();
                            String expenseVoucher = travelEventExpenseArrayList.get(0).getExpenseVoucher();
                            String createdAt = travelEventExpenseArrayList.get(0).getCreatedAt();

                            expenseModel = new ExpenseModel(expenseUniqueId,eventUniqueId,expenseDetails,expenseAmount,expenseDate,expenseVoucher,expenseDate);
//                            expenseModelArrayList.add(expenseModel);



                            boolean addExpenseInSQLite = databaseSource.addTravelEventExpense(expenseModel);


                            if (addExpenseInSQLite){
                                Intent intent = new Intent(AddExpenseActivity.this,TravelEventDetailsActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(AddExpenseActivity.this, "Expense not store in SQLite", Toast.LENGTH_SHORT).show();
                            }
//                        }



                    }else {
                        Log.d(TAG,"Error : True ---- msg : "+expenseResponseModel.getErrorMsg());
                    }


                    Log.d(TAG,"Response: "+expenseResponseModel.getErrorMsg()+"\n"+expenseResponseModel.getTravelEventExpense().get(0).getExpenseDetails());
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ExpenseResponseModel> call, Throwable t) {

            }
        });
    }








}
