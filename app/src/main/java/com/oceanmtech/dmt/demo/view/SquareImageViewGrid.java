package com.oceanmtech.dmt.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;

public class SquareImageViewGrid extends androidx.appcompat.widget.AppCompatImageView {
    public SquareImageViewGrid(Context context) {
        super(context);
    }

    public SquareImageViewGrid(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(i, i);
    }

    public void onDraw(Canvas canvas) {
        if (!(getDrawable() instanceof BitmapDrawable) || !((BitmapDrawable) getDrawable()).getBitmap().isRecycled()) {
            super.onDraw(canvas);
        }
    }
}
