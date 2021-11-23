package com.oceanmtech.dmt.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.Model.Business_Get_model;
import com.oceanmtech.dmt.Model.BussinessCat_Data_model;
import com.oceanmtech.dmt.Model.LocalFrameItem;
import com.oceanmtech.dmt.MultiTouch.MultiTouchListener;
import com.oceanmtech.dmt.NewPremium.PaymentGatewayActivity;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.PreferencesHandler;
import com.oceanmtech.dmt.QuoteCreater.Constans;
import com.oceanmtech.dmt.R;
import com.oceanmtech.dmt.api.ApiInterfase;
import com.oceanmtech.dmt.api.RetrofitClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Select_Bussiness_Activity extends AppCompatActivity {

    public static FrameLayout bussiness_frameimage;
    public static RelativeLayout txt_stkr_relP_custom = null;
    public static ImageView seteffect1;
    String select_bussinesspost;
    RecyclerView rv_select_bussiness_post;
    ProgressDialog progressDialog;
    GridLayoutManager rv_select_bussiness_gridLayoutManager;
    BussinessCat_Data_model bussinessCat_data_model;
    int position_getbussinesspostid;
    String checkbussinesspostData;
    CheckBox cb_logo, cb_name, cb_email, cb_website, cb_mobile, cb_address;
    Select_Bussiness_Adapter select_greetings_adapter;
    String logo = "";
    String imageFileName;
    Dialog dialog;
    Boolean checkMemory;
    Bitmap finalBitmapToSave;
    String savedImagePath = null;
    InterstitialAd interstitialAd;
    int[] effect_3D = new int[]{R.drawable.noneimage, R.drawable.o1, R.drawable.o2, R.drawable.o3, R.drawable.o4,
            R.drawable.o5, R.drawable.o6, R.drawable.o7, R.drawable.o8, R.drawable.o9, R.drawable.o10, R.drawable.o11,
            R.drawable.o12, R.drawable.o13, R.drawable.o14, R.drawable.o15, R.drawable.o16, R.drawable.o17, R.drawable.o18,
            R.drawable.o19, R.drawable.o20, R.drawable.o21, R.drawable.o22, R.drawable.o23, R.drawable.o24, R.drawable.o25,
            R.drawable.o26, R.drawable.o27, R.drawable.o28, R.drawable.o29, R.drawable.o30, R.drawable.o31, R.drawable.o32,
            R.drawable.o33, R.drawable.o34, R.drawable.o35, R.drawable.o36, R.drawable.o37, R.drawable.o38, R.drawable.o39,
            R.drawable.o40, R.drawable.o41, R.drawable.o42, R.drawable.o43, R.drawable.o44, R.drawable.o45};
    private ImageView bussiness_card_set;
    private ImageView bussiness_next_btn11;
    private ImageView bussiness_back_button;
    private TextView Bussiness_frame_button;
    private LinearLayout llFrame;
    private ImageView ivframebg;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private String businessname;
    private String Emailaddress;
    private String Website;
    private String Address;
    private String MobileNo;
    private LinearLayout ll_effect;
    private TextView tv_waterMark;
    private LinearLayout lay_frames;
    private LinearLayout frame;
    private ImageView effect_back;
    private RecyclerView effect3Drecyclerviw;
    private SeekBar opacityBar;
    private TextView effect_button;
    private ImageView userelogoset;
    private GifImageView coming_soon;

    public static Bitmap scaleBitmap(Bitmap bitmap, int wantedWidth, int wantedHeight) {
        Bitmap output = Bitmap.createBitmap(wantedWidth, wantedHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Matrix m = new Matrix();
        m.setScale((float) wantedWidth / bitmap.getWidth(), (float) wantedHeight / bitmap.getHeight());
        canvas.drawBitmap(bitmap, m, new Paint());

        return output;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__bussiness);

        Intent intent = getIntent();
        select_bussinesspost = intent.getStringExtra("bussiness_cat");

        bussiness_card_set = (ImageView) findViewById(R.id.bussiness_card_set);
        bussiness_frameimage = (FrameLayout) findViewById(R.id.bussiness_frameimage);
        bussiness_next_btn11 = (ImageView) findViewById(R.id.bussiness_post_download);
        Bussiness_frame_button = (TextView) findViewById(R.id.Bussiness_frame_button);
        llFrame = (LinearLayout) findViewById(R.id.llframe);
        lay_frames = (LinearLayout) findViewById(R.id.lay_frames);
        effect_button = (TextView) findViewById(R.id.Bussiness_effect_button);
        effect_back = (ImageView) findViewById(R.id.bussiness_effect_back);
        cb_logo = (CheckBox) findViewById(R.id.cb_logo);
        cb_name = (CheckBox) findViewById(R.id.cb_name);
        cb_email = (CheckBox) findViewById(R.id.cb_email);
        cb_website = (CheckBox) findViewById(R.id.cb_website);
        cb_mobile = (CheckBox) findViewById(R.id.cb_mobile);
        cb_address = (CheckBox) findViewById(R.id.cb_address);
        seteffect1 = (ImageView) findViewById(R.id.bussinesss_seteffect);
        llFrame = (LinearLayout) findViewById(R.id.llframe);
        ll_effect = (LinearLayout) findViewById(R.id.effect1);
        tv_waterMark = (TextView) findViewById(R.id.tv_waterMark);
        frame = (LinearLayout) findViewById(R.id.frame);
        effect3Drecyclerviw = (RecyclerView) findViewById(R.id.bussiness_effect3Drecyclerviw);
        opacityBar = (SeekBar) findViewById(R.id.bussiness_opacity);
        userelogoset = (ImageView) findViewById(R.id.bussiness_userelogoset);

        coming_soon = (GifImageView) findViewById(R.id.coming_soon);


        if (new PrefManager(getApplicationContext()).getBoolen(IConstant.IS_PADE)) {
            tv_waterMark.setVisibility(View.GONE);
        } else {
            tv_waterMark.setVisibility(View.VISIBLE);
            //      Toast.makeText(Gujarati_Suvichar_View.this, "You are not Premium", Toast.LENGTH_SHORT).show();
        }

        bussiness_next_btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (new PrefManager(getApplicationContext()).getBoolen(IConstant.IS_PADE)) {

                    if (bussiness_card_set.getDrawable() == null) {

                        Toast toast = Toast.makeText(Select_Bussiness_Activity.this, "Please Wait.....", Toast.LENGTH_LONG);
                        View view1 = toast.getView();
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();

                    } else {
                        tv_waterMark.setVisibility(View.GONE);
                        finalBitmapToSave = scaleBitmap(viewToBitmap(bussiness_frameimage), 2000, 2000);
                        saveBitmap(true);
                    }

                } else {


                    if (bussinessCat_data_model.getBusinessCategoryData().get(position_getbussinesspostid).getBdFreeAndPaid().equals("1")) {
                        if (bussiness_card_set.getDrawable() == null) {

                            Toast toast = Toast.makeText(Select_Bussiness_Activity.this, "Please Wait.....", Toast.LENGTH_LONG);
                            View view1 = toast.getView();
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();

                        } else {
                            if (interstitialAd.isLoaded()) {
                                interstitialAd.show();
                            } else {
                                tv_waterMark.setVisibility(View.VISIBLE);
                                finalBitmapToSave = scaleBitmap(viewToBitmap(bussiness_frameimage), 2000, 2000);
                                saveBitmap(true);
                            }
                        }
                    } else {

                        Dialog();

                    }


                }

            }

        });

        opacity();
        ShowFullScreen();

        Bussiness_AdapterEffect AdapterEffect1 = new Bussiness_AdapterEffect(Select_Bussiness_Activity.this, effect_3D, "effect1");
        effect3Drecyclerviw.setAdapter(AdapterEffect1);
        effect3Drecyclerviw.setLayoutManager(new LinearLayoutManager(Select_Bussiness_Activity.this, LinearLayoutManager.HORIZONTAL, false));

        effect_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ll_effect.getVisibility() == View.GONE) {
                    frame.setVisibility(View.GONE);
                    ll_effect.setVisibility(View.VISIBLE);
                } else {
                    ll_effect.setVisibility(View.GONE);
                }
            }

        });

        Bussiness_frame_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (frame.getVisibility() == View.GONE) {
                    ll_effect.setVisibility(View.GONE);
                    frame.setVisibility(View.VISIBLE);
                } else {
                    frame.setVisibility(View.GONE);
                }
            }
        });

        effect_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                effect3Drecyclerviw.setVisibility(View.GONE);
                opacityBar.setVisibility(View.GONE);
                effect_back.setVisibility(View.GONE);
                effect_button.setVisibility(View.VISIBLE);
            }
        });

        final ArrayList<LocalFrameItem> allFrames = Constans.getAllFrames();
        if (this.lay_frames.getChildCount() > 0) {
            this.lay_frames.removeAllViews();
        }

        final ArrayList arrayList = new ArrayList();
        if (allFrames.size() > 0) {
            for (int i = 0; i < allFrames.size(); i++) {
                View inflate = LayoutInflater.from(this).inflate(allFrames.get(i).getPreview_id(), this.lay_frames, false);
                arrayList.add((ImageView) inflate.findViewById(R.id.ivselected));
                inflate.setTag(Integer.valueOf(i));
                inflate.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        int intValue = ((Integer) view.getTag()).intValue();
                        setFrame((LocalFrameItem) allFrames.get(intValue));
                        for (int i = 0; i < arrayList.size(); i++) {
                            ImageView imageView = (ImageView) arrayList.get(i);
                            if (i == intValue) {
                                imageView.setVisibility(View.VISIBLE);
                            } else {
                                imageView.setVisibility(View.GONE);

                            }
                        }
                    }
                });
                this.lay_frames.addView(inflate);
            }

            setFrame(allFrames.get(0));
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                ImageView imageView = (ImageView) arrayList.get(i2);
                if (i2 == 0) {
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    imageView.setVisibility(View.GONE);
                }
            }
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait..");

        rv_select_bussiness_post = (RecyclerView) findViewById(R.id.rv_select_bussiness_post);
        rv_select_bussiness_gridLayoutManager = new GridLayoutManager(Select_Bussiness_Activity.this, 3, RecyclerView.VERTICAL, false);
        rv_select_bussiness_post.setLayoutManager(rv_select_bussiness_gridLayoutManager);

        bussiness_back_button = (ImageView) findViewById(R.id.bussiness_back_button);

        bussiness_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        selectgreetingscat();

    }

    public void selectgreetingscat() {
        progressDialog.show();
        ApiInterfase apiInterface = RetrofitClient.getApiClient().create(ApiInterfase.class);
        apiInterface.getbussinesscategorydata("business_category_data", select_bussinesspost).enqueue(new Callback<BussinessCat_Data_model>() {
            @Override
            public void onResponse(Call<BussinessCat_Data_model> call, Response<BussinessCat_Data_model> response) {
                bussinessCat_data_model = response.body();
                if (bussinessCat_data_model != null) {
                    if (bussinessCat_data_model.getStatus().equals("success")) {
                        progressDialog.dismiss();
                        Glide.with(Select_Bussiness_Activity.this).load(bussinessCat_data_model.getBusinessCategoryData().get(0).getImage()).placeholder(R.drawable.newlogo).into(bussiness_card_set);
                        select_greetings_adapter = new Select_Bussiness_Adapter(Select_Bussiness_Activity.this, bussinessCat_data_model.getBusinessCategoryData());

                        //  Glide.with(Select_Bussiness_Activity.this).load(logo).into(imageView1);
                        rv_select_bussiness_post.setAdapter(select_greetings_adapter);

                    } else {
                        progressDialog.dismiss();
                        //  Toast.makeText(Select_Bussiness_Activity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                        coming_soon.setVisibility(View.VISIBLE);

                    }
                } else {
                    progressDialog.dismiss();
                    // Toast.makeText(Select_Bussiness_Activity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    coming_soon.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<BussinessCat_Data_model> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void callGetBussiness() {

        final ApiInterfase apiInterface = RetrofitClient.getApiClient().create(ApiInterfase.class);
        apiInterface.get_Business("business", new PrefManager(Select_Bussiness_Activity.this).getString(IConstant.IS_R_ID)).enqueue(new Callback<Business_Get_model>() {
            @Override
            public void onResponse(Call<Business_Get_model> call, Response<Business_Get_model> response) {
                Business_Get_model get_bussuineMode = response.body();
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    if (get_bussuineMode.getStatus().equals("success") && !get_bussuineMode.getBusiness().isEmpty()) {
                        for (int i = 0; i < get_bussuineMode.getBusiness().size(); i++) {
                            Business_Get_model.Business auction = get_bussuineMode.getBusiness().get(i);
                            if (new PrefManager(getApplicationContext()).getString(IConstant.BID).equals(auction.getBId())) {
                                logo = get_bussuineMode.getBusiness().get(i).getBLogo();
                                Glide.with(Select_Bussiness_Activity.this).load(logo).into(imageView1);
                            }
                        }
                    } else {

                    }

                } else {

                }
            }

            @Override
            public void onFailure(Call<Business_Get_model> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public void setFrame(LocalFrameItem localFrameItem) {
        if (llFrame.getChildCount() > 0) {
            llFrame.removeAllViews();
        }

        View inflate = LayoutInflater.from(this).inflate(localFrameItem.getLayout_id(), llFrame, false);
        ivframebg = (ImageView) inflate.findViewById(R.id.ivframebg);
        imageView1 = (ImageView) inflate.findViewById(R.id.ivframelogo);
        imageView2 = (ImageView) inflate.findViewById(R.id.ivCall);
        imageView3 = (ImageView) inflate.findViewById(R.id.ivEmail);
        imageView4 = (ImageView) inflate.findViewById(R.id.ivWebsite);
        imageView5 = (ImageView) inflate.findViewById(R.id.ivLocation);
        textView = (TextView) inflate.findViewById(R.id.tvframephone);
        textView2 = (TextView) inflate.findViewById(R.id.tvframeemail);
        textView3 = (TextView) inflate.findViewById(R.id.tvframeweb);
        textView4 = (TextView) inflate.findViewById(R.id.tvframelocation);

        callGetBussiness();
        imageView1.setOnTouchListener(new MultiTouchListener());

        businessname = new PrefManager(getApplicationContext()).getString(IConstant.BUSINESS_NAME);
        Emailaddress = new PrefManager(getApplicationContext()).getString(IConstant.BUSINESS_EMAIL);
        Website = new PrefManager(getApplicationContext()).getString(IConstant.WEBSITE);
        Address = new PrefManager(getApplicationContext()).getString(IConstant.ADDRESS);
        MobileNo = new PrefManager(getApplicationContext()).getString(IConstant.MOBILE_NO);


        try {
            if (!logo.isEmpty()) {
                Glide.with(this).load(logo).into(imageView1);
                cb_logo.setChecked(true);
            } else {
                cb_logo.setChecked(false);
            }

            if (MobileNo != null) {
                imageView2.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                textView.setText(MobileNo);
                cb_mobile.setChecked(true);
            } else {
                cb_mobile.setChecked(false);
                imageView2.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
            }

            if (Address != null) {
                imageView5.setVisibility(View.VISIBLE);
                textView4.setVisibility(View.VISIBLE);
                textView4.setText(Address);
                cb_address.setChecked(true);
            } else {
                cb_address.setChecked(false);
                imageView5.setVisibility(View.GONE);
                textView4.setVisibility(View.GONE);
            }
            if (Emailaddress != null) {
                imageView3.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                textView2.setText(Emailaddress);
                cb_email.setChecked(true);
            } else {
                cb_email.setChecked(false);
            }
            if (Website != null) {
                imageView4.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.VISIBLE);
                textView3.setText(Website);
                cb_website.setChecked(true);
            } else {
                cb_website.setChecked(false);
            }

            cb_logo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        imageView1.setVisibility(View.VISIBLE);
                    } else {
                        imageView1.setVisibility(View.GONE);
                    }
                }
            });

            cb_mobile.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        imageView2.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.VISIBLE);
                    } else {
                        imageView2.setVisibility(View.GONE);
                        textView.setVisibility(View.GONE);
                    }
                }
            });

            cb_address.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        imageView5.setVisibility(View.VISIBLE);
                        textView4.setVisibility(View.VISIBLE);
                    } else {
                        imageView5.setVisibility(View.GONE);
                        textView4.setVisibility(View.GONE);
                    }
                }
            });

            cb_email.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        imageView3.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                    } else {
                        imageView3.setVisibility(View.GONE);
                        textView2.setVisibility(View.GONE);
                    }
                }
            });

            cb_website.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        imageView4.setVisibility(View.VISIBLE);
                        textView3.setVisibility(View.VISIBLE);
                    } else {
                        imageView4.setVisibility(View.GONE);
                        textView3.setVisibility(View.GONE);
                    }
                }
            });


        } catch (Exception unused) {

        }
        llFrame.addView(inflate);
    }

    private void HelperBoxclear() {
        for (int i = 0; i < this.txt_stkr_relP_custom.getChildCount(); i++) {
            View childAt = this.txt_stkr_relP_custom.getChildAt(i);
            FrameLayout frameLayout = (FrameLayout) childAt.findViewById(R.id.frmBorder);
            if (frameLayout != null) {
                frameLayout.setBackgroundResource(0);
            }
            ImageView imageView = (ImageView) childAt.findViewById(R.id.imgPhotoEditorClose);
            if (imageView != null) {
                imageView.setVisibility(View.GONE);
            }
        }
    }

    public Bitmap SaveBitmap() {
        // HelperBoxclear();
        Bitmap bm = null;
        FrameLayout savedImage = bussiness_frameimage;
        savedImage.setDrawingCacheEnabled(true);
        savedImage.buildDrawingCache();
        bm = savedImage.getDrawingCache();
        return bm;

    }

    public void opacity() {
        opacityBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                if (effect3Drecyclerviw.isShown()) {
                    seteffect1.setAlpha((float) progress / 255);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

        });

    }

    public void Dialog() {
        dialog = new Dialog(Select_Bussiness_Activity.this);
        dialog.setContentView(R.layout.dialog_user_not_paid);
        dialog.setCancelable(false);
        TextView textView = (TextView) dialog.findViewById(R.id.txt_close);
        textView.setText("Sorry!! \nBuy Premium plan for Download this Post");
        TextView btn_buy = (TextView) dialog.findViewById(R.id.btn_buy);
        TextView btn_cancel = (TextView) dialog.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PreferencesHandler.getStringPreference(Select_Bussiness_Activity.this, Constans.INDIA).equals("91")) {
                    Intent intent = new Intent(Select_Bussiness_Activity.this, PaymentGatewayActivity.class);
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(Select_Bussiness_Activity.this, PremiumActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        dialog.show();
    }

    public Bitmap viewToBitmap(View view) {
        try {
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
            view.draw(new Canvas(createBitmap));
            return createBitmap;
        } finally {
            view.destroyDrawingCache();
        }
    }

    public void saveBitmap(final boolean z) {
        new Thread(new Runnable() {
            public void run() {
                String str;
                try {

                    imageFileName = "oceanmtechdmt" + System.currentTimeMillis() + ".png";
                    File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "OceanmtechDMT");
                    //File storageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "Business Card");
                    boolean success = true;
                    if (!storageDir.exists()) {
                        success = storageDir.mkdirs();
                    }
                    if (success) {
                        File imageFile = new File(storageDir, imageFileName);
                        savedImagePath = imageFile.getAbsolutePath();
                        try {
                            OutputStream Out = new FileOutputStream(imageFile);
                            finalBitmapToSave.compress(Bitmap.CompressFormat.PNG, 100, Out);
                            Out.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (new PrefManager(getApplicationContext()).getBoolen(IConstant.IS_PADE)) {
                            Intent intent = new Intent(Select_Bussiness_Activity.this, SaveImageView.class);
                            intent.putExtra("getimagesave", imageFileName);
                            startActivity(intent);
                        } else {


                            Intent intent = new Intent(Select_Bussiness_Activity.this, SaveImageView.class);
                            intent.putExtra("getimagesave", imageFileName);
                            startActivity(intent);

                        }

                    }

                } catch (Exception unused) {
                }
            }
        }).start();

    }

    public void ShowFullScreen() {
        //interstitial FullScreenAd
        AdRequest adRequestfull = new AdRequest.Builder().build();
        interstitialAd = new InterstitialAd(Select_Bussiness_Activity.this);
        interstitialAd.setAdUnitId(getString(R.string.ad_id_interstitial));
        interstitialAd.setAdListener(new AdListener() {

            public void onAdLoaded() {
                super.onAdLoaded();
            }

            public void onAdClosed() {
                finalBitmapToSave = scaleBitmap(viewToBitmap(bussiness_frameimage), 2000, 2000);
                saveBitmap(true);
            }

            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);

            }
        });

        interstitialAd.loadAd(adRequestfull);

    }


    /////////////////////////NEW SAVE ////////////////////

    private class Select_Bussiness_Adapter extends RecyclerView.Adapter<selectbussiness_Myholder> {
        Context context;
        List<BussinessCat_Data_model.BusinessCategoryDatum> businessCategoryDatumList = new ArrayList<>();

        public Select_Bussiness_Adapter(Context context, List<BussinessCat_Data_model.BusinessCategoryDatum> businessCategoryDatumList) {
            this.context = context;
            this.businessCategoryDatumList = businessCategoryDatumList;
        }

        @NonNull
        @Override
        public selectbussiness_Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_greetings, parent, false);
            selectbussiness_Myholder viewHolder = new selectbussiness_Myholder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull selectbussiness_Myholder holder, int position) {
            if (businessCategoryDatumList.get(position).getBdFreeAndPaid().equals("1")) {
                holder.card_freepaid.setVisibility(View.VISIBLE);
            } else {
                holder.card_freepaid.setVisibility(View.GONE);
            }

            Glide.with(context)
                    .load(businessCategoryDatumList
                            .get(position).getImage())
                    .placeholder(R.drawable.newlogo)
                    .into(holder.ivThumb);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    position_getbussinesspostid = position;
                    Glide.with(context).load(businessCategoryDatumList.get(position).getImage()).placeholder(R.drawable.newlogo).into(bussiness_card_set);
                    checkbussinesspostData = businessCategoryDatumList.get(position).getBdFreeAndPaid();
                }
            });


        }

        @Override
        public int getItemCount() {
            return businessCategoryDatumList.size();
        }
    }

    private class selectbussiness_Myholder extends RecyclerView.ViewHolder {
        ImageView ivThumb;
        CardView card_freepaid;

        public selectbussiness_Myholder(@NonNull View itemView) {
            super(itemView);
            ivThumb = (ImageView) itemView.findViewById(R.id.ivThumb);
            card_freepaid = (CardView) itemView.findViewById(R.id.card_freepaid);

        }
    }

    public class Imagesaved extends AsyncTask<Bitmap, Void, String> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            pd = new ProgressDialog(Select_Bussiness_Activity.this);
            pd.setMessage("Save Image ...");
            pd.setCancelable(false);
            pd.show();
        }

        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(Bitmap... params) {
            Bitmap image = params[0];
            String savedImagePath = null;
            imageFileName = "oceanmtechdmt" + System.currentTimeMillis() + ".png";
            File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "OceanmtechDMT");
            //File storageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "Business Card");
            boolean success = true;
            if (!storageDir.exists()) {
                success = storageDir.mkdirs();
            }
            if (success) {
                File imageFile = new File(storageDir, imageFileName);
                savedImagePath = imageFile.getAbsolutePath();
                try {
                    OutputStream Out = new FileOutputStream(imageFile);
                    image.compress(Bitmap.CompressFormat.PNG, 100, Out);
                    Out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    bussiness_frameimage.destroyDrawingCache();
                }
                pd.dismiss();
            }
            return savedImagePath;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            Toast.makeText(Select_Bussiness_Activity.this, "Image saved sucessfully", Toast.LENGTH_SHORT).show();
            if (new PrefManager(getApplicationContext()).getBoolen(IConstant.IS_PADE)) {
                Intent intent = new Intent(Select_Bussiness_Activity.this, SaveImageView.class);
                intent.putExtra("getimagesave", imageFileName);
                startActivity(intent);
            } else {
                Intent intent = new Intent(Select_Bussiness_Activity.this, SaveImageView.class);
                intent.putExtra("getimagesave", imageFileName);
                startActivity(intent);
            }
        }
    }

    private class Bussiness_AdapterEffect extends RecyclerView.Adapter<Bussiness_AdapterEffect.effect_Myholder> {
        Activity activity;
        int[] effect1;
        LayoutInflater inflater;
        String Type1;

        public Bussiness_AdapterEffect(Activity activity, int[] effect1, String type1) {
            this.activity = activity;
            this.effect1 = effect1;
            Type1 = type1;
            inflater = LayoutInflater.from(activity);
        }

        @NonNull
        @Override
        public Bussiness_AdapterEffect.effect_Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.effectitems2, parent, false);
            return new Bussiness_AdapterEffect.effect_Myholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Bussiness_AdapterEffect.effect_Myholder dataHolder, int i) {
            Glide.with(activity).load(effect1[i]).into(dataHolder.imageView1);

            dataHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (i == 0) {
                        Select_Bussiness_Activity.seteffect1.setBackgroundResource(0);
                    } else {
                        Select_Bussiness_Activity.seteffect1.setBackgroundResource(effect1[i]);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return effect1.length;
        }


        private class effect_Myholder extends RecyclerView.ViewHolder {
            ImageView imageView1;
            ImageView item3d;

            public effect_Myholder(@NonNull View itemView) {
                super(itemView);
                imageView1 = itemView.findViewById(R.id.hello3);
                item3d = itemView.findViewById(R.id.item3d);
            }
        }
    }


}