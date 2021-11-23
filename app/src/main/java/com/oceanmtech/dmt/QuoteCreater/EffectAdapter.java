package com.oceanmtech.dmt.QuoteCreater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.oceanmtech.dmt.R;

import java.util.ArrayList;
import java.util.List;


public class EffectAdapter extends RecyclerView.Adapter<EffectAdapter.MyViewHolder> {
    private List<Integer> moviesList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_main_gallery;

        public MyViewHolder(View view) {
            super(view);
            this.iv_main_gallery = (ImageView) view.findViewById(R.id.iv_main_gallery);
        }
    }

    public EffectAdapter(Context con, List<Integer> moviesList) {
        this.moviesList = moviesList;
        this.context=con;
    }

    public void addAll(ArrayList<Integer> files) {
        try {
            this.moviesList.clear();
            this.moviesList.addAll(files);
        } catch (Exception e) {
            e.printStackTrace();
        }
        notifyDataSetChanged();
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_effect, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        // this.imageLoader.displayImage("drawable://" + this.moviesList.get(position), holder.iv_main_gallery);

        Glide.with(context).load(this.moviesList.get(position)).into(holder.iv_main_gallery);
    }

    public int getItemCount() {
        return this.moviesList.size();
    }
}
