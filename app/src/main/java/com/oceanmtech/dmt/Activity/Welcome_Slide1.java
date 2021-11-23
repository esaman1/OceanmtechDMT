package com.oceanmtech.dmt.Activity;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.oceanmtech.dmt.QuoteCreater.Constans;
import com.oceanmtech.dmt.DataBase;
import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.PrefManager1;
import com.oceanmtech.dmt.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

public class Welcome_Slide1 extends AppCompatActivity {
    private static final String TAG = "data-->";
    public static Bitmap bitmap;
    private ImageView nextbutton1;
    private EditText edittext1;
    Animation slideIn;
    Animation slideOut;
    private ImageView uploadlogo;
    private Button button;
    private DataBase dataBase;
    private PrefManager1 prefManager1;
    private TextView t_text;
    String edit;
    private TextView text1;
    private TextView skip1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.welcome_slide1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        dataBase = new DataBase(Welcome_Slide1.this);
        prefManager1 = new PrefManager1(this);


        Find();
        Click();

        Intent intent = getIntent();


        edit = intent.getStringExtra("Type");

        if (edit.equals("Edit")) {
            File file = new File(IConstant.BUSINESS_LOGO_PATH);
            if (!file.exists()) {
                uploadlogo.setImageDrawable(getResources().getDrawable(R.drawable.logo_image));
            } else {
                Glide.with(getApplicationContext()).load(file).into(uploadlogo);
            }
            edittext1.setText(new PrefManager(getApplicationContext()).getString(IConstant.BUSINESS_NAME));
        } else {
            edit = "New";
            uploadlogo.setImageDrawable(getResources().getDrawable(R.drawable.logo_image));
            edittext1.setText("");


        }
    }


    public void Find() {

        nextbutton1 = (ImageView) findViewById(R.id.nextbutton1);
        edittext1 = (EditText) findViewById(R.id.edittext1);

        uploadlogo = (ImageView) findViewById(R.id.uploadlogo);
        t_text = (TextView) findViewById(R.id.t_text);
        text1 = (TextView) findViewById(R.id.text1);
        skip1 = (TextView) findViewById(R.id.skip1);

        Typeface type = Typeface.createFromAsset(getAssets(), "papyrus.ttf");
        t_text.setTypeface(type);

        Typeface type1 = Typeface.createFromAsset(getAssets(), "papyrus.ttf");
        text1.setTypeface(type1);
    }


    private void Click() {

        skip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome_Slide1.this, MainActivity.class);
                startActivity(intent);
            }
        });

        nextbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String businessName = edittext1.getText().toString();

                if (businessName.isEmpty() || businessName.length() < 1) {
                    edittext1.setError("Enter a valid BusinessName");
                    edittext1.requestFocus();
                    return;
                }
                new PrefManager(getApplicationContext()).setString(IConstant.BUSINESS_NAME, businessName);

                Intent intent = new Intent(Welcome_Slide1.this, Welcome_Slide2.class);
                intent.putExtra("Type", edit);
                overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }

        });


        uploadlogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(String.valueOf(button));
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);

            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
            switch (requestCode) {

                case 1:
                    Uri imageuri = data.getData();
                    getPath(Welcome_Slide1.this, imageuri);
                    String picturePath = getPath(Welcome_Slide1.this.getApplicationContext(), imageuri);

                    try {

                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageuri);
                        storeImage(bitmap);
                        Constans.isLogo = true;

                        Glide.with(Welcome_Slide1.this).load(bitmap).into(uploadlogo);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;

            }

    }

    public static String getPath(Context context, Uri uri) {
        String result = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(proj[0]);
                result = cursor.getString(column_index);
            }
            cursor.close();
        }
        if (result == null) {
            result = "Not found";
        }
        return result;
    }

    protected URL stringToURL(String urlString){
        try{
            URL url = new URL(urlString);
            return url;
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
        return null;
    }

    private void storeImage(Bitmap image) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
            new PrefManager(getApplicationContext()).setString(IConstant.LOGO, IConstant.BUSINESS_LOGO_PATH);
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    private File getOutputMediaFile() {

        if (!IConstant.FOLDER_PATH.exists()) {
            if (!IConstant.FOLDER_PATH.mkdirs()) {
                return null;
            }
        }
        // Create a media file name
        File mediaFile;
        mediaFile = new File(IConstant.BUSINESS_LOGO_PATH);
        return mediaFile;
    }

}