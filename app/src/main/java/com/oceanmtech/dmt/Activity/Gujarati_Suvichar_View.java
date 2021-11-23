package com.oceanmtech.dmt.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.gson.Gson;
import com.oceanmtech.dmt.Adapter.AdapterEffect;
import com.oceanmtech.dmt.QuoteCreater.Constans;
import com.oceanmtech.dmt.DataBase;
import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.Model.Business_Get_model;
import com.oceanmtech.dmt.Model.FestivalGetData;
import com.oceanmtech.dmt.Model.LocalFrameItem;
import com.oceanmtech.dmt.Model.Status;
import com.oceanmtech.dmt.Model.UserData3;
import com.oceanmtech.dmt.MultiTouch.MultiTouchListener;
import com.oceanmtech.dmt.NewPremium.PaymentGatewayActivity;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.PreferencesHandler;
import com.oceanmtech.dmt.R;
import com.oceanmtech.dmt.api.ApiInterfase;
import com.oceanmtech.dmt.api.RetrofitClient;
import com.github.ybq.android.spinkit.SpinKitView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Gujarati_Suvichar_View extends AppCompatActivity {

    private static final String TAG = "data--->";
    private RecyclerView specialcategoryrecycler111;
    private SpinKitView speciallodinge;
    View_Adapter view_adapter;
    public static ImageView card_set;
    private ImageView back_button;
    public String id;
    public String cat_id;
    String name;
    String img;
    private String android_id;
    private ImageView download_button;
    ProgressDialog pDialog;
    String stringUrl;
    int tempPos;
    String type;
    // public static ArrayList<UserData3> userData3;
    UserData3 userData3;
    Status status;
    FestivalGetData festivalGetData;
    private DataBase dataBase;
    private Button button_click;
    private TextView businessname_ad;
    private TextView Emailaddress_ad;
    private TextView Website_ad;
    private TextView Address_ad;
    private TextView MobileNo_ad;
    private ImageView userelogoset;
    public static FrameLayout frameimage;
    String savedImagePath = null;
    private RecyclerView effect3Drecyclerviw;
    public static RelativeLayout effect_set;
    private SeekBar opacityBar;
    private TextView effect_button, color_button, frame_button;
    private ImageView effect_back;

    private LinearLayout effect;
    public static ImageView seteffect;
    String imageFileName;

    String businessname;
    String Emailaddress;
    String Website;
    String Address;
    String MobileNo;
    String catname;
    RelativeLayout text_set;
    Dialog dialog;
    public static String freepaid;
    private String filename;
    Boolean checkMemory;
    Bitmap finalBitmapToSave;
    InterstitialAd mInterstitialAd1;

    int[] effect_3D = new int[]{R.drawable.noneimage, R.drawable.o1, R.drawable.o2, R.drawable.o3, R.drawable.o4,
            R.drawable.o5, R.drawable.o6, R.drawable.o7, R.drawable.o8, R.drawable.o9, R.drawable.o10, R.drawable.o11,
            R.drawable.o12, R.drawable.o13, R.drawable.o14, R.drawable.o15, R.drawable.o16, R.drawable.o17, R.drawable.o18,
            R.drawable.o19, R.drawable.o20, R.drawable.o21, R.drawable.o22, R.drawable.o23, R.drawable.o24, R.drawable.o25,
            R.drawable.o26, R.drawable.o27, R.drawable.o28, R.drawable.o29, R.drawable.o30, R.drawable.o31, R.drawable.o32,
            R.drawable.o33, R.drawable.o34, R.drawable.o35, R.drawable.o36, R.drawable.o37, R.drawable.o38, R.drawable.o39,
            R.drawable.o40, R.drawable.o41, R.drawable.o42, R.drawable.o43, R.drawable.o44, R.drawable.o45};

    private TextView titleColorText;
    private SwipeRefreshLayout swipeallimage;
    View view_r, view_l;

    CheckBox cb_logo, cb_name, cb_email, cb_website, cb_mobile, cb_address;

    LinearLayout lay_frames, llFrame;

    LinearLayout ll_effect, frame;

    ImageView ivframebg;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;

    InterstitialAd interstitialAd;
    private TextView tv_waterMark;

    String logo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gujarati__suvichar__view);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        pDialog = new ProgressDialog(Gujarati_Suvichar_View.this);
        pDialog.setTitle("Loading");
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(true);
        pDialog.setMax(100);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setCancelable(false);
        Intent intent = getIntent();
        cat_id = intent.getStringExtra("cat_id");
        tempPos = intent.getIntExtra("pos", 0);
        catname = intent.getStringExtra("Cat");
        type = intent.getStringExtra("type");

        specialcategoryrecycler111 = (RecyclerView) findViewById(R.id.specialcategoryrecycler);
        speciallodinge = (SpinKitView) findViewById(R.id.speciallodinge);
        card_set = (ImageView) findViewById(R.id.card_set);
        back_button = (ImageView) findViewById(R.id.back_button);
        download_button = (ImageView) findViewById(R.id.download_button);
        button_click = (Button) findViewById(R.id.button_click);
        frameimage = (FrameLayout) findViewById(R.id.frameimage);
        effect3Drecyclerviw = (RecyclerView) findViewById(R.id.effect3Drecyclerviw);
        effect_set = (RelativeLayout) findViewById(R.id.effect_set);
        opacityBar = (SeekBar) findViewById(R.id.opacity);
        effect_button = (TextView) findViewById(R.id.effect_button);
        frame_button = (TextView) findViewById(R.id.frame_button);
        color_button = (TextView) findViewById(R.id.color_button);
        effect_back = (ImageView) findViewById(R.id.effect_back);
        effect = (LinearLayout) findViewById(R.id.effect);
        seteffect = (ImageView) findViewById(R.id.seteffect);
        titleColorText = (TextView) findViewById(R.id.titleColorText);
        text_set = (RelativeLayout) findViewById(R.id.text_set);

        cb_logo = (CheckBox) findViewById(R.id.cb_logo);
        cb_name = (CheckBox) findViewById(R.id.cb_name);
        cb_email = (CheckBox) findViewById(R.id.cb_email);
        cb_website = (CheckBox) findViewById(R.id.cb_website);
        cb_mobile = (CheckBox) findViewById(R.id.cb_mobile);
        cb_address = (CheckBox) findViewById(R.id.cb_address);

        lay_frames = (LinearLayout) findViewById(R.id.lay_frames);
        llFrame = (LinearLayout) findViewById(R.id.llframe);

        ll_effect = (LinearLayout) findViewById(R.id.effect);
        frame = (LinearLayout) findViewById(R.id.frame);

        tv_waterMark = (TextView) findViewById(R.id.tv_waterMark);


        if (new PrefManager(getApplicationContext()).getBoolen(IConstant.IS_PADE)) {
            tv_waterMark.setVisibility(View.GONE);
        } else {
            tv_waterMark.setVisibility(View.VISIBLE);
            //      Toast.makeText(Gujarati_Suvichar_View.this, "You are not Premium", Toast.LENGTH_SHORT).show();
        }

        //titleColorText.setText(catname);

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


        opacity();


        AdapterEffect AdapterEffect1 = new AdapterEffect(Gujarati_Suvichar_View.this, effect_3D, "effect1");
        effect3Drecyclerviw.setAdapter(AdapterEffect1);
        effect3Drecyclerviw.setLayoutManager(new LinearLayoutManager(Gujarati_Suvichar_View.this, LinearLayoutManager.HORIZONTAL, false));


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
        frame_button.setOnClickListener(new View.OnClickListener() {
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

        color_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ColorPickerDialogBuilder
                        .with(Gujarati_Suvichar_View.this)
                        .setTitle("Choose color")
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(12)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {
                            }
                        })
                        .setPositiveButton("ok", new ColorPickerClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {

                                ImageViewCompat.setImageTintMode(ivframebg, PorterDuff.Mode.SRC_ATOP);
                                ImageViewCompat.setImageTintList(ivframebg, ColorStateList.valueOf(Color.parseColor("#" + Integer.toHexString(selectedColor))));
                                ImageViewCompat.setImageTintMode(imageView2, PorterDuff.Mode.SRC_ATOP);
                                ImageViewCompat.setImageTintList(imageView2, ColorStateList.valueOf(Color.parseColor("#" + Integer.toHexString(selectedColor))));
                                ImageViewCompat.setImageTintMode(imageView3, PorterDuff.Mode.SRC_ATOP);
                                ImageViewCompat.setImageTintList(imageView3, ColorStateList.valueOf(Color.parseColor("#" + Integer.toHexString(selectedColor))));
                                ImageViewCompat.setImageTintMode(imageView4, PorterDuff.Mode.SRC_ATOP);
                                ImageViewCompat.setImageTintList(imageView4, ColorStateList.valueOf(Color.parseColor("#" + Integer.toHexString(selectedColor))));
                                ImageViewCompat.setImageTintMode(imageView5, PorterDuff.Mode.SRC_ATOP);
                                ImageViewCompat.setImageTintList(imageView5, ColorStateList.valueOf(Color.parseColor("#" + Integer.toHexString(selectedColor))));


                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {


                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .build()
                        .show();
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

        businessname_ad = (TextView) findViewById(R.id.businessname_ad);
        Emailaddress_ad = (TextView) findViewById(R.id.Emailaddress_ad);
        Website_ad = (TextView) findViewById(R.id.Website_ad);
        Address_ad = (TextView) findViewById(R.id.Address_ad);
        view_l = (View) findViewById(R.id.view_l);
        view_r = (View) findViewById(R.id.view_r);
        Address_ad = (TextView) findViewById(R.id.Address_ad);
        MobileNo_ad = (TextView) findViewById(R.id.MobileNo_ad);
        userelogoset = (ImageView) findViewById(R.id.userelogoset);

        swipeallimage = (SwipeRefreshLayout) findViewById(R.id.swipeallimage);

        userelogoset.setOnTouchListener(new MultiTouchListener());
        businessname_ad.setOnTouchListener(new MultiTouchListener());

        //   businessname_ad.setVisibility(View.VISIBLE);
        //   Address_ad.setVisibility(View.VISIBLE);


        businessname_ad.setPaintFlags(businessname_ad.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//        Address_ad.setPaintFlags(Address_ad.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        businessname_ad.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                businessname_ad.setVisibility(View.GONE);

                return false;
            }

        });


        speciallodinge.setVisibility(View.VISIBLE);

        Click();
        specialinitView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        retraivedata();
        ShowFullScreen();

    }

    private void callGetBussiness() {
        pDialog = new ProgressDialog(Gujarati_Suvichar_View.this);
        pDialog.setTitle("Loading");
        pDialog.setMessage("Please wait...");
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setCancelable(false);
        pDialog.show();

        final ApiInterfase apiInterface = RetrofitClient.getApiClient().create(ApiInterfase.class);
        apiInterface.get_Business("business", new PrefManager(Gujarati_Suvichar_View.this).getString(IConstant.IS_R_ID)).enqueue(new Callback<Business_Get_model>() {
            @Override
            public void onResponse(Call<Business_Get_model> call, Response<Business_Get_model> response) {
                Gson gson = new Gson();
                String successResponse = gson.toJson(response.body());
                Business_Get_model get_bussuineMode = response.body();
                if (response.isSuccessful()) {
                    pDialog.dismiss();

                    if (get_bussuineMode.getStatus().equals("success") && !get_bussuineMode.getBusiness().isEmpty()) {

                        for (int i = 0; i < get_bussuineMode.getBusiness().size(); i++)
                        {
                            Business_Get_model.Business auction = get_bussuineMode.getBusiness().get(i);
                            if (new PrefManager(getApplicationContext()).getString(IConstant.BID).equals(auction.getBId()))
                            {
                                logo = get_bussuineMode.getBusiness().get(i).getBLogo();
                                Glide.with(Gujarati_Suvichar_View.this).load(logo).into(imageView);
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

    public void opacity() {
        opacityBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                if (effect3Drecyclerviw.isShown()) {
                    seteffect.setAlpha((float) progress / 255);
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

    public void retraivedata() {
        callGetBussiness();
        businessname = new PrefManager(getApplicationContext()).getString(IConstant.BUSINESS_NAME);
        Emailaddress = new PrefManager(getApplicationContext()).getString(IConstant.BUSINESS_EMAIL);
        Website = new PrefManager(getApplicationContext()).getString(IConstant.WEBSITE);
        Address = new PrefManager(getApplicationContext()).getString(IConstant.ADDRESS);
        MobileNo = new PrefManager(getApplicationContext()).getString(IConstant.MOBILE_NO);

        if (Address.isEmpty()) {
            imageView5.setVisibility(View.GONE);
            textView4.setVisibility(View.GONE);
            cb_address.setChecked(false);

        } else {
            imageView5.setVisibility(View.VISIBLE);
            textView4.setVisibility(View.VISIBLE);
            cb_address.setChecked(true);
        }

        if (MobileNo.isEmpty()) {
            imageView2.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
            cb_mobile.setChecked(false);

        } else {
            cb_mobile.setChecked(true);
            imageView2.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
        }

        if (Emailaddress.isEmpty()) {
            imageView3.setVisibility(View.GONE);
            textView2.setVisibility(View.GONE);
            cb_email.setChecked(false);

        } else {
            cb_email.setChecked(true);
            imageView3.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
        }

        if (Website.isEmpty()) {
            imageView4.setVisibility(View.GONE);
            textView3.setVisibility(View.GONE);
            cb_website.setChecked(false);
        } else {
            cb_website.setChecked(true);
            imageView4.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
        }

    }

    private void Click() {
        button_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void saveImage(int currentItem) {
        stringUrl = userData3.getData().get(currentItem).getImage();

        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(stringUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            File sdCardRoot = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/"+ "OceanmtechDMT");
            if (!sdCardRoot.exists()) {
                sdCardRoot.mkdirs();
            }

            String fileName = stringUrl.substring(
                    stringUrl.lastIndexOf('/') + 1, stringUrl.length());

            File imgFile = new File(sdCardRoot, "IMG"
                    + System.currentTimeMillis() / 100 + fileName);
            if (!sdCardRoot.exists()) {
                imgFile.createNewFile();
            }

            InputStream inputStream = urlConnection.getInputStream();
            int totalSize = urlConnection.getContentLength();
            FileOutputStream outPut = new FileOutputStream(imgFile);

            int downloadedSize = 0;
            byte[] buffer = new byte[2024];
            int bufferLength = 0;
            while ((bufferLength = inputStream.read(buffer)) > 0) {
                outPut.write(buffer, 0, bufferLength);
                downloadedSize += bufferLength;
            }

            outPut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class SaveImage extends AsyncTask<Integer, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog.show();

        }

        @Override
        protected String doInBackground(Integer... strings) {
            saveImage(strings[0]);

            return "Image Saved Sucessfully";
        }

        @Override
        protected void onPostExecute(String s) {

            if (pDialog.isShowing()) {
                pDialog.dismiss();

                scanGallery(stringUrl);
            }
        }

        private void scanGallery(String path) {
            try {
                MediaScannerConnection.scanFile(Gujarati_Suvichar_View.this, new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {

                    }

                });

            } catch (Exception e) {
                e.printStackTrace();

            }

        }

    }

    private void specialinitView() {

        ApiInterfase apiInterfase = RetrofitClient.getApiClient().create(ApiInterfase.class);
        Call<UserData3> userData3Call = apiInterfase.getCategory(type, cat_id);
        userData3Call.enqueue(new Callback<UserData3>() {
            @Override
            public void onResponse(Call<UserData3> call, Response<UserData3> response) {
                if (response.isSuccessful()) {
                    speciallodinge.setVisibility(View.GONE);
                    userData3 = response.body();
                    if (userData3.getStatus().equals("success")) {
                        download_button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (new PrefManager(getApplicationContext()).getBoolen(IConstant.IS_PADE)) {

                                    if (card_set.getDrawable() == null) {

                                        Toast toast = Toast.makeText(Gujarati_Suvichar_View.this, "Please Wait.....", Toast.LENGTH_LONG);
                                        View view1 = toast.getView();
                                        toast.setGravity(Gravity.CENTER, 0, 0);
                                        toast.show();

                                    } else {

                                        finalBitmapToSave = scaleBitmap(viewToBitmap(frameimage), 2000, 2000);
                                        tv_waterMark.setVisibility(View.GONE);
                                        saveBitmap(true);
                                    }

                                } else {

                                    freepaid = userData3.getData().get(tempPos).getFreeAndPaid();
                                    if (freepaid.equals("1")) {
                                        if (card_set.getDrawable() == null) {

                                            Toast toast = Toast.makeText(Gujarati_Suvichar_View.this, "Please Wait.....", Toast.LENGTH_LONG);
                                            View view1 = toast.getView();
                                            toast.setGravity(Gravity.CENTER, 0, 0);
                                            toast.show();

                                        } else {
                                            if (interstitialAd.isLoaded()) {
                                                interstitialAd.show();
                                            } else {
                                                finalBitmapToSave = scaleBitmap(viewToBitmap(frameimage), 2000, 2000);
                                                tv_waterMark.setVisibility(View.VISIBLE);
                                                saveBitmap(true);
                                            }
                                        }
                                    } else {

                                        Dialog();

                                    }


                                }


//}
                            }
                        });

                        if (userData3.getData().size() != 0) {

                            Glide.with(Gujarati_Suvichar_View.this)
                                    .load(userData3.getData().get(tempPos).getImage())
                                    .placeholder(R.drawable.newlogo)
                                    .into(card_set);
                        }

                        specialcategoryrecycler111.setLayoutManager(new GridLayoutManager(Gujarati_Suvichar_View.this, 3, RecyclerView.VERTICAL, false));
                        //specialcategoryrecycler111.setLayoutManager(new LinearLayoutManager(Gujarati_Suvichar_View.this, LinearLayoutManager.VERTICAL, false));
                        view_adapter = new View_Adapter(Gujarati_Suvichar_View.this, userData3.getData());
                        // Collections.reverse(userData3.getData())
                        specialcategoryrecycler111.setAdapter(view_adapter);

                    } else {

                    }

                } else {
                    speciallodinge.setVisibility(View.GONE);
                }

            }


            @Override
            public void onFailure(Call<UserData3> call, Throwable t) {
                // speciallodinge.setVisibility(View.GONE);
            }

        });

    }


    public void Dialog() {
        dialog = new Dialog(Gujarati_Suvichar_View.this);
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
                if (PreferencesHandler.getStringPreference(Gujarati_Suvichar_View.this, Constans.INDIA).equals("91")) {
                    Intent intent = new Intent(Gujarati_Suvichar_View.this, PaymentGatewayActivity.class);
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(Gujarati_Suvichar_View.this, PremiumActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        dialog.show();
    }

    public class Imagesaved extends AsyncTask<Bitmap, Void, String> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            pd = new ProgressDialog(Gujarati_Suvichar_View.this);
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

            //           File storageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "Business Card");

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
                    frameimage.destroyDrawingCache();
                }

                pd.dismiss();

            }

            return savedImagePath;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            Toast.makeText(Gujarati_Suvichar_View.this, "Image saved sucessfully", Toast.LENGTH_SHORT).show();

            if (new PrefManager(getApplicationContext()).getBoolen(IConstant.IS_PADE)) {
                Intent intent = new Intent(Gujarati_Suvichar_View.this, SaveImageView.class);
                intent.putExtra("getimagesave", imageFileName);
                startActivity(intent);
            } else {
                Intent intent = new Intent(Gujarati_Suvichar_View.this, SaveImageView.class);
                intent.putExtra("getimagesave", imageFileName);
                startActivity(intent);
            }
        }

    }

    public static Bitmap SaveBitmap() {
        Bitmap bm = null;
        FrameLayout savedImage = frameimage;
        savedImage.setDrawingCacheEnabled(true);
        savedImage.buildDrawingCache();
        bm = savedImage.getDrawingCache();
        return bm;

    }

    public class View_Adapter extends RecyclerView.Adapter<View_Adapter.DataHolder> {

        Activity activity;
        LayoutInflater inflater;
        List<UserData3.Datum> userData_apis;
        UserData3.Datum data1;

        public View_Adapter(Activity mContext, List<UserData3.Datum> userData3s) {
            this.activity = mContext;
            this.userData_apis = userData3s;
            inflater = LayoutInflater.from(activity);
        }

        @NonNull
        @Override
        public View_Adapter.DataHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = inflater.inflate(R.layout.view_item, viewGroup, false);
            return new View_Adapter.DataHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DataHolder dataHolder, final int i) {
            data1 = userData_apis.get(dataHolder.getAdapterPosition());

            //  Log.i(TAG, "onBindViewHolder: " + data1.getFreeAndPaid());
            if (userData_apis.get(i).getFreeAndPaid().equals("1")) {
                dataHolder.card_freepaid.setVisibility(View.VISIBLE);
            } else {
                dataHolder.card_freepaid.setVisibility(View.GONE);
            }


            if (userData_apis.size() != 0) {
                Glide.with(activity)
                        .load(data1.getImage())
                        .placeholder(R.drawable.newlogo)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(dataHolder.catimage);
            }

            dataHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tempPos = dataHolder.getAdapterPosition();
                    freepaid = userData_apis.get(i).getFreeAndPaid();

                    Glide.with(Gujarati_Suvichar_View.this)
                            .load(userData_apis.get(i).getImage())
                            .placeholder(R.drawable.newlogo)
                            .into(card_set);

                }

            });

        }

        @Override
        public int getItemViewType(int i) {
            return i;
        }

        @Override
        public int getItemCount() {
            return userData_apis.size();
        }

        public class DataHolder extends RecyclerView.ViewHolder {

            ImageView catimage;
            CardView card_freepaid;

            public DataHolder(@NonNull View itemView) {
                super(itemView);

                catimage = (ImageView) itemView.findViewById(R.id.catimage);
                card_freepaid = (CardView) itemView.findViewById(R.id.card_freepaid);

            }
        }

    }



    public void setFrame(LocalFrameItem localFrameItem) {
        if (llFrame.getChildCount() > 0) {
            llFrame.removeAllViews();
        }
        View inflate = LayoutInflater.from(this).inflate(localFrameItem.getLayout_id(), llFrame, false);
        ivframebg = (ImageView) inflate.findViewById(R.id.ivframebg);
        imageView = (ImageView) inflate.findViewById(R.id.ivframelogo);
        imageView2 = (ImageView) inflate.findViewById(R.id.ivCall);
        imageView3 = (ImageView) inflate.findViewById(R.id.ivEmail);
        imageView4 = (ImageView) inflate.findViewById(R.id.ivWebsite);
        imageView5 = (ImageView) inflate.findViewById(R.id.ivLocation);
        textView = (TextView) inflate.findViewById(R.id.tvframephone);
        textView2 = (TextView) inflate.findViewById(R.id.tvframeemail);
        textView3 = (TextView) inflate.findViewById(R.id.tvframeweb);
        textView4 = (TextView) inflate.findViewById(R.id.tvframelocation);

        imageView.setOnTouchListener(new MultiTouchListener());

        businessname = new PrefManager(getApplicationContext()).getString(IConstant.BUSINESS_NAME);
        Emailaddress = new PrefManager(getApplicationContext()).getString(IConstant.BUSINESS_EMAIL);
        Website = new PrefManager(getApplicationContext()).getString(IConstant.WEBSITE);
        Address = new PrefManager(getApplicationContext()).getString(IConstant.ADDRESS);
        MobileNo = new PrefManager(getApplicationContext()).getString(IConstant.MOBILE_NO);


        try {
               /* if (currentBusiness.getBusiLogo() != null) {
                    Glide.with((FragmentActivity) this).load(currentBusiness.getBusiLogo()).into(imageView);
                }*/

            if (!logo.isEmpty()) {


                Glide.with(this).load(logo).into(imageView);
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
                imageView2.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                cb_mobile.setChecked(false);

            }

            if (Address != null) {
                imageView5.setVisibility(View.VISIBLE);
                textView4.setVisibility(View.VISIBLE);
                textView4.setText(Address);
                cb_address.setChecked(true);
            } else {
                imageView5.setVisibility(View.GONE);
                textView4.setVisibility(View.GONE);
                cb_address.setChecked(false);

            }
            if (Emailaddress != null) {
                imageView3.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                textView2.setText(Emailaddress);
                cb_email.setChecked(true);
            } else {
                imageView3.setVisibility(View.GONE);
                textView2.setVisibility(View.GONE);
                cb_email.setChecked(false);
            }
            if (Website != null) {
                imageView4.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.VISIBLE);
                textView3.setText(Website);
                cb_website.setChecked(true);
            } else {
                imageView4.setVisibility(View.GONE);
                textView3.setVisibility(View.GONE);
                cb_website.setChecked(false);
            }

            cb_logo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        imageView.setVisibility(View.VISIBLE);
                    } else {
                        imageView.setVisibility(View.GONE);
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
//        }
        llFrame.addView(inflate);
    }

    public void ShowFullScreen() {
        //interstitial FullScreenAd
        AdRequest adRequestfull = new AdRequest.Builder().build();
        interstitialAd = new InterstitialAd(Gujarati_Suvichar_View.this);
        interstitialAd.setAdUnitId(getString(R.string.ad_id_interstitial));
        interstitialAd.setAdListener(new AdListener() {

            public void onAdLoaded() {
                super.onAdLoaded();
            }

            public void onAdClosed() {

                finalBitmapToSave = scaleBitmap(viewToBitmap(frameimage), 2000, 2000);
                tv_waterMark.setVisibility(View.VISIBLE);
                saveBitmap(true);
            }

            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);

            }
        });

        interstitialAd.loadAd(adRequestfull);

    }


/////////////////////////NEW SAVE ////////////////////


    public Bitmap viewToBitmap(View view) {
        try {
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
            view.draw(new Canvas(createBitmap));
            return createBitmap;
        } finally {
            view.destroyDrawingCache();
        }
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, int wantedWidth, int wantedHeight) {
        Bitmap output = Bitmap.createBitmap(wantedWidth, wantedHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Matrix m = new Matrix();
        m.setScale((float) wantedWidth / bitmap.getWidth(), (float) wantedHeight / bitmap.getHeight());
        canvas.drawBitmap(bitmap, m, new Paint());

        return output;
    }

    public void saveBitmap(final boolean z) {
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Please Wait");
//        progressDialog.setCancelable(false);
//        progressDialog.show();
        new Thread(new Runnable() {
            public void run() {
                String str;
                try {

                    imageFileName = "oceanmtechdmt" + System.currentTimeMillis() + ".png";

                    File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "OceanmtechDMT");

                    //           File storageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "Business Card");

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
                        Intent intent = new Intent(Gujarati_Suvichar_View.this, SaveImageView.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("getimagesave", imageFileName);
                        intent.putExtra("type", "editarray");
                        startActivity(intent);
                        finish();
                    }

                } catch (Exception unused) {
                    unused.printStackTrace();
                }
            }
        }).start();
    }

}