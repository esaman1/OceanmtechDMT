package com.oceanmtech.dmt.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Business_Add_Model implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("isregister")
    @Expose
    private String isregister;
    @SerializedName("id")
    @Expose
    private String id;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIsregister() {
        return isregister;
    }

    public void setIsregister(String isregister) {
        this.isregister = isregister;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}