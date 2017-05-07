package com.example.mdjahirulislam.simplemap.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mdjahirulislam.simplemap.R;
import com.example.mdjahirulislam.simplemap.adapter.ExpenseCustomAdapter;
import com.example.mdjahirulislam.simplemap.database.DatabaseSource;
import com.example.mdjahirulislam.simplemap.modelClass.ExpenseModel;
import com.example.mdjahirulislam.simplemap.sharePreference.UserSharePreference;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowExpenseFragment extends Fragment {

    private static final String TAG = ShowExpenseFragment.class.getSimpleName();


    private DatabaseSource databaseSource;
    private ExpenseCustomAdapter expenseCustomAdapter;
    private UserSharePreference userSharePreference;
    private ArrayList<ExpenseModel> expenseModelArrayList;

    private RecyclerView expenseRV;
    private TextView noExpenseTV;

    private String eventUniqueId;


    public ShowExpenseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_show_expense, container, false);



        databaseSource = new DatabaseSource(getActivity());
//        databaseSource.deleteTravelEventExpenseTable();
        noExpenseTV = (TextView) view.findViewById(R.id.noExpenseTV);
        expenseRV = (RecyclerView) view.findViewById(R.id.expenseRV);

        expenseRV.setHasFixedSize(true);
        userSharePreference = new UserSharePreference(getActivity());
        expenseModelArrayList = new ArrayList<>();

        eventUniqueId = userSharePreference.getTravelUniqueEventId();
        Log.d(TAG,"eventUniqueId : ------------ "+eventUniqueId);

        expenseModelArrayList = databaseSource.getAllTravelEventExpense(eventUniqueId);
        Log.d(TAG,"event expense size : "+expenseModelArrayList.size());

        if (expenseModelArrayList.size()>0){
            noExpenseTV.setVisibility(View.GONE);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        expenseRV.setLayoutManager(layoutManager);
        expenseRV.setItemAnimator(new DefaultItemAnimator());
        expenseCustomAdapter = new ExpenseCustomAdapter(expenseModelArrayList);
        expenseRV.setAdapter(expenseCustomAdapter);


//        mRecyclerView.setAdapter(mAdapter); // Here, mAdapter is null
//        mAdapter = new CountryAdapter(CountryManager.getInstance().getCountries(), R.layout.card_layout, getActivity());

        return view;





    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
