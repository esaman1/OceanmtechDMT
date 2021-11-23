package com.oceanmtech.dmt.home_adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.oceanmtech.dmt.Activity.Gujarati_Suvichar_View;
import com.oceanmtech.dmt.Model.Home_Data_Model;
import com.oceanmtech.dmt.R;

import java.util.List;

import butterknife.BindView;


public class HorizontalRecyclerViewAdapter extends
        RecyclerView.Adapter<HorizontalRecyclerViewAdapter.HorizontalViewHolder> {

    private Context mContext;
    private List<Home_Data_Model.Image> mArrayList;

    public HorizontalRecyclerViewAdapter(Context mContext,
                                         List<Home_Data_Model.Image> mArrayList) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;
    }

    @Override
    public HorizontalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_horizontal, parent, false);
        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HorizontalViewHolder holder, int position) {
        final Home_Data_Model.Image current = mArrayList.get(position);

        holder.txtTitle.setText(current.getTitle());
//        Log.w("Logs","Url : "+current.getImage());

        Glide.with(mContext)
                .load(current.getImage())
                .placeholder(R.drawable.newlogo)
                .into(holder.img_thumb);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Gujarati_Suvichar_View.class);
                intent.putExtra("pos", position);
                intent.putExtra("cat_id", current.getCatId());
                intent.putExtra("type","data_new");
                intent.putExtra("Cat",current.getTitle());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    class HorizontalViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtTitle)
        TextView txtTitle;
        ImageView img_thumb;

        public HorizontalViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            img_thumb = (ImageView) itemView.findViewById(R.id.ivThumb);
        }
    }
}
