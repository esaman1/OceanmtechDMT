package com.oceanmtech.dmt.demo.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;


import com.oceanmtech.dmt.demo.LibContants;
import com.oceanmtech.dmt.demo.StickerInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

public class ContainerHost extends RelativeLayout {
    private String actAdapter = "";
   // private HashMap<String, HoriontalListAdapter> adapterHashMap = new HashMap<>();
    public Context ctx;
    private HorizontalListView horizontalListView;
    public OnItemClickListener itemClickListener = null;

    public interface OnItemClickListener {
        void onItemClick(String str);
    }

    public void setItemClickListener(OnItemClickListener onItemClickListener) {
        this.itemClickListener = onItemClickListener;
    }

    public ContainerHost(Context context) {
        super(context);
      //  initContainerHost(context);
    }

    public ContainerHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
       // initContainerHost(context);
    }

    public ContainerHost(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
       // initContainerHost(context);
    }

 /*   public void initContainerHost(Context context) {
        this.ctx = context;
        HorizontalListView horizontalListView2 = new HorizontalListView(context);
        this.horizontalListView = horizontalListView2;
        horizontalListView2.setLayoutParams(new LayoutParams(-1, -1));
        addView(this.horizontalListView);
        this.horizontalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (ContainerHost.this.itemClickListener != null) {
                    Uri parse = Uri.parse(((StickerInfo) ContainerHost.this.getActiveAdapter().getItem(i)).getIMAGE_PATH());
                    if ("android.resource".equals(parse.getScheme())) {
                        ContainerHost.this.itemClickListener.onItemClick(((StickerInfo) ContainerHost.this.getActiveAdapter().getItem(i)).getIMAGE_PATH());
                    } else if (new File(parse.getPath()).exists()) {
                        ContainerHost.this.itemClickListener.onItemClick(((StickerInfo) ContainerHost.this.getActiveAdapter().getItem(i)).getIMAGE_PATH());
                    } else {
                        Log.i("testing", "Starting AsyncTask");
                        ContainerHost containerHost = ContainerHost.this;
                        new SaveAndLoadAsync(i, (StickerInfo) containerHost.getActiveAdapter().getItem(i)).execute(new Object[0]);
                    }
                }
            }
        });
    }

    public void addAdapter(String str, HoriontalListAdapter horiontalListAdapter) {
        this.adapterHashMap.put(str, horiontalListAdapter);
    }

    public void removeAdapter(String str) {
        if (this.adapterHashMap.containsKey(str)) {
            this.adapterHashMap.remove(str);
        }
        if (!this.adapterHashMap.containsKey(this.actAdapter)) {
            this.actAdapter = "";
        }
    }

    public void changeAdapter(String str) {
        if (this.adapterHashMap.containsKey(str)) {
            this.horizontalListView.setVisibility(VISIBLE);
            this.horizontalListView.setAdapter((ListAdapter) this.adapterHashMap.get(str));
        } else {
            this.horizontalListView.setVisibility(GONE);
        }
        if (this.adapterHashMap.containsKey(str)) {
            this.actAdapter = str;
        } else {
            this.actAdapter = "";
        }
    }

    public HoriontalListAdapter getActiveAdapter() {
        if (this.adapterHashMap.containsKey(this.actAdapter)) {
            return this.adapterHashMap.get(this.actAdapter);
        }
        return null;
    }

    public HoriontalListAdapter getAdapter(String str) {
        if (this.adapterHashMap.containsKey(str)) {
            return this.adapterHashMap.get(str);
        }
        return null;
    }*/

    private class SaveAndLoadAsync extends AsyncTask<Object, Void, Boolean> {
        private ProgressDialog pdia;
        private int position;
        private StickerInfo stickerInfo;

        public SaveAndLoadAsync(int i, StickerInfo stickerInfo2) {
            this.position = i;
            this.stickerInfo = stickerInfo2;
        }

        public void onPreExecute() {
            super.onPreExecute();
            ProgressDialog progressDialog = new ProgressDialog(ContainerHost.this.ctx);
            this.pdia = progressDialog;
            progressDialog.setMessage("File Downloading");
            this.pdia.setCancelable(false);
            this.pdia.show();
        }

        public Boolean doInBackground(Object... objArr) {
            try {
                String saveBitmapObject = saveBitmapObject(BitmapFactory.decodeStream(new URL(this.stickerInfo.getIMAGE_SERVER_PATH()).openStream()), this.stickerInfo.getSTICKER_NAME());
               /* DatabaseHandler dbHandler = DatabaseHandler.getDbHandler(ContainerHost.this.ctx);
                dbHandler.updateStickerImagePath(this.stickerInfo.getSTICKER_ID(), saveBitmapObject, true);
                dbHandler.close();*/
                this.stickerInfo.setIMAGE_PATH(saveBitmapObject);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Thread.sleep(5000);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                return false;
            }
        }

        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            this.pdia.dismiss();
            if (bool.booleanValue()) {
              //  ((StickerInfo) ContainerHost.this.getActiveAdapter().getItem(this.position)).setIMAGE_PATH(this.stickerInfo.getIMAGE_PATH());
                ContainerHost.this.itemClickListener.onItemClick(this.stickerInfo.getIMAGE_PATH());
                return;
            }
            new AlertDialog.Builder(ContainerHost.this.ctx).setTitle("No Internet").setMessage("File Not Downloaded").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).create().show();
        }

        private String saveBitmapObject(Bitmap bitmap, String str) {
            File saveFileLocation = LibContants.getSaveFileLocation();
            saveFileLocation.mkdirs();
            File file = new File(saveFileLocation, str + ".png");
            if (file.exists()) {
                file.delete();
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.close();
                return file.getPath();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
