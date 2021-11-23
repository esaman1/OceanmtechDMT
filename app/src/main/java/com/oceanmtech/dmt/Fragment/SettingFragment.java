package com.oceanmtech.dmt.Fragment;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.oceanmtech.dmt.Activity.BussnessListActivity;
import com.oceanmtech.dmt.Activity.HelpSupport;
import com.oceanmtech.dmt.Activity.MyApp;
import com.oceanmtech.dmt.Activity.OTPActivity;
import com.oceanmtech.dmt.Activity.Our_appActivity;
import com.oceanmtech.dmt.Activity.PremiumActivity;
import com.oceanmtech.dmt.Activity.RegistrationActivity;
import com.oceanmtech.dmt.DataBase;
import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.NewPremium.PaymentGatewayActivity;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.PrefManager1;
import com.oceanmtech.dmt.PreferencesHandler;
import com.oceanmtech.dmt.QuoteCreater.Constans;
import com.oceanmtech.dmt.R;

import java.io.File;

public class SettingFragment extends Fragment {

    private TextView /*button_1,*/ button_6;
    private LinearLayout share_button;
    private TextView logout;
    private PrefManager prefManager;
    Dialog dialog;
    private TextView my_all_business;
    DataBase dataBase;
    private TextView u_logo;
    private TextView u_edit;

    PrefManager1 prefManager1;
    private LinearLayout button_4;
    private LinearLayout button_2;
    private LinearLayout button_5;
    private LinearLayout llMyProfile;
    private LinearLayout rl_premium;
    private FrameLayout ad_Layout;
    AdView adView;
    private TextView Frame_select_button;

    BussinessPostFragment bussinessPostFragment;
    Create_Quotes_Fragment create_quotes_fragment;
    GreetingsFragment greetingsFragment;
    HomeFragment homeFragment;
    SettingFragment settingFragment;

    PreferencesHandler preferencesHandler;
    //    private Button b_payu;
    private ImageView crad6;
    private ImageView crad7;
    private LinearLayout our;
    private LinearLayout tm;

    @Override
    public void onResume() {
        super.onResume();
        MyApp.flagHomeAction=false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        bussinessPostFragment = new BussinessPostFragment();
        create_quotes_fragment = new Create_Quotes_Fragment();
        greetingsFragment = new GreetingsFragment();
        homeFragment = new HomeFragment();
        settingFragment = new SettingFragment();

        dataBase = new DataBase(getActivity());

        prefManager = new PrefManager(getActivity());
        prefManager1 = new PrefManager1(getActivity());
        preferencesHandler = new PreferencesHandler();

//        button_1 = (TextView) view.findViewById(R.id.button_1);
        button_4 = view.findViewById(R.id.button_4);
        button_2 = view.findViewById(R.id.button_2);
        share_button = view.findViewById(R.id.share_button);
        logout = view.findViewById(R.id.logout);
        u_logo = view.findViewById(R.id.u_logo);
        llMyProfile = view.findViewById(R.id.llMyProfile);
//        u_edit = view.findViewById(R.id.u_edit);
        rl_premium = view.findViewById(R.id.Premium);
        ad_Layout = (FrameLayout) view.findViewById(R.id.ad_Layout);
//        Frame_select_button = (TextView) view.findViewById(R.id.Frame_select_button);
//        b_payu = (Button) view.findViewById(R.id.b_payu);
        crad6 = view.findViewById(R.id.crad6);
        crad7 = view.findViewById(R.id.crad7);

        button_5 =  view.findViewById(R.id.button_5);
        our =  view.findViewById(R.id.our);
        tm =  view.findViewById(R.id.tm);
        retraivedata();

        Click();
        loadBanner();

        prefManager1.connectDB();
        File file = new File(IConstant.BUSINESS_LOGO_PATH);
//        u_logo.setImageURI(Uri.fromFile(file));
        prefManager1.closeDB();

        if (new PrefManager(getActivity()).getBoolen(IConstant.IS_PADE)) {
            ad_Layout.setVisibility(View.GONE);
            rl_premium.setVisibility(View.GONE);
//            iview.setVisibility(View.GONE);
        } else {
            rl_premium.setVisibility(View.VISIBLE);
//            iview.setVisibility(View.VISIBLE);
        }
        return view;
    }

    public void gotoinstagram() {

        Uri uri = Uri.parse("http://instagram.com/_u/brandbox_app");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/brandbox_app")));
        }

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

    public void retraivedata() {


        if (Constans.isLogo) {
            File file = new File(IConstant.BUSINESS_LOGO_PATH);
//            u_logo.setImageURI(Uri.fromFile(file));
        } else {
//            u_logo.setVisibility(View.GONE);
        }


    }

    public void gotofacebook() {

        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/brandboxapp/?ti=as"));
            startActivity(intent);
        } catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/brandboxapp/?ti=as")));
        }


    }


    private void Click() {

        tm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://brandbox.webtechinfoway.in/termsandcondition.php")));

            }
        });

        our.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Our_appActivity.class);
                startActivity(intent);
            }
        });

//        b_payu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), PayUActivity.class);
//                startActivity(intent);
//            }
//        });


        crad7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotofacebook();
            }
        });

//        Frame_select_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                Intent intent = new Intent(getActivity(), FrameListActivity.class);
//                startActivity(intent);
//            }
//        });

        rl_premium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (PreferencesHandler.getStringPreference(getContext(), Constans.INDIA).equals("91")) {
                    Intent intent = new Intent(getActivity(), PaymentGatewayActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getActivity(), "ALL", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), PremiumActivity.class);
                    startActivity(intent);
                }
            }
        });

        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Uri uri = Uri.parse("smsto:" + "8866747888");
//                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
//                i.putExtra("sms_body", "Hello");
//                i.setPackage("com.whatsapp");
//                getActivity().startActivity(i);
                Intent intent = new Intent(getActivity(), HelpSupport.class);
                startActivity(intent);
            }
        });

        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getActivity().getPackageName())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://brandbox.webtechinfoway.in/privacypolicy.php")));
//                https://youngbreedtechnologies.blogspot.com/2020/10/festival-post-maker.html
            }
        });

//        u_logo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), RegistrationActivity.class);
//                intent.putExtra("Type", "Edit");
//                startActivity(intent);
//            }
//        });
        llMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), BussnessListActivity.class);
                intent.putExtra("Type", "Edit");
                startActivity(intent);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setContentView(R.layout.downloag_dialog);
                dialog.setCancelable(false);
                ((TextView) dialog.findViewById(R.id.no)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                ((TextView) dialog.findViewById(R.id.videoTitle)).setText(R.string.are_you_sure_to_download);

                ((TextView) dialog.findViewById(R.id.videoDelete)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {

                        Intent intent = new Intent(getActivity(), OTPActivity.class);
                        prefManager.setFirstTimeLaunch(false);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        Toast.makeText(getActivity(), "Successfully Logout", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }

                });

                dialog.show();

            }
        });


//        button_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dataBase.deleteAll();
//                Intent intent = new Intent(getActivity(), RegistrationActivity.class);
//                intent.putExtra("Type", "New");
//                startActivity(intent);
//            }
//
//        });


        share_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Intent.EXTRA_TEXT, "Oceanmtech DMT on Google Play!\n" +
                        "\n" + "https://play.google.com/store/apps/details?id=" + getActivity().getPackageName());
                intent.setType("text/*");
                startActivity(Intent.createChooser(intent, "Share Via"));
            }
        });

        crad6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoinstagram();
            }
        });

    }

}
