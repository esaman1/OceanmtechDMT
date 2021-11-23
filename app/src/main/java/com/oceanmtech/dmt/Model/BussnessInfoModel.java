package com.oceanmtech.dmt.Model;

import java.io.Serializable;

public class BussnessInfoModel implements Serializable
{
    public String bName;
    public String bWebsite;
    public String bAddress;
    public String bEmailId;
    public String bMobileNo;
    public String BId;
    public String BLogo;

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }
    public String getBLogo() {
        return BLogo;
    }

    public void setBLogo(String BLogo) {
        this.BLogo = BLogo;
    }

    public String getbWebsite() {
        return bWebsite;
    }

    public void setbWebsite(String bWebsite) {
        this.bWebsite = bWebsite;
    }

    public String getbAddress() {
        return bAddress;
    }

    public void setbAddress(String bAddress) {
        this.bAddress = bAddress;
    }

    public String getbEmailId() {
        return bEmailId;
    }

    public void setbEmailId(String bEmailId) {
        this.bEmailId = bEmailId;
    }

    public String getbMobileNo() {
        return bMobileNo;
    }

    public void setbMobileNo(String bMobileNo) {
        this.bMobileNo = bMobileNo;
    }

    public String getBId() {
        return BId;
    }

    public void setBId(String BId) {
        this.BId = BId;
    }
}
