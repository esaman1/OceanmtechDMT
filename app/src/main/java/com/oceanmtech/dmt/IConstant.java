package com.oceanmtech.dmt;

import static com.oceanmtech.dmt.Activity.MyApp.context;

import android.os.Environment;

import java.io.File;

public interface IConstant {


    String BUSINESS_NAME = "business_name";

    String BUSINESS_EMAIL = "business_email";
    String WEBSITE = "website";
    String ADDRESS = "address";
    String LOGO = "logo";
    String MOBILE_NO = "mobileno";
    String BID = "bid";
    String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    String IS_STATUS = "isstatus";
    String IS_PREMIUM = "ispremium";
    String IS_PADE = "ispade";
    String IS_Trial = "istrial";
    String IS_Trial_Tost="";
    String IS_R_ID="isrid";
    String SENDERID="senderId";
    String APIKEY="apikey";

    String FbBannerAd="fb_banner_ad";
    String FbFullAd="fb_full_ad";
    String FbMediumRectAd="fb_mediumrect_ad";

    String LOGO_BUSINESS_NAME = "logo_business_name";
    String AAD_TAGLINE = "logo_business_name";


    String VIDEO_CAT_ID = "video_cat_id";


    String Google_NativeAd="ca-app-pub-3940256099942544/2247696110";
    // String BUSINESS_LOGO_PATH ="Business Card";

    File FOLDER_PATH = new File(context.getExternalCacheDir() + "/OceanmtechDMT/.Data/");
    String BUSINESS_LOGO_PATH = context.getExternalCacheDir() + "/OceanmtechDMT/.Data/.logo.png";


}
