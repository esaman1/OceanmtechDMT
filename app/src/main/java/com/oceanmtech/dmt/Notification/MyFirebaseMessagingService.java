package com.oceanmtech.dmt.Notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.oceanmtech.dmt.Activity.Splashscreen;
import com.oceanmtech.dmt.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    NotificationCompat.Builder notifiaction;
    Bitmap bitmap;
    String id = "Default";

    String sound="notification_sound";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        String title = remoteMessage.getData().get("title");
      //  String body = remoteMessage.getData().get("body");
        String imgurl = remoteMessage.getData().get("image");

        bitmap = getbitmap(imgurl);

        getnotifiacation(bitmap, title, imgurl);
    }

    @Override
    public void onNewToken(String token) {

    }

    private void getnotifiacation(Bitmap bitmap, String title, String imgurl) {

        Intent intent = new Intent(this, Splashscreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        notifiaction = new NotificationCompat.Builder(this, id)
                .setAutoCancel(true)
                .setContentTitle(title)
               // .setContentText(body)
                .setLargeIcon(getbitmap(imgurl))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap))
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(id, "notifiaction",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0, notifiaction.build());

    }


    public static Bitmap getbitmap(String imgurl) {
        try {
            URL url = new URL(imgurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


}
