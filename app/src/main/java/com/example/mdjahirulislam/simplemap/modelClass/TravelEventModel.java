package com.example.mdjahirulislam.simplemap.modelClass;

/**
 * Created by mdjahirulislam on 28/04/17.
 */

public class TravelEventModel {

    private String travel_event_unique_id;
    private String user_unique_id;
    private String travel_destination;
    private String estimated_budget;
    private String from_date;
    private String to_date;
    private String created_at;




    private String travelMomentDetails;
    private String travelMomentImage;

    public TravelEventModel(String travel_event_unique_id,String user_unique_id, String travel_destination, String estimated_budget, String from_date, String to_date) {
        this.travel_event_unique_id = travel_event_unique_id;
        this.user_unique_id = user_unique_id;
        this.travel_destination = travel_destination;
        this.estimated_budget = estimated_budget;
        this.from_date = from_date;
        this.to_date = to_date;
    }

    public TravelEventModel(String travel_event_unique_id, String user_unique_id, String travel_destination, String estimated_budget, String from_date, String to_date, String created_at) {
        this.travel_event_unique_id = travel_event_unique_id;
        this.user_unique_id = user_unique_id;
        this.travel_destination = travel_destination;
        this.estimated_budget = estimated_budget;
        this.from_date = from_date;
        this.to_date = to_date;
        this.created_at = created_at;
    }

    public TravelEventModel(String user_unique_id, String travel_destination, String estimated_budget, String from_date, String to_date) {
        this.user_unique_id = user_unique_id;
        this.travel_destination = travel_destination;
        this.estimated_budget = estimated_budget;
        this.from_date = from_date;
        this.to_date = to_date;
    }

    public TravelEventModel() {

    }

    public String getTravel_event_unique_id() {

        return travel_event_unique_id;
    }

    public void setTravel_event_unique_id(String travel_event_unique_id) {
        this.travel_event_unique_id = travel_event_unique_id;
    }

    public String getUser_unique_id() {
        return user_unique_id;
    }

    public void setUser_unique_id(String user_unique_id) {
        this.user_unique_id = user_unique_id;
    }

    public String getTravel_destination() {
        return travel_destination;
    }

    public void setTravel_destination(String travel_destination) {
        this.travel_destination = travel_destination;
    }

    public String getEstimated_budget() {
        return estimated_budget;
    }

    public void setEstimated_budget(String estimated_budget) {
        this.estimated_budget = estimated_budget;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
