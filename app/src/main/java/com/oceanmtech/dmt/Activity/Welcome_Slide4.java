package com.oceanmtech.dmt.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.R;

public class Welcome_Slide4 extends AppCompatActivity {

    private ImageView nextbutton4;
    private ImageView backbutton4;
    private EditText edittext5;
    private TextView skip3;
    ProgressDialog pDialog;
    String edit;
    private TextView text4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_slide4);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);


        Intent intent = getIntent();
        edit = intent.getStringExtra("Type");


        pDialog = new ProgressDialog(Welcome_Slide4.this);
        pDialog.setTitle("Loading");
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(true);
        pDialog.setMax(100);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setCancelable(false);

        Find();
        retraivedata();
        Click();


    }

    private void Find() {

        nextbutton4 = (ImageView) findViewById(R.id.nextbutton4);
        backbutton4 = (ImageView) findViewById(R.id.backbutton4);
        edittext5 = (EditText) findViewById(R.id.edittext5);

        skip3 = (TextView) findViewById(R.id.skip3);
        text4 = (TextView) findViewById(R.id.text4);

        Typeface type = Typeface.createFromAsset(getAssets(), "papyrus.ttf");
        text4.setTypeface(type);


    }

    public void retraivedata() {

        if (edit.equals("Edit")) {
            edittext5.setText(new PrefManager(getApplicationContext()).getString(IConstant.MOBILE_NO));
        } else {
            edittext5.setText("");

        }

    }


    private void Click() {

        skip3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome_Slide4.this, MainActivity.class);
                startActivity(intent);

            }

        });

        nextbutton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new PrefManager(getApplicationContext()).setString(IConstant.MOBILE_NO, edittext5.getText().toString());
                Intent intent = new Intent(Welcome_Slide4.this, Welcome_Slide5.class);
                intent.putExtra("Type", getIntent().getStringExtra("Type"));
                startActivity(intent);
                // overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);

            }

        });

        backbutton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }

        });

    }

}