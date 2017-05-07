package com.example.mdjahirulislam.simplemap.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mdjahirulislam.simplemap.R;
import com.example.mdjahirulislam.simplemap.activity.TravelEventDetailsActivity;
import com.example.mdjahirulislam.simplemap.modelClass.TravelEventModel;

import java.util.ArrayList;

/**
 * Created by mdjahirulislam on 30/04/17.
 */

public class TravelEventCustomAdapterForRecyclerView extends RecyclerView.Adapter<TravelEventCustomAdapterForRecyclerView.EventViewHolder> {

//    private Context context;
    private ArrayList<TravelEventModel> travelEventArrayList;


    public TravelEventCustomAdapterForRecyclerView(ArrayList<TravelEventModel> travelEventArrayList) {
        this.travelEventArrayList = travelEventArrayList;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.single_event_design,parent, false);

        EventViewHolder eventViewHolder = new EventViewHolder(view);

        return eventViewHolder;
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {

//        context = holde
        TravelEventModel travelEventModel = travelEventArrayList.get(position);
        holder.destinationTV.setText(travelEventModel.getTravel_destination());
        holder.budgetTV.setText(travelEventModel.getEstimated_budget());
        holder.fromDateTV.setText(travelEventModel.getFrom_date());
        holder.toDateTV.setText(travelEventModel.getTo_date());
    }

    @Override
    public int getItemCount() {
        return travelEventArrayList.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        TextView destinationTV,budgetTV,fromDateTV,toDateTV;
        public EventViewHolder(View itemView) {
            super(itemView);
             destinationTV= (TextView) itemView.findViewById(R.id.destinationTV);
             budgetTV = (TextView) itemView.findViewById(R.id.budgetTV);
             fromDateTV = (TextView) itemView.findViewById(R.id.fromDateTV);
             toDateTV = (TextView) itemView.findViewById(R.id.toDateTV);
        }
    }
}
