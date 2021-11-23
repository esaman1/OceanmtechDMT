package com.oceanmtech.dmt.NewFile;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.oceanmtech.dmt.R;


public class GalleryOpen extends Activity {

    private GridView grdImages;
    private TextView btnSelect;
    private TextView demo;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_open);
        grdImages = (GridView) findViewById(R.id.grdImages);
        btnSelect = (TextView) findViewById(R.id.btnSelect);
       

    }
}