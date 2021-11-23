package com.oceanmtech.dmt.QuoteCreater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.oceanmtech.dmt.R;

import java.util.List;

public class QuotesWithImageAdapter extends RecyclerView.Adapter<QuotesWithImageAdapter.MyViewHolder> {
    private List<QuotesList> moviesList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_name;
        public ImageView img_qoutes_image;

        public MyViewHolder(View view) {
            super(view);
            txt_name = (TextView) view.findViewById(R.id.txt_quotes);
            img_qoutes_image = (ImageView) view.findViewById(R.id.img_qoutes_image);
        }
    }

    public QuotesWithImageAdapter(Context context, List<QuotesList> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_quotes_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context).load(moviesList.get(position).getImage()).into(holder.img_qoutes_image);
        holder.txt_name.setText(moviesList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
