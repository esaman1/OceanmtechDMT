package com.oceanmtech.dmt.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BussinessCat_Data_model {
    @SerializedName("business_category_data")
    @Expose
    private List<BusinessCategoryDatum> businessCategoryData = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<BusinessCategoryDatum> getBusinessCategoryData() {
        return businessCategoryData;
    }

    public void setBusinessCategoryData(List<BusinessCategoryDatum> businessCategoryData) {
        this.businessCategoryData = businessCategoryData;
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
    public class BusinessCategoryDatum {

        @SerializedName("bd_id")
        @Expose
        private String bdId;
        @SerializedName("bd_free_and_paid")
        @Expose
        private String bdFreeAndPaid;
        @SerializedName("image")
        @Expose
        private String image;

        public String getBdId() {
            return bdId;
        }

        public void setBdId(String bdId) {
            this.bdId = bdId;
        }

        public String getBdFreeAndPaid() {
            return bdFreeAndPaid;
        }

        public void setBdFreeAndPaid(String bdFreeAndPaid) {
            this.bdFreeAndPaid = bdFreeAndPaid;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }
}
