package com.oceanmtech.dmt.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GET_Greetings_Category {
    @SerializedName("greetings_category")
    @Expose
    private List<GreetingsCategory> greetingsCategory = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<GreetingsCategory> getGreetingsCategory() {
        return greetingsCategory;
    }

    public void setGreetingsCategory(List<GreetingsCategory> greetingsCategory) {
        this.greetingsCategory = greetingsCategory;
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

    public class GreetingsCategory {

        @SerializedName("cg_id")
        @Expose
        private String cgId;
        @SerializedName("cat_name")
        @Expose
        private String catName;
        @SerializedName("image")
        @Expose
        private String image;

        public String getCgId() {
            return cgId;
        }

        public void setCgId(String cgId) {
            this.cgId = cgId;
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