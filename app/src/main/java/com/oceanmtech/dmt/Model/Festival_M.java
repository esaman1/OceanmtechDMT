package com.oceanmtech.dmt.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Festival_M {

    @SerializedName("category")
    @Expose
    private List<Category> category = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
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



    public class Category {

        @SerializedName("upc_id")
        @Expose
        private String upcId;
        @SerializedName("upc_category")
        @Expose
        private String upcCategory;
        @SerializedName("upc_date")
        @Expose
        private String upcDate;
        @SerializedName("image")
        @Expose
        private String image;

        public String getUpcId() {
            return upcId;
        }

        public void setUpcId(String upcId) {
            this.upcId = upcId;
        }

        public String getUpcCategory() {
            return upcCategory;
        }

        public void setUpcCategory(String upcCategory) {
            this.upcCategory = upcCategory;
        }

        public String getUpcDate() {
            return upcDate;
        }

        public void setUpcDate(String upcDate) {
            this.upcDate = upcDate;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }
}