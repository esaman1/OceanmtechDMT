package com.oceanmtech.dmt.CreateLogo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.R;

public class Logo_B_NActivity extends AppCompatActivity {

    private EditText ad_bussinessname;
    private EditText ad_ad_tegline;

    String getBusiness;
    String gettagline;
    private CardView cv_adbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo__b__n);

        BindView();
        Click();
    }


    private void BindView(){
        ad_bussinessname=(EditText)findViewById(R.id.ad_bussinessname);
        ad_ad_tegline=(EditText)findViewById(R.id.ad_ad_tegline);
        cv_adbutton=(CardView)findViewById(R.id.cv_adbutton);

    }

    private void Click(){

        cv_adbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new PrefManager(Logo_B_NActivity.this).setString(IConstant.LOGO_BUSINESS_NAME, getBusiness);
                new PrefManager(Logo_B_NActivity.this).setString(IConstant.AAD_TAGLINE, gettagline);


            }
        });

    }
}