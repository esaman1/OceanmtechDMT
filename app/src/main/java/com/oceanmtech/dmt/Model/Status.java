package com.oceanmtech.dmt.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status {

    @SerializedName("a_id")
    @Expose
    private String aId;
    @SerializedName("api_key")
    @Expose
    private String apiKey;
    @SerializedName("sender")
    @Expose
    private String sender;
    @SerializedName("banner_ad")
    @Expose
    private String bannerAd;
    @SerializedName("full_page_ad")
    @Expose
    private String fullPageAd;
    @SerializedName("video_ad")
    @Expose
    private String videoAd;
    @SerializedName("native_ad")
    @Expose
    private String nativeAd;
    @SerializedName("f_banner_ad")
    @Expose
    private String fBannerAd;
    @SerializedName("f_full_page_ad")
    @Expose
    private String fFullPageAd;
    @SerializedName("f_native_ad")
    @Expose
    private String fNativeAd;
    @SerializedName("f_medium_rectangle_ad")
    @Expose
    private String fMediumRectangleAd;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("paid")
    @Expose
    private String paid;

    public String getAId() {
        return aId;
    }

    public void setAId(String aId) {
        this.aId = aId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getBannerAd() {
        return bannerAd;
    }

    public void setBannerAd(String bannerAd) {
        this.bannerAd = bannerAd;
    }

    public String getFullPageAd() {
        return fullPageAd;
    }

    public void setFullPageAd(String fullPageAd) {
        this.fullPageAd = fullPageAd;
    }

    public String getVideoAd() {
        return videoAd;
    }

    public void setVideoAd(String videoAd) {
        this.videoAd = videoAd;
    }

    public String getNativeAd() {
        return nativeAd;
    }

    public void setNativeAd(String nativeAd) {
        this.nativeAd = nativeAd;
    }

    public String getFBannerAd() {
        return fBannerAd;
    }

    public void setFBannerAd(String fBannerAd) {
        this.fBannerAd = fBannerAd;
    }

    public String getFFullPageAd() {
        return fFullPageAd;
    }

    public void setFFullPageAd(String fFullPageAd) {
        this.fFullPageAd = fFullPageAd;
    }

    public String getFNativeAd() {
        return fNativeAd;
    }

    public void setFNativeAd(String fNativeAd) {
        this.fNativeAd = fNativeAd;
    }

    public String getFMediumRectangleAd() {
        return fMediumRectangleAd;
    }

    public void setFMediumRectangleAd(String fMediumRectangleAd) {
        this.fMediumRectangleAd = fMediumRectangleAd;
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

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }
}
