package com.oceanmtech.dmt.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Home_Data_Model {

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

        @SerializedName("c_id")
        @Expose
        private String cId;
            @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("images")
        @Expose
        private List<Image> images = null;

        public String getCId() {
            return cId;
        }

        public void setCId(String cId) {
            this.cId = cId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Image> getImages() {
            return images;
        }

        public void setImages(List<Image> images) {
            this.images = images;
        }




    }
    public class Image {

        @SerializedName("d_id")
        @Expose
        private String dId;
        @SerializedName("cat_id")
        @Expose
        private String catId;
        @SerializedName("title")
        @Expose
        private String title;
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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }

    }

