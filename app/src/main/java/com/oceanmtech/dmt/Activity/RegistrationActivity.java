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

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.Model.Business_Add_Model;
import com.oceanmtech.dmt.Model.Business_Get_model;
import com.oceanmtech.dmt.Model.BussnessInfoModel;
import com.oceanmtech.dmt.Model.UpdateModel;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.QuoteCreater.Constans;
import com.oceanmtech.dmt.R;
import com.oceanmtech.dmt.api.ApiInterfase;
import com.oceanmtech.dmt.api.RetrofitClient;
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

public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = "Data-->";
    EditText edt_business, edt_email, edt_website, edt_address, edt_Mobile;
    TextView btn_Submit;
    ImageView ci_logo;
    ProgressDialog progress;
    String logo = "";
    ActivityRegistrationBinding binding;
    RegistrationActivity context = RegistrationActivity.this;
    boolean facebookSelection = false;
    boolean youtubeSelection = false;
    boolean snapchatSelection = false;
    boolean pinterestSelection = false;
    boolean twitterSelection = false;

//    TextView tvSkip;
    boolean instagramSelection = false;
    boolean whatsappSelection = false;
    private String edit;
    private BussnessInfoModel bussnessData;

    public static String getPath(Context context, Uri uri) {
        String result = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(proj[0]);
                result = cursor.getString(column_index);
            }
            cursor.close();
        }
        if (result == null) {
            result = "Not found";
        }
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(context, R.layout.activity_registration);

        findView();
        init();
        Click();
//        callGetBussiness();
//
//        if (new PrefManager(getApplicationContext()).getBoolen(IConstant.IS_PADE)) {
//            premium_logo.setVisibility(View.VISIBLE);
//        } else {
//            premium_logo.setVisibility(View.GONE);
//            //      Toast.makeText(Gujarati_Suvichar_View.this, "You are not Premium", Toast.LENGTH_SHORT).show();
//        }


    }

    private void callGetBussiness() {

        final ApiInterfase apiInterface = RetrofitClient.getApiClient().create(ApiInterfase.class);
        apiInterface.get_Business("business", new PrefManager(RegistrationActivity.this).getString(IConstant.IS_R_ID)).enqueue(new Callback<Business_Get_model>() {
            @Override
            public void onResponse(Call<Business_Get_model> call, Response<Business_Get_model> response) {
                Gson gson = new Gson();
                String successResponse = gson.toJson(response.body());
                Business_Get_model get_bussuineMode = response.body();
                if (response.isSuccessful()) {
                    progress.dismiss();


                    if (get_bussuineMode.getStatus().equals("success") && !get_bussuineMode.getBusiness().isEmpty()) {
                        for (int i = 0; i < get_bussuineMode.getBusiness().size(); i++) {
                            Business_Get_model.Business auction = get_bussuineMode.getBusiness().get(i);
                            if (new PrefManager(getApplicationContext()).getString(IConstant.BID).equals(auction.getBId())) {
                                logo = get_bussuineMode.getBusiness().get(i).getBLogo();
                                Glide.with(RegistrationActivity.this).load(logo).into(ci_logo);
                            }
                        }


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
        Intent intent = getIntent();
        edit = intent.getStringExtra("Type");

        if (edit.equalsIgnoreCase("Edit")) {
            bussnessData = (BussnessInfoModel) intent.getSerializableExtra("BussnessModel");

//            File file = new File(IConstant.BUSINESS_LOGO_PATH);
//            if (!file.exists()) {
//                ci_logo.setImageDrawable(getResources().getDrawable(R.drawable.logo_image));
//            } else {
            Glide.with(getApplicationContext()).load(bussnessData.getBLogo()).into(ci_logo);
//            }
            edt_business.setText(bussnessData.getbName());
            edt_website.setText(bussnessData.getbWebsite());
            edt_address.setText(bussnessData.getbAddress());
            edt_email.setText(bussnessData.getbEmailId());
            edt_Mobile.setText(bussnessData.getbMobileNo());

        } else {
            edit = "New";
            ci_logo.setImageDrawable(getResources().getDrawable(R.drawable.logo_image));
            edt_business.setText("");
            edt_website.setText("");
            edt_address.setText("");
            edt_email.setText("");
            edt_Mobile.setText(new PrefManager(getApplicationContext()).getString(IConstant.MOBILE_NO));
        }
    }

    private void findView() {
        progress = new ProgressDialog(RegistrationActivity.this);
        progress.setTitle("Loading");
        progress.setMessage("Please wait...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(false);
//        progress.show();

        ci_logo = (ImageView) findViewById(R.id.ci_logo);
        edt_business = (EditText) findViewById(R.id.edt_business);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_website = (EditText) findViewById(R.id.edt_website);
        edt_address = (EditText) findViewById(R.id.edt_address);
        edt_Mobile = (EditText) findViewById(R.id.edt_Mobile);
        btn_Submit = findViewById(R.id.btn_Submit);
//        tvSkip = findViewById(R.id.tvSkip);

    }

    public void Click() {
        binding.ivBack.setOnClickListener(v -> {
            onBackPressed();
        });
        ci_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
//        tvSkip.setOnClickListener(v -> {
//            Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//            finish();
//        });
        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String business = edt_business.getText().toString();
                String email = edt_email.getText().toString();
                String website = edt_website.getText().toString();
                String address = edt_address.getText().toString();
                String mobile = edt_Mobile.getText().toString();

                if (!business.isEmpty() || business.length() != 0) {
                    if (edit.equals("New")) {
                        File file = new File(IConstant.BUSINESS_LOGO_PATH);

                        if (!file.exists()) {
                            binding.logoLable.setText("Upload Logo(Please Upload Logo)");
                            binding.logoLable.setTextColor(getResources().getColor(R.color.red));
                            return;
                        }
                        progress.show();
                        BusinessAdCall(business, email, address, website, mobile);
                    } else {
                        progress.show();
                        BusinessUpdate(business, email, address, website, mobile);
                    }
                } else {
                    edt_business.setError("Enter Business name");
                }
            }
        });
        binding.tvAddSocialButton.setOnClickListener(v -> {
            dialogSocialButton();
        });
    }

    private void dialogSocialButton() {
        Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        DialogCustomSocialBinding mDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_custom_social, null, false);
        dialog.setContentView(mDialogBinding.getRoot());
        dialog.setCancelable(true);
        mDialogBinding.rlMain.setOnClickListener(view -> dialog.dismiss());
        mDialogBinding.ivFacebook.setOnClickListener(v -> {
            if (facebookSelection) {
                facebookSelection = false;
                mDialogBinding.ivFacebook.setImageResource(R.drawable.ic_facebook_gray);
            } else {
                facebookSelection = true;
                mDialogBinding.ivFacebook.setImageResource(R.drawable.ic_facebook_regular);
            }
        });
        mDialogBinding.ivYoutube.setOnClickListener(v -> {
            if (youtubeSelection) {
                youtubeSelection = false;
                mDialogBinding.ivYoutube.setImageResource(R.drawable.ic_youtube_gray);
            } else {
                youtubeSelection = true;
                mDialogBinding.ivYoutube.setImageResource(R.drawable.ic_youtube_regular);
            }
        });
        mDialogBinding.ivSnapchat.setOnClickListener(v -> {
            if (snapchatSelection) {
                snapchatSelection = false;
                mDialogBinding.ivSnapchat.setImageResource(R.drawable.ic_snapchat_gray);
            } else {
                snapchatSelection = true;
                mDialogBinding.ivSnapchat.setImageResource(R.drawable.ic_snapchat_regular);
            }
        });
        mDialogBinding.ivPinterest.setOnClickListener(v -> {
            if (pinterestSelection) {
                pinterestSelection = false;
                mDialogBinding.ivPinterest.setImageResource(R.drawable.ic_pinterest_gray);
            } else {
                pinterestSelection = true;
                mDialogBinding.ivPinterest.setImageResource(R.drawable.ic_pinterest_regular);
            }
        });
        mDialogBinding.ivTwitter.setOnClickListener(v -> {
            if (twitterSelection) {
                twitterSelection = false;
                mDialogBinding.ivTwitter.setImageResource(R.drawable.ic_twitter_gray);
            } else {
                twitterSelection = true;
                mDialogBinding.ivTwitter.setImageResource(R.drawable.ic_twitter_regular);
            }
        });
        mDialogBinding.ivInstagram.setOnClickListener(v -> {
            if (instagramSelection) {
                instagramSelection = false;
                mDialogBinding.ivInstagram.setImageResource(R.drawable.ic_instagram_gray);
            } else {
                instagramSelection = true;
                mDialogBinding.ivInstagram.setImageResource(R.drawable.ic_instagram_regular);
            }
        });
        mDialogBinding.ivWhatsapp.setOnClickListener(v -> {
            if (whatsappSelection) {
                whatsappSelection = false;
                mDialogBinding.ivWhatsapp.setImageResource(R.drawable.ic_whatsapp_gray);
            } else {
                whatsappSelection = true;
                mDialogBinding.ivWhatsapp.setImageResource(R.drawable.ic_whatsapp_regular);
            }
        });
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
            switch (requestCode) {

                case 1:
                    Uri imageuri = data.getData();

                    String picturePath = getPath(RegistrationActivity.this.getApplicationContext(), imageuri);

                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageuri);

                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                        byte[] imageInByte = stream.toByteArray();
                        long lengthbmp = imageInByte.length;


                        storeImage(bitmap);
                        Constans.isLogo = true;


                        Glide.with(RegistrationActivity.this).load(bitmap).into(ci_logo);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;

            }

    }

    private void storeImage(Bitmap image) {

        File pictureFile = getOutputMediaFile();

        if (pictureFile == null) {
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
            new PrefManager(getApplicationContext()).setString(IConstant.LOGO, IConstant.BUSINESS_LOGO_PATH);
        } catch (Exception e) {
        }
    }

    private File getOutputMediaFile() {
        if (!IConstant.FOLDER_PATH.exists()) {
            if (!IConstant.FOLDER_PATH.mkdirs()) {
                return null;
            }
        }
        // Create a media file name
        File mediaFile;
        mediaFile = new File(IConstant.BUSINESS_LOGO_PATH);
        return mediaFile;
    }

    public void BusinessAdCall(String businessname, String Emailaddress, String Address, String Website, String MobileNo) {
        File file = new File(IConstant.BUSINESS_LOGO_PATH);

        RequestBody add_user_login = RequestBody.create(MediaType.parse("text/plain"), "add_business_new");
        RequestBody user_id = RequestBody.create(MediaType.parse("text/plain"), new PrefManager(getApplicationContext()).getString(IConstant.IS_R_ID));
        RequestBody business_name = RequestBody.create(MediaType.parse("text/plain"), businessname);
        RequestBody b_email = RequestBody.create(MediaType.parse("text/plain"), Emailaddress);
        RequestBody b_address = RequestBody.create(MediaType.parse("text/plain"), Address);
        RequestBody b_website = RequestBody.create(MediaType.parse("text/plain"), Website);
        RequestBody b_mobile_number = RequestBody.create(MediaType.parse("text/plain"), MobileNo);

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part profile = MultipartBody.Part.createFormData("b_logo", file.getName(), requestBody);
        ApiInterfase apiInterface = RetrofitClient.getApiClient().create(ApiInterfase.class);
        Call<Business_Add_Model> call = apiInterface.Business_Add(add_user_login, user_id, business_name, b_email, b_website, b_address, profile, b_mobile_number);

        call.enqueue(new Callback<Business_Add_Model>() {
            @Override
            public void onResponse(Call<Business_Add_Model> call, Response<Business_Add_Model> response) {
                Business_Add_Model business_add_model = response.body();
                Gson gson = new Gson();
                String successResponse = gson.toJson(response.body());


                if (business_add_model.getStatus().equals("Success")) {

                    Constans.bid = business_add_model.getId();
                    new PrefManager(getApplicationContext()).setString(IConstant.BID, business_add_model.getId());

                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                    progress.dismiss();

                    // BusinessGet();

                } else {

                    progress.dismiss();

                }

            }

            @Override
            public void onFailure(Call<Business_Add_Model> call, Throwable t) {

                progress.dismiss();
            }

        });

    }

    public void BusinessUpdate(String businessname, String Emailaddress, String Address, String Website, String MobileNo) {
        File file = new File(IConstant.BUSINESS_LOGO_PATH);


        RequestBody Update_Business = RequestBody.create(MediaType.parse("text/plain"), "update_business");

        RequestBody b_id = RequestBody.create(MediaType.parse("text/plain"), bussnessData.getBId());
        RequestBody business_name = RequestBody.create(MediaType.parse("text/plain"), businessname);
        RequestBody b_email = RequestBody.create(MediaType.parse("text/plain"), Emailaddress);
        RequestBody b_address = RequestBody.create(MediaType.parse("text/plain"), Address);
        RequestBody b_website = RequestBody.create(MediaType.parse("text/plain"), Website);
        RequestBody b_mobile_number = RequestBody.create(MediaType.parse("text/plain"), MobileNo);

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part profile = MultipartBody.Part.createFormData("b_logo", file.getName(), requestBody);

        ApiInterfase apiInterface = RetrofitClient.getApiClient().create(ApiInterfase.class);

        Call<UpdateModel> call = apiInterface.Business_Update(Update_Business, b_id, business_name, b_email, b_website, b_address, profile, b_mobile_number);

        call.enqueue(new Callback<UpdateModel>() {
            @Override
            public void onResponse(Call<UpdateModel> call, Response<UpdateModel> response) {
                UpdateModel updateModel = response.body();
                Gson gson = new Gson();
                String successResponse = gson.toJson(response.body());


                if (updateModel.getStatus().equals("Success")) {
                    // Constans.bid=updateModel.getId();
                    Toast.makeText(RegistrationActivity.this, "Successfully Update", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationActivity.this, BussnessListActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                    progress.dismiss();

                } else {
                    progress.dismiss();
                }

            }

            @Override
            public void onFailure(Call<UpdateModel> call, Throwable t) {
                progress.dismiss();
            }
        });

    }

}