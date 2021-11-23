package com.oceanmtech.dmt.demo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class SquareFrameLayoutGrid extends RelativeLayout {
    public SquareFrameLayoutGrid(Context context) {
        super(context);
    }

    public SquareFrameLayoutGrid(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(i, i);
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i, i3, i4);
    }
}
