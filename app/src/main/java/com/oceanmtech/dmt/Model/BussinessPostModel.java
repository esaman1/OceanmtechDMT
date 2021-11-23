package com.oceanmtech.dmt.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BussinessPostModel {

    @SerializedName("business_category")
    @Expose
    private List<BusinessCategory> businessCategory = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<BusinessCategory> getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(List<BusinessCategory> businessCategory) {
        this.businessCategory = businessCategory;
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

    public class BusinessCategory {

        @SerializedName("bc_id")
        @Expose
        private String bcId;
        @SerializedName("cat_name")
        @Expose
        private String catName;
        @SerializedName("image")
        @Expose
        private String image;

        public String getBcId() {
            return bcId;
        }

        public void setBcId(String bcId) {
            this.bcId = bcId;
        }

        public String getCatName() {
            return catName;
        }

        public void setCatName(String catName) {
            this.catName = catName;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }

}
