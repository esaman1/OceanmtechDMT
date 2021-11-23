package com.oceanmtech.dmt.QuoteCreater;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.oceanmtech.dmt.Activity.MyApp;
import com.oceanmtech.dmt.R;
import com.oceanmtech.dmt.Utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;


public class TextActivity extends Activity {
    private String TAG = getClass().getSimpleName();
    ImageView btn_cancel;
    ImageView btn_done;
    ImageView btn_font;
    ImageView btn_multiColor;
    ImageView btn_normal_bold_font;
    ImageView btn_singleColor;
    ImageView btn_textColor;
    ImageView btn_textStyle;
    ColorAdapter colorAdapter;
    CheckBox colorCheckBox;
    Gallery colorGallery;
    LinearLayout colorLayout;
    ArrayList<String> color_data = new ArrayList();
    private int currentStyle = 0;
    File f20a;
    private Typeface f27h;
    private int f32m = 30;
    private int f33n = 0;
    private int f34o = 0;
    private boolean f35p = true;
    private boolean f36q = true;
    private String f38s;
    private String f39t;
    private String f42w;
    FontAdapter fontAdapter;
    Gallery fontGallery;
    LinearLayout fontLayout;
    ArrayList<String> font_data = new ArrayList();
    ImageView inputKet;
    OnClickListener mOnClickListener = new C08587();
    OnClickListener mOnColorCheckBoxClickListener = new C08532();
    OnItemClickListener mOnColorItemClickListener = new C08554();
    OnItemClickListener mOnFontItemClickListener = new C08543();
    OnItemClickListener mOnShadowColorItemClickListener = new C08565();
    private AlertDialog materialDialog;
    OnSeekBarChangeListener monSeekBarChangeListener = new C08576();
    SeekBar seekBar;
    SeekBar shadowRadiosSeekBar;
    ColorAdapter shadowcolorAdapter;
    Gallery shadowcolorGallery;
    SeekBar shadwoXYSeekBar;
    SeekBar textOpacitySeekBar;
    LinearLayout textStyleLayout;
    TextView textView;
    RelativeLayout textviewLayout;


    String str_color[] = {
            "#4c3b3e",
            "#2b3c44",
            "#494949",
            "#f1b0db",
            "#faedf4",
            "#fcf3ec",
            "#f1b9c4",
            "#ffa621",
            "#fdc900",
            "#fffefc",
            "#fde202",
            "#ef8b00",
            "#fae452",
            "#f0331f",
            "#e58c14",
            "#7df063",
            "#31fa03",
            "#ee0c3a",
            "#f2541a",
            "#f5bdf2",
            "#d00333",
            "#231ae1",
            "#01ffff",
            "#00ff4f",
            "#fefd00",
            "#b1fefe",
            "#8affc6",
            "#fe3882",
            "#33dfeb",
            "#6feffa",
            "#fdfaff",
            "#7c1d5d",
            "#ffff07",
            "#9d9504",
            "#f5b225",
            "#ffb3ad",
            "#ffb6b7",
            "#860981"};


    class C08521 implements OnClickListener {
        C08521() {
        }

        public void onClick(View v) {
            TextActivity.this.openEditTextFonts();
        }
    }

    class C08532 implements OnClickListener {
        C08532() {
        }

        public void onClick(View view) {
            if (((CheckBox) view).isChecked()) {
                TextActivity.this.f36q = false;
                TextActivity.this.m20a(TextActivity.this.f38s, TextActivity.this.f39t);
                return;
            }
            TextActivity.this.f36q = true;
            TextActivity.this.m20a(TextActivity.this.f38s, TextActivity.this.f38s);
        }
    }

    class C08543 implements OnItemClickListener {
        C08543() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            TextActivity.this.f27h = Typeface.createFromAsset(TextActivity.this.getAssets(), TextActivity.this.fontAdapter.getItem(i));
            TextActivity.this.textView.setTypeface(TextActivity.this.f27h);
        }
    }

    class C08554 implements OnItemClickListener {
        C08554() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if (TextActivity.this.f35p) {
                TextActivity.this.f38s = TextActivity.this.colorAdapter.getItem(i);
            } else {
                TextActivity.this.f39t = TextActivity.this.colorAdapter.getItem(i);
            }
            if (TextActivity.this.f36q) {
                TextActivity.this.m20a(TextActivity.this.f38s, TextActivity.this.f38s);
            } else {
                TextActivity.this.m20a(TextActivity.this.f38s, TextActivity.this.f39t);
            }
            try {
                TextActivity.this.btn_singleColor.setColorFilter(Color.parseColor(TextActivity.this.f38s));
                TextActivity.this.btn_multiColor.setColorFilter(Color.parseColor(TextActivity.this.f39t));
            } catch (Exception e) {
            }
        }
    }

    class C08565 implements OnItemClickListener {
        C08565() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            TextActivity.this.f42w = TextActivity.this.shadowcolorAdapter.getItem(i);
            TextActivity.this.textView.getPaint().setShader(null);
            TextActivity.this.textView.setShadowLayer((float) TextActivity.this.f34o, (float) TextActivity.this.f33n, (float) TextActivity.this.f33n, Color.parseColor(TextActivity.this.f42w));
            TextActivity.this.textView.setTextColor(Color.parseColor(TextActivity.this.f38s));
            TextActivity.this.textView.invalidate();
        }
    }

    class C08576 implements OnSeekBarChangeListener {
        C08576() {
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            switch (seekBar.getId()) {
                case R.id.seekBar:
                    TextActivity.this.f32m = i;
                    TextActivity.this.textView.setTextSize((float) TextActivity.this.f32m);
                    return;
                case R.id.textOpacitySeekBar:
                    try {
                        TextActivity.this.textView.setAlpha(((float) i) / 100.0f);
                        return;
                    } catch (Exception e) {
                        return;
                    }
                case R.id.shadwoXYSeekBar:
                    TextActivity.this.f33n = (i / 5) - 10;
                    TextActivity.this.textView.setShadowLayer((float) TextActivity.this.f34o, (float) TextActivity.this.f33n, (float) TextActivity.this.f33n, Color.parseColor(TextActivity.this.f42w));
                    TextActivity.this.textView.invalidate();
                    return;
                case R.id.shadowRadiosSeekBar:
                    TextActivity.this.f34o = i / 5;
                    TextActivity.this.textView.setShadowLayer((float) TextActivity.this.f34o, (float) TextActivity.this.f33n, (float) TextActivity.this.f33n, Color.parseColor(TextActivity.this.f42w));
                    TextActivity.this.textView.invalidate();
                    return;
                default:
                    return;
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    class C08587 implements OnClickListener {
        C08587() {
        }

        public void onClick(View view) {
            boolean isReturn = false;
            if (!TextActivity.this.canReadWritePermission()) {
                isReturn = true;
                TextActivity.this.openReadWritePermissionDialog(Utility.READ_WRITE_PERMISSION);
            }
            if (!isReturn) {
                if (view == TextActivity.this.btn_done) {
                    TextActivity.this.f20a = new File(TextActivity.this.getFilesDir(), "Nature_1.png");
                    Intent intent = new Intent();
                    intent.putExtra(Utility.text_path, TextActivity.this.getTextBitmap());
                    TextActivity.this.setResult(-1, intent);
                    TextActivity.this.finish();
                } else if (view == TextActivity.this.btn_cancel) {
                    TextActivity.this.setResult(0);
                    TextActivity.this.finish();
                } else if (view == TextActivity.this.inputKet) {
                    TextActivity.this.openEditTextFonts();
                } else if (view == TextActivity.this.btn_font) {
                    TextActivity.this.resetLayouts();
                    TextActivity.this.fontLayout.setVisibility(View.VISIBLE);
                    TextActivity.this.btn_font.setImageResource(R.drawable.btn_text_style_hover);
                } else if (view == TextActivity.this.btn_textColor) {
                    TextActivity.this.resetLayouts();
                    TextActivity.this.colorLayout.setVisibility(View.VISIBLE);
                    TextActivity.this.btn_textColor.setImageResource(R.drawable.btn_paint_hover);
                } else if (view == TextActivity.this.btn_textStyle) {
                    TextActivity.this.resetLayouts();
                    TextActivity.this.textStyleLayout.setVisibility(View.VISIBLE);
                    TextActivity.this.btn_textStyle.setImageResource(R.drawable.btn_text_glow_hover);
                } else if (view == TextActivity.this.btn_normal_bold_font) {
                    TextActivity.this.showFontStyleDialog();
                } else if (view == TextActivity.this.btn_singleColor) {
                    TextActivity.this.f35p = true;
                    TextActivity.this.btn_singleColor.setImageResource(R.drawable.btn_color1_hover);
                    TextActivity.this.btn_multiColor.setImageResource(R.drawable.btn_color2);
                } else if (view == TextActivity.this.btn_multiColor) {
                    TextActivity.this.f35p = false;
                    TextActivity.this.btn_singleColor.setImageResource(R.drawable.btn_color1);
                    TextActivity.this.btn_multiColor.setImageResource(R.drawable.btn_color2_hover);
                }
            }
        }
    }

    class C08609 implements DialogInterface.OnClickListener {
        C08609() {
        }

        public void onClick(DialogInterface dialog, int whichButton) {
            if (dialog != null) {
                try {
                    dialog.dismiss();
                } catch (Exception e) {
                }
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_text);


        this.btn_cancel = (ImageView) findViewById(R.id.btn_cancel);
        this.btn_done = (ImageView) findViewById(R.id.btn_done);
        this.textviewLayout = (RelativeLayout) findViewById(R.id.textviewLayout);
        this.textView = (TextView) findViewById(R.id.textView);

        textView.setText(getIntent().getStringExtra("Quotes"));
        this.textView.setOnClickListener(new C08521());
        this.inputKet = (ImageView) findViewById(R.id.inputKet);
        this.btn_font = (ImageView) findViewById(R.id.btn_font);
        this.btn_textColor = (ImageView) findViewById(R.id.btn_textColor);
        this.btn_textStyle = (ImageView) findViewById(R.id.btn_textStyle);
        this.fontLayout = (LinearLayout) findViewById(R.id.fontLayout);
        this.colorLayout = (LinearLayout) findViewById(R.id.colorLayout);
        this.textStyleLayout = (LinearLayout) findViewById(R.id.textStyleLayout);
        this.btn_normal_bold_font = (ImageView) findViewById(R.id.btn_normal_bold_font);
        this.btn_singleColor = (ImageView) findViewById(R.id.btn_singleColor);
        this.btn_multiColor = (ImageView) findViewById(R.id.btn_multiColor);
        this.btn_cancel.setOnClickListener(this.mOnClickListener);
        this.btn_done.setOnClickListener(this.mOnClickListener);
        this.inputKet.setOnClickListener(this.mOnClickListener);
        this.btn_font.setOnClickListener(this.mOnClickListener);
        this.btn_textColor.setOnClickListener(this.mOnClickListener);
        this.btn_textStyle.setOnClickListener(this.mOnClickListener);
        this.btn_normal_bold_font.setOnClickListener(this.mOnClickListener);
        this.btn_singleColor.setOnClickListener(this.mOnClickListener);
        this.btn_multiColor.setOnClickListener(this.mOnClickListener);
        this.font_data.clear();
        Collections.addAll(this.font_data, getResources().getStringArray(R.array.FontFamily));
        this.color_data.clear();
        for (int i = 0; i < str_color.length; i++) {
            color_data.add(str_color[i]);
        }
        //Collections.addAll(this.color_data, getResources().getStringArray(R.array.colorArray));
        this.fontAdapter = new FontAdapter(this);
        this.fontGallery = (Gallery) findViewById(R.id.fontGallery);
        this.fontGallery.setAdapter(this.fontAdapter);
        this.fontAdapter.addAll(this.font_data);
        this.fontGallery.setOnItemClickListener(this.mOnFontItemClickListener);
        this.colorAdapter = new ColorAdapter(this);
        this.colorGallery = (Gallery) findViewById(R.id.colorGallery);
        this.colorGallery.setAdapter(this.colorAdapter);
        this.colorAdapter.addAll(this.color_data);
        this.colorGallery.setOnItemClickListener(this.mOnColorItemClickListener);
        this.f38s = this.colorAdapter.getItem(0);
        this.f39t = this.colorAdapter.getItem(1);
        try {
            this.btn_singleColor.setColorFilter(Color.parseColor(this.f38s));
            this.btn_multiColor.setColorFilter(Color.parseColor(this.f39t));
        } catch (Exception e) {
        }
        this.shadowcolorAdapter = new ColorAdapter(this);
        this.shadowcolorGallery = (Gallery) findViewById(R.id.shadowcolorGallery);
        this.shadowcolorGallery.setAdapter(this.shadowcolorAdapter);
        this.shadowcolorAdapter.addAll(this.color_data);
        this.shadowcolorGallery.setOnItemClickListener(this.mOnShadowColorItemClickListener);
        this.f42w = shadowcolorAdapter.getItem(20);
        this.seekBar = (SeekBar) findViewById(R.id.seekBar);
        this.seekBar.setOnSeekBarChangeListener(this.monSeekBarChangeListener);
        this.textOpacitySeekBar = (SeekBar) findViewById(R.id.textOpacitySeekBar);
        this.textOpacitySeekBar.setOnSeekBarChangeListener(this.monSeekBarChangeListener);
        this.shadwoXYSeekBar = (SeekBar) findViewById(R.id.shadwoXYSeekBar);
        this.shadwoXYSeekBar.setOnSeekBarChangeListener(this.monSeekBarChangeListener);
        this.shadowRadiosSeekBar = (SeekBar) findViewById(R.id.shadowRadiosSeekBar);
        this.shadowRadiosSeekBar.setOnSeekBarChangeListener(this.monSeekBarChangeListener);
        this.colorCheckBox = (CheckBox) findViewById(R.id.colorCheckBox);
        this.colorCheckBox.setOnClickListener(this.mOnColorCheckBoxClickListener);
        try {
            this.f27h = Typeface.createFromAsset(getAssets(), this.fontAdapter.getItem(0));
            this.textView.setTypeface(this.f27h);
            if (this.f36q) {
                m20a(this.f38s, this.f38s);
            } else {
                m20a(this.f38s, this.f39t);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public static final float DEFAULT_BACKOFF_MULT = 1f;

    public void m20a(String str, String str2) {
        this.textView.getPaint().setShader(new LinearGradient(0.0f, (float) (this.f32m * 1), 0.0f, (float) (this.f32m * 2), new int[]{Color.parseColor(str), Color.parseColor(str2)}, new float[]{0.0f, DEFAULT_BACKOFF_MULT}, TileMode.CLAMP));
        this.textView.getPaint().setStrokeWidth(5.0f);
        this.textView.invalidate();
    }

    public boolean canReadWritePermission() {
        if (VERSION.SDK_INT < 23) {
            return true;
        }
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            return false;
        }
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") != 0) {
            return false;
        }
        return true;
    }

    @TargetApi(16)
    public void openReadWritePermissionDialog(int permission_constant) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, permission_constant);
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.READ_EXTERNAL_STORAGE")) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, permission_constant);
        }
    }

    private void dismissAlertDialog() {
        try {
            if (this.materialDialog != null) {
                if (this.materialDialog.isShowing()) {
                    this.materialDialog.dismiss();
                }
                this.materialDialog.cancel();
                this.materialDialog = null;
            }
        } catch (Exception e) {
        }
    }

    @SuppressLint("ResourceType")
    public static ContextThemeWrapper GetDialogContext(Activity act) {
        if (VERSION.SDK_INT >= 11) {
            return new ContextThemeWrapper(act, 16973941);
        }
        return new ContextThemeWrapper(act, 16973837);
    }

    private void openEditTextFonts() {
        try {
            dismissAlertDialog();
            Builder alertbuilder = new Builder(GetDialogContext(this));
            alertbuilder.setTitle((int) R.string.enter_text);
            alertbuilder.setCancelable(false);
            final EditText editText = new EditText(this);
            editText.setHint(R.string.enter_your_text);
            editText.setText(this.textView.getText().toString());
            editText.setInputType(1);
            alertbuilder.setView(editText);
            alertbuilder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    try {
                        try {
                            TextActivity.this.textView.setText(editText.getText().toString());
                        } catch (Exception e) {
                        }
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    } catch (Exception e2) {
                    }
                }
            });
            alertbuilder.setNegativeButton(R.string.btn_cancel, new C08609());
            this.materialDialog = alertbuilder.create();
            this.materialDialog.show();
        } catch (Exception e) {
        }
    }

    private void showFontStyleDialog() {
        try {
            dismissAlertDialog();
            Builder alertbuilder = new Builder(GetDialogContext(this));
            alertbuilder.setTitle(R.string.titlefontstyle);
            alertbuilder.setSingleChoiceItems(R.array.FontStyle, this.currentStyle, new DialogInterface.OnClickListener() {
                @SuppressLint("WrongConstant")
                public void onClick(DialogInterface dialog, int which) {
                    if (which == 3) {
                        TextActivity.this.currentStyle = 3;
                        TextActivity.this.textView.setTypeface(TextActivity.this.textView.getTypeface(), 3);
                        TextActivity.this.textView.invalidate();
                    } else if (which == 2) {
                        TextActivity.this.currentStyle = 2;
                        TextActivity.this.textView.setTypeface(TextActivity.this.textView.getTypeface(), 2);
                        TextActivity.this.textView.invalidate();
                    } else if (which == 1) {
                        TextActivity.this.currentStyle = 1;
                        TextActivity.this.textView.setTypeface(TextActivity.this.textView.getTypeface(), 1);
                        TextActivity.this.textView.invalidate();
                    } else {
                        TextActivity.this.currentStyle = 0;
                        TextActivity.this.textView.setTypeface(TextActivity.this.textView.getTypeface(), 0);
                        TextActivity.this.textView.invalidate();
                    }
                    TextActivity.this.dismissAlertDialog();
                }
            });
            this.materialDialog = alertbuilder.create();
            this.materialDialog.show();
        } catch (Exception e) {
        }
    }

    public static void refreshdeleteGallery(Context mcontex, File file) {
        try {
            ((MyApp) mcontex.getApplicationContext()).refreshdeleteGallery(file);
        } catch (Exception e) {
        }
    }

    private String getTextBitmap() {
        this.textviewLayout.setDrawingCacheEnabled(true);
        this.textviewLayout.layout(0, 0, this.textviewLayout.getMeasuredWidth(), this.textviewLayout.getMeasuredHeight());
        try {
            this.textviewLayout.getDrawingCache(true).compress(CompressFormat.PNG, 100, new FileOutputStream(this.f20a));
        } catch (FileNotFoundException e) {
        }
        refreshdeleteGallery(this, this.f20a);
        return this.f20a.getAbsolutePath();
    }

    private Bitmap getTextDrawingBitmap() {
        this.textviewLayout.setDrawingCacheEnabled(true);
        this.textviewLayout.layout(0, 0, this.textviewLayout.getMeasuredWidth(), this.textviewLayout.getMeasuredHeight());
        return this.textviewLayout.getDrawingCache(true);
    }

    private void resetLayouts() {
        this.colorLayout.setVisibility(View.GONE);
        this.fontLayout.setVisibility(View.GONE);
        this.textStyleLayout.setVisibility(View.GONE);
    }
}
