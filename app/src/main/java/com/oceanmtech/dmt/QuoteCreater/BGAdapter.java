package com.oceanmtech.dmt.QuoteCreater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.oceanmtech.dmt.Model.BackgroundImageModel;
import com.oceanmtech.dmt.R;

import java.util.List;

public class BGAdapter extends RecyclerView.Adapter<BGAdapter.MyViewHolder> {
    private List<BackgroundImageModel.Content> moviesList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.img_show);
        }
    }

    public BGAdapter(Context context, List<BackgroundImageModel.Content> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bg_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context).load(moviesList.get(position).bg_url).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
