package com.example.mdjahirulislam.simplemap.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mdjahirulislam.simplemap.R;
import com.example.mdjahirulislam.simplemap.modelClass.ExpenseModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mdjahirulislam on 05/05/17.
 */

public class ExpenseCustomAdapter extends RecyclerView.Adapter<ExpenseCustomAdapter.ExpenseViewHolder> {

    private ArrayList<ExpenseModel> expenseModelArrayList;


    public ExpenseCustomAdapter(ArrayList<ExpenseModel> expenseModelArrayList) {
        this.expenseModelArrayList = expenseModelArrayList;
    }

    @Override
    public ExpenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.single_expense_design,parent, false);

        ExpenseCustomAdapter.ExpenseViewHolder expenseViewHolder = new ExpenseCustomAdapter.ExpenseViewHolder(view);

        return expenseViewHolder;

    }

    @Override
    public void onBindViewHolder(ExpenseViewHolder holder, int position) {

        ExpenseModel expenseModel = expenseModelArrayList.get(position);
        holder.expenseDetailsTV.setText(expenseModel.getExpense_details());
        holder.expenseAmountTV.setText(expenseModel.getExpense_amount()+" tk");
        holder.expenseDateTV.setText(expenseModel.getExpense_date());

        String voucher = expenseModel.getExpense_voucher();
        Log.d("Expense Adapter : ---- ","Voucher : "+voucher);
        if (voucher.equals("no voucher document ")){
            holder.expenseVoucherTV.setText(expenseModel.getExpense_voucher());
            holder.expenseVoucherIV.setVisibility(View.GONE);

        }else {

            Context context= holder.expenseVoucherIV.getContext();
            holder.expenseVoucherIV.setVisibility(View.VISIBLE);
            Uri fileUri = Uri.parse(voucher);

//        Picasso.with(context).load(fileUri);
            Picasso.with(context).load(fileUri)
                    .resize(400, 300)
                    .into(holder.expenseVoucherIV);
            holder.expenseVoucherTV.setVisibility(View.GONE);

        }



    }

    @Override
    public int getItemCount() {
        return expenseModelArrayList.size();
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder {
        TextView expenseDetailsTV;
        TextView expenseAmountTV;
        TextView expenseDateTV;
        TextView expenseVoucherTV;
        ImageView expenseVoucherIV;

        public ExpenseViewHolder(View itemView) {
            super(itemView);
            expenseDetailsTV = (TextView) itemView.findViewById(R.id.singleExpenseDetailsTV);
            expenseAmountTV = (TextView) itemView.findViewById(R.id.singleExpenseAmountTV);
            expenseDateTV = (TextView) itemView.findViewById(R.id.singleExpenseDateTV);
            expenseVoucherTV = (TextView) itemView.findViewById(R.id.singleExpenseVoucherTV);
            expenseVoucherIV = (ImageView) itemView.findViewById(R.id.singleExpenseVoucherIV);
        }
    }



}

