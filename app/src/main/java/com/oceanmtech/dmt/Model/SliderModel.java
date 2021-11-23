package com.oceanmtech.dmt.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SliderModel {
    @SerializedName("slider")
    @Expose
    private List<Slider> slider = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<Slider> getSlider() {
        return slider;
    }

    public void setSlider(List<Slider> slider) {
        this.slider = slider;
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

    public class Slider {
        @SerializedName("s_id")
        @Expose
        private String sId;
        @SerializedName("s_image")
        @Expose
        private String sImage;

        public String getSId() {
            return sId;
        }

        public void setSId(String sId) {
            this.sId = sId;
        }

        public String getSImage() {
            return sImage;
        }

        public void setSImage(String sImage) {
            this.sImage = sImage;
        }

    }
}
