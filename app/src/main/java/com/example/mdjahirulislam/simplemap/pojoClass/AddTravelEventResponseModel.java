package com.example.mdjahirulislam.simplemap.pojoClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mdjahirulislam on 28/04/17.
 */

public class AddTravelEventResponseModel {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("error_msg")
    @Expose
    private String errorMsg;
    @SerializedName("travel_event")
    @Expose
    private List<TravelEvent> travelEvent = null;

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

    public List<TravelEvent> getTravelEvent() {
        return travelEvent;
    }

    public void setTravelEvent(List<TravelEvent> travelEvent) {
        this.travelEvent = travelEvent;
    }

    public static class TravelEvent {

        @SerializedName("travel_event_unique_id")
        @Expose
        private String travelEventUniqueId;
        @SerializedName("user_unique_id")
        @Expose
        private String userUniqueId;
        @SerializedName("travel_destination")
        @Expose
        private String travelDestination;
        @SerializedName("estimated_budget")
        @Expose
        private String estimatedBudget;
        @SerializedName("from_date")
        @Expose
        private String fromDate;
        @SerializedName("to_date")
        @Expose
        private String toDate;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private Object updatedAt;

        public String getTravelEventUniqueId() {
            return travelEventUniqueId;
        }

        public void setTravelEventUniqueId(String travelEventUniqueId) {
            this.travelEventUniqueId = travelEventUniqueId;
        }

        public String getUserUniqueId() {
            return userUniqueId;
        }

        public void setUserUniqueId(String userUniqueId) {
            this.userUniqueId = userUniqueId;
        }

        public String getTravelDestination() {
            return travelDestination;
        }

        public void setTravelDestination(String travelDestination) {
            this.travelDestination = travelDestination;
        }

        public String getEstimatedBudget() {
            return estimatedBudget;
        }

        public void setEstimatedBudget(String estimatedBudget) {
            this.estimatedBudget = estimatedBudget;
        }

        public String getFromDate() {
            return fromDate;
        }

        public void setFromDate(String fromDate) {
            this.fromDate = fromDate;
        }

        public String getToDate() {
            return toDate;
        }

        public void setToDate(String toDate) {
            this.toDate = toDate;
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
