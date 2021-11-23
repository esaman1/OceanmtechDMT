package com.oceanmtech.dmt.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.R;

public class Welcome_Slide2 extends AppCompatActivity {

    private ImageView nextbutton2;
    private ImageView backbutton2;
    public static EditText edittext2;
    public static EditText edittext3;

    Animation slideIn;
    Animation slideOut;
    private TextView skip1;
    String edit;
    private TextView text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_slide2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);



        Find();
        Click();
        retraivedata();

    }

    private void Find() {

        nextbutton2 = (ImageView) findViewById(R.id.nextbutton2);
        backbutton2 = (ImageView) findViewById(R.id.backbutton2);
        edittext2 = (EditText) findViewById(R.id.edittext2);
        edittext3 = (EditText) findViewById(R.id.edittext3);

        skip1 = (TextView) findViewById(R.id.skip1);
        text2 = (TextView) findViewById(R.id.text2);

        Typeface type = Typeface.createFromAsset(getAssets(), "papyrus.ttf");
        text2.setTypeface(type);


    }


    public void retraivedata() {
        if (getIntent().getStringExtra("Type").equals("Edit")) {
            edittext2.setText(new PrefManager(getApplicationContext()).getString(IConstant.BUSINESS_EMAIL));
            edittext3.setText(new PrefManager(getApplicationContext()).getString(IConstant.WEBSITE));
        } else {
            edittext2.setText("");
            edittext3.setText("");
        }


    }

    private void Click() {

        skip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome_Slide2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        nextbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               new PrefManager(getApplicationContext()).setString(IConstant.BUSINESS_EMAIL,edittext2.getText().toString());
               new PrefManager(getApplicationContext()).setString(IConstant.WEBSITE,edittext3.getText().toString());

                Intent intent = new Intent(Welcome_Slide2.this, Welcome_Slide3.class);
                intent.putExtra("Type", getIntent().getStringExtra("Type"));
                overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                startActivity(intent);
            }

        });

        backbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}