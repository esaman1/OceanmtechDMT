package com.oceanmtech.dmt.text;

import androidx.core.view.ViewCompat;

import java.io.Serializable;

public class TextBean implements Serializable {
    private String font = "font6";
    private int shadowColor = ViewCompat.MEASURED_STATE_MASK;
    private int shadowSize = 5;
    private String text = "Preview";
    private int textColor = ViewCompat.MEASURED_STATE_MASK;
    private int textSize = 16;

    public int getTextColor() {
        return this.textColor;
    }

    public void setTextColor(int i) {
        this.textColor = i;
    }

    public int getTextSize() {
        return this.textSize;
    }

    public void setTextSize(int i) {
        this.textSize = i;
    }

    public int getShadowColor() {
        return this.shadowColor;
    }

    public void setShadowColor(int i) {
        this.shadowColor = i;
    }

    public int getShadowSize() {
        return this.shadowSize;
    }

    public void setShadowSize(int i) {
        this.shadowSize = i;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getFont() {
        return this.font;
    }

    public void setFont(String str) {
        this.font = str;
    }
}
