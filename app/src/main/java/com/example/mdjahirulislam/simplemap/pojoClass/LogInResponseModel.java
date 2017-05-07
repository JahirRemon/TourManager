package com.example.mdjahirulislam.simplemap.pojoClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mdjahirulislam on 28/04/17.
 */

public class LogInResponseModel {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("error_msg")
    @Expose
    private String errorMsg;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("event_exists")
    @Expose
    private Boolean eventExists;
    @SerializedName("event_msg")
    @Expose
    private String eventMsg;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getEventExists() {
        return eventExists;
    }

    public void setEventExists(Boolean eventExists) {
        this.eventExists = eventExists;
    }

    public String getEventMsg() {
        return eventMsg;
    }

    public void setEventMsg(String eventMsg) {
        this.eventMsg = eventMsg;
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


    public static class User {

        @SerializedName("user_unique_id")
        @Expose
        private String userUniqueId;
        @SerializedName("user_full_name")
        @Expose
        private String userFullName;
        @SerializedName("user_name")
        @Expose
        private String userName;
        @SerializedName("user_mobile_no")
        @Expose
        private String userMobileNo;
        @SerializedName("user_address")
        @Expose
        private String userAddress;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public String getUserUniqueId() {
            return userUniqueId;
        }

        public void setUserUniqueId(String userUniqueId) {
            this.userUniqueId = userUniqueId;
        }

        public String getUserFullName() {
            return userFullName;
        }

        public void setUserFullName(String userFullName) {
            this.userFullName = userFullName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserMobileNo() {
            return userMobileNo;
        }

        public void setUserMobileNo(String userMobileNo) {
            this.userMobileNo = userMobileNo;
        }

        public String getUserAddress() {
            return userAddress;
        }

        public void setUserAddress(String userAddress) {
            this.userAddress = userAddress;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

    }

}
