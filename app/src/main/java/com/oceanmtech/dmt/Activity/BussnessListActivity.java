package com.oceanmtech.dmt.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.oceanmtech.dmt.Adapter.AdapterBussnessList;
import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.Model.Business_Add_Model;
import com.oceanmtech.dmt.Model.Business_Get_model;
import com.oceanmtech.dmt.Model.UpdateModel;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.QuoteCreater.Constans;
import com.oceanmtech.dmt.R;
import com.oceanmtech.dmt.api.ApiInterfase;
import com.oceanmtech.dmt.api.RetrofitClient;
import com.oceanmtech.dmt.databinding.ActivityBussnesslistBinding;
import com.oceanmtech.dmt.databinding.ActivityRegistrationBinding;
import com.oceanmtech.dmt.databinding.DialogCustomSocialBinding;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BussnessListActivity extends AppCompatActivity {

    private static final String TAG = "Data-->";
    ProgressDialog progress;
    ActivityBussnesslistBinding binding;
    BussnessListActivity context = BussnessListActivity.this;
    private RecyclerView rvBussnessList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(context, R.layout.activity_bussnesslist);
        findView();
        init();
        Click();
        callGetBussiness();
//
//        if (new PrefManager(getApplicationContext()).getBoolen(IConstant.IS_PADE)) {
//            premium_logo.setVisibility(View.VISIBLE);
//        } else {
//            premium_logo.setVisibility(View.GONE);
//            //      Toast.makeText(Gujarati_Suvichar_View.this, "You are not Premium", Toast.LENGTH_SHORT).show();
//        }


    }

    private void callGetBussiness() {
        progress = new ProgressDialog(BussnessListActivity.this);
        progress.setTitle("Loading");
        progress.setMessage("Please wait...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(false);
        progress.show();

        final ApiInterfase apiInterface = RetrofitClient.getApiClient().create(ApiInterfase.class);
        apiInterface.get_Business("business", new PrefManager(BussnessListActivity.this).getString(IConstant.IS_R_ID)).enqueue(new Callback<Business_Get_model>() {
            @Override
            public void onResponse(Call<Business_Get_model> call, Response<Business_Get_model> response) {
                Gson gson = new Gson();
                String successResponse = gson.toJson(response.body());
                Business_Get_model get_bussuineMode = response.body();
                if (response.isSuccessful()) {
                    progress.dismiss();
                    if (get_bussuineMode.getStatus().equals("success") && !get_bussuineMode.getBusiness().isEmpty()) {
                        rvBussnessList.setAdapter(new AdapterBussnessList(BussnessListActivity.this,get_bussuineMode.getBusiness()));
                    }

                }
            }

            @Override
            public void onFailure(Call<Business_Get_model> call, Throwable t) {

                call.cancel();
            }
        });
    }

    private void init() {
        rvBussnessList=findViewById(R.id.rvBussnessList);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvBussnessList.setLayoutManager(layoutManager);
        findViewById(R.id.btAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BussnessListActivity.this, RegistrationActivity.class);
                intent.putExtra("Type", "New");
                startActivity(intent);
            }
        });

    }


    private void findView() {


    }

    public void Click() {
        binding.ivBack.setOnClickListener(v -> {
            onBackPressed();
        });

//        tvSkip.setOnClickListener(v -> {
//            Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//            finish();
//        });
    }





}