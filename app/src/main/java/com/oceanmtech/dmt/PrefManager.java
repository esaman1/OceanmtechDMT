package com.oceanmtech.dmt;

import android.content.Context;
import android.content.SharedPreferences;

import static com.oceanmtech.dmt.IConstant.IS_FIRST_TIME_LAUNCH;
import static com.oceanmtech.dmt.IConstant.IS_STATUS;
import static com.oceanmtech.dmt.IConstant.IS_Trial;

public class PrefManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    int PRIVATE_MODE = 0;

    public static final String PREF_NAME = "my-intro-slider";


    public PrefManager(Context context1) {

        this.context = context1;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }


    public boolean isFirstTimeLaunch() {
        return sharedPreferences.getBoolean(IS_FIRST_TIME_LAUNCH, false);
    }

    public void setisstatus(boolean isstatus) {
        editor.putBoolean(IS_STATUS, isstatus);
        editor.commit();
    }

    public boolean isstatus() {
        return sharedPreferences.getBoolean(IS_STATUS, false);
    }


//    public void setispremium(boolean ispremium) {
//        editor.putBoolean(IS_PREMIUM, ispremium);
//        editor.commit();
//    }
//
//    public boolean ispremium() {
//        return sharedPreferences.getBoolean(IS_PREMIUM, false);
//    }

    public void setistrial(boolean istrial) {
        editor.putBoolean(IS_Trial, istrial);
        editor.commit();
    }




    public boolean get_is_purchased(String KEY) {
        return sharedPreferences.getBoolean(KEY, false);
    }

    public void set_is_purchased(String KEY,boolean is_purchased) {
        editor.putBoolean(KEY, is_purchased);
        editor.commit();
    }


    public boolean istrial() {
        return sharedPreferences.getBoolean(IS_Trial, false);
    }



//    public void setisrid(boolean isrid) {
//        editor.putBoolean(IS_R_ID, isrid);
//        editor.commit();
//    }
//
//    public boolean isrid() {
//        return sharedPreferences.getBoolean(IS_R_ID, false);
//    }


    public String setString(String Key, String data) {
        editor.putString(Key, data);
        editor.commit();
        return data;
    }

    public String getString(String Key) {
        return sharedPreferences.getString(Key,"");
    }


 public boolean setBoolen(String Key, boolean data) {
        editor.putBoolean(Key, data);
        editor.commit();
        return data;
    }

    public boolean getBoolen(String Key) {
        return sharedPreferences.getBoolean(Key,false);
    }

}
