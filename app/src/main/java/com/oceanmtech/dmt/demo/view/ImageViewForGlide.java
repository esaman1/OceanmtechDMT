package com.oceanmtech.dmt.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

public class ImageViewForGlide extends AppCompatImageView {
    public ImageViewForGlide(Context context) {
        super(context);
    }

    public ImageViewForGlide(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ImageViewForGlide(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void onDraw(Canvas canvas) {
        if (!(getDrawable() instanceof BitmapDrawable) || !((BitmapDrawable) getDrawable()).getBitmap().isRecycled()) {
            super.onDraw(canvas);
        }
    }
}
