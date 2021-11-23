package com.oceanmtech.dmt;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Utils {

    public static final String BASE_URl = "http://oceanmtechdmt.in/";
    public static final String MsgOTP = " is your verification code for Oceanmtech DMT Application.";


    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public static void NetworkDialog(AppCompatActivity context) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.internet_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        TextView dialogButton = (TextView) dialog.findViewById(R.id.tv_tryAgain);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.recreate();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
