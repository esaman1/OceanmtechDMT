package com.oceanmtech.dmt.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Business_Get_model implements Serializable {

    @SerializedName("business")
    @Expose
    private List<Business> business = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<Business> getBusiness() {
        return business;
    }

    public void setBusiness(List<Business> business) {
        this.business = business;
    }

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

    public class Business {

        @SerializedName("b_id")
        @Expose
        private String bId;
        @SerializedName("b_name")
        @Expose
        private String bName;
        @SerializedName("b_email")
        @Expose
        private String bEmail;
        @SerializedName("b_mobile_number")
        @Expose
        private String bMobileNumber;
        @SerializedName("b_address")
        @Expose
        private String bAddress;
        @SerializedName("b_website")
        @Expose
        private String bWebsite;
        @SerializedName("b_logo")
        @Expose
        private String bLogo;

        public String getBId() {
            return bId;
        }

        public void setBId(String bId) {
            this.bId = bId;
        }

        public String getBName() {
            return bName;
        }

        public void setBName(String bName) {
            this.bName = bName;
        }

        public String getBEmail() {
            return bEmail;
        }

        public void setBEmail(String bEmail) {
            this.bEmail = bEmail;
        }

        public String getBMobileNumber() {
            return bMobileNumber;
        }

        public void setBMobileNumber(String bMobileNumber) {
            this.bMobileNumber = bMobileNumber;
        }

        public String getBAddress() {
            return bAddress;
        }

        public void setBAddress(String bAddress) {
            this.bAddress = bAddress;
        }

        public String getBWebsite() {
            return bWebsite;
        }

        public void setBWebsite(String bWebsite) {
            this.bWebsite = bWebsite;
        }

        public String getBLogo() {
            return bLogo;
        }

        public void setBLogo(String bLogo) {
            this.bLogo = bLogo;
        }

    }

}
