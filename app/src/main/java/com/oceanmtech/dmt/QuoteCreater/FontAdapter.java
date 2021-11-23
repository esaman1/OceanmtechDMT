package com.oceanmtech.dmt.QuoteCreater;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.oceanmtech.dmt.R;

import java.util.ArrayList;


public final class FontAdapter extends BaseAdapter {
    private String TAG = getClass().getSimpleName();
    private ArrayList<String> data = new ArrayList();
    private LayoutInflater infalter;
    private Activity mContext;

    public class ViewHolder {
        TextView txt_font_style;
    }

    public FontAdapter(Activity c) {
        this.mContext = c;
        this.infalter = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return this.data.size();
    }

    public String getItem(int position) {
        return (String) this.data.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public void addAll(ArrayList<String> files) {
        try {
            this.data.clear();
            this.data.addAll(files);
        } catch (Exception e) {
        }
        notifyDataSetChanged();
    }

    @SuppressLint({"InflateParams"})
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = this.infalter.inflate(R.layout.row_item_text, null);
            holder = new ViewHolder();
            holder.txt_font_style = (TextView) convertView.findViewById(R.id.txt_font_style);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        try {
            holder.txt_font_style.setText("Aa");
            holder.txt_font_style.setTypeface(Typeface.createFromAsset(this.mContext.getAssets(), (String) this.data.get(position)));
        } catch (Exception e) {
        }
        return convertView;
    }
}
