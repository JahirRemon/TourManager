package com.example.mdjahirulislam.simplemap.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mdjahirulislam.simplemap.R;
import com.example.mdjahirulislam.simplemap.adapter.ForecastCustomAdapter;
import com.example.mdjahirulislam.simplemap.app.AppConfig;
import com.example.mdjahirulislam.simplemap.interfaces.ConnectionApi;
import com.example.mdjahirulislam.simplemap.modelClass.ForecastModelClass;
import com.example.mdjahirulislam.simplemap.pojoClass.WeatherModelResponse;
import com.example.mdjahirulislam.simplemap.sharePreference.LocationSharedPreference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.mdjahirulislam.simplemap.app.AppConfig.IMAGE_CODE_URL;

public class WeatherConditionActivity extends AppCompatActivity {

    private LocationSharedPreference locationSharedPreference;

    private ConnectionApi weatherApi;
    private TextView toDaysWeatherConditionTV;
    private TextView maxTempTV;
    private TextView minTempTV;
    private TextView tempTV;
    private TextView currentLocation;
    private ListView forecastList;
    private ImageView imageCode;
    private String cityName="london";
    private static String currentCity = "London";
    private static String searchCity ="";


    private Toolbar weatherToolBar;

    private ProgressDialog progressDialog;

    private String date;
    private ForecastModelClass forecastModelClass;
    private ArrayList<ForecastModelClass> forecastModelClassArrayList;
    private ForecastCustomAdapter myAdapter;
//    private SendWeatherCondition send;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_condition);

        locationSharedPreference = new LocationSharedPreference(this);
        toDaysWeatherConditionTV = (TextView) findViewById(R.id.toDaysConditionTV);
        imageCode = (ImageView) findViewById(R.id.imageCode);
        maxTempTV = (TextView) findViewById(R.id.maxTempTV);
        minTempTV = (TextView) findViewById(R.id.minTempTV);
        tempTV = (TextView) findViewById(R.id.tempTV);
        currentLocation = (TextView) findViewById(R.id.currentLocation);
        forecastList = (ListView) findViewById(R.id.forecastLV);
        forecastModelClassArrayList = new ArrayList<>();


        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);



        weatherToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(weatherToolBar);

        cityName = locationSharedPreference.getLocationCityName();
        currentLocation.setText("Current Location in " + cityName);


    }

    @Override
    protected void onStart() {
        super.onStart();
        getResponse(cityName);
    }


    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        MenuInflater menuInflater = new MenuInflater(this);
//        menuInflater.inflate(R.menu.user_menu,menu);
//
//        return true;
//    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


//        MenuInflater inflater = new MenuInflater(this);
//        menuInflater.inflate(R.menu.user_menu,menu);


        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.weather_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setMessage("Uploading.....");
                progressDialog.show();
                searchCity = query;
                cityName = searchCity;

                getResponse(cityName);
//                startActivity(getIntent());
                Toast.makeText(WeatherConditionActivity.this, ""+query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


    public void getResponse( String cityName){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.WEATHER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.d("weather condition ",cityName);
        weatherApi = retrofit.create(ConnectionApi.class);
        String url = "v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+cityName+"%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

        Call<WeatherModelResponse> weatherModelResponseCall = weatherApi.getWeatherResponse(url);

        weatherModelResponseCall.enqueue(new Callback<WeatherModelResponse>() {
            @Override
            public void onResponse(Call<WeatherModelResponse> call, Response<WeatherModelResponse> response) {
                if (response.code() == 200){

                    WeatherModelResponse weatherModelResponse = response.body();

                    if (weatherModelResponse.getQuery().getCount() == 0){

                        Log.d("Weather response : ",weatherModelResponse.getQuery().getCount().toString());
                        Toast.makeText(WeatherConditionActivity.this, "response error", Toast.LENGTH_SHORT).show();
                    }else {

                        String toDayText = weatherModelResponse.getQuery().getResults().getChannel().getItem().getCondition().getText();
                        String temp = weatherModelResponse.getQuery().getResults().getChannel().getItem().getCondition().getTemp();
                        String max = weatherModelResponse.getQuery().getResults().getChannel().getItem().getForecast().get(0).getHigh();
                        String min = weatherModelResponse.getQuery().getResults().getChannel().getItem().getForecast().get(0).getLow();
                        String imgCode = weatherModelResponse.getQuery().getResults().getChannel().getItem().getCondition().getCode();
                        String city = weatherModelResponse.getQuery().getResults().getChannel().getLocation().getCity();
                        date = weatherModelResponse.getQuery().getResults().getChannel().getItem().getForecast().get(0).getDate();

//                    send.getWeatherCondition(toDayText);

//                    Log.d("image code",imgCode.toString());
//                    Log.d("image code",date.toString());

                        Context context = imageCode.getContext();
                        Uri fileUri = Uri.parse(IMAGE_CODE_URL + imgCode + ".gif");
                        Picasso.with(context).load(fileUri).into(imageCode);

                        toDaysWeatherConditionTV.setText(toDayText);
                        maxTempTV.setText("↑ " + Fahrenheit_Celsius(max) + "°");
                        minTempTV.setText("↓ " + Fahrenheit_Celsius(min) + "°");
                        tempTV.setText(Fahrenheit_Celsius(temp) + "°C");





                        ArrayList<WeatherModelResponse.Query.Results.Channel.Item.Forecast> forecastArrayList =
                                (ArrayList<WeatherModelResponse.Query.Results.Channel.Item.Forecast>) weatherModelResponse.getQuery().getResults().getChannel().getItem().getForecast();


                        Log.d("forecast",String.valueOf(forecastArrayList.size()));

                        for (int i = 0; i < forecastArrayList.size(); i++){


                            String code = forecastArrayList.get(i).getCode().toString();
                            String date = forecastArrayList.get(i).getDate().toString();
                            String day = forecastArrayList.get(i).getDay().toString();
                            String high = forecastArrayList.get(i).getHigh().toString();
                            String low = forecastArrayList.get(i).getLow().toString();
                            String text = forecastArrayList.get(i).getText().toString();

                            forecastModelClass =new ForecastModelClass(code,date,day,high,low,text);

//                        Toast.makeText( getActivity(), ""+forecast, Toast.LENGTH_SHORT).show();
                            forecastModelClassArrayList.add(forecastModelClass);
//                        customAdapter.notifyDataSetChanged();


                            Log.d("forecast",String.valueOf(forecastModelClassArrayList.get(i).getDate()));

                        }
                        myAdapter = new ForecastCustomAdapter(WeatherConditionActivity.this,forecastModelClassArrayList);
                        forecastList.setAdapter(myAdapter);






                    }

                    progressDialog.hide();
                }
            }

            @Override
            public void onFailure(Call<WeatherModelResponse> call, Throwable t) {
                progressDialog.hide();

                Toast.makeText(WeatherConditionActivity.this, "Connection Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public static int Fahrenheit_Celsius(String  fahrenheit){

        int celsius = 0;
        double frn = Double.parseDouble(fahrenheit);
        celsius = (int) ((frn-32)*(0.5556));
        return celsius;
    }


}
