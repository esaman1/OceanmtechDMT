package com.oceanmtech.dmt.NewFile;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.oceanmtech.dmt.Activity.Bussiness_Post_save_Activity;
import com.oceanmtech.dmt.R;


public class BussinessTextAdapater extends BaseAdapter {

    Activity activity;
    LayoutInflater inflater;
    String name;
    public static int total = 35;

    public BussinessTextAdapater(Bussiness_Post_save_Activity thirtActivity) {

        activity = thirtActivity;
        name = "abc";
        inflater = LayoutInflater.from(thirtActivity);
    }

    @Override
    public int getCount() {
        return total;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.text_items1, parent, false);
        TextView textView = (TextView) convertView.findViewById(R.id.gridtext);
        textView.setText(name);
        setfontType(textView, position);
        return convertView;
    }

    public void setfontType(TextView textView, int position) {
        String fontName = "font1.ttf";
        if (position == total) {
            fontName = "font" + total + ".ttf";
        } else {
            fontName = "font" + (position + 1) + ".ttf";
        }

        Typeface typeface = Typeface.createFromAsset(activity.getAssets(), fontName);
        textView.setTypeface(typeface);
    }


}