package com.oceanmtech.dmt.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.oceanmtech.dmt.Activity.MyApp;
import com.oceanmtech.dmt.Activity.View_photo;
import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.Model.UserData1;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Create_Quotes_Fragment extends Fragment {

    public static ArrayList<String> fullimage = new ArrayList<String>();
    ArrayList<String> ip = new ArrayList<String>();
    File[] listFile;
    private ImageView back;
    public static ArrayList<String> allImageList;
    private static final String TAG = "My_Creation";
    private TextView noimage;
    Image_Save_Adapter image_save_adapter;
    RecyclerView imagegrid;
    private TextView no_post;
    private FrameLayout ad_Layout;
    AdView adView;
    InterstitialAd interstitialAd;
    ArrayList<String> userData_apis;
    // ImageView back_button;

    BussinessPostFragment bussinessPostFragment;
    Create_Quotes_Fragment create_quotes_fragment;
    GreetingsFragment greetingsFragment;
    HomeFragment homeFragment;
    SettingFragment settingFragment;

    int dd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_quotes, container, false);


        bussinessPostFragment = new BussinessPostFragment();
        create_quotes_fragment = new Create_Quotes_Fragment();
        greetingsFragment = new GreetingsFragment();
        homeFragment = new HomeFragment();
        settingFragment = new SettingFragment();

        back = (ImageView) view.findViewById(R.id.back);
        imagegrid = (RecyclerView) view.findViewById(R.id.PhoneImageGrid);

        no_post = (TextView) view.findViewById(R.id.no_post);
        ad_Layout = (FrameLayout) view.findViewById(R.id.ad_Layout);


        loadBanner();


        if (new PrefManager(getActivity()).getBoolen(IConstant.IS_PADE)) {
            ad_Layout.setVisibility(View.GONE);
        } else {

        }

        return view;

    }


    @Override
    public void onResume() {
        super.onResume();
        MyApp.flagHomeAction=false;
        initView();
        ShowFullScreen();
        if (fullimage.size() == 0) {
            no_post.setVisibility(View.VISIBLE);
        } else {
            no_post.setVisibility(View.GONE);
        }
    }


    private void initView() {
        fullimage.clear();
        File sdCardRoot = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "OceanmtechDMT");

        //  File sdCardRoot = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "Business Card");

        if (sdCardRoot.isDirectory()) {
            listFile = sdCardRoot.listFiles();

            if (listFile != null) {

                for (int i = 0; i < listFile.length; i++) {
                    if (listFile[i].getAbsolutePath().endsWith(".png")) {
                        fullimage.add(listFile[i].getAbsolutePath());
                    }
                    Collections.reverse(fullimage);
                }

                image_save_adapter = new Create_Quotes_Fragment.Image_Save_Adapter(getActivity(), fullimage);
                imagegrid.setLayoutManager(new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false));
                imagegrid.setAdapter(image_save_adapter);
            } else {
                Toast.makeText(getActivity(), "No Data Found", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(getActivity(), "No Data Found", Toast.LENGTH_SHORT).show();

        }

    }


    public class Image_Save_Adapter extends RecyclerView.Adapter<Image_Save_Adapter.SaveHolder> {

        Activity activity;
        LayoutInflater inflater;

        int pos;
        UserData1 data1;

        public Image_Save_Adapter(Activity mContext, ArrayList<String> userData1s) {
            this.activity = mContext;
            userData_apis = userData1s;
            inflater = LayoutInflater.from(activity);
        }

        @NonNull
        @Override
        public Image_Save_Adapter.SaveHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = inflater.inflate(R.layout.save_item1, viewGroup, false);
            return new SaveHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final SaveHolder dataHolder, final int i) {
            Glide.with(activity)
                    .load(userData_apis.get(i))
                    .into(dataHolder.imageview);

            dataHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (new PrefManager(getActivity()).getBoolen(IConstant.IS_PADE)) {
                        dd = i;

                        getActivity().overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                        Intent intent = new Intent(getActivity(), View_photo.class);
                        intent.putExtra("image", userData_apis.get(dd));
                        intent.putExtra("imagepos", dd);

                        startActivity(intent);

                    } else {

                        if (interstitialAd.isLoaded()) {
                            interstitialAd.show();
                        } else {
                            dd = i;

                            getActivity().overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                            Intent intent = new Intent(getActivity(), View_photo.class);
                            intent.putExtra("image", userData_apis.get(dd));
                            intent.putExtra("imagepos", dd);

                            startActivity(intent);
                        }
                    }
                }
            });

            dataHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    devetevoid(i);
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

        public class SaveHolder extends RecyclerView.ViewHolder {

            ImageView imageview, delete;

            public SaveHolder(@NonNull View itemView) {
                super(itemView);

                imageview = (ImageView) itemView.findViewById(R.id.thumbImage);
                delete = (ImageView) itemView.findViewById(R.id.delete);

            }
        }

    }


    public void devetevoid(final int position) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage(" Are you sure you wamt to delete this image ?");
        builder1.setTitle("Alert");
        builder1.setIcon(R.drawable.backicon);
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {

                            File fdelete = new File(fullimage.get(position));
                            if (fdelete.exists()) {
                                if (fdelete.delete()) {

                                    fullimage.remove(position);
                                    image_save_adapter.notifyDataSetChanged();
                                    System.out.println("suksessfull Deleted :" + fullimage.get(position));
                                } else {
                                    System.out.println("file not Deleted :" + fullimage.get(position));
                                }
                            }
                        } catch (IndexOutOfBoundsException e) {

                        }
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

    private void loadBanner() {
        adView = new AdView(getActivity());
        adView.setAdUnitId(getString(R.string.ad_id_banner));
        ad_Layout.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();

        com.google.android.gms.ads.AdSize adSize = getAdSize();
        adView.setAdSize(adSize);
        adView.loadAd(adRequest);
    }

    private com.google.android.gms.ads.AdSize getAdSize() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);
        return AdSize.getCurrentOrientationBannerAdSizeWithWidth(getActivity(), adWidth);
    }


    public void ShowFullScreen() {
        //interstitial FullScreenAd
        AdRequest adRequestfull = new AdRequest.Builder().build();
        interstitialAd = new InterstitialAd(getContext());
        interstitialAd.setAdUnitId(getString(R.string.ad_id_interstitial));
        interstitialAd.setAdListener(new AdListener() {

            public void onAdLoaded() {
                super.onAdLoaded();
            }

            public void onAdClosed() {
                getActivity().overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);

                Intent intent = new Intent(getActivity(), View_photo.class);
                intent.putExtra("image", userData_apis.get(dd));
                intent.putExtra("imagepos", dd);

                startActivity(intent);
            }

            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);

            }
        });

        interstitialAd.loadAd(adRequestfull);

    }

}
