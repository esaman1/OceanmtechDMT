package com.oceanmtech.dmt.Activity;

import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.gson.Gson;
import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.Model.Status;
import com.oceanmtech.dmt.Model.Trial_Model;
import com.oceanmtech.dmt.Notification.Notification_Model;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.R;
import com.oceanmtech.dmt.Utils;
import com.oceanmtech.dmt.Welcome.WelcomeActivityHome;
import com.oceanmtech.dmt.api.ApiInterfase;
import com.oceanmtech.dmt.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.oceanmtech.dmt.Utils.NetworkDialog;

public class Splashscreen extends AppCompatActivity {
    private ImageView imageload;
    private ImageView imageicon;
    private RelativeLayout r_layout;
    private TextView text;
    private TextView appname;
    private ImageView logo;
    private PrefManager prefManager;
    Status status;
    ApiInterfase apiInterface;

    Trial_Model trial_model;
    private String android_id;
    private FrameLayout nointernet;

    Notification_Model notification_model;

    Notification notification;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);
        android_id = Settings.Secure.getString(Splashscreen.this.getContentResolver(), Settings.Secure.ANDROID_ID);
        prefManager = new PrefManager(this);
        if (!IConstant.FOLDER_PATH.exists()) {
            if (IConstant.FOLDER_PATH.mkdirs()) {

            }
        }
        find();

        if (Utils.isNetworkConnected(getApplicationContext())) {
            init();

        } else {
            NetworkDialog(Splashscreen.this);
        }

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (task.isSuccessful()) {
                    token = task.getResult().getToken();

//                    getNotification();

                }
            }
        });

        // TrialGet();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void getNotification() {
        apiInterface = RetrofitClient.getApiClient().create(ApiInterfase.class);
        apiInterface.getnotification("notification", token).enqueue(new Callback<Notification_Model>() {
            @Override
            public void onResponse(Call<Notification_Model> call, Response<Notification_Model> response) {
                notification_model = response.body();
                if (notification_model != null) {
                    if (notification_model.getStatus().equals("Success")) {

                    } else {

                    }

                } else {

                }
            }

            @Override
            public void onFailure(Call<Notification_Model> call, Throwable t) {

            }
        });
    }

    public void find() {

        appname = (TextView) findViewById(R.id.appname);
        logo = (ImageView) findViewById(R.id.logo);
        nointernet = (FrameLayout) findViewById(R.id.nointernet);

        Animation anim = new AlphaAnimation(0.5f, 5.0f);
        anim.setDuration(150);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        appname.startAnimation(anim);

    }

    public void init() {

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                StatusGet();
            }

        }, 2000);
    }


    private void TrialGet() {

        ApiInterfase apiInterfase = RetrofitClient.getApiClient().create(ApiInterfase.class);

        Call<Trial_Model> trial_modelCall = apiInterfase.getTrial("data", android_id);
        trial_modelCall.enqueue(new Callback<Trial_Model>() {
            @Override
            public void onResponse(Call<Trial_Model> call, Response<Trial_Model> response) {

                if (response.isSuccessful()) {

                    trial_model = response.body();

                    if (trial_model.getStatus().equals("success")) {
                        prefManager.setistrial(true);
                        if (!prefManager.isFirstTimeLaunch()) {
                            Intent intent = new Intent(Splashscreen.this, WelcomeActivityHome.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Intent intent = new Intent(Splashscreen.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    } else {
                        prefManager.setistrial(false);
                        if (!prefManager.isFirstTimeLaunch()) {
                            Intent intent = new Intent(Splashscreen.this, WelcomeActivityHome.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Intent intent = new Intent(Splashscreen.this, MainActivity.class);
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

    private void StatusGet() {
        ApiInterfase apiInterfase = RetrofitClient.getApiClient().create(ApiInterfase.class);
        Call<Status> statusCall = apiInterfase.GetStatus("status", android_id);
        statusCall.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {

                if (response.isSuccessful()) {

                    status = response.body();
                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.w("SagarSagar",""+successResponse);
                    android_id = Settings.Secure.getString(Splashscreen.this.getContentResolver(),
                            Settings.Secure.ANDROID_ID);

                    prefManager.setString(IConstant.APIKEY, status.getApiKey());
                    prefManager.setString(IConstant.SENDERID, status.getSender());
                    prefManager.setString(IConstant.FbBannerAd, status.getFBannerAd());
                    prefManager.setString(IConstant.FbFullAd, status.getFFullPageAd());
                    prefManager.setString(IConstant.FbMediumRectAd, status.getFMediumRectangleAd());


                    if (status.getStatus().equals("success")) {
                        prefManager.setisstatus(true);
                        if (!prefManager.isFirstTimeLaunch()) {
                            Intent intent = new Intent(Splashscreen.this, WelcomeActivityHome.class);
                            intent.putExtra("api_key", status.getApiKey());
                            intent.putExtra("sender", status.getSender());
                            startActivity(intent);
                            finish();

                        } else {
                            Intent intent = new Intent(Splashscreen.this, MainActivity.class);
                            intent.putExtra("api_key", status.getApiKey());
                            intent.putExtra("sender", status.getSender());
                            startActivity(intent);
                            finish();
                        }

                    } else if (status.getStatus().equals("Error")) {
                        prefManager.setisstatus(false);
                        if (!prefManager.isFirstTimeLaunch()) {
                            Intent intent = new Intent(Splashscreen.this, WelcomeActivityHome.class);
                            intent.putExtra("api_key", status.getApiKey());
                            intent.putExtra("sender", status.getSender());
                            startActivity(intent);
                            finish();

                        } else {
                            Intent intent = new Intent(Splashscreen.this, MainActivity.class);
                            intent.putExtra("api_key", status.getApiKey());
                            intent.putExtra("sender", status.getSender());
                            startActivity(intent);
                            finish();
                        }

                    } else {

                        prefManager.setisstatus(true);
                        if (!prefManager.isFirstTimeLaunch()) {
                            Intent intent = new Intent(Splashscreen.this, WelcomeActivityHome.class);
                            intent.putExtra("api_key", status.getApiKey());
                            intent.putExtra("sender", status.getSender());
                            startActivity(intent);
                            finish();

                        } else {
                            Intent intent = new Intent(Splashscreen.this, MainActivity.class);
                            intent.putExtra("api_key", status.getApiKey());
                            intent.putExtra("sender", status.getSender());
                            startActivity(intent);
                            finish();
                        }
                    }

                } else {

                }


                if (status.getPaid().equals("true")) {

                    // Toast.makeText(Splashscreen.this, "Paid 6e", Toast.LENGTH_SHORT).show();
                    prefManager.setBoolen(IConstant.IS_PADE, true);

                } else {
                    //   Toast.makeText(Splashscreen.this, "Paid Nathi", Toast.LENGTH_SHORT).show();

                    prefManager.setBoolen(IConstant.IS_PADE, false);
                }


            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {

            }

        });
    }


    public boolean internet() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) Splashscreen.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            connected = true;
        } else {
            connected = false;
        }
        return connected;
    }

    private void internetoff() {
        androidx.appcompat.app.AlertDialog alertbox = new AlertDialog.Builder(Splashscreen.this)
                .setTitle("Not Conacted")
                .setCancelable(false)
                .setMessage("Please turn on Internet connection and Try again !")
                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        // nointernet.setVisibility(View.VISIBLE);
                        //lodinge.setVisibility(View.GONE);

                        onResume();
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Splashscreen.this.finish();
                    }
                })
                .show();

        alertbox.getButton(alertbox.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.textcolor));
        alertbox.getButton(alertbox.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.textcolor));
    }

}
