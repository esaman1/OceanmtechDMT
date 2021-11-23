package com.oceanmtech.dmt.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.R;

public class HelpSupport extends AppCompatActivity {

    private TextView mo_number;
    private TextView mail_id;
    String number = "8000853781";
    private TextView helpclose;

    AdView adView;
    private FrameLayout ad_Layout;
    private TextView w_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_support);


        mo_number = (TextView) findViewById(R.id.mo_number);
        w_number = (TextView) findViewById(R.id.w_number);
        mail_id = (TextView) findViewById(R.id.mail_id);
        helpclose = (TextView) findViewById(R.id.helpclose);
        ad_Layout=(FrameLayout)findViewById(R.id.ad_Layout);


        if (new PrefManager(HelpSupport.this).getBoolen(IConstant.IS_PADE)) {
            ad_Layout.setVisibility(View.GONE);
        } else {

        }
        helpclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        w_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("smsto:" + "+918000853781");
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.putExtra("sms_body", "Hello");
                i.setPackage("com.whatsapp");
                HelpSupport.this.startActivity(i);


            }
        });


        mo_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });

        mail_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "youngbreedtechnologies@gmail.com", null));
                startActivity(Intent.createChooser(emailIntent, "Send email..."));

            }
        });
        loadBanner();
    }


    private void loadBanner() {
        adView = new AdView(HelpSupport.this);
        adView.setAdUnitId(getString(R.string.ad_id_banner));
        ad_Layout.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();

        com.google.android.gms.ads.AdSize adSize = getAdSize();
        adView.setAdSize(adSize);
        adView.loadAd(adRequest);
    }

    private com.google.android.gms.ads.AdSize getAdSize() {
        Display display = HelpSupport.this.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);
        return AdSize.getCurrentOrientationBannerAdSizeWithWidth(HelpSupport.this, adWidth);
    }
}