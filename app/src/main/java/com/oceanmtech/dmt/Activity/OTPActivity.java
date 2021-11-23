package com.oceanmtech.dmt.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.rilixtech.widget.countrycodepicker.Country;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;
import com.oceanmtech.dmt.QuoteCreater.Constans;
import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.Model.OtpModel;
import com.oceanmtech.dmt.Model.UserRegister;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.PreferencesHandler;
import com.oceanmtech.dmt.R;
import com.oceanmtech.dmt.Utils;
import com.oceanmtech.dmt.api.ApiInterfase;
import com.oceanmtech.dmt.api.RetrofitClient;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPActivity extends AppCompatActivity {

    private static final String TAG = "Data-->";
    private String verificationCode;
    private EditText editTextMobile;
    private ImageView buttonContinue;
    CountryCodePicker ccp;
    String mobile, selectedCountry;
    ProgressDialog dialog;
    private PrefManager prefManager;
    private PreferencesHandler preferencesHandler;
    public static String android_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_o_t_p);
        prefManager = new PrefManager(getApplicationContext());
        preferencesHandler = new PreferencesHandler();
        dialog = new ProgressDialog(OTPActivity.this);
        dialog.setCancelable(false);
        dialog.setMessage("Please wait...");

        editTextMobile = (EditText) findViewById(R.id.editTextMobile);
        buttonContinue =  findViewById(R.id.buttonContinue);
        ccp = (CountryCodePicker) findViewById(R.id.ccp);

        // ccp.registerPhoneNumberTextView(editTextMobile);

        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected(Country selectedCountry) {
                // selectedCountry.getPhoneCode();
            }
        });

        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent =new Intent(OTPActivity.this,MainActivity.class);
//                startActivity(intent);

                mobile = editTextMobile.getText().toString().trim();
                selectedCountry = ccp.getSelectedCountryCode();
                PreferencesHandler.setStringPreference(OTPActivity.this, Constans.INDIA, selectedCountry);
              //  Toast.makeText(OTPActivity.this, ""+PreferencesHandler.getStringPreference(OTPActivity.this,Constans.INDIA), Toast.LENGTH_SHORT).show();
                if (selectedCountry.equals("91")) {
                    if (mobile.isEmpty() || mobile.length() < 10) {

                        editTextMobile.setError("Enter a valid mobile");
                        editTextMobile.requestFocus();
                        return;
                    }
                    UserRegister();
                  //  GetOTP(mobile, getOTPString());
                } else {
                    if (mobile.isEmpty()) {
                        editTextMobile.setError("Enter a valid mobile");
                        editTextMobile.requestFocus();
                        return;
                    }
                    UserRegister();
                }
            }
        });
    }

    public void UserRegister() {
        android_id = Settings.Secure.getString(OTPActivity.this.getContentResolver(), Settings.Secure.ANDROID_ID);
        dialog.show();
        ApiInterfase apiInterfase = RetrofitClient.getApiClient().create(ApiInterfase.class);
        Call<UserRegister> userRegisterCall = apiInterfase.UserRegister("user_register", android_id, mobile);
        userRegisterCall.enqueue(new Callback<UserRegister>() {
            @Override
            public void onResponse(Call<UserRegister> call, Response<UserRegister> response) {

                if (response.isSuccessful()) {
                    //  speciallodinge.setVisibility(View.GONE);
                    UserRegister userRegister = response.body();
                    if (userRegister.getStatus().equals("Success")) {
                        dialog.dismiss();

                        prefManager.setString(IConstant.IS_R_ID, String.valueOf(userRegister.getId()));
                        prefManager.setString(IConstant.MOBILE_NO, mobile);
                        prefManager.setFirstTimeLaunch(true);

                        Intent intent = new Intent(OTPActivity.this, RegistrationActivity.class);
                        intent.putExtra("Type", "New");
                        startActivity(intent);
                        finish();

                    } else {
                        dialog.show();
                        prefManager.setString(IConstant.IS_R_ID, String.valueOf(userRegister.getId()));
                        prefManager.setFirstTimeLaunch(true);
                        Intent intent = new Intent(OTPActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }

                } else {
                    dialog.show();
                    Toast.makeText(OTPActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserRegister> call, Throwable t) {
                dialog.show();
                Toast.makeText(OTPActivity.this, "Server Error", Toast.LENGTH_SHORT).show();

            }

        });
    }


    protected String getOTPString() {
        String SALTCHARS = "0123456789";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    private void GetOTP(String mobile, String Otp) {
        dialog.show();
        ApiInterfase apiInterfase = RetrofitClient.getApiClient().create(ApiInterfase.class);
        Call<OtpModel> business_get_modelCall = apiInterfase.get_otp("sms", new PrefManager(OTPActivity.this).getString(IConstant.APIKEY), mobile, new PrefManager(OTPActivity.this).getString(IConstant.SENDERID), Otp + Utils.MsgOTP, "JSON", "0");
        business_get_modelCall.enqueue(new Callback<OtpModel>() {
            @Override
            public void onResponse(Call<OtpModel> call, Response<OtpModel> response) {

                if (response.isSuccessful()) {
                    OtpModel otpModel = response.body();

                    if (otpModel.getData().get0().getStatus().equals("AWAITED-DLR")) {
                        dialog.dismiss();
                        Intent intent = new Intent(OTPActivity.this, VerifyPhoneActivity.class);
                        intent.putExtra("country", selectedCountry);
                        intent.putExtra("mobile", mobile);
                        intent.putExtra("otp", Otp);
                        startActivity(intent);
                    } else {
                        dialog.dismiss();
                        Toast.makeText(OTPActivity.this, "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<OtpModel> call, Throwable t) {
                dialog.dismiss();
            }
        });
    }

}
