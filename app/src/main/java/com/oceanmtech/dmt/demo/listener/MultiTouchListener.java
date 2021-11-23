package com.oceanmtech.dmt.demo.listener;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.oceanmtech.dmt.demo.view.ResizableStickerView;


public class MultiTouchListener implements View.OnTouchListener {
    private static final int INVALID_POINTER_ID = -1;
    Bitmap bitmap = null;
    boolean checkstickerWH = false;
    private boolean disContinueHandleTransparecy = true;
    boolean f205bt = false;
    public boolean isRotateEnabled = true;
    public boolean isRotationEnabled = false;
    public boolean isTranslateEnabled = true;
    private TouchCallbackListener listener = null;
    private int mActivePointerId = -1;
    private Context mContext;
    private float mPrevX;
    private float mPrevY;
    private ScaleGestureDetector mScaleGestureDetector;
    public float maximumScale = 8.0f;
    public float minimumScale = 0.5f;

    public interface TouchCallbackListener {
        void onCenterPosX(View view);

        void onCenterPosXY(View view);

        void onCenterPosY(View view);

        void onOtherPos(View view);

        void onTouchCallback(View view);

        void onTouchMoveCallback(View view);

        void onTouchUpCallback(View view);
    }

    private static float adjustAngle(float f) {
        return f > 180.0f ? f - 360.0f : f < -180.0f ? f + 360.0f : f;
    }

    public MultiTouchListener setOnTouchCallbackListener(TouchCallbackListener touchCallbackListener) {
        this.listener = touchCallbackListener;
        return this;
    }

    public MultiTouchListener(Context context) {
        this.mContext = context;
        this.mScaleGestureDetector = new ScaleGestureDetector(new ScaleGestureListener());
    }

    public MultiTouchListener enableRotation(boolean z) {
        this.isRotationEnabled = z;
        return this;
    }

    public MultiTouchListener setMinScale(float f) {
        this.minimumScale = f;
        return this;
    }

    public void move(View view, TransformInfo transformInfo) {
        if (this.isRotationEnabled) {
            view.setRotation(adjustAngle(view.getRotation() + transformInfo.deltaAngle));
        }
    }

    private void adjustTranslation(View view, float f, float f2) {
        boolean z;
        boolean z2 = false;
        float[] fArr = {f, f2};
        view.getMatrix().mapVectors(fArr);
        view.setTranslationX(view.getTranslationX() + fArr[0]);
        view.setTranslationY(view.getTranslationY() + fArr[1]);
        ResizableStickerView resizableStickerView = (ResizableStickerView) view;
        float mainWidth = resizableStickerView.getMainWidth();
        float mainHeight = resizableStickerView.getMainHeight();
        this.mContext.getResources();
        float width = (float) (view.getWidth() / 2);
        float height = (float) (view.getHeight() / 2);
        int y = (int) (view.getY() + height);
        float x = (float) ((int) (view.getX() + width));
        float f3 = mainWidth / 2.0f;
        float f4 = (float) ((int) (Resources.getSystem().getDisplayMetrics().density * 5.0f));
        if (x <= f3 - f4 || x >= f3 + f4) {
            z = false;
        } else {
            view.setX(f3 - width);
            z = true;
        }
        float f5 = (float) y;
        float f6 = mainHeight / 2.0f;
        if (f5 > f6 - f4 && f5 < f4 + f6) {
            view.setY(f6 - height);
            z2 = true;
        }
        if (z && z2) {
            TouchCallbackListener touchCallbackListener = this.listener;
            if (touchCallbackListener != null) {
                touchCallbackListener.onCenterPosXY(view);
            }
        } else if (z) {
            TouchCallbackListener touchCallbackListener2 = this.listener;
            if (touchCallbackListener2 != null) {
                touchCallbackListener2.onCenterPosX(view);
            }
        } else if (z2) {
            TouchCallbackListener touchCallbackListener3 = this.listener;
            if (touchCallbackListener3 != null) {
                touchCallbackListener3.onCenterPosY(view);
            }
        } else {
            TouchCallbackListener touchCallbackListener4 = this.listener;
            if (touchCallbackListener4 != null) {
                touchCallbackListener4.onOtherPos(view);
            }
        }
        float rotation = view.getRotation();
        if (Math.abs(90.0f - Math.abs(rotation)) <= 5.0f) {
            rotation = rotation > 0.0f ? 90.0f : -90.0f;
        }
        if (Math.abs(0.0f - Math.abs(rotation)) <= 5.0f) {
            rotation = rotation > 0.0f ? 0.0f : -0.0f;
        }
        if (Math.abs(180.0f - Math.abs(rotation)) <= 5.0f) {
            rotation = rotation > 0.0f ? 180.0f : -180.0f;
        }
        view.setRotation(rotation);
    }

    private static void computeRenderOffset(View view, float f, float f2) {
        if (view.getPivotX() != f || view.getPivotY() != f2) {
            float[] fArr = {0.0f, 0.0f};
            view.getMatrix().mapPoints(fArr);
            view.setPivotX(f);
            view.setPivotY(f2);
            float[] fArr2 = {0.0f, 0.0f};
            view.getMatrix().mapPoints(fArr2);
            float f3 = fArr2[0] - fArr[0];
            float f4 = fArr2[1] - fArr[1];
            view.setTranslationX(view.getTranslationX() - f3);
            view.setTranslationY(view.getTranslationY() - f4);
        }
    }

    public boolean handleTransparency(View view, MotionEvent motionEvent) {
        try {
            boolean z = true;
            boolean z2 = ((float) view.getWidth()) < ((ResizableStickerView) view).getMainWidth() && ((float) view.getHeight()) < ((ResizableStickerView) view).getMainHeight();
            if (z2 && ((ResizableStickerView) view).getBorderVisbilty()) {
                return false;
            }
            motionEvent.getAction();
            if (motionEvent.getAction() == 2 && this.f205bt) {
                return true;
            }
            if (motionEvent.getAction() == 1) {
                if (this.f205bt) {
                    this.f205bt = false;
                    if (this.bitmap != null) {
                        this.bitmap.recycle();
                    }
                    return true;
                }
            }
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            int i = iArr[0];
            int i2 = iArr[1];
            int rawX = (int) (motionEvent.getRawX() - ((float) i));
            int rawY = (int) (motionEvent.getRawY() - ((float) i2));
            float rotation = view.getRotation();
            Matrix matrix = new Matrix();
            matrix.postRotate(-rotation);
            float[] fArr = {(float) rawX, (float) rawY};
            matrix.mapPoints(fArr);
            int i3 = (int) fArr[0];
            int i4 = (int) fArr[1];
            if (motionEvent.getAction() == 0) {
                this.f205bt = false;
                boolean borderVisbilty = ((ResizableStickerView) view).getBorderVisbilty();
                if (borderVisbilty) {
                    ((ResizableStickerView) view).setBorderVisibility(false);
                }
                this.bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
                view.draw(new Canvas(this.bitmap));
                if (borderVisbilty) {
                    ((ResizableStickerView) view).setBorderVisibility(true);
                }
                i3 = (int) (((float) i3) * (((float) this.bitmap.getWidth()) / (((float) this.bitmap.getWidth()) * view.getScaleX())));
                i4 = (int) (((float) i4) * (((float) this.bitmap.getHeight()) / (((float) this.bitmap.getHeight()) * view.getScaleX())));
            }
            if (i3 >= 0 && i4 >= 0 && i3 <= this.bitmap.getWidth()) {
                if (i4 <= this.bitmap.getHeight()) {
                    if (this.bitmap.getPixel(i3, i4) != 0) {
                        z = false;
                    }
                    if (motionEvent.getAction() == 0) {
                        this.f205bt = z;
                        if (z && !z2) {
                            ((ResizableStickerView) view).setBorderVisibility(false);
                        }
                    }
                    return z;
                }
            }
            return false;
        } catch (Error | Exception unused) {
        }
    return false;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.mScaleGestureDetector.onTouchEvent(view, motionEvent);
        RelativeLayout relativeLayout = (RelativeLayout) view.getParent();
        int i = 0;
        if (this.disContinueHandleTransparecy) {
            if (handleTransparency(view, motionEvent)) {
                return false;
            }
            this.disContinueHandleTransparecy = false;
        }
        if (!this.isTranslateEnabled) {
            return true;
        }
        int action = motionEvent.getAction();
        int actionMasked = motionEvent.getActionMasked() & action;
        if (actionMasked == 6) {
            int i2 = (65280 & action) >> 8;
            if (motionEvent.getPointerId(i2) == this.mActivePointerId) {
                if (i2 == 0) {
                    i = 1;
                }
                this.mPrevX = motionEvent.getX(i);
                this.mPrevY = motionEvent.getY(i);
                this.mActivePointerId = motionEvent.getPointerId(i);
            }
        } else if (actionMasked == 0) {
            if (relativeLayout != null) {
                relativeLayout.requestDisallowInterceptTouchEvent(true);
            }
            TouchCallbackListener touchCallbackListener = this.listener;
            if (touchCallbackListener != null) {
                touchCallbackListener.onTouchCallback(view);
            }
            view.bringToFront();
            if (view instanceof ResizableStickerView) {
                ((ResizableStickerView) view).setBorderVisibility(true);
            }
            this.mPrevX = motionEvent.getX();
            this.mPrevY = motionEvent.getY();
            this.mActivePointerId = motionEvent.getPointerId(0);
        } else if (actionMasked == 1) {
            this.mActivePointerId = -1;
            this.disContinueHandleTransparecy = true;
            TouchCallbackListener touchCallbackListener2 = this.listener;
            if (touchCallbackListener2 != null) {
                touchCallbackListener2.onTouchUpCallback(view);
            }
            float rotation = view.getRotation();
            if (Math.abs(90.0f - Math.abs(rotation)) <= 5.0f) {
                rotation = rotation > 0.0f ? 90.0f : -90.0f;
            }
            if (Math.abs(0.0f - Math.abs(rotation)) <= 5.0f) {
                rotation = rotation > 0.0f ? 0.0f : -0.0f;
            }
            if (Math.abs(180.0f - Math.abs(rotation)) <= 5.0f) {
                rotation = rotation > 0.0f ? 180.0f : -180.0f;
            }
            view.setRotation(rotation);
        } else if (actionMasked == 2) {
            if (relativeLayout != null) {
                relativeLayout.requestDisallowInterceptTouchEvent(true);
            }
            TouchCallbackListener touchCallbackListener3 = this.listener;
            if (touchCallbackListener3 != null) {
                touchCallbackListener3.onTouchMoveCallback(view);
            }
            int findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
            if (findPointerIndex != -1) {
                float x = motionEvent.getX(findPointerIndex);
                float y = motionEvent.getY(findPointerIndex);
                if (!this.mScaleGestureDetector.isInProgress()) {
                    adjustTranslation(view, x - this.mPrevX, y - this.mPrevY);
                }
            }
        } else if (actionMasked == 3) {
            this.mActivePointerId = -1;
        }
        return true;
    }

    private class ScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private float mPivotX;
        private float mPivotY;
        private Vector2D mPrevSpanVector;

        private ScaleGestureListener() {
            this.mPrevSpanVector = new Vector2D();
        }

        public boolean onScaleBegin(View view, ScaleGestureDetector scaleGestureDetector) {
            this.mPivotX = scaleGestureDetector.getFocusX();
            this.mPivotY = scaleGestureDetector.getFocusY();
            this.mPrevSpanVector.set(scaleGestureDetector.getCurrentSpanVector());
            return true;
        }

        public boolean onScale(View view, ScaleGestureDetector scaleGestureDetector) {
            TransformInfo transformInfo = new TransformInfo();
            float f = 0.0f;
            transformInfo.deltaAngle = MultiTouchListener.this.isRotateEnabled ? Vector2D.getAngle(this.mPrevSpanVector, scaleGestureDetector.getCurrentSpanVector()) : 0.0f;
            transformInfo.deltaX = MultiTouchListener.this.isTranslateEnabled ? scaleGestureDetector.getFocusX() - this.mPivotX : 0.0f;
            if (MultiTouchListener.this.isTranslateEnabled) {
                f = scaleGestureDetector.getFocusY() - this.mPivotY;
            }
            transformInfo.deltaY = f;
            transformInfo.pivotX = this.mPivotX;
            transformInfo.pivotY = this.mPivotY;
            transformInfo.minimumScale = MultiTouchListener.this.minimumScale;
            transformInfo.maximumScale = MultiTouchListener.this.maximumScale;
            MultiTouchListener.this.move(view, transformInfo);
            return false;
        }
    }

    private class TransformInfo {
        public float deltaAngle;
        public float deltaScale;
        public float deltaX;
        public float deltaY;
        public float maximumScale;
        public float minimumScale;
        public float pivotX;
        public float pivotY;

        private TransformInfo() {
        }
    }
}
