package com.oceanmtech.dmt.Activity;

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

public class Welcome_Slide3 extends AppCompatActivity {

    private View nextbutton3;
    private View backbutton3;
    private EditText edittext4;
    private TextView skip2;
    String edit;
    private TextView text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_slide3);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);



        Intent intent = getIntent();
        edit = intent.getStringExtra("Type");

        Find();
        Click();
        retraivedata();

    }

    private void Find() {

        nextbutton3 = (ImageView) findViewById(R.id.nextbutton3);
        backbutton3 = (ImageView) findViewById(R.id.backbutton3);
        edittext4 = (EditText) findViewById(R.id.edittext4);
        skip2 = (TextView) findViewById(R.id.skip2);
        text3 = (TextView) findViewById(R.id.text3);

        Typeface type = Typeface.createFromAsset(getAssets(), "papyrus.ttf");
        text3.setTypeface(type);


    }


    public void retraivedata() {

        if (edit.equals("Edit")) {
            edittext4.setText(new PrefManager(getApplicationContext()).getString(IConstant.ADDRESS));
        } else {
            edittext4.setText("");
        }

    }


    private void Click() {
        skip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome_Slide3.this, MainActivity.class);
                startActivity(intent);
            }
        });


        nextbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PrefManager(getApplicationContext()).setString(IConstant.ADDRESS,edittext4.getText().toString());
                Intent intent = new Intent(Welcome_Slide3.this, Welcome_Slide4.class);
                intent.putExtra("Type", getIntent().getStringExtra("Type"));
                startActivity(intent);
               // overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
            }
        });

        backbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}