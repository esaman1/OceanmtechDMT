package com.oceanmtech.dmt.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.oceanmtech.dmt.QuoteCreater.Constans;
import com.oceanmtech.dmt.DataBase;
import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.Model.Business_Add_Model;
import com.oceanmtech.dmt.Model.Business_Get_model;
import com.oceanmtech.dmt.Model.UpdateModel;
import com.oceanmtech.dmt.MultiTouch.MultiTouchListener;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.R;
import com.oceanmtech.dmt.api.ApiInterfase;
import com.oceanmtech.dmt.api.RetrofitClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Welcome_Slide5 extends AppCompatActivity {

    private ImageView nextbutton5;
    private ImageView backbutton5;
    private DataBase dataBase;
    private ImageView userelogoset;
    private TextView businessname_ad;
    private TextView Emailaddress_ad;
    private TextView Website_ad;
    private TextView Address_ad;
    private TextView MobileNo_ad;
    private ImageView back_button;
    private TextView viewdatel;
    PrefManager prefManager;
    Business_Add_Model business_add_model;

    ProgressDialog progress;
    private FrameLayout frameimage;
    String edit;
    String businessname;
    String Emailaddress;
    String Website;
    String Address;
    String MobileNo;
    String bid;

    Business_Get_model business_get_model;
    private TextView b_id;
    private TextView text5;
    private TextView text6;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_slide5);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        prefManager = new PrefManager(getApplicationContext());
        Intent intent = getIntent();
        edit = intent.getStringExtra("Type");



        Find();

        retraivedata();

        Click();


        progress = new ProgressDialog(Welcome_Slide5.this);
        progress.setTitle("Loading");
        progress.setMessage("Business Add. Please wait.....");
        progress.setIndeterminate(true);
        progress.setMax(100);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(false);

    }

    private void Find() {

        nextbutton5 = (ImageView) findViewById(R.id.nextbutton5);
        backbutton5 = (ImageView) findViewById(R.id.backbutton5);
        userelogoset = (ImageView) findViewById(R.id.userelogoset);
        back_button = (ImageView) findViewById(R.id.back_button);

        businessname_ad = (TextView) findViewById(R.id.businessname_ad);
        Emailaddress_ad = (TextView) findViewById(R.id.Emailaddress_ad);
        Website_ad = (TextView) findViewById(R.id.Website_ad);
        Address_ad = (TextView) findViewById(R.id.Address_ad);
        MobileNo_ad = (TextView) findViewById(R.id.MobileNo_ad);

        viewdatel = (TextView) findViewById(R.id.viewdatel);
        b_id = (TextView) findViewById(R.id.bid);
        text5 = (TextView) findViewById(R.id.text5);
        text6 = (TextView) findViewById(R.id.text6);


        Typeface type = Typeface.createFromAsset(getAssets(), "papyrus.ttf");
        text5.setTypeface(type);

        Typeface type1 = Typeface.createFromAsset(getAssets(), "papyrus.ttf");
        text6.setTypeface(type1);


        userelogoset.setOnTouchListener(new MultiTouchListener());


    }

    private void Click() {


        Emailaddress_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome_Slide5.this, Welcome_Slide2.class);
                startActivity(intent);
            }

        });

        Website_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome_Slide5.this, Welcome_Slide2.class);
                startActivity(intent);
            }

        });

        Address_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome_Slide5.this, Welcome_Slide3.class);
                startActivity(intent);
            }

        });

        MobileNo_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome_Slide5.this, Welcome_Slide4.class);
                startActivity(intent);
            }

        });

        userelogoset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome_Slide5.this, Welcome_Slide1.class);
                startActivity(intent);
            }
        });

        businessname_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome_Slide5.this, Welcome_Slide1.class);
                startActivity(intent);
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        nextbutton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (progress == null) {
//                    progress.show();
//                    //retraivedata();
//                }

//                Toast.makeText(Welcome_Slide5.this, "Successfully Inserted", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Welcome_Slide5.this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);


                if (edit.equals("New")) {
                    progress.show();
                    BusinessAdCall();
                } else {
                    progress.show();
                    BusinessUpdate();
                }


//              Constans.Address=edittext4.getText().toString();

                //boolean insert = dataBase.InsertData(Constans.logo, Constans.businessname, Constans.Emailaddress, Constans.Website, Constans.Address, Constans.MobileNo);

//                if (insert) {
//                    Toast.makeText(Welcome_Slide5.this, "Sucessfully InsertData", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(Welcome_Slide5.this, "No InsertData", Toast.LENGTH_SHORT).show();
//                }
//
//                Log.e("GAJERA", "===>" + Constans.businessname + "===>" + Constans.Emailaddress + "===>" + Constans.Website + "===>" + Constans.Address + "===>" + Constans.MobileNo);


              /*  Cursor cursor = dataBase.deleteAll();
                cursor.moveToLast();*/

            }

        });

        viewdatel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.show();
                //retraivedata();

            }

        });

        backbutton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }

        });

    }


    public void retraivedata() {

        businessname = prefManager.getString(IConstant.BUSINESS_NAME);
        Emailaddress = prefManager.getString(IConstant.BUSINESS_EMAIL);
        Website = prefManager.getString(IConstant.WEBSITE);
        Address = prefManager.getString(IConstant.ADDRESS);
        MobileNo = prefManager.getString(IConstant.MOBILE_NO);
        bid = prefManager.getString(IConstant.BID);

        if (!new PrefManager(getApplicationContext()).getString(IConstant.LOGO).isEmpty()) {
            userelogoset.setVisibility(View.VISIBLE);
            File file = new File(IConstant.BUSINESS_LOGO_PATH);
            userelogoset.setImageURI(Uri.fromFile(file));
        } else {
            userelogoset.setVisibility(View.GONE);
        }

        businessname_ad.setText(businessname);
        Emailaddress_ad.setText(Emailaddress);
        Website_ad.setText(Website);
        Address_ad.setText(Address);
        MobileNo_ad.setText(MobileNo);
        b_id.setText(bid);


    }

//        Bitmap bitmap = BitmapFactory.decodeByteArray(Constans.logo, 0, Constans.logo.length);
//        Glide.with(Welcome_Slide5.this)
//                .load(bitmap)
//                .into(userelogoset);


// Api Call

    public void BusinessAdCall() {
        File file = new File(IConstant.BUSINESS_LOGO_PATH);

//        Log.i("tag->", "filepath->" + file.getAbsolutePath());

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
        Call<Business_Add_Model> call = apiInterface.Business_Add(add_user_login,user_id, business_name, b_email, b_website, b_address, profile, b_mobile_number);
        call.enqueue(new Callback<Business_Add_Model>() {
            @Override
            public void onResponse(Call<Business_Add_Model> call, Response<Business_Add_Model> response) {
                Business_Add_Model business_add_model = response.body();

                if (business_add_model.getStatus().equals("Success")) {

                    Constans.bid = business_add_model.getId();

                    new PrefManager(getApplicationContext()).setString(IConstant.BID, business_add_model.getId());

                    Intent intent = new Intent(Welcome_Slide5.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

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

//
//    private void BusinessGet() {
//
//        ApiInterfase apiInterfase = RetrofitClient.getApiClient().create(ApiInterfase.class);
//
//        Call<Business_Get_model> business_get_modelCall = apiInterfase.Business_Get("business", "user_id");
//        business_get_modelCall.enqueue(new Callback<Business_Get_model>() {
//            @Override
//            public void onResponse(Call<Business_Get_model> call, Response<Business_Get_model> response) {
//
//                if (response.isSuccessful()) {
//
//                    business_get_model = response.body();
//
//                    if (business_get_model.getStatus().equals("success")) {
//
//                        Toast.makeText(Welcome_Slide5.this, "GET_BUSINESS", Toast.LENGTH_SHORT).show();
//
//                    } else {
//
//                    }
//
//                } else {
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Business_Get_model> call, Throwable t) {
//
//            }
//
//        });
//    }


    public void BusinessUpdate() {
        File file = new File(IConstant.BUSINESS_LOGO_PATH);

        // Log.i("tag->", "filepath->" + file.getAbsolutePath());

        RequestBody Update_Business = RequestBody.create(MediaType.parse("text/plain"), "update_business");

        RequestBody b_id = RequestBody.create(MediaType.parse("text/plain"), prefManager.getString(IConstant.BID));
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


                if (updateModel.getStatus().equals("Success")) {
                    // Constans.bid=updateModel.getId();
                    Toast.makeText(Welcome_Slide5.this, "Successfully Update", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Welcome_Slide5.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
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


    //    public void PassLoginData(String email) {
//        File file = new File("/storage/emulated/0/Video Status/.".concat(email.substring(0, email.indexOf("@"))).concat(".png"));
//
//        Log.i("tag->", "filepath->" + file.getAbsolutePath());
//
//        RequestBody add_user_login = RequestBody.create(MediaType.parse("text/plain"), "add_user_login");
//        RequestBody Lname = RequestBody.create(MediaType.parse("text/plain"), name);
//        RequestBody Lemail = RequestBody.create(MediaType.parse("text/plain"), email);
//
//        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
//        MultipartBody.Part profile = MultipartBody.Part.createFormData("profile", file.getName(), requestBody);
//
//        RequestBody Luser_name = RequestBody.create(MediaType.parse("text/plain"), "@" + email.substring(0, email.indexOf("@")));
//
//        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
//        Call<LoginData> call = apiInterface.PassLoginData(add_user_login, Lname, Lemail, profile, Luser_name);
//        call.enqueue(new Callback<LoginData>() {
//            @Override
//            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
//                LoginData loginData = response.body();
//
//                Log.i("tag->", "response->" + loginData.getMessage());
//
//                if (loginData != null) {
//                    if (loginData.getStatus().equals("Success")) {
//                        prefrenceHandler.setBooleanData(Constants.ISLOGIN, true);
//                        prefrenceHandler.setStringData(Constants.USER_ID, loginData.getId());
//                        prefrenceHandler.setStringData(Constants.USER_NAME, "@" + email.substring(0, email.indexOf("@")));
//
//                        Toast.makeText(getActivity(), "login---> success", Toast.LENGTH_SHORT).show();
//                        Log.i("tag->", "username->" + "@" + email.substring(0, email.indexOf("@")));
//
//                        Fragment fragment = new Upload_Video_Fragment();
//                        loadFragment(fragment);
//
//                        progress.dismiss();
//
//                    } else {
//                        prefrenceHandler.setBooleanData(Constants.ISLOGIN, true);
//                        prefrenceHandler.setStringData(Constants.USER_NAME, "@" + email.substring(0, email.indexOf("@")));
//                        prefrenceHandler.setStringData(Constants.USER_ID, loginData.getId());
//
//                        Log.i("tag->", "username->" + "@" + email.substring(0, email.indexOf("@")));
//                        Toast.makeText(getActivity(), "login---> already register", Toast.LENGTH_SHORT).show();
//
//
//                        Fragment fragment = new Upload_Video_Fragment();
//                        loadFragment(fragment);
//
//                        progress.dismiss();
//
//
//                    }
//                } else {
//                    assert loginData != null;
//                    Log.v("tag->", loginData.toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LoginData> call, Throwable t) {
//                Log.i("tag->", "failure->" + t.toString());
//            }
//        });
//
//    }


    public static File bitmapToFile(Context context, Bitmap bitmap, String fileNameToSave) { // File name like "image.png"
        //create a file to write bitmap data
        File file = null;
        try {
            file = new File(Environment.getExternalStorageDirectory() + File.separator + fileNameToSave);
            file.createNewFile();

//Convert bitmap to byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos); // YOU can also save it in JPEG
            byte[] bitmapdata = bos.toByteArray();

            //write the bytes in file
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return file; // it will return null
        }
    }


}
