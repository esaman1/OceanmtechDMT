package com.oceanmtech.dmt.QuoteCreater;

import android.os.Environment;

import com.oceanmtech.dmt.Model.LocalFrameItem;
import com.oceanmtech.dmt.R;

import java.io.File;
import java.util.ArrayList;

public class Constans {

    public static String businessname = "";
    public static String Emailaddress = "";
    public static String Website = "";
    public static String Address = "";
    public static String MobileNo = "";
    public static String bid = "";
    public static boolean logo;
    public static boolean isLogo;

    public static File mSdCard = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath());
    public static File Created_DIRECTORY = new File(mSdCard, "OceanmtechDMT");
    public static File Video_DIRECTORY = new File(mSdCard, "OceanmtechDMT" + File.separator + "Video");
    public static File status_DIRECTORY = new File(mSdCard, "OceanmtechDMT/Status");
    public static File Data_DIRECTORY = new File(mSdCard, "OceanmtechDMT/.data");



    public static String INDIA="91";

    public static final int REQUEST_CODE_ADD_TEXT = 787;

    public static final String IsPurchased = "ispurchased";
    //public static final String INAPPID = "android.test.purchased";
    // public static final String INAPPID = "brandbox_purchase";
     public static final String INAPPID = "oceanmtechdmt_premium";

    public static ArrayList<LocalFrameItem> getAllFrames() {
        ArrayList<LocalFrameItem> arrayList = new ArrayList<>();
        arrayList.add(new LocalFrameItem(R.layout.new_custom_frame_1, R.layout.new_custom_frame_preview_1));
        arrayList.add(new LocalFrameItem(R.layout.custom_frame_1, R.layout.custom_frame_preview_1));
        arrayList.add(new LocalFrameItem(R.layout.custom_frame_21_bordar, R.layout.custom_frame_preview_21_bordar));
        arrayList.add(new LocalFrameItem(R.layout.custom_frame_5, R.layout.custom_frame_preview_5));
        arrayList.add(new LocalFrameItem(R.layout.custom_frame_8, R.layout.custom_frame_preview_8));
        arrayList.add(new LocalFrameItem(R.layout.custom_frame_6, R.layout.custom_frame_preview_6));
        arrayList.add(new LocalFrameItem(R.layout.custom_frame_3, R.layout.custom_frame_preview_3));
        arrayList.add(new LocalFrameItem(R.layout.custom_frame_4, R.layout.custom_frame_preview_4));
        arrayList.add(new LocalFrameItem(R.layout.custom_frame_7, R.layout.custom_frame_preview_7));
        arrayList.add(new LocalFrameItem(R.layout.custom_frame_22_bordar, R.layout.custom_frame_preview_22_bordar));
        arrayList.add(new LocalFrameItem(R.layout.custom_frame_17_bordar, R.layout.custom_frame_preview_17_bordar));
        arrayList.add(new LocalFrameItem(R.layout.custom_frame_18_bordar, R.layout.custom_frame_preview_18_bordar));
        arrayList.add(new LocalFrameItem(R.layout.custom_frame_19_bordar, R.layout.custom_frame_preview_19_bordar));
        arrayList.add(new LocalFrameItem(R.layout.custom_frame_20_bordar, R.layout.custom_frame_preview_20_bordar));

        return arrayList;
    }

}
