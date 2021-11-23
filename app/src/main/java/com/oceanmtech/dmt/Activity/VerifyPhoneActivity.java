package com.oceanmtech.dmt.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.Model.Trial_Model;
import com.oceanmtech.dmt.Model.UserRegister;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.R;
import com.oceanmtech.dmt.Welcome.WelcomeActivityHome;
import com.oceanmtech.dmt.api.ApiInterfase;
import com.oceanmtech.dmt.api.RetrofitClient;
import com.goodiebag.pinview.Pinview;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyPhoneActivity extends AppCompatActivity {
    private static final String TAG = "data-->";

    private Pinview editTextCode;
    private String mVerificationId;
    private PrefManager prefManager;
    String country;
    String mobile;
    public static String android_id;
    ProgressDialog pDialog;
    Trial_Model trial_model;
    private TextView tv_note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_verify_phone);

        pDialog = new ProgressDialog(VerifyPhoneActivity.this);
        pDialog.setTitle("OTP");
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(true);
        pDialog.setMax(100);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setCancelable(false);


        prefManager = new PrefManager(getApplicationContext());

        Intent intent = getIntent();
        mobile = intent.getStringExtra("mobile");
        country = intent.getStringExtra("country");

        editTextCode = (Pinview) findViewById(R.id.editTextCode);
        Pinview pin = new Pinview(this);
        pin.setPinBackgroundRes(R.drawable.post_logo);


        tv_note = (TextView) findViewById(R.id.tv_note);
        tv_note.setText("Please the type verification code sent\nto +" + country + " " + mobile);


        findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editTextCode.getValue().toString().trim();
                if (code.isEmpty() || code.length() < 6) {
                    editTextCode.setHint("Enter valid code");
                    editTextCode.requestFocus();
                    return;
                }

                if (code.equals(getIntent().getStringExtra("otp"))) {
                    UserRegister();
                } else {
                    Toast.makeText(VerifyPhoneActivity.this, "Please enter valid OTP", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    public void UserRegister() {
        android_id = Settings.Secure.getString(VerifyPhoneActivity.this.getContentResolver(), Settings.Secure.ANDROID_ID);
        pDialog.show();
        ApiInterfase apiInterfase = RetrofitClient.getApiClient().create(ApiInterfase.class);
        Call<UserRegister> userRegisterCall = apiInterfase.UserRegister("user_register", android_id, mobile);
        userRegisterCall.enqueue(new Callback<UserRegister>() {
            @Override
            public void onResponse(Call<UserRegister> call, Response<UserRegister> response) {

                if (response.isSuccessful()) {
                    //  speciallodinge.setVisibility(View.GONE);
                    UserRegister userRegister = response.body();
                    if (userRegister.getStatus().equals("Success")) {
                        pDialog.dismiss();

                        prefManager.setString(IConstant.IS_R_ID, String.valueOf(userRegister.getId()));
                        prefManager.setString(IConstant.MOBILE_NO,mobile);
                        prefManager.setFirstTimeLaunch(true);

                        Intent intent = new Intent(VerifyPhoneActivity.this, RegistrationActivity.class);
                        intent.putExtra("Type", "New");
                        startActivity(intent);
                        finish();

                    } else {
                        pDialog.show();
                        prefManager.setString(IConstant.IS_R_ID, String.valueOf(userRegister.getId()));
                        prefManager.setFirstTimeLaunch(true);
                        Intent intent = new Intent(VerifyPhoneActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }

                } else {
                    pDialog.show();
                    Toast.makeText(VerifyPhoneActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserRegister> call, Throwable t) {
                pDialog.show();
                Toast.makeText(VerifyPhoneActivity.this, "Server Error", Toast.LENGTH_SHORT).show();

            }

        });
    }


    private void TrialGet() {

        ApiInterfase apiInterfase = RetrofitClient.getApiClient().create(ApiInterfase.class);

        Call<Trial_Model> trial_modelCall = apiInterfase.getTrial("data", prefManager.getString(IConstant.IS_R_ID));
        trial_modelCall.enqueue(new Callback<Trial_Model>() {
            @Override
            public void onResponse(Call<Trial_Model> call, Response<Trial_Model> response) {

                if (response.isSuccessful()) {

                    trial_model = response.body();

                    if (trial_model.getStatus().equals("success")) {
                        prefManager.setistrial(true);
                        if (!prefManager.isFirstTimeLaunch()) {
                            Intent intent = new Intent(VerifyPhoneActivity.this, WelcomeActivityHome.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Intent intent = new Intent(VerifyPhoneActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    } else {
                        prefManager.setistrial(false);
                        if (!prefManager.isFirstTimeLaunch()) {
                            Intent intent = new Intent(VerifyPhoneActivity.this, WelcomeActivityHome.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Intent intent = new Intent(VerifyPhoneActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                } else {

                }

            }

            @Override
            public void onFailure(Call<Trial_Model> call, Throwable t) {

            }

        });
    }


}

