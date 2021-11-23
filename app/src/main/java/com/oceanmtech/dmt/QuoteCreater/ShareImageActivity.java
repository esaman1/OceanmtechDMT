package com.oceanmtech.dmt.QuoteCreater;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.oceanmtech.dmt.R;
import com.oceanmtech.dmt.Utility;

import java.io.File;


public class ShareImageActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout img_fb, img_whatapp, img_insta, img_all_share;
    ImageView img_delete;
    ImageView img_share, imgBack;
    LinearLayout linearLayout;
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_image);
        initView();

        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView = new AdView(ShareImageActivity.this);
        mAdView.setAdSize(AdSize.BANNER);
        mAdView.setAdUnitId("PreferenceHelper.getString(Constants.banner, )");
        mAdView.loadAd(adRequest);
        linearLayout = (LinearLayout) findViewById(R.id.adView);
        linearLayout.addView(mAdView);
    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img_share = findViewById(R.id.img_share);
        Glide.with(getApplicationContext()).load(Utility.shareFile).into(img_share);

        img_fb = findViewById(R.id.img_fb);
        img_whatapp = findViewById(R.id.img_whatapp);
        img_insta = findViewById(R.id.img_insta);
        img_all_share = findViewById(R.id.img_all_share);
        img_delete = findViewById(R.id.img_delete);
        img_fb.setOnClickListener(this);
        img_whatapp.setOnClickListener(this);
        img_insta.setOnClickListener(this);
        img_all_share.setOnClickListener(this);
        img_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_fb:
                shareImage("com.facebook.katana");
                break;
            case R.id.img_insta:
                shareImage("com.instagram.android");
                break;
            case R.id.img_whatapp:
                shareImage("com.whatsapp");
                break;
            case R.id.img_all_share:
                shareImage("all");
                break;
            case R.id.img_delete:
                loadAlter();
                break;
        }
    }

    private void loadAlter() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(ShareImageActivity.this);
        builder1.setMessage("Are you want to sure delete this image..");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        loadDelete();
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void loadDelete() {
        File fdelete = new File(String.valueOf(Utility.shareFile));
        if (fdelete.exists()) {
            if (fdelete.delete()) {
                System.out.println("Image Delete Successful");
                setResult(RESULT_OK);
                finish();
                Toast.makeText(this, "Image Delete Successful", Toast.LENGTH_SHORT).show();
            } else {
                // System.out.println("Image  not Deleted :" + file_dj_path);
                Toast.makeText(this, "Image not Deleted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void shareImage(String appPackage) {
        String str_path = String.valueOf(Utility.shareFile);
        String str_photo_name = Utility.shareFile.getName();
        Utility.shareImageDialog(ShareImageActivity.this, str_path, str_photo_name, appPackage);
        try {
            Intent share = new Intent("android.intent.action.SEND");
            share.setType("image/jpeg");
            if (appPackage != "all") {
                share.setPackage(appPackage);
            }
            share.putExtra("android.intent.extra.TEXT", "Using " + getResources().getString(R.string.app_name) + " photo edit App.\n " + "http://play.google.com/store/apps/details?id=" + getPackageName() + "\n #Multiple Photo Blender");
            share.putExtra("android.intent.extra.STREAM", Uri.fromFile(Utility.shareFile));
            /////startActivity(Intent.createChooser(share, "Share Image"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
