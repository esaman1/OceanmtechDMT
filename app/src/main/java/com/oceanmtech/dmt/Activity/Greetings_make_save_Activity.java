package com.oceanmtech.dmt.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
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
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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
import com.oceanmtech.dmt.MultiTouch.MultiTouchListener;
import com.oceanmtech.dmt.NewFile.ImageAdapter;
import com.oceanmtech.dmt.NewFile.ImageModel;
import com.oceanmtech.dmt.NewFile.TextAdapater1;
import com.oceanmtech.dmt.NewPremium.PaymentGatewayActivity;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.PreferencesHandler;
import com.oceanmtech.dmt.R;
import com.oceanmtech.dmt.api.ApiInterfase;
import com.oceanmtech.dmt.api.RetrofitClient;
import com.oceanmtech.dmt.text.NewMultiTouchListener;
import com.oceanmtech.dmt.text.TextBean;
import com.oceanmtech.dmt.text.ViewType;


import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import petrov.kristiyan.colorpicker.ColorPicker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.oceanmtech.dmt.Activity.ImagePickerActivity.REQUEST_IMAGE_CAPTURE;
import static com.oceanmtech.dmt.NewFile.ImagesActivity.PICK_IMAGES;
import static com.oceanmtech.dmt.NewFile.ImagesActivity.STORAGE_PERMISSION;

public class Greetings_make_save_Activity extends AppCompatActivity {
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
    private TextView effect_button/*, color_button, frame_button*/;
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
    Boolean checkMemory;
    Bitmap finalBitmapToSave;
    String savedImagePath = null;
    int[] effect_3D = new int[]{R.drawable.noneimage, R.drawable.o1, R.drawable.o2, R.drawable.o3, R.drawable.o4,
            R.drawable.o5, R.drawable.o6, R.drawable.o7, R.drawable.o8, R.drawable.o9, R.drawable.o10, R.drawable.o11,
            R.drawable.o12, R.drawable.o13, R.drawable.o14, R.drawable.o15, R.drawable.o16, R.drawable.o17, R.drawable.o18,
            R.drawable.o19, R.drawable.o20, R.drawable.o21, R.drawable.o22, R.drawable.o23, R.drawable.o24, R.drawable.o25,
            R.drawable.o26, R.drawable.o27, R.drawable.o28, R.drawable.o29, R.drawable.o30, R.drawable.o31, R.drawable.o32,
            R.drawable.o33, R.drawable.o34, R.drawable.o35, R.drawable.o36, R.drawable.o37, R.drawable.o38, R.drawable.o39,
            R.drawable.o40, R.drawable.o41, R.drawable.o42, R.drawable.o43, R.drawable.o44, R.drawable.o45};

    private TextView titleColorText;
    private SwipeRefreshLayout swipeallimage;

    CheckBox cb_logo, cb_name, cb_email, cb_website, cb_mobile, cb_address, cb_buss;
    LinearLayout /*lay_frames,*/ llFrame;
    LinearLayout ll_effect/*, frame*/;
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
    private Button button;
    String addtext, img_set;
    TextView textset;
    FrameLayout textframe, textborder;
    ImageView textdelete, add_image;
    File storageDir;
    FrameLayout frame_addimage;

    String free_paid;
    TextBean textBean;
    public static final int REQUEST_IMAGE = 100;

    private LayoutInflater layoutInflater;
    private Typeface mDefaultTextTypeface;
    private List<View> addedViewList;
    ScrollView lay_scroll;
    public static RelativeLayout txt_stkr_relP_custom = null;
    private LinearLayout Clickebul;
    private TextView tv_image;
    private TextView tv_text;
    private SeekBar textSizeseek;
    private ImageView tv_bold;
    private ImageView tv_italic;
    private ImageView iv_font;
    private ImageView iv_normal;
    private ImageView iv_color;
    private RelativeLayout rl_textclick;
    private TextView tv_fontstyle;
    private GridView gv_font_choose;
    Typeface typeface;
    private ImageView iv_close;
    private RelativeLayout rl_fontstyle;

    //.//////////////////////
    private RecyclerView imageRecyclerView;
    ArrayList<ImageModel> imageList;
    ArrayList<String> selectedImageList;
    ImageAdapter imageAdapter;
    int[] resImg = {R.drawable.closeicon, R.drawable.arrowdown};
    String[] title = {"Camera", "Folder"};
    String[] projection = {MediaStore.MediaColumns.DATA};
    File image;
    String mCurrentPhotoPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greetings_make_save);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        this.textBean = new TextBean();
        pDialog = new ProgressDialog(Greetings_make_save_Activity.this);
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
        free_paid = intent.getStringExtra("freepaid");

        this.layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        this.mDefaultTextTypeface = Typeface.createFromAsset(getAssets(), "fonts/font1.ttf");
        this.addedViewList = new ArrayList();
        txt_stkr_relP_custom = (RelativeLayout) findViewById(R.id.txt_stkr_relP_custom);


//      img_set = intent.getStringExtra("getimagesave");
//      storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), ".BrandBox/" + img_set);
        specialcategoryrecycler111 = (RecyclerView) findViewById(R.id.specialcategoryrecycler);
        card_set = (ImageView) findViewById(R.id.card_set);
        back_button = (ImageView) findViewById(R.id.back_button);
        download_button = (ImageView) findViewById(R.id.download_button);
        button_click = (Button) findViewById(R.id.button_click);
        frameimage = (FrameLayout) findViewById(R.id.frameimage);
        effect3Drecyclerviw = (RecyclerView) findViewById(R.id.effect3Drecyclerviw);
        effect_set = (RelativeLayout) findViewById(R.id.effect_set);
        opacityBar = (SeekBar) findViewById(R.id.opacity);
        effect_button = (TextView) findViewById(R.id.effect_button);
        //    frame_button = (TextView) findViewById(R.id.frame_button);
        // color_button = (TextView) findViewById(R.id.color_button);
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
        cb_buss = (CheckBox) findViewById(R.id.cb_buss);
        // lay_frames = (LinearLayout) findViewById(R.id.lay_frames);
        llFrame = (LinearLayout) findViewById(R.id.llframe);
        ll_effect = (LinearLayout) findViewById(R.id.effect);
        //  frame = (LinearLayout) findViewById(R.id.frame);
        tv_waterMark = (TextView) findViewById(R.id.tv_waterMark);
        businessname_ad = (TextView) findViewById(R.id.businessname_ad);
        Emailaddress_ad = (TextView) findViewById(R.id.Emailaddress_ad);
        Website_ad = (TextView) findViewById(R.id.Website_ad);
        Address_ad = (TextView) findViewById(R.id.Address_ad);
        MobileNo_ad = (TextView) findViewById(R.id.MobileNo_ad);
        userelogoset = (ImageView) findViewById(R.id.userelogoset);
      /*  view_l = (View) findViewById(R.id.view_l);
        view_r = (View) findViewById(R.id.view_r);*/
        swipeallimage = (SwipeRefreshLayout) findViewById(R.id.swipeallimage);
        ll_add_text = (LinearLayout) findViewById(R.id.ll_add_text);
        ll_add_img = (LinearLayout) findViewById(R.id.ll_add_img);
        ll_text_color = (LinearLayout) findViewById(R.id.ll_text_color);
        ll_font_type = (LinearLayout) findViewById(R.id.ll_font_type);
        add_image = (ImageView) findViewById(R.id.add_image);
        frame_addimage = (FrameLayout) findViewById(R.id.frame_addimage);
        textset = (TextView) findViewById(R.id.textset);
        textframe = (FrameLayout) findViewById(R.id.textframe);
        textdelete = (ImageView) findViewById(R.id.textdelete);
        textborder = (FrameLayout) findViewById(R.id.textborder);
        Clickebul = (LinearLayout) findViewById(R.id.Clickebul);
        tv_image = (TextView) findViewById(R.id.tv_image);
        tv_text = (TextView) findViewById(R.id.tv_text);
        textSizeseek = (SeekBar) findViewById(R.id.textSizeseek);
        tv_bold = (ImageView) findViewById(R.id.tv_bold);
        tv_italic = (ImageView) findViewById(R.id.tv_italic);
        iv_font = (ImageView) findViewById(R.id.iv_font);
        iv_normal = (ImageView) findViewById(R.id.iv_normal);
        iv_color = (ImageView) findViewById(R.id.iv_color);
        rl_textclick = (RelativeLayout) findViewById(R.id.rl_textclick);
        tv_fontstyle = (TextView) findViewById(R.id.tv_fontstyle);
        gv_font_choose = (GridView) findViewById(R.id.gv_font_choose);
        iv_close=(ImageView)findViewById(R.id.iv_close);
        rl_fontstyle=(RelativeLayout)findViewById(R.id.rl_fontstyle);




        iv_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ColorPicker colorPicker = new ColorPicker(Greetings_make_save_Activity.this);
                ArrayList<String> colors = new ArrayList<>();
                colors.add("#82B926");
                colors.add("#a276eb");
                colors.add("#6a3ab2");
                colors.add("#666666");
                colors.add("#FFFF00");
                colors.add("#3C8D2F");
                colors.add("#FA9F00");
                colors.add("#FF0000");


                colorPicker
                        .setDefaultColorButton(Color.parseColor("#f84c44"))
                        .setColors(colors)
                        .setColumns(5)
                        .setRoundColorButton(true)
                        .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                            @Override
                            public void onChooseColor(int position, int color) {

                                businessname_ad.setTextColor(color);

                            }

                            @Override
                            public void onCancel() {

                            }


                        }).show();
            }
        });





        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_fontstyle.setVisibility(View.GONE);
                rl_textclick.setVisibility(View.VISIBLE);

            }
        });


        tv_fontstyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rl_fontstyle.getVisibility() == View.GONE) {
                    rl_fontstyle.setVisibility(View.VISIBLE);
                    rl_textclick.setVisibility(View.GONE);

                } else {
                    rl_fontstyle.setVisibility(View.GONE);
                }
            }
        });

        gv_font_choose.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                setfontType(businessname_ad, position);
            }

            public void setfontType(TextView eText, int position) {
                String fontName = "font1.ttf";
                if (position == total) {
                    fontName = "font" + total + ".ttf";
                } else {
                    fontName = "font" + (position + 1) + ".ttf";
                }

                typeface = Typeface.createFromAsset(getAssets(), fontName);
                eText.setTypeface(typeface);
            }
        });

        TextAdapater1 textAdapater = new TextAdapater1(Greetings_make_save_Activity.this);
        gv_font_choose.setAdapter(textAdapater);









        iv_font.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                businessname_ad.setAllCaps(false);

            }
        });

        iv_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView) findViewById(R.id.businessname_ad)).setTypeface(Typeface.DEFAULT);

            }
        });

        tv_italic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                businessname_ad.setTypeface(businessname_ad.getTypeface(), Typeface.ITALIC);

            }
        });

        tv_bold.hasFocus();
        tv_bold.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    ((TextView) findViewById(R.id.businessname_ad)).setTypeface(Typeface.DEFAULT_BOLD);
                else
                    ((TextView) findViewById(R.id.businessname_ad)).setTypeface(Typeface.DEFAULT);

            }
        });

        tv_bold.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                ((TextView) findViewById(R.id.businessname_ad)).setTypeface(Typeface.DEFAULT_BOLD);
                return false;

            }
        });


        textSizeseek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                textBean.setTextSize(progress / 2);
                businessname_ad.setTextSize((float) textBean.getTextSize());

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

                rl_textclick.setVisibility(View.VISIBLE);

            }
        });


        tv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rl_textclick.setVisibility(View.GONE);


            }
        });


        Glide.with(Greetings_make_save_Activity.this).load(getIntent().getStringExtra("getimagesave"))
                .placeholder(R.drawable.newlogo)
                .into(card_set);

        if (new PrefManager(getApplicationContext()).getBoolen(IConstant.IS_PADE)) {
            tv_waterMark.setVisibility(View.GONE);
        } else {
        }


        opacity();
        Custom_AdapterEffect AdapterEffect1 = new Custom_AdapterEffect(Greetings_make_save_Activity.this, effect_3D, "effect1");
        effect3Drecyclerviw.setAdapter(AdapterEffect1);
        effect3Drecyclerviw.setLayoutManager(new LinearLayoutManager(Greetings_make_save_Activity.this, LinearLayoutManager.HORIZONTAL, false));
        clickall();

    }



    public void clickall() {


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        effect_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ll_effect.getVisibility() == View.GONE) {
                    //    frame.setVisibility(View.GONE);
                    ll_effect.setVisibility(View.VISIBLE);
                } else {
                    ll_effect.setVisibility(View.GONE);
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

        ll_add_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AddTextDialog();
                // openTextActivity();
                startActivityForResult(new Intent(Greetings_make_save_Activity.this, TextActivity_1.class), Constans.REQUEST_CODE_ADD_TEXT);

            }
        });

        ll_text_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialogBuilder
                        .with(Greetings_make_save_Activity.this)
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
                                textset.setTextColor(Color.parseColor("#" + Integer.toHexString(selectedColor)));
                                businessname_ad.setTextColor(Color.parseColor("#" + Integer.toHexString(selectedColor)));
                                Emailaddress_ad.setTextColor(Color.parseColor("#" + Integer.toHexString(selectedColor)));
                                Website_ad.setTextColor(Color.parseColor("#" + Integer.toHexString(selectedColor)));
                                MobileNo_ad.setTextColor(Color.parseColor("#" + Integer.toHexString(selectedColor)));
                                Address_ad.setTextColor(Color.parseColor("#" + Integer.toHexString(selectedColor)));
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

                showImagePickerOptions();

//                Dexter.withActivity(Greetings_make_save_Activity.this)
//                        .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                        .withListener(new MultiplePermissionsListener() {
//                            @Override
//                            public void onPermissionsChecked(MultiplePermissionsReport report) {
//                                if (report.areAllPermissionsGranted()) {
//                                    showImagePickerOptions();
//                                }
//
//                                if (report.isAnyPermissionPermanentlyDenied()) {
//                                    showSettingsDialog();
//                                }
//                            }
//
//                            @Override
//                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
//                                token.continuePermissionRequest();
//                            }
//                        }).check();
            }
        });
        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(Greetings_make_save_Activity.this)
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
        textframe.setOnTouchListener(new MultiTouchListener());
        frame_addimage.setOnTouchListener(new MultiTouchListener());
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

                if (free_paid.equals("0")) {
                    if (new PrefManager(getApplicationContext()).getBoolen(IConstant.IS_PADE)) {
                        if (new PrefManager(getApplicationContext()).isstatus()) {
                            HelperBoxclear();
                            //new Imagesaved().execute(SaveBitmap());
                            finalBitmapToSave = scaleBitmap(viewToBitmap(frameimage),2000,2000);
                            saveBitmap(true);
                        } else {
                            Toast.makeText(Greetings_make_save_Activity.this, "You are not authorized", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Dialog();
                    }
                } else {
                    if (new PrefManager(getApplicationContext()).isstatus()) {
                        HelperBoxclear();
                        //new Imagesaved().execute(SaveBitmap());
                        finalBitmapToSave = scaleBitmap(viewToBitmap(frameimage),2000,2000);
                        saveBitmap(true);
                    } else {
                        Toast.makeText(Greetings_make_save_Activity.this, "You are not authorized", Toast.LENGTH_SHORT).show();
                    }
                }
//                }

            }
        });
    }

    public void Dialog() {
        dialog = new Dialog(Greetings_make_save_Activity.this);
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


                if (PreferencesHandler.getStringPreference(Greetings_make_save_Activity.this, Constans.INDIA).equals("91")) {
                    Intent intent = new Intent(Greetings_make_save_Activity.this, PaymentGatewayActivity.class);
                    startActivity(intent);

                } else {

                    Intent intent = new Intent(Greetings_make_save_Activity.this, PremiumActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        dialog.show();
    }

    private void loadProfile(String url) {
        Glide.with(this).load(url)
                .into(add_image);

        add_image.setClickable(false);
        add_image.setColorFilter(ContextCompat.getColor(this, android.R.color.transparent));
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
        Intent intent = new Intent(Greetings_make_save_Activity.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, REQUEST_IMAGE_CAPTURE);

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
        Intent intent = new Intent(Greetings_make_save_Activity.this, ImagePickerActivity.class);
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

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Greetings_make_save_Activity.this);
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


    private void AddTextDialog() {
        dialog = new Dialog(Greetings_make_save_Activity.this);
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

               /* for (int i = 0; i < 10; i++) {
                    textset.setText(addtext);
                }
*/
                textset.setText(addtext);

                textframe.setVisibility(View.VISIBLE);
                textdelete.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });

        textframe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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
        dialog = new Dialog(Greetings_make_save_Activity.this);
        dialog.setContentView(R.layout.dialog_font_type);
        dialog.setCancelable(false);
        RecyclerView rv_font_type = dialog.findViewById(R.id.rv_font_type);
        ImageView close = dialog.findViewById(R.id.close);

        Edit_TextAdapater edit_textAdapater = new Edit_TextAdapater(Greetings_make_save_Activity.this, businessname_ad);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Greetings_make_save_Activity.this);
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

    private void retraivedata() {

        callGetBussiness();

        businessname = new PrefManager(getApplicationContext()).getString(IConstant.BUSINESS_NAME);
        Emailaddress = new PrefManager(getApplicationContext()).getString(IConstant.BUSINESS_EMAIL);
        Website = new PrefManager(getApplicationContext()).getString(IConstant.WEBSITE);
        Address = new PrefManager(getApplicationContext()).getString(IConstant.ADDRESS);
        MobileNo = new PrefManager(getApplicationContext()).getString(IConstant.MOBILE_NO);

        businessname_ad.setText(businessname);
        Emailaddress_ad.setText(Emailaddress);
        Website_ad.setText(Website);
        Address_ad.setText(Address);
        MobileNo_ad.setText(MobileNo);

        businessname_ad.setOnTouchListener(new MultiTouchListener());
        Emailaddress_ad.setOnTouchListener(new MultiTouchListener());
        Website_ad.setOnTouchListener(new MultiTouchListener());
        Address_ad.setOnTouchListener(new MultiTouchListener());
        MobileNo_ad.setOnTouchListener(new MultiTouchListener());

        if (!logo.isEmpty()) {
            Glide.with(this).load(logo)
                    .placeholder(R.drawable.newlogo)
                    .into(userelogoset);

            cb_logo.setChecked(true);
        } else {
            cb_logo.setChecked(false);
        }

        if (MobileNo != null) {
            // imageView2.setVisibility(View.VISIBLE);
            MobileNo_ad.setVisibility(View.VISIBLE);
            MobileNo_ad.setText(MobileNo);
            cb_mobile.setChecked(true);
        } else {
            cb_mobile.setChecked(false);
            //imageView2.setVisibility(View.GONE);
            MobileNo_ad.setVisibility(View.GONE);
        }

        if (Address != null) {
            // imageView5.setVisibility(View.VISIBLE);
            Address_ad.setVisibility(View.VISIBLE);
            Address_ad.setText(Address);
            cb_address.setChecked(true);
        } else {
            cb_address.setChecked(false);
            //imageView5.setVisibility(View.GONE);
            Address_ad.setVisibility(View.GONE);
        }
        if (Emailaddress != null) {
            //imageView3.setVisibility(View.VISIBLE);
            Emailaddress_ad.setVisibility(View.VISIBLE);
            Emailaddress_ad.setText(Emailaddress);
            cb_email.setChecked(true);
        } else {
            cb_email.setChecked(false);
            Emailaddress_ad.setVisibility(View.GONE);
        }
        if (Website != null) {
            //imageView4.setVisibility(View.VISIBLE);
            Website_ad.setVisibility(View.VISIBLE);
            Website_ad.setText(Website);
            cb_website.setChecked(true);
        } else {
            cb_website.setChecked(false);
            Website_ad.setVisibility(View.GONE);
        }

        if (businessname != null) {
            //imageView4.setVisibility(View.VISIBLE);
            businessname_ad.setVisibility(View.VISIBLE);
            businessname_ad.setText(businessname);
            cb_buss.setChecked(true);
        } else {
            cb_buss.setChecked(false);
            businessname_ad.setVisibility(View.GONE);
        }

        cb_buss.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //  imageView2.setVisibility(View.VISIBLE);
                    businessname_ad.setVisibility(View.VISIBLE);
                } else {
                    // imageView2.setVisibility(View.GONE);
                    businessname_ad.setVisibility(View.GONE);
                }
            }
        });

        cb_mobile.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //  imageView2.setVisibility(View.VISIBLE);
                    MobileNo_ad.setVisibility(View.VISIBLE);
                } else {
                    // imageView2.setVisibility(View.GONE);
                    MobileNo_ad.setVisibility(View.GONE);
                }
            }
        });

        cb_address.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //imageView5.setVisibility(View.VISIBLE);
                    Address_ad.setVisibility(View.VISIBLE);
                } else {
                    // imageView5.setVisibility(View.GONE);
                    Address_ad.setVisibility(View.GONE);
                }
            }
        });

        cb_email.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // imageView3.setVisibility(View.VISIBLE);
                    Emailaddress_ad.setVisibility(View.VISIBLE);
                } else {
                    // imageView3.setVisibility(View.GONE);
                    Emailaddress_ad.setVisibility(View.GONE);
                }
            }
        });

        cb_website.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //  imageView4.setVisibility(View.VISIBLE);
                    Website_ad.setVisibility(View.VISIBLE);
                } else {
                    // imageView4.setVisibility(View.GONE);
                    Website_ad.setVisibility(View.GONE);
                }
            }
        });

    }

    private void callGetBussiness() {
        pDialog = new ProgressDialog(Greetings_make_save_Activity.this);
        pDialog.setTitle("Loading");
        pDialog.setMessage("Please wait...");
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setCancelable(false);
        pDialog.show();

        final ApiInterfase apiInterface = RetrofitClient.getApiClient().create(ApiInterfase.class);
        apiInterface.get_Business("business", new PrefManager(Greetings_make_save_Activity.this).getString(IConstant.IS_R_ID)).enqueue(new Callback<Business_Get_model>() {
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
                                Glide.with(Greetings_make_save_Activity.this).load(logo).into(userelogoset);
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

    public class Imagesaved extends AsyncTask<Bitmap, Void, String> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Greetings_make_save_Activity.this);
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

            Toast.makeText(Greetings_make_save_Activity.this, "Image saved sucessfully", Toast.LENGTH_SHORT).show();

            if (new PrefManager(getApplicationContext()).getBoolen(IConstant.IS_PADE)) {
                Intent intent = new Intent(Greetings_make_save_Activity.this, SaveImageView.class);
                intent.putExtra("getimagesave", imageFileName);
                startActivity(intent);
            } else {
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    Intent intent = new Intent(Greetings_make_save_Activity.this, SaveImageView.class);
                    intent.putExtra("getimagesave", imageFileName);
                    startActivity(intent);
                }
            }
        }
    }

    public void ShowFullScreen() {
        //interstitial FullScreenAd
        AdRequest adRequestfull = new AdRequest.Builder().build();
        interstitialAd = new InterstitialAd(Greetings_make_save_Activity.this);
        interstitialAd.setAdUnitId(getString(R.string.ad_id_interstitial));
        interstitialAd.setAdListener(new AdListener() {

            public void onAdLoaded() {
                super.onAdLoaded();
            }

            public void onAdClosed() {
                super.onAdClosed();
                Intent intent = new Intent(Greetings_make_save_Activity.this, SaveImageView.class);
                intent.putExtra("getimagesave", imageFileName);
                startActivity(intent);

            }

            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);

            }
        });

        interstitialAd.loadAd(adRequestfull);

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
        public Custom_AdapterEffect.effect_Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.effectitems2, parent, false);
            return new Custom_AdapterEffect.effect_Myholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Custom_AdapterEffect.effect_Myholder dataHolder, int i) {
            Glide.with(activity).load(effect1[i]).into(dataHolder.imageView1);

            dataHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (i == 0) {
                        Greetings_make_save_Activity.seteffect.setBackgroundResource(0);
                    } else {
                        Greetings_make_save_Activity.seteffect.setBackgroundResource(effect1[i]);
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
        Greetings_make_save_Activity activity1;
        String name;
        TextView font_text;

        public Edit_TextAdapater(Greetings_make_save_Activity activity, TextView textView) {
            activity1 = activity;
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
                    Typeface typeface = Typeface.createFromAsset(activity1.getAssets(), fontName);
                    businessname_ad.setTypeface(typeface);
                    Website_ad.setTypeface(typeface);
                    MobileNo_ad.setTypeface(typeface);
                    Address_ad.setTypeface(typeface);
                    Emailaddress_ad.setTypeface(typeface);
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Greetings_make_save_Activity.this.finish();
    }


    ////////////////////////////// NEW CODE //////////////////////////////////////// NEW CODE ///////////////////////////////////// NEW CODE ////////////////////////


    public void init(){
        imageRecyclerView =(RecyclerView) findViewById(R.id.recycler_view);
        selectedImageList = new ArrayList<>();
        imageList = new ArrayList<>();
    }

    public void setImageList(){
        imageRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));
        imageAdapter = new  ImageAdapter(getApplicationContext(), imageList);
        imageRecyclerView.setAdapter(imageAdapter);

        imageAdapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (position == 0) {
                    takePicture();
                } else if (position == 1) {
                    getPickImageIntent();
                } else {
                    try {
                        if (!imageList.get(position).isSelected()) {
                            selectImage(position);
                        } else {
                            unSelectImage(position);
                        }
                    } catch (ArrayIndexOutOfBoundsException ed) {
                        ed.printStackTrace();
                    }
                }
            }
        });
        setImagePickerList();
    }

    public void setImagePickerList(){
        for (int i = 0; i < resImg.length; i++) {
            ImageModel imageModel = new ImageModel();
            imageModel.setResImg(resImg[i]);
            imageModel.setTitle(title[i]);
            imageList.add(i, imageModel);
        }
        imageAdapter.notifyDataSetChanged();
    }

    public void getAllImages(){
        imageList.clear();
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null,null, null);
        while (cursor.moveToNext()) {
            String absolutePathOfImage = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
            ImageModel ImageModel = new ImageModel();
            ImageModel.setImage(absolutePathOfImage);
            imageList.add(ImageModel);
        }
        cursor.close();
    }

    public void takePicture(){
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Continue only if the File was successfully created;
        File photoFile = createImageFile();
        if (photoFile != null) {
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
            startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
        }
    }


    public void getPickImageIntent(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent, PICK_IMAGES);
    }

    // Add image in SelectedArrayList
    public void selectImage(int position) {
        // Check before add new item in ArrayList;
        if (!selectedImageList.contains(imageList.get(position).getImage())) {
            imageList.get(position).setSelected(true);
            selectedImageList.add(0, imageList.get(position).getImage());
            //selectedImageAdapter.notifyDataSetChanged();
            imageAdapter.notifyDataSetChanged();
        }
    }

    // Remove image from selectedImageList
    public void unSelectImage(int position) {
        for (int i = 0; i < selectedImageList.size(); i++) {
            if (imageList.get(position).getImage() != null) {
                if (selectedImageList.get(i).equals(imageList.get(position).getImage())) {
                    imageList.get(position).setSelected(false);
                    selectedImageList.remove(i);
                    //selectedImageAdapter.notifyDataSetChanged();
                    imageAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    public File createImageFile() {
        // Create an image file name
        String dateTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "IMG_" + dateTime + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        try {
            image = File.createTempFile(imageFileName, ".jpg", storageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }



    // Get image file path
    public void getImageFilePath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, projection, null,    null, null);
        if (cursor != null) {
            while  (cursor.moveToNext()) {
                String absolutePathOfImage = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
                if (absolutePathOfImage != null) {
                    checkImage(absolutePathOfImage);
                } else {
                    checkImage(String.valueOf(uri));
                }
            }
        }
    }

    public void checkImage(String filePath) {
        // Check before adding a new image to ArrayList to avoid duplicate images
        if (!selectedImageList.contains(filePath)) {
            for (int pos = 0; pos < imageList.size(); pos++) {
                if (imageList.get(pos).getImage() != null) {
                    if (imageList.get(pos).getImage().equalsIgnoreCase(filePath)) {
                        imageList.remove(pos);
                    }
                }
            }
            addImage(filePath);
        }
    }

    // add image in selectedImageList and imageList
    public void addImage(String filePath) {
        ImageModel imageModel = new ImageModel();
        imageModel.setImage(filePath);
        imageModel.setSelected(true);
        imageList.add(2, imageModel);
        selectedImageList.add(0, filePath);
      //  selectedImageAdapter.notifyDataSetChanged();
        imageAdapter.notifyDataSetChanged();
    }

    public boolean isStoragePermissionGranted() {
        int ACCESS_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if ((ACCESS_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION);
            return false;
        }
        return true;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            init();
            getAllImages();
            setImageList();
           // setSelectedImageList();
        }
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
                            Intent intent = new Intent(Greetings_make_save_Activity.this, SaveImageView.class);
                            intent.putExtra("getimagesave", imageFileName);
                            startActivity(intent);
                        } else {
//                            if (interstitialAd.isLoaded()) {
//                                interstitialAd.show();
//                            } else {
                                Intent intent = new Intent(Greetings_make_save_Activity.this, SaveImageView.class);
                                intent.putExtra("getimagesave", imageFileName);
                                startActivity(intent);
                            }
                        }

//                    }

                } catch (Exception unused) {
                }
            }
        }).start();
    }


}