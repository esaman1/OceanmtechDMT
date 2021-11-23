package com.oceanmtech.dmt.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.R;

public class Our_appActivity extends AppCompatActivity {

    private CardView app1;
    private CardView app2;
    TemplateView template;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_app);


        app1=(CardView)findViewById(R.id.app1);
        app2=(CardView)findViewById(R.id.app2);
        template = findViewById(R.id.my_template);



        if (new PrefManager(Our_appActivity.this).getBoolen(IConstant.IS_PADE)) {
            template.setVisibility(View.GONE);
        } else {
            LoadNativeAd();
        }

        app1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.youngbreed.videostatusmaker&hl=en_IN")));

            }
        });

        app2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.youngbreed.allscanner&hl=en_IN")));

            }
        });
    }




    private void LoadNativeAd() {
        AdLoader adLoader = new AdLoader.Builder(Our_appActivity.this,"ca-app-pub-4944024742392470/1730179215")
                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        NativeTemplateStyle styles = new
                                NativeTemplateStyle.Builder().build();

                        template.setStyles(styles);
                        template.setNativeAd(unifiedNativeAd);
                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }

}