package com.oceanmtech.dmt.home_adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oceanmtech.dmt.Activity.Gujarati_Suvichar_View;
import com.oceanmtech.dmt.Model.Home_Data_Model;
import com.oceanmtech.dmt.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VerticalRecyclerViewAdapter extends RecyclerView.Adapter<VerticalRecyclerViewAdapter.VerticalRecyclerViewHolder> {

    private Context mContext;
    private List<Home_Data_Model.Category> mArrayList;

    public VerticalRecyclerViewAdapter(Context mContext, List<Home_Data_Model.Category> mArrayList) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;
    }

    @Override
    public VerticalRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical, parent, false);
        return new VerticalRecyclerViewHolder(view);
    }

 /*   public void setList(ArrayList<VerticalModel> mArrayList){
        this.mArrayList.addAll(mArrayList);
        notifyDataSetChanged();
    }*/

    @Override
    public void onBindViewHolder(VerticalRecyclerViewHolder holder, int position) {

        final Home_Data_Model.Category current = mArrayList.get(position);

        final String strTitle = current.getTitle();

//        ArrayList<HorizontalModel> singleSectionItems = current.getArrayList();

        holder.tvTitle.setText(strTitle);

        HorizontalRecyclerViewAdapter itemListDataAdapter =
                new HorizontalRecyclerViewAdapter(mContext, current.getImages());

        holder.rvHorizontal.setHasFixedSize(true);
        holder.rvHorizontal.setLayoutManager(new LinearLayoutManager(mContext,
                LinearLayoutManager.HORIZONTAL, false));
        holder.rvHorizontal.setAdapter(itemListDataAdapter);

        holder.rvHorizontal.setNestedScrollingEnabled(false);

        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, Gujarati_Suvichar_View.class);
                intent.putExtra("cat_id", current.getCId());
                intent.putExtra("pos", 0);
                intent.putExtra("type", "data_new");


                mContext.startActivity(intent);
            }

        });
        if (position==mArrayList.size()-1){
            holder.itemView.setLayoutParams(mParamSub());
        }

    }
    public LinearLayout.LayoutParams mParamSub() {
        Resources r = mContext.getResources();
        int px3 = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                40,
                r.getDisplayMetrics());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, px3 );
        return params;
    }
    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    class VerticalRecyclerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.rvHorizontal)
        RecyclerView rvHorizontal;

        @BindView(R.id.btnMore)
        TextView btnMore;

        public VerticalRecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
