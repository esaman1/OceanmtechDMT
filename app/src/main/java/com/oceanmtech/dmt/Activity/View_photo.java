package com.oceanmtech.dmt.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.oceanmtech.dmt.Fragment.Create_Quotes_Fragment;
import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class View_photo extends AppCompatActivity {
    private static final String TAG = "View_Photos";
    String image = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.shutterstock.com%2Fimage-photo%2Fbeautiful-water-drop-on-dandelion-flower-789676552&psig=AOvVaw3uW2U7N1s09lzF8Z7EoBBF&ust=1615631958567000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCICpwprIqu8CFQAAAAAdAAAAABAV";
    String imageUrl;
    ImageView FullImage;
    private ImageView icon_share;
    private ImageView icon_download;
    private ImageView close;
    private ArrayList<String> data;
    int pos2 = 1, newpos;
    private ViewPager imageviewpager;
    private ImageView icon_delete;
    FrameLayout adContainerView;
    Dialog dialog;
    private FrameLayout ad_Layout;
    AdView adView;
    private CardView w_share;
    private ImageView f_share;
    private ImageView i_share;
    String imageName;
    File file;
    public static Bitmap bitmap;

    public enum TransformType {
        FLOW,
        DEPTH,
        ZOOM,
        SLIDE_OVER,
        SCALE_IN_OUT_TRANSFORMER,
        NORMAL,
        SCALING_EFFECT

    }

    private static final float MIN_SCALE_DEPTH = 0.75f;
    private static final float MIN_SCALE_ZOOM = 0.85f;
    private static final float MIN_ALPHA_ZOOM = 0.5f;
    private static final float SCALE_FACTOR_SLIDE = 0.85f;
    private static final float MIN_ALPHA_SLIDE = 0.35f;

    private float alpha;
    private float scale;
    private float translationX;
    private TransformType mTransformType = TransformType.SCALING_EFFECT;
    private String filename1;
    Boolean checkMemory;
    Bitmap finalBitmapToSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photo);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);


        Intent intent = getIntent();
        image = intent.getStringExtra("image");

        find();
        getAllImage();
        init();
        initView();
        addlistener();
        loadBanner();
        setPagerTransformation();

        if (new PrefManager(View_photo.this).getBoolen(IConstant.IS_PADE)) {
            ad_Layout.setVisibility(View.GONE);
        } else {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void find() {

        FullImage = (ImageView) findViewById(R.id.Fullimage);
        icon_share = (ImageView) findViewById(R.id.icon_share);
        icon_download = (ImageView) findViewById(R.id.icon_download);
        close = (ImageView) findViewById(R.id.close);
        imageviewpager = (ViewPager) findViewById(R.id.imageviewpager);
        icon_delete = (ImageView) findViewById(R.id.icon_delete);

        w_share = (CardView) findViewById(R.id.w_share);
        f_share = (ImageView) findViewById(R.id.f_share);
        i_share = (ImageView) findViewById(R.id.i_share);

        ad_Layout = (FrameLayout) findViewById(R.id.ad_Layout);

    }

    public void imageDownload(){
        bitmap =((BitmapDrawable)FullImage.getDrawable()).getBitmap();
        String time=new SimpleDateFormat("HHmmss", Locale.getDefault()).format(System.currentTimeMillis());
        File path = Environment.getExternalStorageDirectory();
        File dir = new File(path+"/OceanmtechDMT");
        dir.mkdirs();
        imageName="OceanmtechDMT"+time+".png";
        file=new File(dir,imageName);
        OutputStream out;
        try {
            out=new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,out);
            out.flush();
            out.close();
            Toast.makeText(View_photo.this, "Saved...", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        icon_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });

        w_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap unused = finalBitmapToSave = scaleBitmap(viewToBitmap(imageviewpager), 2000, 2000);
                saveBitmap(false);

            }

            private boolean isPackageInstalled(String packagename, Context context) {
                PackageManager pm = context.getPackageManager();
                try {
                    pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
                    return true;
                } catch (PackageManager.NameNotFoundException e) {
                    return false;
                }
            }
        });

        f_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                icon_share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        try {

                            String bitmapPath = MediaStore.Images.Media.insertImage(getContentResolver(), image, "title", null);
                            Uri bitmapUri = Uri.parse(bitmapPath);

                            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra(Intent.EXTRA_STREAM, bitmapUri);
                            intent.putExtra(Intent.EXTRA_TEXT, "Make Free HD Festival Photo Frame on Google Play!\n" +
                                    "\n" + "https://play.google.com/store/apps/details?id=" + getPackageName());
                            intent.setType("image/*");
                            startActivity(Intent.createChooser(intent, "Share Via"));

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });


            }

            private boolean isPackageInstalled(String packagename, Context context) {
                PackageManager pm = context.getPackageManager();
                try {
                    pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
                    return true;
                } catch (PackageManager.NameNotFoundException e) {
                    return false;
                }
            }
        });

        i_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = View_photo.this.getPackageManager()
                        .getLaunchIntentForPackage("com.instagram.android");
                if (intent != null) {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.setPackage("com.instagram.android");
//                    8780222108
                    try {
                        shareIntent.putExtra(Intent.EXTRA_STREAM,
                        Uri.parse(data.get(imageviewpager.getCurrentItem())));
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    shareIntent.setType("*/*");

                    startActivity(shareIntent);
                } else {
                    // bring user to the market to download the app.
                    // or let them choose an app?
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("market://details?id="
                            + "com.instagram.android"));
                    startActivity(intent);
                }


            }

            private boolean isPackageInstalled(String packagename, Context context) {
                PackageManager pm = context.getPackageManager();
                try {
                    pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
                    return true;
                } catch (PackageManager.NameNotFoundException e) {
                    return false;
                }
            }
        });


        icon_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Deletedialog();
            }
        });
        icon_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                File imgFile = new File(image);
                if (imgFile.exists()) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    createDirectoryAndSaveFile(myBitmap, "OceanmtechDMT_0" + 1);
                }
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                finish();
            }
        });

    }

    private void Deletedialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete");
        builder.setMessage("Are you sure you want to Delete this Photo?");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                File file = new File(data.get(imageviewpager.getCurrentItem()));
                file.delete();
                //data.get(imageviewpager.getCurrentItem());
                imageviewpager.setAdapter(mAdapter);

                if (pos2 == 0) {
                    pos2 = 1;
                }
                imageviewpager.setCurrentItem(pos2);
                mAdapter.notifyDataSetChanged();
                dialog.dismiss();
               /* Intent intent = new Intent(View_photo.this, Save_Photo_Activity.class);
                startActivity(intent);*/
                onBackPressed();
                finish();
                MediaScannerConnection.scanFile(View_photo.this,
                        new String[]{file.getPath()},
                        new String[]{"image/jpeg"}, null);
                dialog.dismiss();

            }
        });
        builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void createDirectoryAndSaveFile(Bitmap imageToSave, String fileName) {

        File pictureFileDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Photo Edit");

        if (!pictureFileDir.exists()) {
            File wallpaperDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Photo Edit");
            wallpaperDirectory.mkdirs();
        }

        File file = new File(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Photo Edit"), fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            Toast.makeText(this, "Successfully Downloaded !", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        pos2 = 1 + getIntent().getExtras().getInt("imagepos");
        imageviewpager.setAdapter(mAdapter);
        imageviewpager.setCurrentItem(pos2 - 1);


    }

    private void getAllImage() {
        data = new ArrayList<String>();
        for (int i = 0; i < Create_Quotes_Fragment.fullimage.size(); i++) {
            data.add(Create_Quotes_Fragment.fullimage.get(i));
        }
    }

    private void addlistener() {
        imageviewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            public void onPageSelected(int pos) {
                pos2 = pos + 1;
                newpos = pos;
                imageUrl = data.get(newpos);

            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            public void onPageScrollStateChanged(int arg0) {

            }

        });

    }

    // Pager Adapter //
    PagerAdapter mAdapter = new PagerAdapter() {
        private ImageView image;

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(View arg0, int position, Object object) {
            View view = (View) object;
            ((ViewPager) arg0).removeView(view);
            view = null;
        }

        @Override
        public Object instantiateItem(View arg0, int position) {

            View view = LayoutInflater.from(View_photo.this)
                    .inflate(R.layout.imagesaveviewpager_item, null);

            view.setTag(data.get(position));
            ((ViewPager) arg0).addView(view);

            image = (ImageView) view
                    .findViewById(R.id.image);

            image.setAdjustViewBounds(true);
            image.setScaleType(ImageView.ScaleType.FIT_XY);

            Glide.with(View_photo.this)
                    .load(data.get(position))
                    .into(image);

            return view;
        }
    };



    private void setPagerTransformation() {
        imageviewpager.setPageTransformer(false, new ViewPager.PageTransformer() {
            public void transformPage(View page, float position) {
                int currentIndex = imageviewpager.getCurrentItem() + 1;
                switch (mTransformType) {
                    case FLOW:
                        page.setRotationY(position * -30f);
                        return;

                    case NORMAL:
                        float transformer = Math.abs(Math.abs(position) - 1);
                        page.setAlpha(transformer);
                        return;

                    case SCALING_EFFECT:
                        float normalizedposition = Math.abs(Math.abs(position) - 1);
                        page.setScaleX(normalizedposition / 2 + 0.5f);
                        page.setScaleY(normalizedposition / 2 + 0.5f);
                        return;

                    case SCALE_IN_OUT_TRANSFORMER:
                        page.setPivotX(position < 0 ? 0 : page.getWidth());
                        page.setPivotY(page.getHeight() / 2f);
                        float scale = position < 0 ? 1f + position : 1f - position;
                        page.setScaleX(scale);
                        page.setScaleY(scale);
                        return;

                    case SLIDE_OVER:
                        if (position < 0 && position > -1) {
                            // this is the page to the left
                            scale = Math.abs(Math.abs(position) - 1) * (1.0f - SCALE_FACTOR_SLIDE) + SCALE_FACTOR_SLIDE;
                            alpha = Math.max(MIN_ALPHA_SLIDE, 1 - Math.abs(position));
                            int pageWidth = page.getWidth();
                            float translateValue = position * -pageWidth;
                            if (translateValue > -pageWidth) {
                                translationX = translateValue;
                            } else {
                                translationX = 0;
                            }
                        } else {
                            alpha = 1;
                            scale = 1;
                            translationX = 0;
                        }
                        break;

                    case DEPTH:
                        if (position > 0 && position < 1) {
                            // moving to the right
                            alpha = (1 - position);
                            scale = MIN_SCALE_DEPTH + (1 - MIN_SCALE_DEPTH) * (1 - Math.abs(position));
                            translationX = (page.getWidth() * -position);
                        } else {
                            // use default for all other cases
                            alpha = 1;
                            scale = 1;
                            translationX = 0;
                        }
                        break;

                    case ZOOM:
                        if (position >= -1 && position <= 1) {
                            scale = Math.max(MIN_SCALE_ZOOM, 1 - Math.abs(position));
                            alpha = MIN_ALPHA_ZOOM +
                                    (scale - MIN_SCALE_ZOOM) / (1 - MIN_SCALE_ZOOM) * (1 - MIN_ALPHA_ZOOM);
                            float vMargin = page.getHeight() * (1 - scale) / 2;
                            float hMargin = page.getWidth() * (1 - scale) / 2;
                            if (position < 0) {
                                translationX = (hMargin - vMargin / 2);
                            } else {
                                translationX = (-hMargin + vMargin / 2);
                            }
                        } else {
                            alpha = 1;
                            scale = 1;
                            translationX = 0;
                        }
                        break;

                    default:
                        return;
                }

                page.setAlpha(alpha);
                page.setTranslationX(translationX);
                page.setScaleX(scale);
                page.setScaleY(scale);
            }

        });
    }

    private void loadBanner() {
        adView = new AdView(View_photo.this);
        adView.setAdUnitId(getString(R.string.ad_id_banner));
        ad_Layout.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();

        AdSize adSize = getAdSize();
        adView.setAdSize(adSize);
        adView.loadAd(adRequest);
    }

    private AdSize getAdSize() {
        Display display = View_photo.this.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);
        return AdSize.getCurrentOrientationBannerAdSizeWithWidth(View_photo.this, adWidth);
    }




    public static Bitmap scaleBitmap(Bitmap bitmap, int wantedWidth, int wantedHeight) {
        Bitmap output = Bitmap.createBitmap(wantedWidth, wantedHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Matrix m = new Matrix();
        m.setScale((float) wantedWidth / bitmap.getWidth(), (float) wantedHeight / bitmap.getHeight());
        canvas.drawBitmap(bitmap, m, new Paint());
        return output;
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

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait for Preview");
        progressDialog.setCancelable(false);
        progressDialog.show();
        new Thread(new Runnable() {
            public void run() {
                String str;
                try {
                    // File file =new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + getString(R.string.directory));

                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/"+"OceanmtechDMT");

                    // File file = new File(Environment.getExternalStorageDirectory().toString() + getString(R.string.directory));
                    if (!file.exists()) {
                        if (!file.mkdirs()) {
//                            Toast.makeText(getApplicationContext(), "No Dir. found", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

                    str = String.valueOf(new Date().getTime())+".demo.png";
                    filename1 = file.getPath() + File.separator + str;
                    File file2 = new File(filename1);
                    try {
                        if (!file2.exists()) {
                            file2.createNewFile();
                        }

                        FileOutputStream fileOutputStream = new FileOutputStream(file2);
                        if (z) {

                            checkMemory = finalBitmapToSave.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                        } else {

                            Bitmap createBitmap = Bitmap.createBitmap(finalBitmapToSave.getWidth(), finalBitmapToSave.getHeight(), finalBitmapToSave.getConfig());
                            Canvas canvas = new Canvas(createBitmap);
                            canvas.drawColor(-1);
                            canvas.drawBitmap(finalBitmapToSave, 0.0f, 0.0f, (Paint) null);
                            checkMemory = createBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                            createBitmap.recycle();
                        }

                        fileOutputStream.flush();
                        fileOutputStream.close();
                        //isUpdated = true;
                        sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file2)));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Thread.sleep(1000);
                    progressDialog.dismiss();
                } catch (Exception unused) {
                }
            }

        }).start();
        progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

            //             renamed from: com.regalguide.postermaker.main.PosterActivity$35$C03041
            class C03041 implements DialogInterface.OnClickListener {
                C03041() {
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }

            public void onDismiss(DialogInterface dialogInterface) {
                if (checkMemory) {
                    Context applicationContext = getApplicationContext();


                    final Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("image/jpeg");
                    final File photoFile = new File(filename1);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(View_photo.this, getApplicationContext().getPackageName() + ".fileProvider", photoFile));
                    startActivity(Intent.createChooser(shareIntent, "Share image using"));
//                    startActivity(new Intent(getApplicationContext(), ThemeActivity.class)
//                            .putExtra("path", filename));
                    //Toast.makeText(applicationContext, "Successful Download Image", Toast.LENGTH_SHORT).show();
                    return;
                }
                androidx.appcompat.app.AlertDialog create = new androidx.appcompat.app.AlertDialog.Builder(View_photo.this)
                        .setMessage("Memory Error")
                        .setPositiveButton("OK", new C03041())
                        .create();
                create.show();
            }
        });
    }

}