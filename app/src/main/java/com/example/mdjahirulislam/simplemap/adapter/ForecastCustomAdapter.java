package com.example.mdjahirulislam.simplemap.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mdjahirulislam.simplemap.R;
import com.example.mdjahirulislam.simplemap.modelClass.ForecastModelClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mdjahirulislam on 28/03/17.
 */

public class ForecastCustomAdapter extends ArrayAdapter<ForecastModelClass>{

    private final String IMAGE_CODE_URL = "http://fullnightfun.com/weather_icon/";

    private Context context;
    private ArrayList<ForecastModelClass> weatherArrayList;

    public ForecastCustomAdapter(@NonNull Context context, @NonNull ArrayList<ForecastModelClass> weatherList) {
        super(context, R.layout.single_forecast_design,weatherList);
        this.context=context;
        this.weatherArrayList =weatherList;
    }

    public class Holder{
        TextView day;
        TextView high;
        TextView low;
        String imageCode;
        ImageView forecastImage;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



            Holder holder;
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (convertView==null){
             holder = new Holder();
            convertView=layoutInflater.inflate(R.layout.single_forecast_design,parent,false);
            holder.day = (TextView) convertView.findViewById(R.id.forecastDay);
            holder.high = (TextView) convertView.findViewById(R.id.forecastHigh);
            holder.low = (TextView) convertView.findViewById(R.id.forecastLow);
            holder.forecastImage = (ImageView) convertView.findViewById(R.id.forecastIconIV);
//            holder.bodyPic = (ImageView) convertView.findViewById(R.id.bodyPicIV);

            convertView.setTag(holder);
        }else {

            holder = (Holder) convertView.getTag();
        }


        holder.day.setText(weatherArrayList.get(position).getDate());
        holder.high.setText(weatherArrayList.get(position).getHigh());
        holder.low.setText(weatherArrayList.get(position).getLow());
        holder.imageCode = weatherArrayList.get(position).getCode();
        Context context= holder.forecastImage.getContext();
        Uri fileUri= Uri.parse(IMAGE_CODE_URL+holder.imageCode+".gif");
        Picasso.with(context).load(fileUri).into(holder.forecastImage);

        return convertView;
    }
}