package com.oceanmtech.dmt.text;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


public class PhotoEditView extends RelativeLayout {
    private static final String TAG = "PhotoEditorView";
    private static final int brushSrcId = 2;
    private static final int glFilterId = 3;
    private static final int imgSrcId = 1;
    private BrushDrawingView mBrushDrawingView;
  /*  public ImageFilterview mImageFilterView;
    public FilterImageView mImgSource;*/

    public PhotoEditView(Context context) {
        super(context);
        init(null);
    }

    public PhotoEditView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public PhotoEditView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    @RequiresApi(api = 21)
    public PhotoEditView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(attributeSet);
    }

    @SuppressLint({"Recycle", "ResourceType"})
    private void init(@Nullable AttributeSet attributeSet) {
      /*  this.mImgSource = new FilterImageView(getContext());
        this.mImgSource.setId(1);
        this.mImgSource.setAdjustViewBounds(true);*/
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(13, -1);
        /*if (attributeSet != null) {
            Drawable drawable = getContext().obtainStyledAttributes(attributeSet, ja.burhanrashid52.photoeditor.R.styleable.PhotoEditorView).getDrawable(ja.burhanrashid52.photoeditor.R.styleable.PhotoEditorView_photo_src);
            if (drawable != null) {
                this.mImgSource.setImageDrawable(drawable);
            }
        }*/
        this.mBrushDrawingView = new BrushDrawingView(getContext());
        this.mBrushDrawingView.setVisibility(GONE);
        this.mBrushDrawingView.setId(2);
        LayoutParams layoutParams2 = new LayoutParams(-1, -2);
        layoutParams2.addRule(13, -1);
        layoutParams2.addRule(6, 1);
        layoutParams2.addRule(8, 1);
       /* this.mImageFilterView = new ImageFilterview(getContext());
        this.mImageFilterView.setId(3);
        this.mImageFilterView.setVisibility(8);*/
        LayoutParams layoutParams3 = new LayoutParams(-1, -2);
        layoutParams3.addRule(13, -1);
        layoutParams3.addRule(6, 1);
        layoutParams3.addRule(8, 1);
      /*  this.mImgSource.setOnImageChangedListener(new FilterImageView.OnImageChangedListener() {
            public void onBitmapLoaded(@Nullable Bitmap bitmap) {
                com.romanticlove.photo.EditorView.PhotoEditView.this.mImageFilterView.setFilterEffect(PhotoFilter.NONE);
                com.romanticlove.photo.EditorView.PhotoEditView.this.mImageFilterView.setSourceBitmap(bitmap);
                String str = com.romanticlove.photo.EditorView.PhotoEditView.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onBitmapLoaded() called with: sourceBitmap = [");
                sb.append(bitmap);
                sb.append("]");
                Log.d(str, sb.toString());
            }
        });
        addView(this.mImgSource, layoutParams);
        addView(this.mImageFilterView, layoutParams3);*/
        addView(this.mBrushDrawingView, layoutParams2);
    }

   /* public ImageView getSource() {
        return this.mImgSource;
    }*/

    /* access modifiers changed from: 0000 */
    public BrushDrawingView getBrushDrawingView() {
        return this.mBrushDrawingView;
    }
/*
    public void saveFilter(@NonNull final OnSaveBitmap onSaveBitmap) {
        if (this.mImageFilterView.getVisibility() == VISIBLE) {
            this.mImageFilterView.saveBitmap(new OnSaveBitmap() {
                public void onBitmapReady(Bitmap bitmap) {
                    String str = com.romanticlove.photo.EditorView.PhotoEditView.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("saveFilter: ");
                    sb.append(bitmap);
                    Log.e(str, sb.toString());
                    com.romanticlove.photo.EditorView.PhotoEditView.this.mImgSource.setImageBitmap(bitmap);
                    com.romanticlove.photo.EditorView.PhotoEditView.this.mImageFilterView.setVisibility(GONE);
                    onSaveBitmap.onBitmapReady(bitmap);
                }

                public void onFailure(Exception exc) {
                    onSaveBitmap.onFailure(exc);
                }
            });
        } else {
            onSaveBitmap.onBitmapReady(this.mImgSource.getBitmap());
        }
    }

    public void setFilterEffect(PhotoFilter photoFilter) {
        this.mImageFilterView.setVisibility(VISIBLE);
        this.mImageFilterView.setSourceBitmap(this.mImgSource.getBitmap());
        this.mImageFilterView.setFilterEffect(photoFilter);
    }

    public void setFilterEffect(CustomEffect customEffect) {
        this.mImageFilterView.setVisibility(VISIBLE);
        this.mImageFilterView.setSourceBitmap(this.mImgSource.getBitmap());
        this.mImageFilterView.setFilterEffect(customEffect);
    }*/
}
