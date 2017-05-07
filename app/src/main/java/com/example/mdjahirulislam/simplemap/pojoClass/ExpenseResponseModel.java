package com.example.mdjahirulislam.simplemap.pojoClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mdjahirulislam on 02/05/17.
 */

public class ExpenseResponseModel {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("error_msg")
    @Expose
    private String errorMsg;
    @SerializedName("travel_event_expense")
    @Expose
    private List<TravelEventExpense> travelEventExpense = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<TravelEventExpense> getTravelEventExpense() {
        return travelEventExpense;
    }

    public void setTravelEventExpense(List<TravelEventExpense> travelEventExpense) {
        this.travelEventExpense = travelEventExpense;
    }


    public static class TravelEventExpense {

        @SerializedName("expense_unique_id")
        @Expose
        private String expenseUniqueId;
        @SerializedName("travel_event_unique_id")
        @Expose
        private String travelEventUniqueId;
        @SerializedName("expense_details")
        @Expose
        private String expenseDetails;
        @SerializedName("expense_amount")
        @Expose
        private String expenseAmount;
        @SerializedName("expense_date")
        @Expose
        private String expenseDate;
        @SerializedName("expense_voucher")
        @Expose
        private String expenseVoucher;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private Object updatedAt;

        public String getExpenseUniqueId() {
            return expenseUniqueId;
        }

        public void setExpenseUniqueId(String expenseUniqueId) {
            this.expenseUniqueId = expenseUniqueId;
        }

        public String getTravelEventUniqueId() {
            return travelEventUniqueId;
        }

        public void setTravelEventUniqueId(String travelEventUniqueId) {
            this.travelEventUniqueId = travelEventUniqueId;
        }

        public String getExpenseDetails() {
            return expenseDetails;
        }

        public void setExpenseDetails(String expenseDetails) {
            this.expenseDetails = expenseDetails;
        }

        public String getExpenseAmount() {
            return expenseAmount;
        }

        public void setExpenseAmount(String expenseAmount) {
            this.expenseAmount = expenseAmount;
        }

        public String getExpenseDate() {
            return expenseDate;
        }

        public void setExpenseDate(String expenseDate) {
            this.expenseDate = expenseDate;
        }

        public String getExpenseVoucher() {
            return expenseVoucher;
        }

        public void setExpenseVoucher(String expenseVoucher) {
            this.expenseVoucher = expenseVoucher;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Object getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Object updatedAt) {
            this.updatedAt = updatedAt;
        }

    }
}
