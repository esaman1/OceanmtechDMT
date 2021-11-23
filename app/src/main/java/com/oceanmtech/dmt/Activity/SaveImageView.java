  package com.oceanmtech.dmt.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

  public class SaveImageView extends AppCompatActivity {


    String setimageview;
    private ImageView ViewFullimage;
    private ImageView view_icon_share;
    private ImageView view_icon_download;
    private ImageView viewclose;
    private ViewPager viewimageviewpager;
    private ImageView view_icon_delete;
    private CardView view_w_share;
    private ImageView view_f_share;
    private ImageView view_i_share;
    File storageDir;
    private FrameLayout ad_Layout;
    AdView adView;
      private String filename1;
      Boolean checkMemory;
      Bitmap finalBitmapToSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_image_view);


        Intent intent = getIntent();
        setimageview = intent.getStringExtra("getimagesave");

        ViewFullimage = (ImageView) findViewById(R.id.ViewFullimage);
        view_icon_share = (ImageView) findViewById(R.id.view_icon_share);
        viewclose = (ImageView) findViewById(R.id.viewclose);
        //viewimageviewpager = (ViewPager) findViewById(R.id.viewimageviewpager);

        view_w_share = (CardView) findViewById(R.id.view_w_share);
        view_f_share = (ImageView) findViewById(R.id.view_f_share);
        view_i_share = (ImageView) findViewById(R.id.view_i_share);
        ad_Layout=(FrameLayout)findViewById(R.id.ad_Layout);

        Click();
        loadBanner();




        if (new PrefManager(SaveImageView.this).getBoolen(IConstant.IS_PADE)) {
            ad_Layout.setVisibility(View.GONE);
        } else {

        }
    }



    private void loadBanner() {
        adView = new AdView(SaveImageView.this);
        adView.setAdUnitId(getString(R.string.ad_id_banner));
        ad_Layout.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();

        com.google.android.gms.ads.AdSize adSize = getAdSize();
        adView.setAdSize(adSize);
        adView.loadAd(adRequest);
    }

    private com.google.android.gms.ads.AdSize getAdSize() {
        Display display = SaveImageView.this.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);
        return AdSize.getCurrentOrientationBannerAdSizeWithWidth(SaveImageView.this, adWidth);
    }


    private void Click() {
        storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "OceanmtechDMT/" + setimageview);
        Glide.with(SaveImageView.this)
                .load(storageDir)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ViewFullimage);

        view_w_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap unused = finalBitmapToSave = scaleBitmap(viewToBitmap(ViewFullimage), 2000, 2000);
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

        view_f_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Bitmap adv = BitmapFactory.decodeResource(getResources(), R.drawable.brandpostlogofinal);
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");

                share.putExtra(Intent.EXTRA_STREAM,
                        Uri.parse(storageDir.getAbsolutePath()));
                if (isPackageInstalled("com.facebook.katana", SaveImageView.this)) {
                    share.setPackage("com.facebook.katana");
                    startActivity(Intent.createChooser(share, "Share Image"));

                } else {

                    Toast.makeText(getApplicationContext(), "Please Install Facebook", Toast.LENGTH_LONG).show();
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

        view_i_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Bitmap adv = BitmapFactory.decodeResource(getResources(), R.drawable.brandpostlogofinal);
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");

                share.putExtra(Intent.EXTRA_STREAM,
                        Uri.parse(storageDir.getAbsolutePath()));
                if (isPackageInstalled("com.instagram.android", SaveImageView.this)) {
                    share.setPackage("com.instagram.android");
                    startActivity(Intent.createChooser(share, "Share Image"));

                } else {

                    Toast.makeText(getApplicationContext(), "Please Install Instagram", Toast.LENGTH_LONG).show();
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

        view_icon_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        viewclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

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
                      shareIntent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(SaveImageView.this, getApplicationContext().getPackageName() + ".fileProvider", photoFile));
                      startActivity(Intent.createChooser(shareIntent, "Share image using"));
                      return;
                  }
                  AlertDialog create = new AlertDialog.Builder(SaveImageView.this)
                          .setMessage("Memory Error")
                          .setPositiveButton("OK", new C03041())
                          .create();
                  create.show();
              }
          });
      }

  }