package com.example.mdjahirulislam.simplemap.modelClass;

/**
 * Created by mdjahirulislam on 02/05/17.
 */

public class ExpenseModel {

    private String expense_unique_id;
    private String travel_event_unique_id;
    private String expense_details;
    private String expense_amount;
    private String expense_date;
    private String expense_voucher;
    private String created_at;

    public ExpenseModel(String travel_event_unique_id, String expense_details, String expense_amount, String expense_date, String expense_voucher, String created_at) {
        this.travel_event_unique_id = travel_event_unique_id;
        this.expense_details = expense_details;
        this.expense_amount = expense_amount;
        this.expense_date = expense_date;
        this.expense_voucher = expense_voucher;
        this.created_at = created_at;
    }

    public ExpenseModel(String expense_unique_id, String travel_event_unique_id, String expense_details, String expense_amount, String expense_date, String expense_voucher, String created_at) {
        this.expense_unique_id = expense_unique_id;
        this.travel_event_unique_id = travel_event_unique_id;
        this.expense_details = expense_details;
        this.expense_amount = expense_amount;
        this.expense_date = expense_date;
        this.expense_voucher = expense_voucher;
        this.created_at = created_at;
    }

    public ExpenseModel(String travel_event_unique_id, String expense_details, String expense_amount, String expense_date) {
        this.travel_event_unique_id = travel_event_unique_id;
        this.expense_details = expense_details;
        this.expense_amount = expense_amount;
        this.expense_date = expense_date;
    }


    public String getExpense_unique_id() {
        return expense_unique_id;
    }

    public void setExpense_unique_id(String expense_unique_id) {
        this.expense_unique_id = expense_unique_id;
    }

    public String getTravel_event_unique_id() {
        return travel_event_unique_id;
    }

    public void setTravel_event_unique_id(String travel_event_unique_id) {
        this.travel_event_unique_id = travel_event_unique_id;
    }

    public String getExpense_details() {
        return expense_details;
    }

    public void setExpense_details(String expense_details) {
        this.expense_details = expense_details;
    }

    public String getExpense_amount() {
        return expense_amount;
    }

    public void setExpense_amount(String expense_amount) {
        this.expense_amount = expense_amount;
    }

    public String getExpense_date() {
        return expense_date;
    }

    public void setExpense_date(String expense_date) {
        this.expense_date = expense_date;
    }

    public String getExpense_voucher() {
        return expense_voucher;
    }

    public void setExpense_voucher(String expense_voucher) {
        this.expense_voucher = expense_voucher;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
