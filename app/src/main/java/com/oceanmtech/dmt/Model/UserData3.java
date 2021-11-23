package com.oceanmtech.dmt.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserData3 {

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
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

    public class Datum {

        @SerializedName("d_id")
        @Expose
        private String dId;
        @SerializedName("cat_id")
        @Expose
        private String catId;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("free_and_paid")
        @Expose
        private String freeAndPaid;
        @SerializedName("image")
        @Expose
        private String image;

        public String getDId() {
            return dId;
        }

        public void setDId(String dId) {
            this.dId = dId;
        }

        public String getCatId() {
            return catId;
        }

        public void setCatId(String catId) {
            this.catId = catId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFreeAndPaid() {
            return freeAndPaid;
        }

        public void setFreeAndPaid(String freeAndPaid) {
            this.freeAndPaid = freeAndPaid;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }


}