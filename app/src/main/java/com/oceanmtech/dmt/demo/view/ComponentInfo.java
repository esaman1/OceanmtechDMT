package com.oceanmtech.dmt.demo.view;

import android.graphics.Bitmap;
import android.net.Uri;
import org.json.JSONObject;

public class ComponentInfo {
    private Bitmap BITMAP;
    private String COLORTYPE;
    private int COMP_ID;
    private String FIELD_FOUR = "";
    int FIELD_ONE = 0;
    private String FIELD_THREE = "";
    private String FIELD_TWO = "";
    private int HEIGHT;
    private int ORDER;
    private float POS_X;
    private float POS_Y;
    private String RES_ID;
    private Uri RES_URI;
    private float ROTATION;
    private int STC_COLOR;
    private int STC_HUE;
    private int STC_OPACITY;
    private String STKR_PATH = "";
    int ScaleProg;
    private int TEMPLATE_ID;
    private String TYPE = "";
    private int WIDTH;
    int XRotateProg;
    int YRotateProg;
    private float Y_ROTATION;
    int ZRotateProg;

    public ComponentInfo() {
    }

    public String toString() {
        return "new ComponentInfo(templateId, Constants.getNewX(" + this.POS_X + "f), Constants.getNewY(" + this.POS_Y + "f), Constants.getNewWidth(" + this.WIDTH + "), Constants.getNewHeight(" + this.HEIGHT + "), " + this.ROTATION + "f, " + this.Y_ROTATION + "f, \"" + this.RES_ID + "\", \"" + this.TYPE + "\", " + this.ORDER + ", " + this.STC_COLOR + ", " + this.STC_OPACITY + ", " + this.XRotateProg + ", " + ((int) this.Y_ROTATION) + ", " + this.ZRotateProg + ", " + this.ScaleProg + ", \"" + this.STKR_PATH + "\", \"" + this.COLORTYPE + "\", " + this.STC_HUE + ", " + this.FIELD_ONE + ", \"" + this.FIELD_TWO + "\", \"" + this.FIELD_THREE + "\", \"" + this.FIELD_FOUR + "\", null, null)";
    }

    public ComponentInfo(int i, float f, float f2, int i2, int i3, float f3, float f4, String str, String str2, int i4, int i5, int i6, int i7, int i8, int i9, int i10, String str3, String str4, int i11, int i12, String str5, String str6, String str7, Uri uri, Bitmap bitmap) {
        this.TEMPLATE_ID = i;
        this.POS_X = f;
        this.POS_Y = f2;
        this.WIDTH = i2;
        this.HEIGHT = i3;
        this.ROTATION = f3;
        this.Y_ROTATION = f4;
        this.RES_ID = str;
        this.RES_URI = uri;
        this.BITMAP = bitmap;
        this.TYPE = str2;
        this.ORDER = i4;
        this.STC_COLOR = i5;
        this.COLORTYPE = str4;
        this.STC_OPACITY = i6;
        this.XRotateProg = i7;
        this.YRotateProg = i8;
        this.ZRotateProg = i9;
        this.ScaleProg = i10;
        this.STKR_PATH = str3;
        this.STC_HUE = i11;
        this.FIELD_ONE = i12;
        this.FIELD_TWO = str5;
        this.FIELD_THREE = str6;
        this.FIELD_FOUR = str7;
    }

    public ComponentInfo(JSONObject jSONObject) {
        try {
            this.TEMPLATE_ID = jSONObject.getInt("TEMPLATE_ID");
            this.POS_X = Float.parseFloat(jSONObject.getString("POS_X"));
            this.POS_Y = Float.parseFloat(jSONObject.getString("POS_Y"));
            this.WIDTH = jSONObject.getInt("WIDHT");
            this.HEIGHT = jSONObject.getInt("HEIGHT");
            this.ROTATION = Float.parseFloat(jSONObject.getString("ROTATION"));
            this.Y_ROTATION = Float.parseFloat(jSONObject.getString("Y_ROTATION"));
            this.RES_ID = jSONObject.getString("RES_ID");
            this.TYPE = jSONObject.getString("TYPE");
            this.ORDER = jSONObject.getInt("ORDER_");
            this.STC_COLOR = jSONObject.getInt("STC_COLOR");
            this.COLORTYPE = jSONObject.getString("COLORTYPE");
            this.STC_OPACITY = jSONObject.getInt("STC_OPACITY");
            this.XRotateProg = jSONObject.getInt("XROTATEPROG");
            this.YRotateProg = jSONObject.getInt("YROTATEPROG");
            this.ZRotateProg = jSONObject.getInt("ZROTATEPROG");
            this.ScaleProg = jSONObject.getInt("STC_SCALE");
            this.STKR_PATH = jSONObject.getString("STKR_PATH");
            this.STC_HUE = jSONObject.getInt("STC_HUE");
            this.FIELD_ONE = jSONObject.getInt("FIELD_ONE");
            this.FIELD_TWO = jSONObject.getString("FIELD_TWO");
            this.FIELD_THREE = jSONObject.getString("FIELD_THREE");
            this.FIELD_FOUR = jSONObject.getString("FIELD_FOUR");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONObject getComponentJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("TEMPLATE_ID", this.TEMPLATE_ID);
            jSONObject.put("POS_X", (double) this.POS_X);
            jSONObject.put("POS_Y", (double) this.POS_Y);
            jSONObject.put("WIDHT", this.WIDTH);
            jSONObject.put("HEIGHT", this.HEIGHT);
            jSONObject.put("ROTATION", (double) this.ROTATION);
            jSONObject.put("Y_ROTATION", (double) this.Y_ROTATION);
            jSONObject.put("RES_ID", this.RES_ID);
            jSONObject.put("TYPE", this.TYPE);
            jSONObject.put("ORDER_", this.ORDER);
            jSONObject.put("STC_COLOR", this.STC_COLOR);
            jSONObject.put("COLORTYPE", this.COLORTYPE);
            jSONObject.put("STC_OPACITY", this.STC_OPACITY);
            jSONObject.put("XROTATEPROG", this.XRotateProg);
            jSONObject.put("YROTATEPROG", this.YRotateProg);
            jSONObject.put("ZROTATEPROG", this.ZRotateProg);
            jSONObject.put("STC_SCALE", this.STC_OPACITY);
            jSONObject.put("STKR_PATH", this.STKR_PATH);
            jSONObject.put("STC_HUE", this.STC_HUE);
            jSONObject.put("FIELD_ONE", this.FIELD_ONE);
            jSONObject.put("FIELD_TWO", this.FIELD_TWO);
            jSONObject.put("FIELD_THREE", this.FIELD_THREE);
            jSONObject.put("FIELD_FOUR", this.FIELD_FOUR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public int getCOMP_ID() {
        return this.COMP_ID;
    }

    public void setCOMP_ID(int i) {
        this.COMP_ID = i;
    }

    public int getTEMPLATE_ID() {
        return this.TEMPLATE_ID;
    }

    public void setTEMPLATE_ID(int i) {
        this.TEMPLATE_ID = i;
    }

    public float getPOS_X() {
        return this.POS_X;
    }

    public void setPOS_X(float f) {
        this.POS_X = f;
    }

    public float getPOS_Y() {
        return this.POS_Y;
    }

    public void setPOS_Y(float f) {
        this.POS_Y = f;
    }

    public String getRES_ID() {
        return this.RES_ID;
    }

    public void setRES_ID(String str) {
        this.RES_ID = str;
    }

    public String getTYPE() {
        return this.TYPE;
    }

    public void setTYPE(String str) {
        this.TYPE = str;
    }

    public int getORDER() {
        return this.ORDER;
    }

    public void setORDER(int i) {
        this.ORDER = i;
    }

    public float getROTATION() {
        return this.ROTATION;
    }

    public void setROTATION(float f) {
        this.ROTATION = f;
    }

    public int getWIDTH() {
        return this.WIDTH;
    }

    public void setWIDTH(int i) {
        this.WIDTH = i;
    }

    public int getHEIGHT() {
        return this.HEIGHT;
    }

    public void setHEIGHT(int i) {
        this.HEIGHT = i;
    }

    public float getY_ROTATION() {
        return this.Y_ROTATION;
    }

    public void setY_ROTATION(float f) {
        this.Y_ROTATION = f;
    }

    public Uri getRES_URI() {
        return this.RES_URI;
    }

    public void setRES_URI(Uri uri) {
        this.RES_URI = uri;
    }

    public Bitmap getBITMAP() {
        return this.BITMAP;
    }

    public void setBITMAP(Bitmap bitmap) {
        this.BITMAP = bitmap;
    }

    public int getSTC_COLOR() {
        return this.STC_COLOR;
    }

    public void setSTC_COLOR(int i) {
        this.STC_COLOR = i;
    }

    public String getCOLORTYPE() {
        return this.COLORTYPE;
    }

    public void setCOLORTYPE(String str) {
        this.COLORTYPE = str;
    }

    public int getSTC_OPACITY() {
        return this.STC_OPACITY;
    }

    public void setSTC_OPACITY(int i) {
        this.STC_OPACITY = i;
    }

    public int getXRotateProg() {
        return this.XRotateProg;
    }

    public void setXRotateProg(int i) {
        this.XRotateProg = i;
    }

    public int getYRotateProg() {
        return this.YRotateProg;
    }

    public void setYRotateProg(int i) {
        this.YRotateProg = i;
    }

    public int getZRotateProg() {
        return this.ZRotateProg;
    }

    public void setZRotateProg(int i) {
        this.ZRotateProg = i;
    }

    public int getScaleProg() {
        return this.ScaleProg;
    }

    public void setScaleProg(int i) {
        this.ScaleProg = i;
    }

    public int getFIELD_ONE() {
        return this.FIELD_ONE;
    }

    public void setFIELD_ONE(int i) {
        this.FIELD_ONE = i;
    }

    public String getFIELD_TWO() {
        return this.FIELD_TWO;
    }

    public void setFIELD_TWO(String str) {
        this.FIELD_TWO = str;
    }

    public String getFIELD_THREE() {
        return this.FIELD_THREE;
    }

    public void setFIELD_THREE(String str) {
        this.FIELD_THREE = str;
    }

    public String getFIELD_FOUR() {
        return this.FIELD_FOUR;
    }

    public void setFIELD_FOUR(String str) {
        this.FIELD_FOUR = str;
    }

    public String getSTKR_PATH() {
        return this.STKR_PATH;
    }

    public void setSTKR_PATH(String str) {
        this.STKR_PATH = str;
    }

    public int getSTC_HUE() {
        return this.STC_HUE;
    }

    public void setSTC_HUE(int i) {
        this.STC_HUE = i;
    }
}
