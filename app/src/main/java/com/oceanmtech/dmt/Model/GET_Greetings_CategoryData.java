package com.oceanmtech.dmt.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GET_Greetings_CategoryData {
    @SerializedName("greetings_category_data")
    @Expose
    private List<GreetingsCategoryDatum> greetingsCategoryData = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<GreetingsCategoryDatum> getGreetingsCategoryData() {
        return greetingsCategoryData;
    }

    public void setGreetingsCategoryData(List<GreetingsCategoryDatum> greetingsCategoryData) {
        this.greetingsCategoryData = greetingsCategoryData;
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


    public class GreetingsCategoryDatum {

        @SerializedName("gd_id")
        @Expose
        private String gdId;
        @SerializedName("gd_free_and_paid")
        @Expose
        private String gdFreeAndPaid;
        @SerializedName("thumb_image")
        @Expose
        private String thumbImage;
        @SerializedName("image")
        @Expose
        private String image;

        public String getGdId() {
            return gdId;
        }

        public void setGdId(String gdId) {
            this.gdId = gdId;
        }

        public String getGdFreeAndPaid() {
            return gdFreeAndPaid;
        }

        public void setGdFreeAndPaid(String gdFreeAndPaid) {
            this.gdFreeAndPaid = gdFreeAndPaid;
        }

        public String getThumbImage() {
            return thumbImage;
        }

        public void setThumbImage(String thumbImage) {
            this.thumbImage = thumbImage;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }
}