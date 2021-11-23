package com.oceanmtech.dmt.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.oceanmtech.dmt.Activity.Custom_Activity;
import com.oceanmtech.dmt.Activity.MyApp;
import com.oceanmtech.dmt.Activity.PremiumActivity;
import com.oceanmtech.dmt.Adapter.Festival_Adapter;
import com.oceanmtech.dmt.QuoteCreater.Constans;
import com.oceanmtech.dmt.CreateLogo.Logo_B_NActivity;
import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.Model.Business_Get_model;
import com.oceanmtech.dmt.Model.Festival_M;
import com.oceanmtech.dmt.Model.Home_Data_Model;
import com.oceanmtech.dmt.Model.SliderModel;
import com.oceanmtech.dmt.NewPremium.PaymentGatewayActivity;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.PreferencesHandler;
import com.oceanmtech.dmt.R;
import com.oceanmtech.dmt.Adapter.ViewPagerAdapter;
import com.oceanmtech.dmt.Model.UserData1;
import com.oceanmtech.dmt.Utils;
import com.oceanmtech.dmt.api.ApiInterfase;
import com.oceanmtech.dmt.api.RetrofitClient;
import com.oceanmtech.dmt.home_adapters.VerticalRecyclerViewAdapter;
import com.github.ybq.android.spinkit.SpinKitView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private static final String TAG = "Data-->";
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    private ViewPager viewPager;
    private RecyclerView specialcategoryrecycler;
    public static ArrayList<UserData1> categoryModels;
    public static ArrayList<Home_Data_Model> home_data_models;
    FrameLayout adContainerView;
    SpinKitView alldatalodinge;


    private LinearLayout layout_dots;
    private AdapterImageSlider adapterImageSlider;
    private Runnable runnable = null;
    private Handler handler = new Handler();
    public String id;

    VerticalRecyclerViewAdapter mAdapter;
    private RecyclerView rvVertical;

    Festival_M festival_m;
    Home_Data_Model homedatamodel;
    ArrayList<Festival_M.Category> FestivalModel;
    Dialog dialog;
    Festival_Adapter festival_adapter;

    private TextView name,tvUpcomming;
    public static ImageView u_logo;
    //private LinearLayout u_edit;
    private RecyclerView Ucoming;
    private FrameLayout nointernet;
    private SwipeRefreshLayout swipeallimage;
    ProgressDialog progressDialog;
    SliderModel sliderModel;
    private LinearLayout Premium;

    CardView custom_img;
    BussinessPostFragment bussinessPostFragment;
    Create_Quotes_Fragment create_quotes_fragment;
    GreetingsFragment greetingsFragment;
    HomeFragment homeFragment;
    SettingFragment settingFragment;
    private CardView logo_create;
    private ImageView premium_logo;
    private CardView pgon;
    private ImageView video;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        bussinessPostFragment = new BussinessPostFragment();
        create_quotes_fragment = new Create_Quotes_Fragment();
        greetingsFragment = new GreetingsFragment();
        homeFragment = new HomeFragment();
        settingFragment = new SettingFragment();

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait..");
        progressDialog.setCancelable(false);


        // ButterKnife.bind(getActivity());
        categoryModels = new ArrayList<>();
        FestivalModel = new ArrayList<>();
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        sliderDotspanel = (LinearLayout) view.findViewById(R.id.layout_dots);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity());
        viewPager.setAdapter(viewPagerAdapter);
        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        specialcategoryrecycler = (RecyclerView) view.findViewById(R.id.specialcategoryrecycler);
        alldatalodinge = (SpinKitView) view.findViewById(R.id.alldatalodinge);


        layout_dots = view.findViewById(R.id.layout_dots);
        viewPager = view.findViewById(R.id.pager);
        rvVertical = (RecyclerView) view.findViewById(R.id.rvVertical);
        Ucoming = (RecyclerView) view.findViewById(R.id.Ucoming);

        name = (TextView) view.findViewById(R.id.name);
        tvUpcomming = (TextView) view.findViewById(R.id.tvUpcomming);
        u_logo = (ImageView) view.findViewById(R.id.u_logo);
        //  u_edit = (LinearLayout) view.findViewById(R.id.u_edit);
        nointernet = (FrameLayout) view.findViewById(R.id.nointernet);
        swipeallimage = (SwipeRefreshLayout) view.findViewById(R.id.swipeallimage);
        Premium = (LinearLayout) view.findViewById(R.id.Premium);
        custom_img = (CardView) view.findViewById(R.id.custom_img);
        logo_create = (CardView) view.findViewById(R.id.logo_create);
        premium_logo = (ImageView) view.findViewById(R.id.premium_logo);
        pgon = (CardView) view.findViewById(R.id.pgon);

        if (Utils.isNetworkConnected(getActivity())) {
            callGetBussiness();

            specialinitView();
        } else {
            Toast.makeText(getActivity(), "Please Check Internet Connection!!", Toast.LENGTH_SHORT).show();
        }

        Refresh();
        alldatalodinge.setVisibility(View.VISIBLE);
        Slider();
        Click();


        if (new PrefManager(getContext()).getBoolen(IConstant.IS_PADE)) {
            premium_logo.setVisibility(View.VISIBLE);
            pgon.setVisibility(View.GONE);
        } else {
            premium_logo.setVisibility(View.GONE);
            pgon.setVisibility(View.VISIBLE);
            //      Toast.makeText(Gujarati_Suvichar_View.this, "You are not Premium", Toast.LENGTH_SHORT).show();
        }


        return view;
    }

    private void callGetBussiness() {
        final ApiInterfase apiInterface = RetrofitClient.getApiClient().create(ApiInterfase.class);
        apiInterface.get_Business("business", new PrefManager(getActivity()).getString(IConstant.IS_R_ID)).enqueue(new Callback<Business_Get_model>() {
            @Override
            public void onResponse(Call<Business_Get_model> call, Response<Business_Get_model> response) {
                Business_Get_model get_bussuineMode = response.body();
                if (response.isSuccessful()) {

                    if (get_bussuineMode.getStatus().equals("success") && !get_bussuineMode.getBusiness().isEmpty()) {

                        for (int i = 0; i < get_bussuineMode.getBusiness().size(); i++)
                        {
                            Business_Get_model.Business auction = get_bussuineMode.getBusiness().get(i);
                            if (new PrefManager(getActivity()).getString(IConstant.BID)!=null & !TextUtils.isEmpty(new PrefManager(getActivity()).getString(IConstant.BID)) & new PrefManager(getActivity()).getString(IConstant.BID).equals(auction.getBId()))
                            {
                                setBusinessData(get_bussuineMode.getBusiness().get(i).getBId(),
                                        get_bussuineMode.getBusiness().get(i).getBName(),
                                        get_bussuineMode.getBusiness().get(i).getBEmail(),
                                        get_bussuineMode.getBusiness().get(i).getBMobileNumber(),
                                        get_bussuineMode.getBusiness().get(i).getBAddress(),
                                        get_bussuineMode.getBusiness().get(i).getBWebsite(),
                                        get_bussuineMode.getBusiness().get(i).getBLogo());
                            }
                        }

                    } else {
                        setBusinessData("", "", "", "", "", "", "");
                    }

                } else {

                }
            }

            @Override
            public void onFailure(Call<Business_Get_model> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public void setBusinessData(String bid, String businessName, String businessEmail, String businessMobile, String businessAddress, String businesswebsite, String logo) {
        new PrefManager(getActivity()).setString(IConstant.BID, bid);
        new PrefManager(getActivity()).setString(IConstant.BUSINESS_NAME, businessName);
        new PrefManager(getActivity()).setString(IConstant.BUSINESS_EMAIL, businessEmail);
        new PrefManager(getActivity()).setString(IConstant.MOBILE_NO, businessMobile);
        new PrefManager(getActivity()).setString(IConstant.ADDRESS, businessAddress);
        new PrefManager(getActivity()).setString(IConstant.WEBSITE, businesswebsite);
        new PrefManager(getActivity()).setString(IConstant.LOGO, logo);

        if (!logo.isEmpty()) {
            new DownloadTask().execute(stringToURL(logo));
        }

        retraivedata();
    }

    public void Refresh() {
        swipeallimage.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                specialinitView();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeallimage.setRefreshing(false);
                    }
                }, 4000);
            }
        });
    }

    private void setDataOnVerticalRecyclerView(Home_Data_Model home_data_model) {
        for (int i = 0; i <= home_data_model.getCategory().size(); i++) {

            for (int j = 0; j <= 5; j++) {
                Collections.reverse(home_data_model.getCategory());

                rvVertical.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                mAdapter = new VerticalRecyclerViewAdapter(getActivity(), home_data_model.getCategory());
                rvVertical.setAdapter(mAdapter);

            }

        }

        mAdapter.notifyDataSetChanged();
    }

    public void retraivedata() {
        try {
            if (!new PrefManager(getActivity()).getString(IConstant.LOGO).isEmpty()) {
                File file = new File(IConstant.BUSINESS_LOGO_PATH);
                u_logo.setImageURI(Uri.fromFile(file));
            } else {
                u_logo.setVisibility(View.GONE);
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        name.setText(new PrefManager(getActivity()).getString(IConstant.BUSINESS_NAME));
    }

    private void Click() {


        logo_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Logo_B_NActivity.class);
                startActivity(intent);

            }
        });


        pgon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PreferencesHandler.getStringPreference(getContext(), Constans.INDIA).equals("91")) {
                    Intent intent = new Intent(getActivity(), PaymentGatewayActivity.class);
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(getActivity(), PremiumActivity.class);
                    startActivity(intent);
                }
            }
        });


        custom_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Custom_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void specialinitView() {
        ApiInterfase apiInterfase = RetrofitClient.getApiClient().create(ApiInterfase.class);
        Call<Home_Data_Model> categoryModelscall = apiInterfase.getCategory1("home_data");
        categoryModelscall.enqueue(new Callback<Home_Data_Model>() {
            @Override
            public void onResponse(Call<Home_Data_Model> call, Response<Home_Data_Model> response) {
                if (response.isSuccessful()) {
                    homedatamodel = response.body();
                    if (homedatamodel.getStatus().equals("success")) {

                        Festival();
                        alldatalodinge.setVisibility(View.GONE);
                        Home_Data_Model home_data_model = response.body();
                        setDataOnVerticalRecyclerView(home_data_model);

                    } else {

                        Toast.makeText(getActivity(), "NO DATA FOUND", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    alldatalodinge.setVisibility(View.GONE);

                }

            }

            @Override
            public void onFailure(Call<Home_Data_Model> call, Throwable t) {
                alldatalodinge.setVisibility(View.VISIBLE);

            }

        });
    }

    private void Slider() {
        progressDialog.show();
        final ApiInterfase apiInterface = RetrofitClient.getApiClient().create(ApiInterfase.class);
        apiInterface.get_slider("slider").enqueue(new Callback<SliderModel>() {
            @Override
            public void onResponse(Call<SliderModel> call, Response<SliderModel> response) {
                sliderModel = response.body();
                if (response.isSuccessful()) {
                    if (sliderModel.getStatus().equals("success")) {
                        progressDialog.dismiss();
                        adapterImageSlider = new AdapterImageSlider(getActivity(), sliderModel.getSlider());

                        adapterImageSlider.setItems(sliderModel.getSlider());
                        viewPager.setAdapter(adapterImageSlider);
                        viewPager.setCurrentItem(0);
//                        "delete"
//                        addBottomDots(layout_dots, sliderModel.getSlider().size(), 0);
                        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int pos, float positionOffset, int positionOffsetPixels) {
                            }

                            @Override
                            public void onPageSelected(int pos) {
                                addBottomDots(layout_dots, sliderModel.getSlider().size(), pos);
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });
                        startAutoSlider(sliderModel.getSlider().size());

                    } else {

                        progressDialog.dismiss();
                    }

                } else {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<SliderModel> call, Throwable t) {
                call.cancel();
                progressDialog.dismiss();
            }
        });

        // displaying selected image first

    }

    private void addBottomDots(LinearLayout layout_dots, int size, int current) {
        ImageView[] dots = new ImageView[size];

        layout_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(getActivity());
            int width_height = 25;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle);
            dots[i].setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            layout_dots.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current].setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        }
    }

    private static class AdapterImageSlider extends PagerAdapter {
        private Activity act;
        private List<SliderModel.Slider> items;

        // constructor
        private AdapterImageSlider(Activity activity, List<SliderModel.Slider> items) {
            this.act = activity;
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        public void setItems(List<SliderModel.Slider> items) {
            this.items = items;
            notifyDataSetChanged();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == (object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.item_slider_image, container, false);

            ImageView imageView = v.findViewById(R.id.image);
            displayImageOriginal(act, imageView, items, position);

            (container).addView(v);

            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            (container).removeView((RelativeLayout) object);
        }

    }

    private void startAutoSlider(final int count) {
        runnable = new Runnable() {
            @Override
            public void run() {
                int pos = viewPager.getCurrentItem();
                pos = pos + 1;
                if (pos >= count) pos = 0;
                viewPager.setCurrentItem(pos);
                handler.postDelayed(runnable, 2000);
            }
        };

        handler.postDelayed(runnable, 2000);

    }

    private static void displayImageOriginal(Context context, ImageView img, List<SliderModel.Slider> items, int position) {
        try {
            Glide.with(context).load(items.get(position).getSImage())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(img);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public void onDestroy() {
        if (runnable != null) handler.removeCallbacks(runnable);
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApp.flagHomeAction=true;

    }

    private void Festival() {
        FestivalModel.clear();
        ApiInterfase apiInterfase = RetrofitClient.getApiClient().create(ApiInterfase.class);
        Call<Festival_M> festival_mCall = apiInterfase.getFestival("upcoming_festival_category");
        festival_mCall.enqueue(new Callback<Festival_M>() {
            @Override
            public void onResponse(Call<Festival_M> call, Response<Festival_M> response) {
                if (response.isSuccessful()) {
                    festival_m = response.body();
                    SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
                    Date todayDate = new Date();
                    String thisDate = currentDate.format(todayDate);
                    for (int i = 0; i < festival_m.getCategory().size(); i++) {
                        try {
                            if (festival_m.getCategory().get(i).getUpcDate().equals(thisDate)) {
                                FestivalModel.add(festival_m.getCategory().get(i));
                            }
                            if (new SimpleDateFormat("dd-MM-yyyy").parse(festival_m.getCategory().get(i).getUpcDate()).before(new Date())) {
                            } else {
                                FestivalModel.add(festival_m.getCategory().get(i));
                            }

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                    festival_adapter = new Festival_Adapter(getActivity(), FestivalModel);
                    Ucoming.setAdapter(festival_adapter);
                    Ucoming.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                    if(festival_adapter.getItemCount()>0)
                    {
                        Ucoming.setVisibility(View.VISIBLE);
                        tvUpcomming.setVisibility(View.VISIBLE);
                    }else {
                        Ucoming.setVisibility(View.GONE);
                        tvUpcomming.setVisibility(View.GONE);
                    }
                } else {

                        Ucoming.setVisibility(View.GONE);
                        tvUpcomming.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<Festival_M> call, Throwable t) {
                // speciallodinge.setVisibility(View.GONE);

            }

        });
    }

    public boolean internet() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            connected = true;
        } else {
            connected = false;
        }
        return connected;
    }

    private void internetoff() {
        androidx.appcompat.app.AlertDialog alertbox = new AlertDialog.Builder(getActivity())
                .setTitle("Not Conacted")
                .setCancelable(false)
                .setMessage("Please turn on Internet connection and Try again !")
                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        nointernet.setVisibility(View.VISIBLE);
                        alldatalodinge.setVisibility(View.GONE);
                        onResume();
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        getActivity().finish();
                    }
                })
                .show();

        alertbox.getButton(alertbox.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.textcolor));
        alertbox.getButton(alertbox.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.textcolor));
    }

    protected URL stringToURL(String urlString) {
        try {
            URL url = new URL(urlString);
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class DownloadTask extends AsyncTask<URL, Void, Bitmap> {
        protected void onPreExecute() {
        }

        protected Bitmap doInBackground(URL... urls) {
            URL url = urls[0];
            HttpURLConnection connection = null;

            try {

                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                Bitmap bmp = BitmapFactory.decodeStream(bufferedInputStream);
                return bmp;

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                connection.disconnect();
            }
            return null;
        }

        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                //    Uri imageInternalUri = saveImageToInternalStorage(result);
                u_logo.setImageBitmap(result);
                storeImage(result);
            } else {
                // Notify user that an error occurred while downloading image
            }
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
//            new PrefManager(getActivity()).setString(IConstant.LOGO, IConstant.BUSINESS_LOGO_PATH);
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
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
}

