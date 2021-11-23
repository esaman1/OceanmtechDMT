package com.oceanmtech.dmt.QuoteCreater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.oceanmtech.dmt.R;

import java.util.List;

public class QuotesListAdapter extends RecyclerView.Adapter<QuotesListAdapter.MyViewHolder> {
    private List<String> moviesList;
    private Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_name;
        public MyViewHolder(View view) {
            super(view);
            txt_name = (TextView) view.findViewById(R.id.txt_name);
        }
    }
    public QuotesListAdapter(Context context, List<String> moviesList) {
        this.moviesList = moviesList;
        this.context=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.quotes_list_new_row, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //Glide.with(context).load(moviesList.get(position)).into(holder.imageView);
        holder.txt_name.setText(moviesList.get(position));
    }
    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
