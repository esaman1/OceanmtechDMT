package com.oceanmtech.dmt.demo.view;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import java.security.MessageDigest;

public class CropTransformation extends BitmapTransformation {
    private float mHeight;
    private float mOffsetX;
    private float mOffsetY;
    private float mWidth;

    public String getId() {
        return "com.msl.demo.view.CropTransformation";
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
    }

    public CropTransformation(Context context, float f, float f2, float f3, float f4) {
        this.mOffsetX = f;
        this.mOffsetY = f2;
        this.mWidth = f3;
        this.mHeight = f4;
    }

    public Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i2) {
        return Bitmap.createBitmap(bitmap, (int) (this.mOffsetX * ((float) bitmap.getWidth())), (int) (this.mOffsetY * ((float) bitmap.getHeight())), (int) (this.mWidth * ((float) bitmap.getWidth())), (int) (this.mHeight * ((float) bitmap.getHeight())));
    }
}
