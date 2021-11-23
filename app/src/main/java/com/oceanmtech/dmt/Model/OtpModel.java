package com.oceanmtech.dmt.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public class Data {

        @SerializedName("0")
        @Expose
        private innerData _0;
        @SerializedName("group_id")
        @Expose
        private double groupId;

        public innerData get0() {
            return _0;
        }

        public void set0(innerData _0) {
            this._0 = _0;
        }

        public double getGroupId() {
            return groupId;
        }

        public void setGroupId(Integer groupId) {
            this.groupId = groupId;
        }

    }

    public class innerData {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("customid")
        @Expose
        private String customid;
        @SerializedName("customid1")
        @Expose
        private String customid1;
        @SerializedName("customid2")
        @Expose
        private String customid2;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("status")
        @Expose
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCustomid() {
            return customid;
        }

        public void setCustomid(String customid) {
            this.customid = customid;
        }

        public String getCustomid1() {
            return customid1;
        }

        public void setCustomid1(String customid1) {
            this.customid1 = customid1;
        }

        public String getCustomid2() {
            return customid2;
        }

        public void setCustomid2(String customid2) {
            this.customid2 = customid2;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }
}
