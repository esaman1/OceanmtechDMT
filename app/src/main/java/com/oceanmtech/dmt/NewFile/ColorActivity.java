package com.oceanmtech.dmt.NewFile;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.oceanmtech.dmt.R;

import java.util.ArrayList;

import petrov.kristiyan.colorpicker.ColorPicker;

public class ColorActivity extends AppCompatActivity {

    private TextView demo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);


        demo = (TextView) findViewById(R.id.demo);



        final FloatingActionButton fab = findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final ColorPicker colorPicker = new ColorPicker(ColorActivity.this);
                    ArrayList<String> colors = new ArrayList<>();
                    colors.add("#82B926");
                    colors.add("#a276eb");
                    colors.add("#6a3ab2");
                    colors.add("#666666");
                    colors.add("#FFFF00");
                    colors.add("#3C8D2F");
                    colors.add("#FA9F00");
                    colors.add("#FF0000");

                    colorPicker
                            .setDefaultColorButton(Color.parseColor("#f84c44"))
                            .setColors(colors)
                            .setColumns(5)
                            .setRoundColorButton(true)
                            .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                                @Override
                                public void onChooseColor(int position, int color) {

                                    demo.setTextColor(color);

                                }

                                @Override
                                public void onCancel() {

                                }
                            })
                            .addListenerButton("newButton", new ColorPicker.OnButtonListener() {
                                @Override
                                public void onClick(View v, int position, int color) {
                                }
                            }).show();
                }
            });
        }
    }
}