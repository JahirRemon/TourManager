package com.example.mdjahirulislam.simplemap.modelClass;

/**
 * Created by mdjahirulislam on 27/04/17.
 */

public class RegistrationModel {
    private String user_unique_id;
    private String user_full_name;
    private String user_name;
    private String user_mobile_no;
    private String user_address;
    private String password;
    private String created_at;

    public RegistrationModel(String user_full_name, String user_name, String user_mobile_no, String user_address, String password) {
        this.user_full_name = user_full_name;
        this.user_name = user_name;
        this.user_mobile_no = user_mobile_no;
        this.user_address = user_address;
        this.password = password;
    }

    public RegistrationModel(String user_name, String password) {
        this.user_name = user_name;
        this.password = password;
    }

    public RegistrationModel(String user_unique_id, String user_full_name, String user_name, String user_mobile_no, String user_address, String created_at) {
        this.user_unique_id = user_unique_id;
        this.user_full_name = user_full_name;
        this.user_name = user_name;
        this.user_mobile_no = user_mobile_no;
        this.user_address = user_address;
        this.created_at = created_at;
    }

    public String getUser_unique_id() {
        return user_unique_id;
    }

    public void setUser_unique_id(String user_unique_id) {
        this.user_unique_id = user_unique_id;
    }

    public String getUser_full_name() {
        return user_full_name;
    }

    public void setUser_full_name(String user_full_name) {
        this.user_full_name = user_full_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_mobile_no() {
        return user_mobile_no;
    }

    public void setUser_mobile_no(String user_mobile_no) {
        this.user_mobile_no = user_mobile_no;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
