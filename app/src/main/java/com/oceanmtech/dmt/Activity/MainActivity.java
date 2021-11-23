package com.oceanmtech.dmt.Activity;

import static com.oceanmtech.dmt.Utils.NetworkDialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.messaging.FirebaseMessaging;
import com.oceanmtech.dmt.Adapter.FragmentAdapter;
import com.oceanmtech.dmt.Fragment.BussinessPostFragment;
import com.oceanmtech.dmt.Fragment.Create_Quotes_Fragment;
import com.oceanmtech.dmt.Fragment.GreetingsFragment;
import com.oceanmtech.dmt.Fragment.HomeFragment;
import com.oceanmtech.dmt.Fragment.SettingFragment;
import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.Model.Status;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.R;
import com.oceanmtech.dmt.Utils;
import com.oceanmtech.dmt.api.ApiInterfase;
import com.oceanmtech.dmt.api.RetrofitClient;
import com.oceanmtech.dmt.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "data-->";
    private static final float MIN_SCALE_DEPTH = 0.75f;
    private static final float MIN_SCALE_ZOOM = 0.85f;
    private static final float MIN_ALPHA_ZOOM = 0.5f;
    private static final float SCALE_FACTOR_SLIDE = 0.85f;
    private static final float MIN_ALPHA_SLIDE = 0.35f;
    Status status;
    ActivityMainBinding binding;
    InterstitialAd interstitialAd;
    MainActivity mContext = MainActivity.this;
    private TabLayout tablayout;
    private ViewPager viewpager;
    private SpinKitView lodinge;
    private TextView trail_text;
    private String android_id;
    private float alpha;
    private float scale;
    private float translationX;
    private MainActivity.TransformType mTransformType = TransformType.SCALING_EFFECT;

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(mContext, R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        android_id = Settings.Secure.getString(MainActivity.this.getContentResolver(), Settings.Secure.ANDROID_ID);
        find();
        init();
        ShowFullScreen();
        setPagerTransformation();
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        onCLickListener();
    }

    public void find() {
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        lodinge = (SpinKitView) findViewById(R.id.lodingemain);
        trail_text = (TextView) findViewById(R.id.trail_text);
    }

    public void init() {
        TabLayout.Tab tab1 = tablayout.newTab();
        tab1.setIcon(R.drawable.button_1);
        tab1.setText("Home");
        tab1.getIcon().setColorFilter(getResources().getColor(R.color.tabcolor1), PorterDuff.Mode.SRC_IN);
        tablayout.addTab(tab1);

        TabLayout.Tab tabbussiness = tablayout.newTab();
        tabbussiness.setIcon(R.drawable.businessicon);
        tabbussiness.setText("Bussiness Post");
        tablayout.addTab(tabbussiness);

        TabLayout.Tab tab3 = tablayout.newTab();
        tab3.setIcon(R.drawable.greetings);
        tab3.setText("Greetings");
        tablayout.addTab(tab3);

        TabLayout.Tab tab5 = tablayout.newTab();
        tab5.setIcon(R.drawable.button_2);
        tab5.setText("My Post");
        tablayout.addTab(tab5);

        TabLayout.Tab tab4 = tablayout.newTab();
        tab4.setIcon(R.drawable.button_3);
        tab4.setText("Settings");
        tablayout.addTab(tab4);

        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), tablayout.getTabCount());
        viewpager.setAdapter(adapter);

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewpager.setCurrentItem(tab.getPosition());
                tab.getIcon().setColorFilter(getResources().getColor(R.color.tabcolor1), PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        Intent intent = getIntent();
        if (intent != null) {

            if (intent.hasExtra("he")) {
                viewpager.setCurrentItem(0);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (MyApp.flagHomeAction)
            exitByBackKey();
        else {
            onHomeSelected();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, new HomeFragment()).commit();
            setVisible(View.VISIBLE, View.GONE, View.GONE, View.GONE, View.GONE);
        }
    }

    public void ShowFullScreen() {
        //interstitial FullScreenAd
        AdRequest adRequestfull = new AdRequest.Builder().build();
        interstitialAd = new InterstitialAd(MainActivity.this);
        interstitialAd.setAdUnitId(getString(R.string.ad_id_interstitial));
        interstitialAd.setAdListener(new AdListener() {

            public void onAdLoaded() {
                super.onAdLoaded();
            }

            public void onAdClosed() {
                super.onAdClosed();
                finish();
            }

            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);

            }
        });

        interstitialAd.loadAd(adRequestfull);

    }

    //9714243681
    private void exitByBackKey() {
        androidx.appcompat.app.AlertDialog alertbox = new AlertDialog.Builder(this)

                .setTitle("Oceanmtech DMT")
                .setMessage("Are you sure you want to Exit ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        if (new PrefManager(getApplicationContext()).getBoolen(IConstant.IS_PADE)) {
                            finish();

                        } else {
                            if (interstitialAd.isLoaded()) {
                                interstitialAd.show();
                            } else {
                                finish();

                            }
                            //    Toast.makeText(MainActivity.this, "You are not Premium", Toast.LENGTH_SHORT).show();
                        }


                    }
                })
                .setNegativeButton("Rate Us", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
                .show();

        alertbox.getButton(alertbox.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        alertbox.getButton(alertbox.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    private void setPagerTransformation() {
        viewpager.setPageTransformer(false, new ViewPager.PageTransformer() {
            public void transformPage(View page, float position) {
                int currentIndex = viewpager.getCurrentItem() + 1;
                switch (mTransformType) {
                    case FLOW:
                        page.setRotationY(position * -30f);
                        return;

                    case NORMAL:
                        float transformer = Math.abs(Math.abs(position) - 1);
                        page.setAlpha(transformer);
                        return;

                    case SCALING_EFFECT:
                        float normalizedposition = Math.abs(Math.abs(position) - 1);
                        page.setScaleX(normalizedposition / 2 + 0.5f);
                        page.setScaleY(normalizedposition / 2 + 0.5f);
                        return;

                    case SCALE_IN_OUT_TRANSFORMER:
                        page.setPivotX(position < 0 ? 0 : page.getWidth());
                        page.setPivotY(page.getHeight() / 2f);
                        float scale = position < 0 ? 1f + position : 1f - position;
                        page.setScaleX(scale);
                        page.setScaleY(scale);
                        return;

                    case SLIDE_OVER:
                        if (position < 0 && position > -1) {
                            // this is the page to the left
                            scale = Math.abs(Math.abs(position) - 1) * (1.0f - SCALE_FACTOR_SLIDE) + SCALE_FACTOR_SLIDE;
                            alpha = Math.max(MIN_ALPHA_SLIDE, 1 - Math.abs(position));
                            int pageWidth = page.getWidth();
                            float translateValue = position * -pageWidth;
                            if (translateValue > -pageWidth) {
                                translationX = translateValue;
                            } else {
                                translationX = 0;
                            }

                        } else {
                            alpha = 1;
                            scale = 1;
                            translationX = 0;
                        }
                        break;

                    case DEPTH:
                        if (position > 0 && position < 1) {
                            alpha = (1 - position);
                            scale = MIN_SCALE_DEPTH + (1 - MIN_SCALE_DEPTH) * (1 - Math.abs(position));
                            translationX = (page.getWidth() * -position);
                        } else {
                            alpha = 1;
                            scale = 1;
                            translationX = 0;
                        }
                        break;

                    case ZOOM:
                        if (position >= -1 && position <= 1) {
                            scale = Math.max(MIN_SCALE_ZOOM, 1 - Math.abs(position));
                            alpha = MIN_ALPHA_ZOOM +
                                    (scale - MIN_SCALE_ZOOM) / (1 - MIN_SCALE_ZOOM) * (1 - MIN_ALPHA_ZOOM);
                            float vMargin = page.getHeight() * (1 - scale) / 2;
                            float hMargin = page.getWidth() * (1 - scale) / 2;
                            if (position < 0) {
                                translationX = (hMargin - vMargin / 2);
                            } else {
                                translationX = (-hMargin + vMargin / 2);
                            }
                        } else {
                            alpha = 1;
                            scale = 1;
                            translationX = 0;
                        }
                        break;

                    default:
                        return;
                }

                page.setAlpha(alpha);
                page.setTranslationX(translationX);
                page.setScaleX(scale);
                page.setScaleY(scale);
            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Utils.isNetworkConnected(getApplicationContext())) {
            StatusGet();
        } else {
            NetworkDialog(MainActivity.this);
        }

    }

    private void StatusGet() {
        ApiInterfase apiInterfase = RetrofitClient.getApiClient().create(ApiInterfase.class);
        Call<Status> statusCall = apiInterfase.GetStatus("status", android_id);
        statusCall.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {

                if (response.isSuccessful()) {
                    status = response.body();
                    new PrefManager(MainActivity.this).setString(IConstant.FbBannerAd, status.getFBannerAd());
                    new PrefManager(MainActivity.this).setString(IConstant.FbFullAd, status.getFFullPageAd());
                    new PrefManager(MainActivity.this).setString(IConstant.FbMediumRectAd, status.getFMediumRectangleAd());

                    if (status.getStatus().equals("success")) {
                        new PrefManager(getApplicationContext()).setisstatus(true);
                    } else if (status.getStatus().equals("Error")) {
                        new PrefManager(getApplicationContext()).setisstatus(false);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NotNull Call<Status> call, @NotNull Throwable t) {

            }

        });
    }

    private void onCLickListener() {
        onHomeSelected();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, new HomeFragment()).commit();
        binding.llHome.setOnClickListener(v -> {
            onHomeSelected();
            fragmentManager.beginTransaction().replace(R.id.container, new HomeFragment()).commit();
        });
        binding.llBusiness.setOnClickListener(v -> {
            onBusinessSelected();
            fragmentManager.beginTransaction().replace(R.id.container, new BussinessPostFragment()).commit();
        });
        binding.llMyGreeting.setOnClickListener(v -> {
            onMyGreetingSelected();
            fragmentManager.beginTransaction().replace(R.id.container, new GreetingsFragment()).commit();
        });
        binding.llMyPost.setOnClickListener(v -> {
            onMyPostSelected();
            fragmentManager.beginTransaction().replace(R.id.container, new Create_Quotes_Fragment()).commit();
        });
        binding.llSetting.setOnClickListener(v -> {
            onSettingSelected();
            fragmentManager.beginTransaction().replace(R.id.container, new SettingFragment()).commit();
        });
    }

    private void onHomeSelected() {
        setLayoutParam(mParamMain(), mParamSub(), mParamSub(), mParamSub(), mParamSub());
        setVisible(View.VISIBLE, View.GONE, View.GONE, View.GONE, View.GONE);
        setTextSize(14, 9, 9, 9, 9);
        setColor(R.color.white, R.color.bottombar_unselection, R.color.bottombar_unselection, R.color.bottombar_unselection,
                R.color.bottombar_unselection);
    }

    private void onBusinessSelected() {
        setLayoutParam(mParamSub(), mParamMain(), mParamSub(), mParamSub(), mParamSub());
        setVisible(View.GONE, View.VISIBLE, View.GONE, View.GONE, View.GONE);
        setTextSize(9, 14, 9, 9, 9);
        setColor(R.color.bottombar_unselection, R.color.white, R.color.bottombar_unselection, R.color.bottombar_unselection,
                R.color.bottombar_unselection);
    }

    private void onMyGreetingSelected() {
        setLayoutParam(mParamSub(), mParamSub(), mParamMain(), mParamSub(), mParamSub());
        setVisible(View.GONE, View.GONE, View.VISIBLE, View.GONE, View.GONE);
        setTextSize(9, 9, 14, 9, 9);
        setColor(R.color.bottombar_unselection, R.color.bottombar_unselection, R.color.white, R.color.bottombar_unselection,
                R.color.bottombar_unselection);
    }

    private void onMyPostSelected() {
        setLayoutParam(mParamSub(), mParamSub(), mParamSub(), mParamMain(), mParamSub());
        setVisible(View.GONE, View.GONE, View.GONE, View.VISIBLE, View.GONE);
        setTextSize(9, 9, 9, 14, 9);
        setColor(R.color.bottombar_unselection, R.color.bottombar_unselection, R.color.bottombar_unselection, R.color.white,
                R.color.bottombar_unselection);
    }

    private void onSettingSelected() {
        setLayoutParam(mParamSub(), mParamSub(), mParamSub(), mParamSub(), mParamMain());
        setVisible(View.GONE, View.GONE, View.GONE, View.GONE, View.VISIBLE);
        setTextSize(9, 9, 9, 9, 14);
        setColor(R.color.bottombar_unselection, R.color.bottombar_unselection, R.color.bottombar_unselection, R.color.bottombar_unselection,
                R.color.white);
    }

    private void setColor(int value1, int value2, int value3, int value4, int value5) {
        binding.tvHome.setTextColor(getResources().getColor(value1));
        binding.tvBusiness.setTextColor(getResources().getColor(value2));
        binding.tvMyGreeting.setTextColor(getResources().getColor(value3));
        binding.tvMyPosts.setTextColor(getResources().getColor(value4));
        binding.tvSetting.setTextColor(getResources().getColor(value5));
        binding.ivHome.setColorFilter(ContextCompat.getColor(mContext, value1), android.graphics.PorterDuff.Mode.MULTIPLY);
        binding.ivBusiness.setColorFilter(ContextCompat.getColor(mContext, value2), android.graphics.PorterDuff.Mode.MULTIPLY);
        binding.ivGreetings.setColorFilter(ContextCompat.getColor(mContext, value3), android.graphics.PorterDuff.Mode.MULTIPLY);
        binding.ivMyPosts.setColorFilter(ContextCompat.getColor(mContext, value4), android.graphics.PorterDuff.Mode.MULTIPLY);
        binding.ivSettings.setColorFilter(ContextCompat.getColor(mContext, value5), android.graphics.PorterDuff.Mode.MULTIPLY);
    }

    private void setLayoutParam(LinearLayout.LayoutParams value1, LinearLayout.LayoutParams value2, LinearLayout.LayoutParams value3,
                                LinearLayout.LayoutParams value4, LinearLayout.LayoutParams value5) {
        binding.tvHome.setLayoutParams(value1);
        binding.tvBusiness.setLayoutParams(value2);
        binding.tvMyGreeting.setLayoutParams(value3);
        binding.tvMyPosts.setLayoutParams(value4);
        binding.tvSetting.setLayoutParams(value5);
    }

    public void setTextSize(int value1, int value2, int value3, int value4, int value5) {
        binding.tvHome.setTextSize(value1);
        binding.tvBusiness.setTextSize(value2);
        binding.tvMyGreeting.setTextSize(value3);
        binding.tvMyPosts.setTextSize(value4);
        binding.tvSetting.setTextSize(value5);
    }

    public LinearLayout.LayoutParams mParamMain() {
        Resources r = getResources();
        int px3 = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                3,
                r.getDisplayMetrics());
        int px17 = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                17,
                r.getDisplayMetrics());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, px3, 0, px17);
        return params;
    }

    public LinearLayout.LayoutParams mParamSub() {
        Resources r = getResources();
        int px3 = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                3,
                r.getDisplayMetrics());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, px3, 0, 0);
        return params;
    }

    private void setVisible(int value1, int value2, int value3, int value4, int value5) {
        binding.ivLayer1.setVisibility(value1);
        binding.ivLayer2.setVisibility(value2);
        binding.ivLayer3.setVisibility(value3);
        binding.ivLayer4.setVisibility(value4);
        binding.ivLayer5.setVisibility(value5);
    }

    public enum TransformType {
        FLOW,
        DEPTH,
        ZOOM,
        SLIDE_OVER,
        SCALE_IN_OUT_TRANSFORMER,
        NORMAL,
        SCALING_EFFECT
    }

}