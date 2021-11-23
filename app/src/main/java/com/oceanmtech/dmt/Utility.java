package com.oceanmtech.dmt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;

public class Utility {

    public static File shareFile;
    public static String shareFile_url;
    public static final String DIRECTORY_PATH = "/MotivationalQuotes/CreatedQuotes";
    public static final String DIRECTORY_PATH2 = "/MotivationalQuotes/DownloadQuotes";
    public static final String DIRECTORY_temp = "temp";
    public static final String DIRECTORY_Liked = "/MotivationalQuotes/LikedQuotes";
    public static Bitmap share_;


    public static final float HUE_CYAN = 180.0f;
    public static final float HUE_MAGENTA = 300.0f;
    public static final float HUE_VIOLET = 270.0f;

    public static int READ_WRITE_PERMISSION = 121;
    public static final String text_path = "text_path";

    public static final int[] effect_thumb = new int[]{R.drawable.filter_0, R.drawable.filter_1, R.drawable.filter_2, R.drawable.filter_3, R.drawable.filter_4, R.drawable.filter_5, R.drawable.filter_6, R.drawable.filter_7, R.drawable.filter_8, R.drawable.filter_9, R.drawable.filter_10, R.drawable.filter_11, R.drawable.filter_12, R.drawable.filter_13, R.drawable.filter_14, R.drawable.filter_15, R.drawable.filter_16, R.drawable.filter_17, R.drawable.filter_18, R.drawable.filter_19, R.drawable.filter_20, R.drawable.filter_21};
    public static String str_quotes[] = {"Anniversary", "Attitude", "Birthday", "Dreams", "Education", "Friendship", "Genius", "Life", "Love", "Man", "Miss u", "Money", "Saying", "Success", "Wisdom", "Woman"};
    public static int image[] = {R.drawable.anniversary, R.drawable.attitude, R.drawable.birthday, R.drawable.dreams, R.drawable.education, R.drawable.friendship, R.drawable.genius, R.drawable.life, R.drawable.love, R.drawable.men, R.drawable.missu, R.drawable.money, R.drawable.saying, R.drawable.success, R.drawable.wisdom, R.drawable.women};


    public static Bitmap getBitmapFromAsset(Context context, String filePath) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(context.getAssets().open(filePath));
        } catch (IOException e) {
        }
        return bitmap;
    }


    public static boolean isInternetAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void shareImageDialog(Activity context, String mediaPath, String photo_name, String package_name) {
        try {
            File file = new File(mediaPath);
            Uri uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".my.package.name.provider", file);
            Intent intent = new Intent("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.STREAM", uri);
            intent.putExtra("android.intent.extra.SUBJECT", "" + context.getString(R.string.app_name));
            intent.setType("image/*");
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            filterByPackageName(context, intent, package_name);
            context.startActivityForResult(Intent.createChooser(intent, "Share image by..."), 108);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void filterByPackageName(Context context, Intent intent, String prefix) {
        for (ResolveInfo info : context.getPackageManager().queryIntentActivities(intent, 0)) {
            if (info.activityInfo.packageName.toLowerCase().startsWith(prefix)) {
                intent.setPackage(info.activityInfo.packageName);
                return;
            }
        }
    }

}
