package com.oceanmtech.dmt.Activity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;

import java.io.File;


public class MyApp extends Application {
    public static Context context;

    public static  boolean flagHomeAction=true;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        // AudienceNetworkAds.initialize(this);
    }

    public void refreshdeleteGallery(File file) {
        try {
            MediaScannerConnection.scanFile(getApplicationContext(), new String[]{file.getAbsolutePath()}, null, new C08421());
        } catch (Exception e) {
        }
    }

    class C08421 implements MediaScannerConnection.OnScanCompletedListener {
        C08421() {
        }

        public void onScanCompleted(String path, Uri deleteUri) {
            if (path != null) {
                if (deleteUri != null) {
                    try {
                        getApplicationContext().getContentResolver().delete(deleteUri, null, null);
                        getApplicationContext().sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", deleteUri));
                    } catch (Exception e) {
                    }
                }
            } else if (deleteUri != null) {
                getApplicationContext().getContentResolver().delete(deleteUri, null, null);
                getApplicationContext().sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", deleteUri));
            }
        }
    }
}
