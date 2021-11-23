package com.oceanmtech.dmt.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BackgroundImageModel {

    @SerializedName("content")
    public ArrayList<Content> content;
    @SerializedName("status")
    public String status;

    public static class Content {
        @SerializedName("bg_url")
        public String bg_url;
        @SerializedName("id")
        public int id;
    }
}
