package com.oceanmtech.dmt.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.R;
import com.oceanmtech.dmt.api.ApiInterfase;
import com.oceanmtech.dmt.Model.GET_Greetings_CategoryData;
import com.oceanmtech.dmt.api.RetrofitClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Select_Greetings_Activity extends AppCompatActivity {

    private static final String TAG = "data-->";
    String select_greetings;
    RecyclerView rv_select_greetings;
    GridLayoutManager rv_select_greetings_gridLayoutManager;
    Select_Greetings_Adapter select_greetings_adapter;
    ImageView card_set, back_button;
    CardView next_btn;

    GET_Greetings_CategoryData get_greetings_category;
    int position_getid;
    public static Uri imageInternalUri;
    ProgressDialog progressDialog;

    String imageFileName;
    public static FrameLayout frameimage;

    String checkData;
    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__greetings);

        Intent intent = getIntent();
        select_greetings = intent.getStringExtra("cat");

        card_set = (ImageView) findViewById(R.id.card_set);
        frameimage = (FrameLayout) findViewById(R.id.frameimage);
        next_btn = (CardView) findViewById(R.id.next_btn);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait..");

        rv_select_greetings = (RecyclerView) findViewById(R.id.rv_select_greetings);
        rv_select_greetings_gridLayoutManager = new GridLayoutManager(Select_Greetings_Activity.this, 3, RecyclerView.VERTICAL, false);
        rv_select_greetings.setLayoutManager(rv_select_greetings_gridLayoutManager);

        back_button = (ImageView) findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        selectgreetingscat();
        ShowFullScreen();
    }

    public void selectgreetingscat() {
        progressDialog.show();
        ApiInterfase apiInterface = RetrofitClient.getApiClient().create(ApiInterfase.class);
        apiInterface.getgreetingscategorydata("greetings_category_data", select_greetings).enqueue(new Callback<GET_Greetings_CategoryData>() {
            @Override
            public void onResponse(Call<GET_Greetings_CategoryData> call, Response<GET_Greetings_CategoryData> response) {
                get_greetings_category = response.body();
                if (get_greetings_category != null) {
                    if (get_greetings_category.getStatus().equals("success")) {
                        progressDialog.dismiss();
                        Glide.with(Select_Greetings_Activity.this).load(get_greetings_category.getGreetingsCategoryData().get(0).getImage())
                                .placeholder(R.drawable.newlogo).into(card_set);
                        select_greetings_adapter = new Select_Greetings_Adapter(Select_Greetings_Activity.this, get_greetings_category.getGreetingsCategoryData());
                        rv_select_greetings.setAdapter(select_greetings_adapter);

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(Select_Greetings_Activity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(Select_Greetings_Activity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GET_Greetings_CategoryData> call, Throwable t) {
                Toast.makeText(Select_Greetings_Activity.this, "Failure", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    private class Select_Greetings_Adapter extends RecyclerView.Adapter<selectgreetings_Myholder> {
        Context context;
        List<GET_Greetings_CategoryData.GreetingsCategoryDatum> greetingsCategoryDatumList = new ArrayList<>();

        public Select_Greetings_Adapter(Context context, List<GET_Greetings_CategoryData.GreetingsCategoryDatum> greetingsCategoryDatumList) {
            this.context = context;
            this.greetingsCategoryDatumList = greetingsCategoryDatumList;
        }

        @NonNull
        @Override
        public selectgreetings_Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_greetings, parent, false);
            selectgreetings_Myholder viewHolder = new selectgreetings_Myholder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull selectgreetings_Myholder holder, int position) {
            if (greetingsCategoryDatumList.get(position).getGdFreeAndPaid().equals("1")) {
                holder.card_freepaid.setVisibility(View.VISIBLE);
            } else {
                holder.card_freepaid.setVisibility(View.GONE);
            }

            Glide.with(context).load(greetingsCategoryDatumList.get(position).getThumbImage())
                    .placeholder(R.drawable.newlogo).into(holder.ivThumb);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    position_getid = position;
                    Glide.with(context).load(greetingsCategoryDatumList.get(position).getThumbImage())
                            .placeholder(R.drawable.newlogo).into(card_set);
                    checkData = greetingsCategoryDatumList.get(position).getGdFreeAndPaid();
                }
            });

            next_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (new PrefManager(getApplicationContext()).getBoolen(IConstant.IS_PADE)) {
                        if (ActivityCompat.checkSelfPermission(Select_Greetings_Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(Select_Greetings_Activity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                        } else {
                            //  new Imagesaved().execute(SaveBitmap());

                                Intent intent = new Intent(Select_Greetings_Activity.this, Greetings_make_save_Activity.class);
                                intent.putExtra("getimagesave", get_greetings_category.getGreetingsCategoryData().get(position_getid).getImage());
                                intent.putExtra("freepaid", get_greetings_category.getGreetingsCategoryData().get(position_getid).getGdFreeAndPaid());
                                startActivity(intent);

                        }
                    } else {

                        if(interstitialAd.isLoaded()){
                            interstitialAd.show();
                        }else {
                            Intent intent = new Intent(Select_Greetings_Activity.this, Greetings_make_save_Activity.class);
                            intent.putExtra("getimagesave", get_greetings_category.getGreetingsCategoryData().get(position_getid).getImage());
                            intent.putExtra("freepaid", get_greetings_category.getGreetingsCategoryData().get(position_getid).getGdFreeAndPaid());
                            startActivity(intent);

                        }
                    }


                }
            });

        }

        @Override
        public int getItemCount() {
            return greetingsCategoryDatumList.size();
        }
    }

    private class selectgreetings_Myholder extends RecyclerView.ViewHolder {
        ImageView ivThumb;
        CardView card_freepaid;

        public selectgreetings_Myholder(@NonNull View itemView) {
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
            pd = new ProgressDialog(Select_Greetings_Activity.this);
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
            File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), ".OceanmtechDMT");
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

            Intent intent = new Intent(Select_Greetings_Activity.this, Greetings_make_save_Activity.class);
            intent.putExtra("getimagesave", imageFileName);
            intent.putExtra("freepaid", get_greetings_category.getGreetingsCategoryData().get(position_getid).getGdFreeAndPaid());
            startActivity(intent);

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



    public void ShowFullScreen() {
        //interstitial FullScreenAd
        AdRequest adRequestfull = new AdRequest.Builder().build();
        interstitialAd = new InterstitialAd(Select_Greetings_Activity.this);
        interstitialAd.setAdUnitId(getString(R.string.ad_id_interstitial));
        interstitialAd.setAdListener(new AdListener() {

            public void onAdLoaded() {
                super.onAdLoaded();
            }

            public void onAdClosed() {
                Intent intent = new Intent(Select_Greetings_Activity.this, Greetings_make_save_Activity.class);
                intent.putExtra("getimagesave", get_greetings_category.getGreetingsCategoryData().get(position_getid).getImage());
                intent.putExtra("freepaid", get_greetings_category.getGreetingsCategoryData().get(position_getid).getGdFreeAndPaid());
                startActivity(intent);
            }

            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);

            }
        });

        interstitialAd.loadAd(adRequestfull);

    }
}