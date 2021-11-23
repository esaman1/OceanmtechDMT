package com.oceanmtech.dmt.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Video_Model {

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

        @SerializedName("upv_id")
        @Expose
        private String upvId;
        @SerializedName("cat_id")
        @Expose
        private String catId;
        @SerializedName("free_and_paid")
        @Expose
        private String freeAndPaid;
        @SerializedName("video")
        @Expose
        private String video;

        public String getUpvId() {
            return upvId;
        }

        public void setUpvId(String upvId) {
            this.upvId = upvId;
        }

        public String getCatId() {
            return catId;
        }

        public void setCatId(String catId) {
            this.catId = catId;
        }

        public String getFreeAndPaid() {
            return freeAndPaid;
        }

        public void setFreeAndPaid(String freeAndPaid) {
            this.freeAndPaid = freeAndPaid;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

    }
}
