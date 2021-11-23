package com.oceanmtech.dmt.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.oceanmtech.dmt.Activity.MyApp;
import com.oceanmtech.dmt.Activity.Select_Greetings_Activity;
import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.Model.GET_Greetings_Category;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.R;
import com.oceanmtech.dmt.api.ApiInterfase;
import com.oceanmtech.dmt.api.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GreetingsFragment extends Fragment {
    RecyclerView rv_greetings_cat;
    GridLayoutManager rv_greetings_gridLayoutManager;
    Greetingscate_Adapter greetingscate_adapter;
    ProgressDialog progressDialog;
    private FrameLayout ad_Layout;
    AdView adView;
    BussinessPostFragment bussinessPostFragment;
    Create_Quotes_Fragment create_quotes_fragment;
    GreetingsFragment greetingsFragment;
    HomeFragment homeFragment;
    SettingFragment settingFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_greetings, container, false);
        rv_greetings_cat = (RecyclerView) view.findViewById(R.id.rv_greetings_cat);

        bussinessPostFragment = new BussinessPostFragment();
        create_quotes_fragment = new Create_Quotes_Fragment();
        greetingsFragment = new GreetingsFragment();
        homeFragment = new HomeFragment();
        settingFragment = new SettingFragment();


        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait..");
        progressDialog.setCancelable(false);
        ad_Layout = (FrameLayout) view.findViewById(R.id.ad_Layout);
        loadBanner();
        if (new PrefManager(getActivity()).getBoolen(IConstant.IS_PADE)) {
            ad_Layout.setVisibility(View.GONE);
        } else {

        }

        getgreetingscat();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApp.flagHomeAction=false;

    }

    public void getgreetingscat() {
        progressDialog.show();
        ApiInterfase apiInterface = RetrofitClient.getApiClient().create(ApiInterfase.class);
        apiInterface.getgreetingscategory("greetings_category").enqueue(new Callback<GET_Greetings_Category>() {
            @Override
            public void onResponse(Call<GET_Greetings_Category> call, Response<GET_Greetings_Category> response) {
                GET_Greetings_Category get_greetings_category = response.body();
                if (get_greetings_category != null) {
                    if (get_greetings_category.getStatus().equals("success")) {
                        progressDialog.dismiss();
                        rv_greetings_gridLayoutManager = new GridLayoutManager(getActivity(), 3, RecyclerView.VERTICAL, false);
                        rv_greetings_cat.setLayoutManager(rv_greetings_gridLayoutManager);
                        greetingscate_adapter = new Greetingscate_Adapter(getActivity(), get_greetings_category.getGreetingsCategory());
                        rv_greetings_cat.setAdapter(greetingscate_adapter);
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GET_Greetings_Category> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class Greetingscate_Adapter extends RecyclerView.Adapter<Greetingscat_Myholder> {
        Context context;
        List<GET_Greetings_Category.GreetingsCategory> greetingsCategoryList = new ArrayList<>();

        public Greetingscate_Adapter(Context context, List<GET_Greetings_Category.GreetingsCategory> greetingsCategoryList) {
            this.context = context;
            this.greetingsCategoryList = greetingsCategoryList;
        }

        @NonNull
        @Override
        public Greetingscat_Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_greetings_cat, parent, false);
            Greetingscat_Myholder viewHolder = new Greetingscat_Myholder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull Greetingscat_Myholder holder, int position) {
            Glide.with(context).load(greetingsCategoryList.get(position).getImage()).into(holder.ivThumb);
            holder.txtTitle.setText(greetingsCategoryList.get(position).getCatName());
            if (position == greetingsCategoryList.size() - 1) {
                holder.itemView.setLayoutParams(mParamSub());
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), Select_Greetings_Activity.class);
                    intent.putExtra("cat", greetingsCategoryList.get(position).getCgId());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return greetingsCategoryList.size();
        }
    }

    private class Greetingscat_Myholder extends RecyclerView.ViewHolder {
        ImageView ivThumb;
        TextView txtTitle;

        public Greetingscat_Myholder(@NonNull View itemView) {
            super(itemView);
            ivThumb = (ImageView) itemView.findViewById(R.id.ivThumb);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
        }
    }

    public LinearLayout.LayoutParams mParamSub() {
        Resources r = getResources();
        int px3 = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                40,
                r.getDisplayMetrics());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, px3);
        return params;
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
}