package com.oceanmtech.dmt.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.oceanmtech.dmt.QuoteCreater.Constans;
import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.Model.Business_Get_model;
import com.oceanmtech.dmt.Model.LocalFrameItem;
import com.oceanmtech.dmt.MultiTouch.MultiTouchListener;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.R;
import com.oceanmtech.dmt.api.ApiInterfase;
import com.oceanmtech.dmt.api.RetrofitClient;
import com.oceanmtech.dmt.text.NewMultiTouchListener;
import com.oceanmtech.dmt.text.TextBean;
import com.oceanmtech.dmt.text.ViewType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Custom_Activity extends AppCompatActivity {/*implements ResizableStickerView.TouchEventListener, AutofitTextRel.TouchEventListener {*/
    private static final String TAG = "data--->";
    private RecyclerView specialcategoryrecycler111;
    public static ImageView card_set;
    private ImageView back_button;
    public String id;
    public String cat_id;
    private ImageView download_button;
    ProgressDialog pDialog;
    int tempPos;
    String type;
    private Button button_click;
    private TextView businessname_ad;
    private TextView Emailaddress_ad;
    private TextView Website_ad;
    private TextView Address_ad;
    private TextView MobileNo_ad;
    private ImageView userelogoset;
    public static FrameLayout frameimage;
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
    public static ArrayList<String> mImagesUri = new ArrayList<>();
    Boolean checkMemory;
    Bitmap finalBitmapToSave;
    String savedImagePath = null;
    RelativeLayout text_set;
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
    LinearLayout ll_add_text, ll_add_img, ll_text_color, ll_font_type;

    public static int total = 35;
    Dialog dialog;

    String addtext;
    TextView textset;
    FrameLayout textframe, textborder;
    ImageView textdelete, add_image;

    public static final int REQUEST_IMAGE = 100;
    public static RelativeLayout txt_stkr_relP_custom = null;
    private LayoutInflater layoutInflater;
    private Typeface mDefaultTextTypeface;
    private List<View> addedViewList;
    private TextView tv_image;
    private TextView tv_text;
    private SeekBar textSizeseek;
    TextBean textBean;
    private TextView testingdata;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        this.textBean = new TextBean();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        pDialog = new ProgressDialog(Custom_Activity.this);
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


        txt_stkr_relP_custom = (RelativeLayout) findViewById(R.id.txt_stkr_relP_custom);
        this.layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        this.mDefaultTextTypeface = Typeface.createFromAsset(getAssets(), "fonts/font1.ttf");
        this.addedViewList = new ArrayList();


        card_set = (ImageView) findViewById(R.id.card_set);
        back_button = (ImageView) findViewById(R.id.back_button);
        tv_image = (TextView) findViewById(R.id.tv_image);
        tv_text = (TextView) findViewById(R.id.tv_text);
        testingdata = (TextView) findViewById(R.id.testingdata);
        textSizeseek = (SeekBar) findViewById(R.id.textSizeseek);


        textSizeseek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                textBean.setTextSize(progress / 2);
                testingdata.setTextSize((float) textBean.getTextSize());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        tv_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tv_image.setOnClickListener(new View.OnClickListener() {
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
        }
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

        Custom_AdapterEffect AdapterEffect1 = new Custom_AdapterEffect(Custom_Activity.this, effect_3D, "effect1");
        effect3Drecyclerviw.setAdapter(AdapterEffect1);
        effect3Drecyclerviw.setLayoutManager(new LinearLayoutManager(Custom_Activity.this, LinearLayoutManager.HORIZONTAL, false));

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
                        .with(Custom_Activity.this)
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

        ll_add_text = (LinearLayout) findViewById(R.id.ll_add_text);
        ll_add_img = (LinearLayout) findViewById(R.id.ll_add_img);
        ll_text_color = (LinearLayout) findViewById(R.id.ll_text_color);
        ll_font_type = (LinearLayout) findViewById(R.id.ll_font_type);

        add_image = (ImageView) findViewById(R.id.add_image);

        textset = (TextView) findViewById(R.id.textset);
        textframe = (FrameLayout) findViewById(R.id.textframe);
        textdelete = (ImageView) findViewById(R.id.textdelete);
        textborder = (FrameLayout) findViewById(R.id.textborder);
        textset.setText(addtext);
        textframe.setOnTouchListener(new MultiTouchListener());

        MultiTouchListener multiTouchListener = new MultiTouchListener();
        multiTouchListener.setmOnGestureControl(new MultiTouchListener.OnGestureControl() {
            @Override
            public void onClick() {
                ImageView imageView = textdelete;
                imageView.setVisibility(imageView.getVisibility() == textset.VISIBLE ? textset.GONE : textset.VISIBLE);
            }

            @Override
            public void onLongClick() {

            }
        });

        textset.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ResourceType")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == (MotionEvent.ACTION_DOWN)) {
                    ImageView imageView = textdelete;
                    imageView.setVisibility(imageView.getVisibility() == textdelete.VISIBLE ? textdelete.GONE : textdelete.VISIBLE);
                }
                if (event.getAction() == (MotionEvent.ACTION_UP)) {
                }
                return false;
            }
        });

        textdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertbox1 = new AlertDialog.Builder(Custom_Activity.this)
                        .setMessage("Do you want to Delete Text")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                // new saveImage().execute(ganratBitmap());
                                textframe.setVisibility(View.GONE);
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                arg0.dismiss();
                                textdelete.setVisibility(View.GONE);

                            }
                        })
                        .show();
            }
        });

        ll_add_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AddTextDialog();
                startActivityForResult(new Intent(Custom_Activity.this, TextActivity_1.class), Constans.REQUEST_CODE_ADD_TEXT);

            }
        });
        ll_text_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialogBuilder
                        .with(Custom_Activity.this)
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

                                textView.setTextColor(Color.parseColor("#" + Integer.toHexString(selectedColor)));
                                textView2.setTextColor(Color.parseColor("#" + Integer.toHexString(selectedColor)));
                                textView3.setTextColor(Color.parseColor("#" + Integer.toHexString(selectedColor)));
                                textView4.setTextColor(Color.parseColor("#" + Integer.toHexString(selectedColor)));
                                textset.setTextColor(Color.parseColor("#" + Integer.toHexString(selectedColor)));
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
        ll_font_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTextDataDialog();
            }
        });
        ll_add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
*/
                Dexter.withActivity(Custom_Activity.this)
                        .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport report) {
                                if (report.areAllPermissionsGranted()) {
                                    showImagePickerOptions();
                                }

                                if (report.isAnyPermissionPermanentlyDenied()) {
                                    showSettingsDialog();
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
            }
        });


        userelogoset.setOnTouchListener(new MultiTouchListener());
        businessname_ad.setOnTouchListener(new MultiTouchListener());
        businessname_ad.setPaintFlags(businessname_ad.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        businessname_ad.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                businessname_ad.setVisibility(View.GONE);

                return false;
            }

        });

        download_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (new PrefManager(getApplicationContext()).isstatus()) {
                    HelperBoxclear();
                    //  new Imagesaved().execute(SaveBitmap());

                    if (interstitialAd.isLoaded()) {
                        interstitialAd.show();
                    } else {
                        finalBitmapToSave = scaleBitmap(viewToBitmap(frameimage), 2000, 2000);
                        saveBitmap(true);
                    }
                } else {
                    Toast.makeText(Custom_Activity.this, "You are not authorized", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    public void ShowFullScreen() {
        //interstitial FullScreenAd
        AdRequest adRequestfull = new AdRequest.Builder().build();
        interstitialAd = new InterstitialAd(Custom_Activity.this);
        interstitialAd.setAdUnitId(getString(R.string.ad_id_interstitial));
        interstitialAd.setAdListener(new AdListener() {

            public void onAdLoaded() {
                super.onAdLoaded();
            }

            public void onAdClosed() {
                finalBitmapToSave = scaleBitmap(viewToBitmap(frameimage), 2000, 2000);
                saveBitmap(true);
            }

            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);

            }
        });

        interstitialAd.loadAd(adRequestfull);

    }

    private void loadProfile(String url) {

        Glide.with(this).load(url)
                .into(add_image);
        add_image.setColorFilter(ContextCompat.getColor(this, android.R.color.transparent));
    }

    private void loadProfileDefault() {
        /*Glide.with(this).load(R.drawable.new1)
                .into(add_image);*/
        add_image.setColorFilter(ContextCompat.getColor(this, R.color.red));
    }

    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(this, new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        });
    }

    private void launchCameraIntent() {
        Intent intent = new Intent(Custom_Activity.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    private void launchGalleryIntent() {
        Intent intent = new Intent(Custom_Activity.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getParcelableExtra("path");
                try {
                    // You can update this bitmap to your server
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);

                    // loading profile image from local cache
                    loadProfile(uri.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (requestCode == 787 && resultCode == 200 && data.hasExtra(getResources().getString(R.string.text_view_model))) {
            Toast.makeText(this, "ttttttttttt", Toast.LENGTH_SHORT).show();
            addText((TextBean) data.getSerializableExtra(getResources().getString(R.string.text_view_model)));
        }

    }

    @NonNull
    private NewMultiTouchListener getMultiTouchListener() {
        NewMultiTouchListener multiTouchListener = new NewMultiTouchListener();
        return multiTouchListener;
    }

    public void addText(TextBean textModel) {
        try {
            View layout = getLayout(ViewType.TEXT);
            TextView textView = (TextView) layout.findViewById(R.id.tvPhotoEditorText);
            final ImageView imageView = (ImageView) layout.findViewById(R.id.imgPhotoEditorClose);
            textView.setText(textModel.getText());
            textView.setTextColor(textModel.getTextColor());
            textView.setTextSize((float) textModel.getTextSize());
            AssetManager assets = getAssets();
            StringBuilder sb = new StringBuilder();
            sb.append(textModel.getFont());
            if (textModel.getFont().equals("font6")) {
                textView.setTypeface(Typeface.createFromAsset(assets, "fonts/" + sb.toString() + ".ttf"));
            } else {
                textView.setTypeface(Typeface.createFromAsset(assets, sb.toString()));
            }
            textView.setShadowLayer(1.75f, (float) textModel.getShadowSize(), (float) textModel.getShadowSize(), textModel.getShadowColor());
            NewMultiTouchListener multitouchListener = getMultiTouchListener();
            multitouchListener.setOnGestureControl(new NewMultiTouchListener.OnGestureControl() {
                public void onLongClick() {
                }

                public void onClick() {
                    ImageView imageView2 = imageView;
                    imageView2.setVisibility(imageView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                }
            });
            layout.setOnTouchListener(new NewMultiTouchListener());
            addViewToParent(layout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addViewToParent(View view) {
        new RelativeLayout.LayoutParams(-2, -2).addRule(13, -1);
        txt_stkr_relP_custom.addView(view);
        this.addedViewList.add(view);
    }

    public void undoView(View view) {
        if (this.addedViewList.size() > 0 && this.addedViewList.contains(view)) {

            txt_stkr_relP_custom.removeView(view);
            this.addedViewList.remove(view);
        }
    }

    private View getLayout(ViewType viewType) {
        View view = null;
        switch (viewType) {
            case TEXT:
                view = this.layoutInflater.inflate(R.layout.view_photo_editor_text, null);
                TextView textView = (TextView) view.findViewById(R.id.tvPhotoEditorText);
                if (!(textView == null || this.mDefaultTextTypeface == null)) {
                    textView.setGravity(17);
                    textView.setTypeface(this.mDefaultTextTypeface);
                    break;
                }

        }

        if (view != null) {
            view.setTag(viewType);
            ImageView imageView = (ImageView) view.findViewById(R.id.imgPhotoEditorClose);
            if (imageView != null) {

                final View f$1 = view;
                imageView.setOnClickListener(new View.OnClickListener() {

                    public final void onClick(View view) {
                        undoView(f$1);
                    }
                });
            }
        }
        return view;
    }


    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Custom_Activity.this);
        builder.setTitle(getString(R.string.dialog_permission_title));
        builder.setMessage(getString(R.string.dialog_permission_message));
        builder.setPositiveButton(getString(R.string.go_to_settings), (dialog, which) -> {
            dialog.cancel();
            openSettings();
        });
        builder.setNegativeButton(getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
        builder.show();

    }

    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    private void AddTextDialog() {
        dialog = new Dialog(Custom_Activity.this);
        dialog.setContentView(R.layout.dialog_add_text);
        dialog.setCancelable(false);
        dialog.show();
        ImageView close = dialog.findViewById(R.id.close);
        EditText edt_add_text = dialog.findViewById(R.id.edt_add_text);
        CardView card_done = dialog.findViewById(R.id.card_done);

        card_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtext = edt_add_text.getText().toString();
                textset.setText(addtext);
                textframe.setVisibility(View.VISIBLE);
                textdelete.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void openTextDataDialog() {

        dialog = new Dialog(Custom_Activity.this);
        dialog.setContentView(R.layout.dialog_font_type);
        dialog.setCancelable(false);
        RecyclerView rv_font_type = dialog.findViewById(R.id.rv_font_type);
        ImageView close = dialog.findViewById(R.id.close);

        Edit_TextAdapater edit_textAdapater = new Edit_TextAdapater(Custom_Activity.this, textView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Custom_Activity.this);
        rv_font_type.setLayoutManager(layoutManager);
        rv_font_type.setAdapter(edit_textAdapater);
        dialog.show();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ShowFullScreen();
        retraivedata();
    }

    private void callGetBussiness() {
        pDialog = new ProgressDialog(Custom_Activity.this);
        pDialog.setTitle("Loading");
        pDialog.setMessage("Please wait...");
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setCancelable(false);
        pDialog.show();

        final ApiInterfase apiInterface = RetrofitClient.getApiClient().create(ApiInterfase.class);
        apiInterface.get_Business("business", new PrefManager(Custom_Activity.this).getString(IConstant.IS_R_ID)).enqueue(new Callback<Business_Get_model>() {
            @Override
            public void onResponse(Call<Business_Get_model> call, Response<Business_Get_model> response) {
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
                                Glide.with(Custom_Activity.this).load(logo).into(imageView);
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
        MobileNo = new PrefManager(getApplicationContext()).getString(IConstant.MOBILE_NO);


        try {

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
        llFrame.addView(inflate);
    }

    public class Imagesaved extends AsyncTask<Bitmap, Void, String> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            pd = new ProgressDialog(Custom_Activity.this);
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
            Toast.makeText(Custom_Activity.this, "Image saved sucessfully", Toast.LENGTH_SHORT).show();
            if (new PrefManager(getApplicationContext()).getBoolen(IConstant.IS_PADE)) {
                Intent intent = new Intent(Custom_Activity.this, SaveImageView.class);
                intent.putExtra("getimagesave", imageFileName);
                startActivity(intent);
            } else {

                Intent intent = new Intent(Custom_Activity.this, SaveImageView.class);
                intent.putExtra("getimagesave", imageFileName);
                startActivity(intent);

            }
        }
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
        HelperBoxclear();
        Bitmap bm = null;
        FrameLayout savedImage = frameimage;
        savedImage.setDrawingCacheEnabled(true);
        savedImage.buildDrawingCache();
        bm = savedImage.getDrawingCache();
        return bm;

    }


    private class Custom_AdapterEffect extends RecyclerView.Adapter<Custom_AdapterEffect.effect_Myholder> {
        Activity activity;
        int[] effect1;
        LayoutInflater inflater;
        String Type1;

        public Custom_AdapterEffect(Activity activity, int[] effect1, String type1) {
            this.activity = activity;
            this.effect1 = effect1;
            Type1 = type1;
            inflater = LayoutInflater.from(activity);
        }

        @NonNull
        @Override
        public effect_Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.effectitems2, parent, false);
            return new effect_Myholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull effect_Myholder dataHolder, int i) {
            Glide.with(activity).load(effect1[i]).into(dataHolder.imageView1);

            dataHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (i == 0) {
                        Custom_Activity.seteffect.setBackgroundResource(0);
                    } else {
                        Custom_Activity.seteffect.setBackgroundResource(effect1[i]);
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

    private class Edit_TextAdapater extends RecyclerView.Adapter<font_myholder> {
        Custom_Activity activity;
        String name;
        TextView font_text;

        public Edit_TextAdapater(Custom_Activity customActivity, TextView textView) {
            activity = customActivity;
            name = "abc";
            font_text = textView;
        }

        @NonNull
        @Override
        public font_myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_font_type, parent, false);

            font_myholder myHolder = new font_myholder(view);
            return myHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull font_myholder holder, int position) {
            holder.txt_font_type.setText(R.string.app_name);
            setfontType(holder.txt_font_type, position);

            holder.txt_font_type.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String fontName = "fonts/" + "font" + (position + 1) + ".ttf";
                    Typeface typeface = Typeface.createFromAsset(activity.getAssets(), fontName);
                    textView.setTypeface(typeface);
                    textView2.setTypeface(typeface);
                    textView3.setTypeface(typeface);
                    textView4.setTypeface(typeface);
                    textset.setTypeface(typeface);
                    dialog.dismiss();

                }
            });
        }

        @Override
        public int getItemCount() {
            return total;
        }
    }

    private class font_myholder extends RecyclerView.ViewHolder {
        TextView txt_font_type;

        public font_myholder(@NonNull View itemView) {
            super(itemView);

            txt_font_type = (TextView) itemView.findViewById(R.id.txt_font_type);
        }
    }

    public void setfontType(TextView textView, int position) {
        String fontName = "fonts/font1.ttf";
        if (position == total) {
            fontName = "fonts/" + "font" + total + ".ttf";
        } else {
            fontName = "fonts/" + "font" + (position + 1) + ".ttf";
        }

        Typeface typeface = Typeface.createFromAsset(getAssets(), fontName);
        textView.setTypeface(typeface);
        //Custom_Activity.setTypeface=typeface;
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
                            Intent intent = new Intent(Custom_Activity.this, SaveImageView.class);
                            intent.putExtra("getimagesave", imageFileName);
                            startActivity(intent);
                        } else {
//                            if (interstitialAd.isLoaded()) {
//                                interstitialAd.show();
//                            } else {
                            Intent intent = new Intent(Custom_Activity.this, SaveImageView.class);
                            intent.putExtra("getimagesave", imageFileName);
                            startActivity(intent);
                        }


                    }


                } catch (Exception unused) {
                }
            }
        }).start();

    }


}