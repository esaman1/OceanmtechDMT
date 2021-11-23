package com.oceanmtech.dmt.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SquareImageViewList extends ImageView {
    public SquareImageViewList(Context context) {
        super(context);
    }

    public SquareImageViewList(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(i2, i2);
    }

    public void onDraw(Canvas canvas) {
        if (!(getDrawable() instanceof BitmapDrawable) || !((BitmapDrawable) getDrawable()).getBitmap().isRecycled()) {
            super.onDraw(canvas);
        }
    }
}
