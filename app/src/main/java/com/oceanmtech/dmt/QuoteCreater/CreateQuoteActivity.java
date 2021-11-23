package com.oceanmtech.dmt.QuoteCreater;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.oceanmtech.dmt.Activity.MainActivity;
import com.oceanmtech.dmt.Model.BackgroundImageModel;
import com.oceanmtech.dmt.QuoteCreater.StickerView.StickerView;
import com.oceanmtech.dmt.R;
import com.oceanmtech.dmt.Utility;
import com.oceanmtech.dmt.api.ApiInterfase;
import com.oceanmtech.dmt.api.RetrofitClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageLookupFilter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.oceanmtech.dmt.Utility.DIRECTORY_PATH;
import static com.oceanmtech.dmt.Utility.isInternetAvailable;


public class CreateQuoteActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyel_view_bg, recyel_view_effect;
    RelativeLayout rl_bg, rl_effect, rl_design, rl_gallery;
    LinearLayout ll_add_text, ll_background, ll_sticker, ll_quotes, ll_save, ll_effect;
    ImageView img_design;
    private GPUImageLookupFilter filter;
    GPUImage gpuImage;
    private Bitmap filtered_img;
    private int currentBackgroundColor = 0xffffffff;

    ImageView img_back;
    int check_ads;
    ProgressDialog progressdialog;
    private FrameLayout ss_frame;
    LinearLayout linearLayout;
    AdView mAdView;

    private void GetBackgorund() {

        if (isInternetAvailable(this)) {
            RetrofitClient.getApiClient().create(ApiInterfase.class).GetbgImage().enqueue(new Callback<BackgroundImageModel>() {
                @Override
                public void onResponse(Call<BackgroundImageModel> call, final Response<BackgroundImageModel> response) {
                    if (response.code() == 200) {

                        if (response.body().status.equalsIgnoreCase("success")) {

                            loadBG(response.body().content);

                        } else {


                            AlertDialog.Builder alertbox =
                                    new AlertDialog.Builder(CreateQuoteActivity.this);
                            String msg = "Something went wrong";
                            alertbox.setMessage(msg);
                            alertbox.show();
                        }

                    } else {

                        AlertDialog.Builder alertbox =
                                new AlertDialog.Builder(CreateQuoteActivity.this);
                        String msg = "Something went wrong";
                        alertbox.setMessage(msg);
                        alertbox.show();
                    }
                }

                @Override
                public void onFailure(Call<BackgroundImageModel> call, Throwable t) {

                }
            });
        } else {

            AlertDialog.Builder alertbox =
                    new AlertDialog.Builder(CreateQuoteActivity.this);
            String msg = "Check your Internet Connectivity";
            alertbox.setMessage(msg);
            alertbox.show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quote);
        initView();
        GetBackgorund();
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView = new AdView(CreateQuoteActivity.this);
        mAdView.setAdSize(AdSize.BANNER);
        mAdView.setAdUnitId("");
        mAdView.loadAd(adRequest);
        linearLayout = (LinearLayout) findViewById(R.id.adView);
        linearLayout.addView(mAdView);

    }

    private void initView() {

        img_back = (ImageView) findViewById(R.id.img_back);
        ss_frame = findViewById(R.id.ss_frame);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        progressdialog = new ProgressDialog(CreateQuoteActivity.this);
        progressdialog.setMessage("Apply Filter....");
        progressdialog.setCancelable(false);
        progressdialog.setCanceledOnTouchOutside(false);
        recyel_view_bg = (RecyclerView) findViewById(R.id.recyel_view_bg);
        recyel_view_effect = (RecyclerView) findViewById(R.id.recyel_view_quotes);
        rl_design = (RelativeLayout) findViewById(R.id.rl_design);
        rl_bg = (RelativeLayout) findViewById(R.id.rl_bg);
        rl_gallery = (RelativeLayout) findViewById(R.id.rl_gallery);

        rl_effect = (RelativeLayout) findViewById(R.id.rl_quotes);
        img_design = (ImageView) findViewById(R.id.img_design);

        img_design.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                resetSticker();
                return false;
            }
        });

        original_img = BitmapFactory.decodeResource(getResources(), R.drawable.friendship);
        ItemClickSupport.addTo(recyel_view_effect).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                check_ads++;
                if (check_ads == 3) {
                    check_ads = 0;
                    if (Utility.isInternetAvailable(getApplicationContext())) {
                        // Utility.fullScreenAd(getApplicationContext());
                    }
                }
                new ApplayFilterFilesTask(position).execute(new Void[0]);
            }
        });


        ll_add_text = (LinearLayout) findViewById(R.id.ll_add_text);
        ll_background = (LinearLayout) findViewById(R.id.ll_background);
        ll_quotes = (LinearLayout) findViewById(R.id.ll_quotes);
        ll_save = (LinearLayout) findViewById(R.id.ll_save);
        ll_sticker = (LinearLayout) findViewById(R.id.ll_sticker);
        ll_effect = (LinearLayout) findViewById(R.id.ll_effect);


        ll_add_text.setOnClickListener(this);
        ll_background.setOnClickListener(this);
        ll_quotes.setOnClickListener(this);
        ll_save.setOnClickListener(this);
        ll_sticker.setOnClickListener(this);
        rl_gallery.setOnClickListener(this);
        ll_effect.setOnClickListener(this);
        //   loadQuotes();

        this.filter = new GPUImageLookupFilter();
        this.gpuImage = new GPUImage(CreateQuoteActivity.this);

        loadLookupFilters();
        // parse();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_add_text:
                Intent intent = new Intent(getApplicationContext(), TextActivity.class);
                intent.putExtra("Quotes", "Enter Your Text");
                startActivityForResult(intent, textquesetcode);
                break;
            case R.id.ll_background:
                rl_effect.setVisibility(View.INVISIBLE);
                rl_bg.setVisibility(View.VISIBLE);

                break;
            case R.id.ll_quotes:
                rl_bg.setVisibility(View.INVISIBLE);
                // rl_quotes.setVisibility(View.VISIBLE);
                //loadQuotes();
                loadQuotes();
                break;
            case R.id.ll_save:
                resetSticker();
                loadSave();
                break;
            case R.id.rl_gallery:
                pickImage();
                break;
            case R.id.ll_sticker:
                ColorpickerDialog();
//                if (Utility.isInternetAvailable(getApplicationContext())) {
//                    //Utility.showInterstitial();
//                    //  Utility.fullScreenAd(getApplicationContext());
//                }
//                //  startActivityForResult(new Intent(getApplicationContext(),StickerAvtivity.class),stickerquesetcode);
//
//                final Intent intentdwsdq = new Intent(getApplicationContext(), StickerAvtivity.class);
//                startActivityForResult(intentdwsdq, stickerquesetcode);
                break;

            case R.id.ll_effect:
                rl_bg.setVisibility(View.INVISIBLE);
                rl_effect.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void ColorpickerDialog() {
        ColorPickerDialogBuilder
                .with(CreateQuoteActivity.this)
                .setTitle("Choose color")
                .initialColor(currentBackgroundColor)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
//                        rl_design.setBackgroundColor(selectedColor);
//                        toast("onColorSelected: 0x" + Integer.toHexString(selectedColor));

                        Toast.makeText(CreateQuoteActivity.this, "onColorSelected: 0x" + Integer.toHexString(selectedColor), Toast.LENGTH_SHORT).show();
                    }

                })
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @SuppressLint("ResourceType")
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
//                        changeBackgroundColor(selectedColor);
                        img_design.setImageBitmap(null);
                        rl_design.setBackgroundColor(selectedColor);
                    }

                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .build()
                .show();


    }

    int PICK_IMAGE_REQUEST = 185;

    private void pickImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    ArrayList<QuotesList> stringArrayList = new ArrayList<>();
    int check_quotes = 0;

    private void loadQuotes() {
        stringArrayList.clear();
        final Dialog dialog = new Dialog(CreateQuoteActivity.this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.dialog_layout);
        stringArrayList = getQuotes();
        RecyclerView recyel_view_quotes_list = (RecyclerView) dialog.findViewById(R.id.recyel_view_quotes_list);
        TextView txt_title = (TextView) dialog.findViewById(R.id.txt_title);
        ImageView img_done = (ImageView) dialog.findViewById(R.id.img_done);
        img_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        txt_title.setText("Quotes Category");
        QuotesWithImageAdapter mAdapter = new QuotesWithImageAdapter(getApplicationContext(), stringArrayList);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        recyel_view_quotes_list.setLayoutManager(mLayoutManager);
        recyel_view_quotes_list.setItemAnimator(new DefaultItemAnimator());
        recyel_view_quotes_list.setAdapter(mAdapter);
        ItemClickSupport.addTo(recyel_view_quotes_list).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                check_quotes++;
                if (check_quotes == 3) {
                    check_quotes = 0;

                }
                loadDialog(stringArrayList.get(position).getName(), position);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    ArrayList<String> quaotesList = new ArrayList<>();
    int check_quotes_list = 0;

    private void loadDialog(String s, int positions) {
        final Dialog dialog = new Dialog(CreateQuoteActivity.this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.dialog_layout);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);


        ImageView img_done = (ImageView) dialog.findViewById(R.id.img_done);
        img_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        RecyclerView recyel_view_quotes_list = (RecyclerView) dialog.findViewById(R.id.recyel_view_quotes_list);
        int i = 0;
        TextView ti = (TextView) dialog.findViewById(R.id.txt_title);
        ti.setText(s);
        quaotesList.clear();
        //int intExtra = getIntent().getIntExtra("findIndex", 0);
        //this.f1796n.getText().toString().trim();
        try {
            if (positions == 0) {
                Collections.addAll(quaotesList, getResources().getStringArray(R.array.AnniversaryQuotes));
            } else if (positions == 1) {
                Collections.addAll(quaotesList, getResources().getStringArray(R.array.AnniversaryQuotes));

            } else if (positions == 2) {
                Collections.addAll(quaotesList, getResources().getStringArray(R.array.BirthdayQuotes));
            } else if (positions == 3) {
                Collections.addAll(quaotesList, getResources().getStringArray(R.array.DreamsQuotes));
            } else if (positions == 4) {
                Collections.addAll(quaotesList, getResources().getStringArray(R.array.EducationQuotes));
            } else if (positions == 5) {
                Collections.addAll(quaotesList, getResources().getStringArray(R.array.FriendshipQuotes));
            } else if (positions == 6) {
                Collections.addAll(quaotesList, getResources().getStringArray(R.array.GeniusQuotes));
            } else if (positions == 7) {
                Collections.addAll(quaotesList, getResources().getStringArray(R.array.LifeQuotes));
            } else if (positions == 8) {
                Collections.addAll(quaotesList, getResources().getStringArray(R.array.LoveQuotes));
            } else if (positions == 9) {
                Collections.addAll(quaotesList, getResources().getStringArray(R.array.ManQuotes));
            } else if (positions == 10) {
                Collections.addAll(quaotesList, getResources().getStringArray(R.array.MissuQuotes));
            } else if (positions == 11) {
                Collections.addAll(quaotesList, getResources().getStringArray(R.array.MoneyQuotes));
            } else if (positions == 12) {
                Collections.addAll(quaotesList, getResources().getStringArray(R.array.SayingsQuotes));
            } else if (positions == 13) {
                Collections.addAll(quaotesList, getResources().getStringArray(R.array.SuccessQuotes));
            } else if (positions == 14) {
                Collections.addAll(quaotesList, getResources().getStringArray(R.array.WisdomQuotes));
            } else if (positions == 15) {
                Collections.addAll(quaotesList, getResources().getStringArray(R.array.WomanQuotes));
            }
            QuotesListAdapter mAdapter = new QuotesListAdapter(getApplicationContext(), quaotesList);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyel_view_quotes_list.setLayoutManager(mLayoutManager);
            recyel_view_quotes_list.setItemAnimator(new DefaultItemAnimator());
            recyel_view_quotes_list.setAdapter(mAdapter);
            ItemClickSupport.addTo(recyel_view_quotes_list).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    dialog.dismiss();
                    check_quotes_list++;
                    if (check_quotes_list == 5) {
                        check_quotes_list = 0;
                    }
                    Intent intent = new Intent(getApplicationContext(), TextActivity.class);
                    intent.putExtra("Quotes", quaotesList.get(position));
                    startActivityForResult(intent, textquesetcode);
                    // loadDialog(stringArrayList.get(position),position);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialog.show();
    }

    private ArrayList<QuotesList> getQuotes() {
        ArrayList<QuotesList> arrayList1 = new ArrayList<>();
        for (int i = 0; i < Utility.str_quotes.length; i++) {
            QuotesList quotesList = new QuotesList();
            quotesList.setName(Utility.str_quotes[i]);
            quotesList.setImage(Utility.image[i]);
            arrayList1.add(quotesList);
        }
        return arrayList1;
    }

    ArrayList<Integer> arrayList = new ArrayList<>();
    int check_bg = 0;
    public String url = "";

    private void loadBG(ArrayList<BackgroundImageModel.Content> content) {
        url = content.get(0).bg_url;
//                    original_img = getBitmapFromURL(content.get(position).bg_url);
        MyAsync myAsync = new MyAsync(CreateQuoteActivity.this);
        myAsync.execute();
//        arrayList = getImage();
        BGAdapter mAdapter = new BGAdapter(getApplicationContext(), content);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1, GridLayoutManager.HORIZONTAL, false);
        recyel_view_bg.setLayoutManager(mLayoutManager);
        recyel_view_bg.setItemAnimator(new DefaultItemAnimator());
        recyel_view_bg.setAdapter(mAdapter);
        ItemClickSupport.addTo(recyel_view_bg).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                check_bg++;
                if (check_bg == 5) {
                    check_bg = 0;

                }
                url = content.get(position).bg_url;
//                    original_img = getBitmapFromURL(content.get(position).bg_url);
                MyAsync myAsync = new MyAsync(CreateQuoteActivity.this);
                myAsync.execute();
//                    img_design.setImageBitmap(original_img);

//                original_img = BitmapFactory.decodeResource(getResources(), arrayList.get(position));
//                img_design.setImageBitmap(original_img);
            }
        });
    }

    public class MyAsync extends AsyncTask<Void, Void, Bitmap> {
        String finalUrl = url;
        Context context;
        ProgressDialog progressDialog;

        MyAsync(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Loading Image...");
            progressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(Void... params) {

            try {
                URL url = new URL(finalUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);

                return myBitmap;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            original_img = bitmap;
            img_design.setImageBitmap(original_img);
            progressDialog.dismiss();
        }

        public class execute {
        }
    }

    int textquesetcode = 102;
    int stickerquesetcode = 101;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
      /*  if (requestCode == stickerquesetcode) {
            if (StickerAvtivity.stiker_image == 0) {

            } else {
                initStickerBitmap(BitmapFactory.decodeResource(getResources(), StickerAvtivity.stiker_image));
            }
        } else*/ if (requestCode == textquesetcode) {
            if (data != null && data.getExtras() != null && data.getExtras().containsKey(Utility.text_path)) {
                String text_path = data.getStringExtra(Utility.text_path);
                if (text_path != null && text_path.length() != 0) {
                    try {
                        Bitmap decodeFile = BitmapFactory.decodeFile(text_path);
                        if (decodeFile != null) {
                            System.out.println("text_path....................." + decodeFile.getHeight());
                            initStickerBitmap(decodeFile);
                            return;
                        }
                        return;
                    } catch (Exception e) {
                        return;
                    }
                }
                return;
            }
        } else if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                img_design.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private StickerView mCurrentView;
    private ArrayList<View> mViews = new ArrayList<>();

    private void initStickerBitmap(Bitmap bitmap) {
        final StickerView stickerView = new StickerView(this);
        stickerView.setBitmap(bitmap);
        stickerView.setOperationListener(new StickerView.OperationListener() {
            @Override
            public void onDeleteClick() {
                mViews.remove(stickerView);
                rl_design.removeView(stickerView);
            }

            @Override
            public void onEdit(StickerView stickerView) {

                mCurrentView.setInEdit(false);
                mCurrentView = stickerView;
                mCurrentView.setInEdit(true);
            }

            @Override
            public void onTop(StickerView stickerView) {
                int position = mViews.indexOf(stickerView);
                if (position == mViews.size() - 1) {
                    return;
                }
                StickerView stickerTemp = (StickerView) mViews.remove(position);
                mViews.add(mViews.size(), stickerTemp);
            }
        });
        int w = img_design.getWidth();
        int hw = img_design.getHeight();
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(w, hw);
        rl_design.addView(stickerView, lp);
        mViews.add(stickerView);
        setCurrentEdit(stickerView);
    }

    private void setCurrentEdit(StickerView stickerView) {
        if (mCurrentView != null) {
            mCurrentView.setInEdit(false);
        }
        mCurrentView = stickerView;
        stickerView.setInEdit(true);
    }

    private void resetSticker() {
        if (mCurrentView != null) {
            mCurrentView.setInEdit(false);
        }
    }

    String imageSavePath = new StringBuilder(String.valueOf(Environment.getExternalStorageDirectory().getPath())).append(DIRECTORY_PATH).toString();

    private void loadSave() {
        ss_frame.setBackgroundColor(Color.TRANSPARENT);
        this.ss_frame.setDrawingCacheEnabled(true);
        Bitmap createBitmap = Bitmap.createBitmap(this.ss_frame.getDrawingCache());
        this.ss_frame.setDrawingCacheEnabled(false);
        Utility.share_ = createBitmap;
        File file = new File(imageSavePath);
        file.mkdirs();
        Utility.shareFile = new File(this.imageSavePath, System.currentTimeMillis() + ".png");
        try {
            FileOutputStream out = new FileOutputStream(Utility.shareFile);
            Utility.share_.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Utility.isInternetAvailable(getApplicationContext())) {
            //Utility.showInterstitial();
            //Utility.fullScreenAd(getApplicationContext());
        }
        final Intent intentdwsdq = new Intent(getApplicationContext(), ShareImageActivity.class);
        startActivityForResult(intentdwsdq, 1012);
    }

    private ArrayList<String> filters_lookup_res = new ArrayList();
    private ArrayList<Integer> filters_res = new ArrayList();
    Bitmap original_img;

    private void loadLookupFilters() {
        new AsyncTask<Void, Void, Void>() {
            protected void onPreExecute() {
                super.onPreExecute();
            }

            protected Void doInBackground(Void... params) {
                try {
                    filters_res.clear();
                    filters_lookup_res.clear();
                    for (int i = 0; i < 21; i++) {
                        filters_res.add(Utility.effect_thumb[i]);
                        filters_lookup_res.add("lookupFliter/amatorka_" + i + ".png");
                    }
                } catch (Exception e) {
                    try {
                        e.printStackTrace();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                return null;
            }

            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                EffectAdapter effectAdapterWithRecy = new EffectAdapter(getApplicationContext(), CreateQuoteActivity.this.filters_res);
                recyel_view_effect.setLayoutManager(new GridLayoutManager(CreateQuoteActivity.this, 1, RecyclerView.HORIZONTAL, false));
                recyel_view_effect.setItemAnimator(new DefaultItemAnimator());
                recyel_view_effect.setAdapter(effectAdapterWithRecy);
            }
        }.execute(new Void[0]);
    }

    private class ApplayFilterFilesTask extends AsyncTask<Void, Void, Bitmap> {

        int val_finalI = 1;

        public ApplayFilterFilesTask(int i) {
            this.val_finalI = i;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            // ImageEditingActivity.this.setProgress(true);
            progressdialog.show();
        }

        protected Bitmap doInBackground(Void... params) {
            try {
                if (this.val_finalI == 0) {
                    filtered_img = original_img;
                } else {
                    filter.setBitmap(Utility.getBitmapFromAsset(getApplicationContext(), (String) filters_lookup_res.get(this.val_finalI)));
                    gpuImage.setImage(original_img);
                    gpuImage.setFilter(filter);
                    filtered_img = gpuImage.getBitmapWithFilterApplied();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return filtered_img;
        }

        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);

            progressdialog.dismiss();
            if (result != null) {
                img_design.setImageBitmap(result);
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CreateQuoteActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}


