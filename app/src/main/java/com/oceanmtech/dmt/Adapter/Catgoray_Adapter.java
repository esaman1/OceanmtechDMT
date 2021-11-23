package com.oceanmtech.dmt.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.oceanmtech.dmt.Activity.Gujarati_Suvichar_View;
import com.oceanmtech.dmt.R;
import com.oceanmtech.dmt.Model.UserData1;

import java.util.ArrayList;


public class Catgoray_Adapter extends RecyclerView.Adapter<Catgoray_Adapter.DataHolder> {

    Activity activity;
    LayoutInflater inflater;
    ArrayList<UserData1> userData_apis;
    int pos;
    UserData1 data1;

    public Catgoray_Adapter(Activity mContext, ArrayList<UserData1> userData1s) {
        this.activity = mContext;
        this.userData_apis = userData1s;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.pager_item1, viewGroup, false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DataHolder dataHolder, final int i) {

        data1 = userData_apis.get(i);

        Glide.with(activity)
                .load(data1.getImage())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(dataHolder.catimage);

        dataHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos = i;

                Intent intent = new Intent(activity, Gujarati_Suvichar_View.class);
                intent.putExtra("category_image", data1.getImage());
                intent.putExtra("pos", i);
                intent.putExtra("id", userData_apis.get(i).getId());
                intent.putExtra("name",userData_apis.get(i).getCategory());
                intent.putExtra("img",userData_apis.get(i).getImage());
                activity.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemViewType(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return userData_apis.size();
    }

    public class DataHolder extends RecyclerView.ViewHolder {

        ImageView catimage;

        public DataHolder(@NonNull View itemView) {
            super(itemView);

            catimage = (ImageView) itemView.findViewById(R.id.catimage);

        }
    }

}
