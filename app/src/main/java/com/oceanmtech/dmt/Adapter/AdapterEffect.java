package com.oceanmtech.dmt.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.oceanmtech.dmt.Activity.Gujarati_Suvichar_View;
import com.oceanmtech.dmt.R;

public class AdapterEffect extends RecyclerView.Adapter<AdapterEffect.DataHolder> {

    Activity activity;
    int[] effect1;
    LayoutInflater inflater;
    String Type1;

    public AdapterEffect(Activity EditActivity, int[] effect1, String type1) {
        activity = EditActivity;
        this.effect1 = effect1;
        this.Type1 = type1;
        inflater = LayoutInflater.from(activity);

    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.effectitems2, viewGroup, false);
        return new DataHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder dataHolder, final int i) {

       // dataHolder.item3d.setImageBitmap(PhotoEditorActivity.bitmap);

        Glide.with(activity).load(effect1[i]).into(dataHolder.imageView1);

        dataHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (i == 0) {

                    Gujarati_Suvichar_View.seteffect.setBackgroundResource(0);


                } else {
                    Gujarati_Suvichar_View.seteffect.setBackgroundResource(effect1[i]);


                }

            }

        });

    }

    @Override
    public int getItemCount() {
        return effect1.length;
    }

    public class DataHolder extends RecyclerView.ViewHolder {

        ImageView imageView1;
        ImageView item3d;

        public DataHolder(@NonNull View itemView) {
            super(itemView);

            imageView1 = itemView.findViewById(R.id.hello3);
            item3d = itemView.findViewById(R.id.item3d);
        }
    }
}

