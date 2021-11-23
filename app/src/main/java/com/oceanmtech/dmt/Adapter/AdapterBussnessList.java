package com.oceanmtech.dmt.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.oceanmtech.dmt.Activity.RegistrationActivity;
import com.oceanmtech.dmt.Fragment.HomeFragment;
import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.Model.Business_Get_model;
import com.oceanmtech.dmt.Model.BussnessInfoModel;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class AdapterBussnessList extends RecyclerView.Adapter<AdapterBussnessList.DataHolder> {

    private final List<Business_Get_model.Business> bussnessList;
    Activity activity;
    LayoutInflater inflater;
    int selectedPosition=-1;

    public AdapterBussnessList(Activity activity, List<Business_Get_model.Business> bussnessList) {
        this.activity = activity;
        this.bussnessList = bussnessList;
        inflater = LayoutInflater.from(activity);
        selectedPosition=getIndex(new PrefManager(activity).getString(IConstant.BID));
    }
    public int getIndex(String bId)
    {
        for (int i = 0; i < bussnessList.size(); i++)
        {
            Business_Get_model.Business auction = bussnessList.get(i);
            if (bId.equals(auction.getBId()))
            {
                return i;
            }
        }

        return -1;
    }
    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_bussness_list, viewGroup, false);
        return new DataHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder dataHolder, final int i) {
        Business_Get_model.Business bussnessData = bussnessList.get(i);
        // dataHolder.item3d.setImageBitmap(PhotoEditorActivity.bitmap);

        if(selectedPosition==i){
            dataHolder.ivSetAs.setText("Default");
            dataHolder.ivSetAs.setTextColor(activity.getResources().getColor(R.color.white));
            dataHolder.ivSetAs.setBackgroundResource(R.drawable.select_bt);
        }
        else{
            dataHolder.ivSetAs.setText("Set As");
            dataHolder.ivSetAs.setTextColor(activity.getResources().getColor(R.color.selectedColor));
            dataHolder.ivSetAs.setBackgroundResource(R.drawable.unselect_bt);

        }

        if (!bussnessData.getBLogo().isEmpty()) {

            Glide.with(activity)
                    .load(bussnessData.getBLogo())
                    .placeholder(R.drawable.newlogo)
                    .into(dataHolder.imageView1);

        }
        if (!TextUtils.isEmpty(bussnessData.getBName())) {
            dataHolder.tvBussnessName.setText("" + bussnessData.getBName());
        }

        dataHolder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BussnessInfoModel bussnessInfoModel=new BussnessInfoModel();
                bussnessInfoModel.setBId(bussnessData.getBId());
                bussnessInfoModel.setbName(bussnessData.getBName());
                bussnessInfoModel.setbWebsite(bussnessData.getBWebsite());
                bussnessInfoModel.setbAddress(bussnessData.getBAddress());
                bussnessInfoModel.setbEmailId(bussnessData.getBEmail());
                bussnessInfoModel.setbMobileNo(bussnessData.getBMobileNumber());
                bussnessInfoModel.setBLogo(bussnessData.getBLogo());
                Intent intent = new Intent(activity, RegistrationActivity.class);
                intent.putExtra("Type", "Edit");
                intent.putExtra("BussnessModel", bussnessInfoModel);
                activity.startActivity(intent);
            }

        });
        dataHolder.ivSetAs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PrefManager(activity).setString(IConstant.BID, bussnessData.getBId());

                setBusinessData(bussnessData.getBId(),
                        bussnessData.getBName(),
                        bussnessData.getBEmail(),
                        bussnessData.getBMobileNumber(),
                        bussnessData.getBAddress(),
                        bussnessData.getBWebsite(),
                        bussnessData.getBLogo());
                selectedPosition=i;
                notifyDataSetChanged();
            }
        });

    }

    public void setBusinessData(String bid, String businessName, String businessEmail, String businessMobile, String businessAddress, String businesswebsite, String logo) {
        new PrefManager(activity).setString(IConstant.BID, bid);
        new PrefManager(activity).setString(IConstant.BUSINESS_NAME, businessName);
        new PrefManager(activity).setString(IConstant.BUSINESS_EMAIL, businessEmail);
        new PrefManager(activity).setString(IConstant.MOBILE_NO, businessMobile);
        new PrefManager(activity).setString(IConstant.ADDRESS, businessAddress);
        new PrefManager(activity).setString(IConstant.WEBSITE, businesswebsite);
        new PrefManager(activity).setString(IConstant.LOGO, logo);

        if (!logo.isEmpty()) {
            new DownloadTask().execute(stringToURL(logo));
        }

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
//                u_logo.setImageBitmap(result);
//                storeImage(result);
            } else {
                // Notify user that an error occurred while downloading image
            }
        }
    }

    @Override
    public int getItemCount() {
        return bussnessList.size();
    }

    public class DataHolder extends RecyclerView.ViewHolder {

        ImageView imageView1;
        TextView  ivEdit,ivSetAs;
        TextView tvBussnessName;

        public DataHolder(@NonNull View itemView) {
            super(itemView);

            imageView1 = itemView.findViewById(R.id.bussnessProfile);
            tvBussnessName = itemView.findViewById(R.id.tvBussnessName);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivSetAs = itemView.findViewById(R.id.ivSetAs);
        }
    }
}

