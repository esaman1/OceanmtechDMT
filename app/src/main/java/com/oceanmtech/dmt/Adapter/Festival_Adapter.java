package com.oceanmtech.dmt.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.oceanmtech.dmt.Activity.Gujarati_Suvichar_View;
import com.oceanmtech.dmt.Model.Festival_M;
import com.oceanmtech.dmt.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Festival_Adapter extends RecyclerView.Adapter<Festival_Adapter.DataHolder> {

    Activity activity;
    LayoutInflater inflater;
    List<Festival_M.Category> userData_apis;
    int pos;
    Festival_M.Category f_data1;
    TextView name;
    TextView date;
    TextView type;
    String mydate;

    public Festival_Adapter(Activity mContext, List<Festival_M.Category> userData1s) {
        this.activity = mContext;
        this.userData_apis = userData1s;

    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(activity).inflate(R.layout.festival_item, viewGroup, false);
        // loadVideoAds();
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DataHolder dataHolder, final int i) {

        f_data1 = userData_apis.get(i);
//        Log.w("Logs","Url : "+f_data1.getImage());
        Glide.with(activity)
                .load(f_data1.getImage())
                .transition(DrawableTransitionOptions.withCrossFade())
//               .placeholder(R.drawable.back_g)
                .into(dataHolder.catimage);


        name.setText(f_data1.getUpcCategory());
        date.setText(f_data1.getUpcDate());
//      type.setText(data1.Type);


        dataHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                pos = i;


                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                Date date = null;
                try {
                    date = dateFormat.parse(userData_apis.get(i).getUpcDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.DATE, -1);
                String yesterdayAsString = dateFormat.format(calendar.getTime());

                SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
                Date todayDate = new Date();
                String thisDate = currentDate.format(todayDate);

                if (yesterdayAsString.equals(thisDate) || userData_apis.get(i).getUpcDate().equals(thisDate)) {
                    Intent intent = new Intent(activity, Gujarati_Suvichar_View.class);
                    intent.putExtra("pos", 0);
                    intent.putExtra("cat_id", userData_apis.get(pos).getUpcId());
                    intent.putExtra("type", "upcoming_festival_new");
                    activity.startActivity(intent);

                } else {

                    final Dialog dialog = new Dialog(activity);
                    dialog.setContentView(R.layout.error_dialog);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.setCancelable(false);
                    TextView dialogButton = (TextView) dialog.findViewById(R.id.tv_close);

                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }


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

        TextView title;
        ImageView catimage;

        public DataHolder(@NonNull View itemView) {
            super(itemView);

            catimage = (ImageView) itemView.findViewById(R.id.catimage);

            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);

        }
    }


}
