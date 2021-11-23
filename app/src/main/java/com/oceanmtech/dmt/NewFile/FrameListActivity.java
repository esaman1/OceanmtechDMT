package com.oceanmtech.dmt.NewFile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.oceanmtech.dmt.QuoteCreater.Constans;
import com.oceanmtech.dmt.Model.LocalFrameItem;
import com.oceanmtech.dmt.R;

import java.util.ArrayList;

public class FrameListActivity extends AppCompatActivity {

    private LinearLayout lay_frames, frame;
    private LinearLayout llFrame;
    int tempPos;
    String type;
    String freepaid;
    private NeweDataBase neweDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_list);


        frame = (LinearLayout) findViewById(R.id.frame);
        lay_frames = (LinearLayout) findViewById(R.id.lay_frames);
        llFrame = (LinearLayout) findViewById(R.id.llframe);


        final ArrayList<LocalFrameItem> allFrames = Constans.getAllFrames();
        if (this.lay_frames.getChildCount() > 0) {
            this.lay_frames.removeAllViews();
        }

        final ArrayList arrayList = new ArrayList();
        if (allFrames.size() > 0) {
            for (int i = 0; i < allFrames.size(); i++) {
                View inflate = LayoutInflater.from(this).inflate(allFrames.get(i).getPreview_id(), this.lay_frames, false);
                arrayList.add((ImageView) inflate.findViewById(R.id.ivselected));
                inflate.setTag(Integer.valueOf(i));
                inflate.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        int intValue = ((Integer) view.getTag()).intValue();
                     //   setFrame((LocalFrameItem) allFrames.get(intValue));
                        for (int i = 0; i < arrayList.size(); i++) {
                            ImageView imageView = (ImageView) arrayList.get(i);

                            if (i == intValue) {
                                imageView.setVisibility(View.VISIBLE);

                                Toast.makeText(FrameListActivity.this, ""+intValue, Toast.LENGTH_SHORT).show();


                            } else {
                                imageView.setVisibility(View.GONE);
                            }
                        }
                    }
                });
                this.lay_frames.addView(inflate);
            }
          //  setFrame(allFrames.get(0));
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                ImageView imageView = (ImageView) arrayList.get(i2);
                if (i2 == 0) {
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    imageView.setVisibility(View.GONE);
                }
            }
        }
    }


//    DATABASE

}