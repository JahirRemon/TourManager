package com.example.mdjahirulislam.simplemap.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mdjahirulislam.simplemap.R;
import com.example.mdjahirulislam.simplemap.modelClass.TravelEventModel;

import java.util.ArrayList;

/**
 * Created by mdjahirulislam on 28/03/17.
 */

public class TravelEventCustomAdapter extends ArrayAdapter<TravelEventModel>{


    private Context context;
    private ArrayList<TravelEventModel> travelEventArrayList;

    public TravelEventCustomAdapter(@NonNull Context context, @NonNull ArrayList<TravelEventModel> travelEventArrayList) {
        super(context, R.layout.single_event_design, travelEventArrayList);
        this.context=context;
        this.travelEventArrayList=travelEventArrayList;
    }

    public static class Holder{
        TextView destinationTV;
        TextView budgetTV;
        TextView fromDateTV;
        TextView toDateTV;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



            Holder holder;
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (convertView==null){
             holder = new Holder();
            convertView=layoutInflater.inflate(R.layout.single_event_design,parent,false);
            holder.destinationTV = (TextView) convertView.findViewById(R.id.destinationTV);
            holder.budgetTV = (TextView) convertView.findViewById(R.id.budgetTV);
            holder.fromDateTV = (TextView) convertView.findViewById(R.id.fromDateTV);
            holder.toDateTV = (TextView) convertView.findViewById(R.id.toDateTV);

            convertView.setTag(holder);
        }else {

            holder = (Holder) convertView.getTag();
        }





        holder.destinationTV.setText(travelEventArrayList.get(position).getTravel_destination());
        holder.budgetTV.setText(travelEventArrayList.get(position).getEstimated_budget());
        holder.fromDateTV.setText(travelEventArrayList.get(position).getFrom_date());
        holder.toDateTV.setText(travelEventArrayList.get(position).getTo_date());
//        holder.bodyPic.setImageResource(studentInfoArrayList.get(position).getBodyPic());



        return convertView;
    }
}