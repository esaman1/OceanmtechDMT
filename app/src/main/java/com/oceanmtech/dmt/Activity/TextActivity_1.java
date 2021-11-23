package com.oceanmtech.dmt.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rtugeek.android.colorseekbar.ColorSeekBar;
import com.oceanmtech.dmt.R;
import com.oceanmtech.dmt.text.TextBean;


public class TextActivity_1 extends AppCompatActivity {


    ImageButton btn_color, btn_size, btn_font;
    ConstraintLayout color_cc_inner, size_cc_inner, fonts_cc_inner;
    EditText edt_text;
    TextView tv_Preview;
    public ColorSeekBar textShadowColorseek, textColorseek;
    public SeekBar textSizeseek, textShadowSizeseek;
    TextBean textBean;
    RecyclerView rclFont;
    fontAdapter fontAdapter;
    public static int total = 36;
    ImageView ivdone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_text_1);

        Bindview();
        init();
        AdListener();

    }


    public void Bindview() {
        size_cc_inner = (ConstraintLayout) findViewById(R.id.size_cc_inner);
        color_cc_inner = (ConstraintLayout) findViewById(R.id.color_cc_inner);
        fonts_cc_inner = (ConstraintLayout) findViewById(R.id.fonts_cc_inner);
        tv_Preview = (TextView) findViewById(R.id.tv_preview);
        edt_text = (EditText) findViewById(R.id.edt_text);
        btn_color = (ImageButton) findViewById(R.id.btn_color);
        btn_size = (ImageButton) findViewById(R.id.btn_size);
        btn_font = (ImageButton) findViewById(R.id.btn_font);
        ivdone = (ImageView) findViewById(R.id.ivdone);

        textColorseek = (ColorSeekBar) findViewById(R.id.textColorseek);
        textShadowColorseek = (ColorSeekBar) findViewById(R.id.textShadowColorseek);

        textSizeseek = (SeekBar) findViewById(R.id.textSizeseek);
        textShadowSizeseek = (SeekBar) findViewById(R.id.textShadowSizeseek);
        rclFont = (RecyclerView) findViewById(R.id.rclFont);


    }

    public void init() {
        this.textBean = new TextBean();
        tv_Preview.setText(textBean.getText());
        this.tv_Preview.setTextColor(this.textBean.getTextColor());
        this.tv_Preview.setTextSize((float) this.textBean.getTextSize());
        this.tv_Preview.setShadowLayer(1.5f, (float) this.textBean.getShadowSize(), (float) this.textBean.getShadowSize(), this.textBean.getShadowColor());
        TextView textView = tv_Preview;
        AssetManager assets = getAssets();
        StringBuilder sb = new StringBuilder();
        sb.append("fonts/");
        sb.append(this.textBean.getFont());
        sb.append(".ttf");
        textView.setTypeface(Typeface.createFromAsset(assets, sb.toString()));


        fontAdapter = new fontAdapter(TextActivity_1.this);
        rclFont.setLayoutManager(new GridLayoutManager(this, 3));
        rclFont.setAdapter(fontAdapter);

    }

    public void AdListener() {

        btn_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                size_cc_inner.setVisibility(View.GONE);
                fonts_cc_inner.setVisibility(View.GONE);
                color_cc_inner.setVisibility(View.VISIBLE);

            }
        });

        btn_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color_cc_inner.setVisibility(View.GONE);
                fonts_cc_inner.setVisibility(View.GONE);
                size_cc_inner.setVisibility(View.VISIBLE);
            }
        });

        btn_font.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color_cc_inner.setVisibility(View.GONE);
                size_cc_inner.setVisibility(View.GONE);
                fonts_cc_inner.setVisibility(View.VISIBLE);

            }
        });

        textColorseek.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            @Override
            public void onColorChangeListener(int colorBarPosition, int alphaBarPosition, int color) {
                tv_Preview.setTextColor(color);
                textBean.setTextColor(color);
            }
        });

        textShadowColorseek.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            @Override
            public void onColorChangeListener(int colorBarPosition, int alphaBarPosition, int color) {
                textBean.setShadowColor(color);
                tv_Preview.setShadowLayer(1.75f, (float) textBean.getShadowSize(), (float) textBean.getShadowSize(), textBean.getShadowColor());

            }
        });

        textSizeseek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                textBean.setTextSize(progress / 2);
                tv_Preview.setTextSize((float) textBean.getTextSize());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        textShadowSizeseek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textBean.setShadowSize(progress / 3);
                tv_Preview.setShadowLayer(1.5f, (float) textBean.getShadowSize(), (float) textBean.getShadowSize(), textBean.getShadowColor());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        edt_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String valueOf = String.valueOf(s);
                textBean.setText(valueOf);
                tv_Preview.setText(valueOf);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ivdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_done(TextActivity_1.this, v);
            }
        });


    }

    public static void btn_done(TextActivity_1 addTextActivity, View view) {
        Intent intent = new Intent();
        intent.putExtra(addTextActivity.getResources().getString(R.string.text_view_model), addTextActivity.textBean);
        addTextActivity.setResult(200, intent);
        addTextActivity.finish();
    }

    class fontAdapter extends RecyclerView.Adapter<fontAdapter.MyHolder> {

        Context context;


        public fontAdapter(Context context) {
            this.context = context;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.font_item, null, false);
            MyHolder holder = new MyHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, final int position) {

            setfontType(holder.tvFont, position);

            holder.rel_font.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("fonts/");
                    sb.append("font" + (position + 1));
                    sb.append(".ttf");
                    Typeface typeface = Typeface.createFromAsset(context.getAssets(), sb.toString());
                    tv_Preview.setTypeface(typeface);
                    textBean.setFont(sb.toString());

                }
            });
        }

        @Override
        public int getItemCount() {
            return total;
        }

        private class MyHolder extends RecyclerView.ViewHolder {
            TextView tvFont;
            RelativeLayout rel_font;

            public MyHolder(@NonNull View itemView) {
                super(itemView);
                rel_font = (RelativeLayout) itemView.findViewById(R.id.rel_font);
                tvFont = (TextView) itemView.findViewById(R.id.tvFont);
            }
        }

        public void setfontType(TextView textView, int position) {
            StringBuilder sb = new StringBuilder();
            sb.append("fonts/");
            if (position == total) {
                sb.append("font" + total);
            } else {
                sb.append("font" + (position + 1));
            }
            sb.append(".ttf");
            textView.setTypeface(Typeface.createFromAsset(context.getAssets(), sb.toString()));
        }

    }

}
