package com.oceanmtech.dmt.demo.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;

import com.oceanmtech.dmt.R;
import com.oceanmtech.dmt.demo.listener.MultiTouchListener;

import java.io.File;
import java.io.FileOutputStream;

public class ResizableStickerView extends RelativeLayout implements MultiTouchListener.TouchCallbackListener {
    private static final int SELF_SIZE_DP = 30;
    public static final String TAG = "ResizableStickerView";
    private int alphaProg = 0;
    double angle = 0.0d;
    int baseh;
    int basew;
    int basex;
    int basey;
    private ImageView border_iv;
    public double centerX;
    public double centerY;
    private String colorType = "colored";
    private Context context;
    double dAngle = 0.0d;
/*    private ImageView delete_iv;*/
    private String drawableId;
    public int extraMargin = 35;
    float f206cX = 0.0f;
    float f207cY = 0.0f;
    public int f208he;
    private int f209s;
    public int f210wi;
    private String field_four = "";
    private int field_one = 0;
    private String field_three = "UNLOCKED";
    public String field_two = "0,0";
    private ImageView flip_iv;
    float heightMain = 0.0f;
    private int hueProg = 1;
    private int imgAlpha = 100;
    private int imgColor = 0;
    private boolean isBorderVisible = false;
    private boolean isColorFilterEnable = false;
    public boolean isMultiTouchEnabled = true;
    private boolean isSticker = true;
    private boolean isStrickerEditEnable = false;
    public int leftMargin = 0;
    public TouchEventListener listener = null;
    private OnTouchListener mTouchListener = new OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int i;
            int action = motionEvent.getAction();
            if (action == 0) {
                ResizableStickerView resizableStickerView = ResizableStickerView.this;
                resizableStickerView.this_orgX = resizableStickerView.getX();
                ResizableStickerView resizableStickerView2 = ResizableStickerView.this;
                resizableStickerView2.this_orgY = resizableStickerView2.getY();
                ResizableStickerView.this.scale_orgX = motionEvent.getRawX();
                ResizableStickerView.this.scale_orgY = motionEvent.getRawY();
                ResizableStickerView resizableStickerView3 = ResizableStickerView.this;
                resizableStickerView3.scale_orgWidth = (double) resizableStickerView3.getLayoutParams().width;
                ResizableStickerView resizableStickerView4 = ResizableStickerView.this;
                resizableStickerView4.scale_orgHeight = (double) resizableStickerView4.getLayoutParams().height;
                ResizableStickerView resizableStickerView5 = ResizableStickerView.this;
                resizableStickerView5.centerX = (double) (resizableStickerView5.getX() + ((View) ResizableStickerView.this.getParent()).getX() + (((float) ResizableStickerView.this.getWidth()) / 2.0f));
                int i2 = 0;
                int identifier = ResizableStickerView.this.getResources().getIdentifier("status_bar_height", "dimen", "android");
                if (identifier > 0) {
                    i2 = ResizableStickerView.this.getResources().getDimensionPixelSize(identifier);
                }
                ResizableStickerView resizableStickerView6 = ResizableStickerView.this;
                double y = (double) (resizableStickerView6.getY() + ((View) ResizableStickerView.this.getParent()).getY());
                double d = (double) i2;
                Double.isNaN(y);
                Double.isNaN(d);
                double height = (double) (((float) ResizableStickerView.this.getHeight()) / 2.0f);
                Double.isNaN(height);
                resizableStickerView6.centerY = y + d + height;
                return true;
            } else if (action == 1) {
                ResizableStickerView resizableStickerView7 = ResizableStickerView.this;
                resizableStickerView7.f210wi = resizableStickerView7.getLayoutParams().width;
                ResizableStickerView resizableStickerView8 = ResizableStickerView.this;
                resizableStickerView8.f208he = resizableStickerView8.getLayoutParams().height;
                return true;
            } else if (action != 2) {
                return true;
            } else {
                double atan2 = Math.atan2((double) (motionEvent.getRawY() - ResizableStickerView.this.scale_orgY), (double) (motionEvent.getRawX() - ResizableStickerView.this.scale_orgX));
                double d2 = (double) ResizableStickerView.this.scale_orgY;
                double d3 = ResizableStickerView.this.centerY;
                Double.isNaN(d2);
                double d4 = d2 - d3;
                double d5 = (double) ResizableStickerView.this.scale_orgX;
                double d6 = ResizableStickerView.this.centerX;
                Double.isNaN(d5);
                double abs = (Math.abs(atan2 - Math.atan2(d4, d5 - d6)) * 180.0d) / 3.141592653589793d;
                ResizableStickerView resizableStickerView9 = ResizableStickerView.this;
                double length = resizableStickerView9.getLength(resizableStickerView9.centerX, ResizableStickerView.this.centerY, (double) ResizableStickerView.this.scale_orgX, (double) ResizableStickerView.this.scale_orgY);
                ResizableStickerView resizableStickerView10 = ResizableStickerView.this;
                double length2 = resizableStickerView10.getLength(resizableStickerView10.centerX, ResizableStickerView.this.centerY, (double) motionEvent.getRawX(), (double) motionEvent.getRawY());
                ResizableStickerView resizableStickerView11 = ResizableStickerView.this;
                int dpToPx = (int) resizableStickerView11.dpToPx(resizableStickerView11.getContext(), 30.0f);
                if (length2 > length && (abs < 25.0d || Math.abs(abs - 180.0d) < 25.0d)) {
                    double round = (double) Math.round(Math.max((double) Math.abs(motionEvent.getRawX() - ResizableStickerView.this.scale_orgX), (double) Math.abs(motionEvent.getRawY() - ResizableStickerView.this.scale_orgY)));
                    ViewGroup.LayoutParams layoutParams = ResizableStickerView.this.getLayoutParams();
                    double d7 = (double) layoutParams.width;
                    Double.isNaN(d7);
                    Double.isNaN(round);
                    layoutParams.width = (int) (d7 + round);
                    ViewGroup.LayoutParams layoutParams2 = ResizableStickerView.this.getLayoutParams();
                    double d8 = (double) layoutParams2.height;
                    Double.isNaN(d8);
                    Double.isNaN(round);
                    layoutParams2.height = (int) (d8 + round);
                } else if (length2 < length && ((abs < 25.0d || Math.abs(abs - 180.0d) < 25.0d) && ResizableStickerView.this.getLayoutParams().width > (i = dpToPx / 2) && ResizableStickerView.this.getLayoutParams().height > i)) {
                    double round2 = (double) Math.round(Math.max((double) Math.abs(motionEvent.getRawX() - ResizableStickerView.this.scale_orgX), (double) Math.abs(motionEvent.getRawY() - ResizableStickerView.this.scale_orgY)));
                    ViewGroup.LayoutParams layoutParams3 = ResizableStickerView.this.getLayoutParams();
                    double d9 = (double) layoutParams3.width;
                    Double.isNaN(d9);
                    Double.isNaN(round2);
                    layoutParams3.width = (int) (d9 - round2);
                    ViewGroup.LayoutParams layoutParams4 = ResizableStickerView.this.getLayoutParams();
                    double d10 = (double) layoutParams4.height;
                    Double.isNaN(d10);
                    Double.isNaN(round2);
                    layoutParams4.height = (int) (d10 - round2);
                }
                ResizableStickerView.this.scale_orgX = motionEvent.getRawX();
                ResizableStickerView.this.scale_orgY = motionEvent.getRawY();
                ResizableStickerView.this.postInvalidate();
                ResizableStickerView.this.requestLayout();
                return true;
            }
        }
    };
    private OnTouchListener mTouchListener1 = new OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            ResizableStickerView resizableStickerView = (ResizableStickerView) view.getParent();
            int rawX = (int) motionEvent.getRawX();
            int rawY = (int) motionEvent.getRawY();
            LayoutParams layoutParams = (LayoutParams) ResizableStickerView.this.getLayoutParams();
            int action = motionEvent.getAction();
            if (action == 0) {
                if (resizableStickerView != null) {
                    resizableStickerView.requestDisallowInterceptTouchEvent(true);
                }
                if (ResizableStickerView.this.listener != null) {
                    ResizableStickerView.this.listener.onScaleDown(ResizableStickerView.this);
                }
                ResizableStickerView.this.invalidate();
                ResizableStickerView.this.basex = rawX;
                ResizableStickerView.this.basey = rawY;
                ResizableStickerView resizableStickerView2 = ResizableStickerView.this;
                resizableStickerView2.basew = resizableStickerView2.getWidth();
                ResizableStickerView resizableStickerView3 = ResizableStickerView.this;
                resizableStickerView3.baseh = resizableStickerView3.getHeight();
                ResizableStickerView.this.getLocationOnScreen(new int[2]);
                ResizableStickerView.this.margl = layoutParams.leftMargin;
                ResizableStickerView.this.margt = layoutParams.topMargin;
            } else if (action == 1) {
                ResizableStickerView resizableStickerView4 = ResizableStickerView.this;
                resizableStickerView4.f210wi = resizableStickerView4.getLayoutParams().width;
                ResizableStickerView resizableStickerView5 = ResizableStickerView.this;
                resizableStickerView5.f208he = resizableStickerView5.getLayoutParams().height;
                ResizableStickerView resizableStickerView6 = ResizableStickerView.this;
                resizableStickerView6.leftMargin = ((LayoutParams) resizableStickerView6.getLayoutParams()).leftMargin;
                ResizableStickerView resizableStickerView7 = ResizableStickerView.this;
                resizableStickerView7.topMargin = ((LayoutParams) resizableStickerView7.getLayoutParams()).topMargin;
                ResizableStickerView resizableStickerView8 = ResizableStickerView.this;
                resizableStickerView8.field_two = String.valueOf(ResizableStickerView.this.leftMargin) + "," + String.valueOf(ResizableStickerView.this.topMargin);
                if (ResizableStickerView.this.listener != null) {
                    ResizableStickerView.this.listener.onScaleUp(ResizableStickerView.this);
                }
            } else if (action == 2) {
                if (resizableStickerView != null) {
                    resizableStickerView.requestDisallowInterceptTouchEvent(true);
                }
                if (ResizableStickerView.this.listener != null) {
                    ResizableStickerView.this.listener.onScaleMove(ResizableStickerView.this);
                }
                float degrees = (float) Math.toDegrees(Math.atan2((double) (rawY - ResizableStickerView.this.basey), (double) (rawX - ResizableStickerView.this.basex)));
                if (degrees < 0.0f) {
                    degrees += 360.0f;
                }
                int i = rawX - ResizableStickerView.this.basex;
                int i2 = rawY - ResizableStickerView.this.basey;
                int i3 = i2 * i2;
                int sqrt = (int) (Math.sqrt((double) ((i * i) + i3)) * Math.cos(Math.toRadians((double) (degrees - ResizableStickerView.this.getRotation()))));
                int sqrt2 = (int) (Math.sqrt((double) ((sqrt * sqrt) + i3)) * Math.sin(Math.toRadians((double) (degrees - ResizableStickerView.this.getRotation()))));
                int i4 = (sqrt * 2) + ResizableStickerView.this.basew;
                int i5 = (sqrt2 * 2) + ResizableStickerView.this.baseh;
                if (i4 > (ResizableStickerView.this.extraMargin * 2) + ResizableStickerView.this.margin5dp) {
                    layoutParams.width = i4;
                    layoutParams.leftMargin = ResizableStickerView.this.margl - sqrt;
                }
                if (i5 > (ResizableStickerView.this.extraMargin * 2) + ResizableStickerView.this.margin5dp) {
                    layoutParams.height = i5;
                    layoutParams.topMargin = ResizableStickerView.this.margt - sqrt2;
                }
                ResizableStickerView.this.setLayoutParams(layoutParams);
                ResizableStickerView.this.performLongClick();
            }
            return true;
        }
    };
    public ImageViewForGlide main_iv;
    public int margin5dp = 2;
    int margl;
    int margt;
    private OnTouchListener rTouchListener = new OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            ResizableStickerView resizableStickerView = (ResizableStickerView) view.getParent();
            int action = motionEvent.getAction();
            if (action == 0) {
                if (resizableStickerView != null) {
                    resizableStickerView.requestDisallowInterceptTouchEvent(true);
                }
                if (ResizableStickerView.this.listener != null) {
                    ResizableStickerView.this.listener.onRotateDown(ResizableStickerView.this);
                }
                Rect rect = new Rect();
                ((View) view.getParent()).getGlobalVisibleRect(rect);
                ResizableStickerView.this.f206cX = rect.exactCenterX();
                ResizableStickerView.this.f207cY = rect.exactCenterY();
                ResizableStickerView.this.vAngle = (double) ((View) view.getParent()).getRotation();
                ResizableStickerView resizableStickerView2 = ResizableStickerView.this;
                resizableStickerView2.tAngle = (Math.atan2((double) (resizableStickerView2.f207cY - motionEvent.getRawY()), (double) (ResizableStickerView.this.f206cX - motionEvent.getRawX())) * 180.0d) / 3.141592653589793d;
                ResizableStickerView resizableStickerView3 = ResizableStickerView.this;
                resizableStickerView3.dAngle = resizableStickerView3.vAngle - ResizableStickerView.this.tAngle;
            } else if (action != 1) {
                if (action == 2) {
                    if (resizableStickerView != null) {
                        resizableStickerView.requestDisallowInterceptTouchEvent(true);
                    }
                    if (ResizableStickerView.this.listener != null) {
                        ResizableStickerView.this.listener.onRotateMove(ResizableStickerView.this);
                    }
                    ResizableStickerView resizableStickerView4 = ResizableStickerView.this;
                    resizableStickerView4.angle = (Math.atan2((double) (resizableStickerView4.f207cY - motionEvent.getRawY()), (double) (ResizableStickerView.this.f206cX - motionEvent.getRawX())) * 180.0d) / 3.141592653589793d;
                    float f = (float) (ResizableStickerView.this.angle + ResizableStickerView.this.dAngle);
                    ((View) view.getParent()).setRotation(f);
                    ((View) view.getParent()).invalidate();
                    ((View) view.getParent()).requestLayout();
                    if (Math.abs(90.0f - Math.abs(f)) <= 5.0f) {
                        f = f > 0.0f ? 90.0f : -90.0f;
                    }
                    if (Math.abs(0.0f - Math.abs(f)) <= 5.0f) {
                        f = f > 0.0f ? 0.0f : -0.0f;
                    }
                    if (Math.abs(180.0f - Math.abs(f)) <= 5.0f) {
                        f = f > 0.0f ? 180.0f : -180.0f;
                    }
                    ((View) view.getParent()).setRotation(f);
                }
            } else if (ResizableStickerView.this.listener != null) {
                ResizableStickerView.this.listener.onRotateUp(ResizableStickerView.this);
            }
            return true;
        }
    };
    private Uri resUri = null;
  //  private ImageView rotate_iv;
    private float rotation;
    Animation scale;
    private int scaleRotateProg = 0;
    private ImageView scale_iv;
    public double scale_orgHeight = -1.0d;
    public double scale_orgWidth = -1.0d;
    public float scale_orgX = -1.0f;
    public float scale_orgY = -1.0f;
   // int screenHeight = HttpStatus.SC_MULTIPLE_CHOICES;
   // int screenWidth = HttpStatus.SC_MULTIPLE_CHOICES;
    public String stkr_path = "";
    double tAngle = 0.0d;
    public float this_orgX = -1.0f;
    public float this_orgY = -1.0f;
    public int topMargin = 0;
    double vAngle = 0.0d;
    float widthMain = 0.0f;
    private int xRotateProg = 0;
    private int yRotateProg = 0;
    private float yRotation;
    private int zRotateProg = 0;
    Animation zoomInScale;
    Animation zoomOutScale;

    public interface TouchEventListener {
        byte[] getResBytes(Context context, String str);

        void onCenterX(View view);

        void onCenterXY(View view);

        void onCenterY(View view);

        void onDelete();

        void onEdit(View view, Uri uri);

        void onOtherXY(View view);

        void onRotateDown(View view);

        void onRotateMove(View view);

        void onRotateUp(View view);

        void onScaleDown(View view);

        void onScaleMove(View view);

        void onScaleUp(View view);

        void onTouchDown(View view);

        void onTouchMove(View view);

        void onTouchUp(View view);
    }

    public String getField_three() {
        return this.field_three;
    }

    public void setField_three(String str) {
        this.field_three = str;
    }

    public ResizableStickerView setOnTouchCallbackListener(TouchEventListener touchEventListener) {
        this.listener = touchEventListener;
        return this;
    }

    public ResizableStickerView(Context context2) {
        super(context2);
        init(context2);
    }

    public ResizableStickerView(Context context2, AttributeSet attributeSet) {
        super(context2, attributeSet);
        init(context2);
    }

    public ResizableStickerView(Context context2, AttributeSet attributeSet, int i) {
        super(context2, attributeSet, i);
        init(context2);
    }

    public void init(Context context2) {
        this.context = context2;
        this.main_iv = new ImageViewForGlide(this.context);
        this.scale_iv = new ImageView(this.context);
        this.border_iv = new ImageView(this.context);
        this.flip_iv = new ImageView(this.context);
     //   this.rotate_iv = new ImageView(this.context);
     //   this.delete_iv = new ImageView(this.context);
        this.extraMargin = (int) dpToPx(this.context, 30.0f);
        this.margin5dp = (int) dpToPx(this.context, 2.5f);
        this.f209s = (int) dpToPx(this.context, 25.0f);
        this.f210wi = (int) dpToPx(this.context, 200.0f);
        this.f208he = (int) dpToPx(this.context, 200.0f);
        this.scale_iv.setImageResource(R.drawable.border);
        this.border_iv.setImageResource(R.drawable.border);
        this.flip_iv.setImageResource(R.drawable.border);
      //  this.rotate_iv.setImageResource(R.drawable.rotate);
      //  this.delete_iv.setImageResource(R.drawable.sticker_delete1);
        LayoutParams layoutParams = new LayoutParams(this.f210wi, this.f208he);
        LayoutParams layoutParams2 = new LayoutParams(-1, -1);
        int dpToPx = (int) dpToPx(this.context, 1.5f);
        layoutParams2.setMargins(dpToPx, dpToPx, dpToPx, dpToPx);
        layoutParams2.addRule(17);
        int i = this.f209s;
        LayoutParams layoutParams3 = new LayoutParams(i, i);
        layoutParams3.addRule(12);
        layoutParams3.addRule(11);
        int i2 = this.margin5dp;
        layoutParams3.setMargins(i2, i2, i2, i2);
        int i3 = this.f209s;
        LayoutParams layoutParams4 = new LayoutParams(i3, i3);
        layoutParams4.addRule(10);
        layoutParams4.addRule(11);
        int i4 = this.margin5dp;
        layoutParams4.setMargins(i4, i4, i4, i4);
        int i5 = this.f209s;
        LayoutParams layoutParams5 = new LayoutParams(i5, i5);
        layoutParams5.addRule(12);
        layoutParams5.addRule(9);
        int i6 = this.margin5dp;
        layoutParams5.setMargins(i6, i6, i6, i6);
        int i7 = this.f209s;
        LayoutParams layoutParams6 = new LayoutParams(i7, i7);
        layoutParams6.addRule(10);
        layoutParams6.addRule(9);
        int i8 = this.margin5dp;
        layoutParams6.setMargins(i8, i8, i8, i8);
        LayoutParams layoutParams7 = new LayoutParams(-1, -1);
        setLayoutParams(layoutParams);
        setBackgroundResource(R.drawable.border);
        addView(this.border_iv);
        this.border_iv.setLayoutParams(layoutParams7);
        this.border_iv.setScaleType(ImageView.ScaleType.FIT_XY);
        this.border_iv.setTag("border_iv");
        addView(this.main_iv);
        this.main_iv.setLayoutParams(layoutParams2);
        addView(this.flip_iv);
        this.flip_iv.setLayoutParams(layoutParams4);
        this.flip_iv.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ImageViewForGlide imageViewForGlide = ResizableStickerView.this.main_iv;
                float f = -180.0f;
                if (ResizableStickerView.this.main_iv.getRotationY() == -180.0f) {
                    f = 0.0f;
                }
                imageViewForGlide.setRotationY(f);
                ResizableStickerView.this.main_iv.invalidate();
                ResizableStickerView.this.requestLayout();
            }
        });
//        addView(this.rotate_iv);
//        this.rotate_iv.setLayoutParams(layoutParams5);
//        this.rotate_iv.setOnTouchListener(this.rTouchListener);
    //    addView(this.delete_iv);
//        this.delete_iv.setLayoutParams(layoutParams6);
//        this.delete_iv.setOnClickListener(new OnClickListener() {
//            public void onClick(View view) {
//               /* final ViewGroup viewGroup = (ViewGroup) ResizableStickerView.this.getParent();
//                ResizableStickerView.this.zoomInScale.setAnimationListener(new Animation.AnimationListener() {
//                    public void onAnimationRepeat(Animation animation) {
//                    }
//
//                    public void onAnimationStart(Animation animation) {
//                    }
//
//                    public void onAnimationEnd(Animation animation) {
//                        viewGroup.removeView(ResizableStickerView.this);
//                    }
//                });
//                ResizableStickerView.this.main_iv.startAnimation(ResizableStickerView.this.zoomInScale);
//                ResizableStickerView.this.setBorderVisibility(false);
//                if (ResizableStickerView.this.listener != null) {
//                    ResizableStickerView.this.listener.onDelete();
//                }*/
//            }
//        });
        addView(this.scale_iv);
        this.scale_iv.setLayoutParams(layoutParams3);
        this.scale_iv.setOnTouchListener(this.mTouchListener1);
        this.scale_iv.setTag("scale_iv");
        this.rotation = getRotation();
        //this.scale = AnimationUtils.loadAnimation(getContext(), R.anim.sticker_scale_anim);
       // this.zoomOutScale = AnimationUtils.loadAnimation(getContext(), R.anim.sticker_scale_zoom_out);
      //  this.zoomInScale = AnimationUtils.loadAnimation(getContext(), R.anim.sticker_scale_zoom_in);
        this.isMultiTouchEnabled = setDefaultTouchListener(true);
    }

    public boolean setDefaultTouchListener(boolean z) {
        if (z) {
            this.field_three = "UNLOCKED";
            setOnTouchListener(new MultiTouchListener(this.context).enableRotation(true).setOnTouchCallbackListener(this));
            return true;
        }
        this.field_three = "LOCKED";
        setOnTouchListener((OnTouchListener) null);
        return false;
    }

    public void setBorderVisibility(boolean z) {
        this.isBorderVisible = z;
        if (!z) {
            this.border_iv.setVisibility(GONE);
            this.scale_iv.setVisibility(GONE);
            this.flip_iv.setVisibility(GONE);
       //     this.rotate_iv.setVisibility(GONE);
        //    this.delete_iv.setVisibility(GONE);
            setBackgroundResource(0);
            if (this.isColorFilterEnable) {
                this.main_iv.setColorFilter(Color.parseColor("#303828"));
            }
        } else if (this.border_iv.getVisibility() != VISIBLE) {
            this.border_iv.setVisibility(VISIBLE);
            this.scale_iv.setVisibility(VISIBLE);
            this.flip_iv.setVisibility(VISIBLE);
         //   this.rotate_iv.setVisibility(VISIBLE);
        //    this.delete_iv.setVisibility(GONE);
            setBackgroundResource(R.drawable.border);
            this.main_iv.startAnimation(this.scale);
        }
    }

    public boolean getBorderVisbilty() {
        return this.isBorderVisible;
    }

    public void opecitySticker(int i) {
        try {
            this.main_iv.setAlpha(((float) i) / 100.0f);
            this.imgAlpha = i;
        } catch (Exception unused) {
        }
    }

    public int getHueProg() {
        return this.hueProg;
    }

    public void setHueProg(int i) {
        this.hueProg = i;
        if (i == 0) {
            this.main_iv.setColorFilter(-1);
        } else if (i == 100) {
            this.main_iv.setColorFilter(ViewCompat.MEASURED_STATE_MASK);
        } else {
            this.main_iv.setColorFilter(ColorFilterGenerator.adjustHue((float) i));
        }
    }

    public String getColorType() {
        return this.colorType;
    }

    public void setColorType(String str) {
        this.colorType = str;
    }

    public int getAlphaProg() {
        return this.imgAlpha;
    }

    public void setAlphaProg(int i) {
        this.alphaProg = i;
        opecitySticker(i);
    }

    public int getColor() {
        return this.imgColor;
    }

    public void setColor(int i) {
        try {
            this.main_iv.setColorFilter(i);
            this.imgColor = i;
        } catch (Exception unused) {
        }
    }

    public void setBgDrawable(String str) {
        try {
            this.main_iv.setImageResource(getResources().getIdentifier(str, "drawable", this.context.getPackageName()));
            this.drawableId = str;
            this.main_iv.startAnimation(this.zoomOutScale);
        } catch (Error e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

/*
    public void setStrPath(String str) {
        try {
            String[] split = str.split(":");
            String str2 = split[0];
            if (split.length > 1) {
                ((RequestBuilder) ((RequestBuilder) ((RequestBuilder) ((RequestBuilder) ((RequestBuilder) ((RequestBuilder) ((RequestBuilder) Glide.with(this.context).load(new File(str2).getAbsoluteFile()).diskCacheStrategy(DiskCacheStrategy.NONE)).skipMemoryCache(true)).override(ImageUtils.dpToPx(this.context, HttpStatus.SC_MULTIPLE_CHOICES), ImageUtils.dpToPx(this.context, HttpStatus.SC_MULTIPLE_CHOICES))).transform((Transformation<Bitmap>) new CropTransformation(this.context, Float.parseFloat(split[1]), Float.parseFloat(split[2]), Float.parseFloat(split[3]), Float.parseFloat(split[4])))).dontAnimate()).placeholder((int) R.drawable.no_image)).error((int) R.drawable.no_image)).into((ImageView) this.main_iv);
            } else {
                ((RequestBuilder) ((RequestBuilder) ((RequestBuilder) ((RequestBuilder) ((RequestBuilder) ((RequestBuilder) Glide.with(this.context).load(new File(str).getAbsoluteFile()).diskCacheStrategy(DiskCacheStrategy.NONE)).skipMemoryCache(true)).override(ImageUtils.dpToPx(this.context, HttpStatus.SC_MULTIPLE_CHOICES), ImageUtils.dpToPx(this.context, HttpStatus.SC_MULTIPLE_CHOICES))).dontAnimate()).placeholder((int) R.drawable.no_image)).error((int) R.drawable.instagram)).into((ImageView) this.main_iv);
            }
            this.stkr_path = str;
            this.main_iv.startAnimation(this.zoomOutScale);
        } catch (Error | Exception e) {
            e.printStackTrace();
        }
    }
*/

    public void setMainImageUri(Uri uri) {
        this.resUri = uri;
        this.main_iv.setImageURI(uri);
    }

    public Uri getMainImageUri() {
        return this.resUri;
    }

    public void setMainImageBitmap(Bitmap bitmap) {
        this.main_iv.setImageBitmap(bitmap);
    }

    public ComponentInfo getComponentInfo(boolean z) {
        if (z) {
            return getComponentInfoWithMargin();
        }
        return getComponentInfo1();
    }

    public ComponentInfo getComponentInfo1() {
        ComponentInfo componentInfo = new ComponentInfo();
        componentInfo.setPOS_X(getX());
        componentInfo.setPOS_Y(getY());
        componentInfo.setWIDTH(this.f210wi);
        componentInfo.setHEIGHT(this.f208he);
        componentInfo.setRES_ID(this.drawableId);
        componentInfo.setSTC_COLOR(this.imgColor);
        componentInfo.setRES_URI(this.resUri);
        componentInfo.setSTC_OPACITY(this.imgAlpha);
        componentInfo.setCOLORTYPE(this.colorType);
        componentInfo.setROTATION(getRotation());
        componentInfo.setY_ROTATION(this.main_iv.getRotationY());
        componentInfo.setXRotateProg(this.xRotateProg);
        componentInfo.setYRotateProg(this.yRotateProg);
        componentInfo.setZRotateProg(this.zRotateProg);
        componentInfo.setScaleProg(this.scaleRotateProg);
        componentInfo.setSTKR_PATH(this.stkr_path);
        componentInfo.setSTC_HUE(this.hueProg);
        componentInfo.setFIELD_ONE(this.field_one);
        componentInfo.setFIELD_TWO(this.field_two);
        componentInfo.setFIELD_THREE(this.field_three);
        componentInfo.setFIELD_FOUR(this.field_four);
        return componentInfo;
    }

    public ComponentInfo getComponentInfoWithMargin() {
        ComponentInfo componentInfo = new ComponentInfo();
        componentInfo.setPOS_X(getX() + this.main_iv.getX());
        componentInfo.setPOS_Y(getY() + this.main_iv.getY());
        componentInfo.setWIDTH(this.main_iv.getWidth());
        componentInfo.setHEIGHT(this.main_iv.getHeight());
        componentInfo.setRES_ID(this.drawableId);
        componentInfo.setSTC_COLOR(this.imgColor);
        componentInfo.setRES_URI(this.resUri);
        componentInfo.setSTC_OPACITY(this.imgAlpha);
        componentInfo.setCOLORTYPE(this.colorType);
        componentInfo.setROTATION(getRotation());
        componentInfo.setY_ROTATION(this.main_iv.getRotationY());
        componentInfo.setXRotateProg(this.xRotateProg);
        componentInfo.setYRotateProg(this.yRotateProg);
        componentInfo.setZRotateProg(this.zRotateProg);
        componentInfo.setScaleProg(this.scaleRotateProg);
        componentInfo.setSTKR_PATH(this.stkr_path);
        componentInfo.setSTC_HUE(this.hueProg);
        componentInfo.setFIELD_ONE(this.field_one);
        componentInfo.setFIELD_TWO(this.field_two);
        componentInfo.setFIELD_THREE(this.field_three);
        componentInfo.setFIELD_FOUR(this.field_four);
        return componentInfo;
    }

    public void setComponentInfo(ComponentInfo componentInfo, boolean z) {
        if (z) {
            setComponentInfoWithMargin(componentInfo);
        } else {
            setComponentInfo1(componentInfo);
        }
    }

    public void setComponentInfo1(ComponentInfo componentInfo) {
        this.f210wi = componentInfo.getWIDTH();
        this.f208he = componentInfo.getHEIGHT();
        this.drawableId = componentInfo.getRES_ID();
        this.resUri = componentInfo.getRES_URI();
        this.rotation = componentInfo.getROTATION();
        this.imgColor = componentInfo.getSTC_COLOR();
        this.yRotation = componentInfo.getY_ROTATION();
        this.imgAlpha = componentInfo.getSTC_OPACITY();
        this.stkr_path = componentInfo.getSTKR_PATH();
        this.colorType = componentInfo.getCOLORTYPE();
        this.hueProg = componentInfo.getSTC_HUE();
        this.field_two = componentInfo.getFIELD_TWO();
        this.field_three = componentInfo.getFIELD_THREE();
        if (!this.stkr_path.equals("")) {
          //  setStrPath(this.stkr_path);
        } else if (!this.drawableId.equals("")) {
            setBgDrawable(this.drawableId);
        }
        if (this.colorType.equals("white")) {
            setColor(this.imgColor);
        } else {
            setHueProg(this.hueProg);
        }
        setRotation(this.rotation);
        opecitySticker(this.imgAlpha);
        if (!this.field_two.equals("")) {
            String[] split = this.field_two.split(",");
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1]);
            ((LayoutParams) getLayoutParams()).leftMargin = parseInt;
            ((LayoutParams) getLayoutParams()).topMargin = parseInt2;
            getLayoutParams().width = this.f210wi;
            getLayoutParams().height = this.f208he;
            setX(componentInfo.getPOS_X() + ((float) (parseInt * -1)));
            setY(componentInfo.getPOS_Y() + ((float) (parseInt2 * -1)));
        } else {
            getLayoutParams().width = this.f210wi;
            getLayoutParams().height = this.f208he;
            setX(componentInfo.getPOS_X());
            setY(componentInfo.getPOS_Y());
        }
        if (componentInfo.getTYPE() == "SHAPE") {
            this.flip_iv.setVisibility(GONE);
            this.isSticker = false;
        }
        if (componentInfo.getTYPE() == "STICKER") {
            this.flip_iv.setVisibility(VISIBLE);
            this.isSticker = true;
        }
        if ("LOCKED".equals(this.field_three)) {
            this.isMultiTouchEnabled = setDefaultTouchListener(false);
        } else {
            this.isMultiTouchEnabled = setDefaultTouchListener(true);
        }
        this.main_iv.setRotationY(this.yRotation);
    }

    public void setComponentInfoWithMargin(ComponentInfo componentInfo) {
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        int i = this.extraMargin;
        layoutParams.setMargins(i, i, i, i);
        layoutParams.addRule(17);
        this.main_iv.setLayoutParams(layoutParams);
        this.f210wi = componentInfo.getWIDTH() + (this.extraMargin * 2);
        this.f208he = componentInfo.getHEIGHT() + (this.extraMargin * 2);
        this.drawableId = componentInfo.getRES_ID();
        this.resUri = componentInfo.getRES_URI();
        this.rotation = componentInfo.getROTATION();
        this.imgColor = componentInfo.getSTC_COLOR();
        this.yRotation = componentInfo.getY_ROTATION();
        this.imgAlpha = componentInfo.getSTC_OPACITY();
        this.stkr_path = componentInfo.getSTKR_PATH();
        this.colorType = componentInfo.getCOLORTYPE();
        this.hueProg = componentInfo.getSTC_HUE();
        this.field_two = componentInfo.getFIELD_TWO();
        this.field_three = componentInfo.getFIELD_THREE();
        if (!this.stkr_path.equals("")) {
          //  setStrPath(this.stkr_path);
        } else if (!this.drawableId.equals("")) {
            setBgDrawable(this.drawableId);
        }
       /* if (this.colorType.equals("white")) {
            setColor(this.imgColor);
        } else {
            setHueProg(this.hueProg);
        }*/
        setRotation(this.rotation);
        opecitySticker(this.imgAlpha);
        if (((float) this.f210wi) > this.widthMain) {
            ((LayoutParams) getLayoutParams()).leftMargin = (int) (this.widthMain - ((float) this.f210wi));
            setX((componentInfo.getPOS_X() - ((float) this.extraMargin)) + ((this.widthMain - ((float) this.f210wi)) * -1.0f));
        } else {
            setX(componentInfo.getPOS_X() - ((float) this.extraMargin));
        }
        if (((float) this.f208he) > this.heightMain) {
            ((LayoutParams) getLayoutParams()).topMargin = (int) (this.heightMain - ((float) this.f208he));
            setY((componentInfo.getPOS_Y() - ((float) this.extraMargin)) + ((this.heightMain - ((float) this.f208he)) * -1.0f));
        } else {
            setY(componentInfo.getPOS_Y() - ((float) this.extraMargin));
        }
        getLayoutParams().width = this.f210wi;
        getLayoutParams().height = this.f208he;
        if (componentInfo.getTYPE() == "SHAPE") {
            this.flip_iv.setVisibility(GONE);
            this.isSticker = false;
        }
        if (componentInfo.getTYPE() == "STICKER") {
            this.flip_iv.setVisibility(VISIBLE);
            this.isSticker = true;
        }
        if ("LOCKED".equals(this.field_three)) {
            this.isMultiTouchEnabled = setDefaultTouchListener(false);
        } else {
            this.isMultiTouchEnabled = setDefaultTouchListener(true);
        }
        this.main_iv.setRotationY(this.yRotation);
    }

    public void optimizeScreen(float f, float f2) {
     /*   this.screenHeight = (int) f2;
        this.screenWidth = (int) f;*/
    }

    public void setMainLayoutWH(float f, float f2) {
        this.widthMain = f;
        this.heightMain = f2;
    }

    public float getMainWidth() {
        return this.widthMain;
    }

    public float getMainHeight() {
        return this.heightMain;
    }

    public void incrX() {
        setX(getX() + 1.0f);
    }

    public void decX() {
        setX(getX() - 1.0f);
    }

    public void incrY() {
        setY(getY() + 1.0f);
    }

    public void decY() {
        setY(getY() - 1.0f);
    }

    private void saveStkrBitmap(final Bitmap bitmap) {
        final ProgressDialog show = ProgressDialog.show(this.context, "", "", true);
        show.setCancelable(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    ResizableStickerView.this.stkr_path = ResizableStickerView.this.saveBitmapObject1(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                show.dismiss();
            }
        }).start();
        show.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
            }
        });
    }

    public String saveBitmapObject1(Bitmap bitmap) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), ".Poster Maker Stickers/category1");
        file.mkdirs();
        long currentTimeMillis = System.currentTimeMillis();
        File file2 = new File(file, "raw1-" + currentTimeMillis + ".png");
        String absolutePath = file2.getAbsolutePath();
        if (file2.exists()) {
            file2.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.close();
            return absolutePath;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public float dpToPx(Context context2, float f) {
        context2.getResources();
        return (float) Math.round(f * Resources.getSystem().getDisplayMetrics().density);
    }

    public double getLength(double d, double d2, double d3, double d4) {
        return Math.sqrt(Math.pow(d4 - d2, 2.0d) + Math.pow(d3 - d, 2.0d));
    }

    public void enableColorFilter(boolean z) {
        this.isColorFilterEnable = z;
    }

    public void onTouchCallback(View view) {
        TouchEventListener touchEventListener = this.listener;
        if (touchEventListener != null) {
            touchEventListener.onTouchDown(view);
        }
    }

    public void onTouchUpCallback(View view) {
        TouchEventListener touchEventListener = this.listener;
        if (touchEventListener != null) {
            touchEventListener.onTouchUp(view);
        }
    }

    public void onTouchMoveCallback(View view) {
        TouchEventListener touchEventListener = this.listener;
        if (touchEventListener != null) {
            touchEventListener.onTouchMove(view);
        }
    }

    public void onCenterPosX(View view) {
        TouchEventListener touchEventListener = this.listener;
        if (touchEventListener != null) {
            touchEventListener.onCenterX(view);
        }
    }

    public void onCenterPosY(View view) {
        TouchEventListener touchEventListener = this.listener;
        if (touchEventListener != null) {
            touchEventListener.onCenterY(view);
        }
    }

    public void onCenterPosXY(View view) {
        TouchEventListener touchEventListener = this.listener;
        if (touchEventListener != null) {
            touchEventListener.onCenterXY(view);
        }
    }

    public void onOtherPos(View view) {
        TouchEventListener touchEventListener = this.listener;
        if (touchEventListener != null) {
            touchEventListener.onOtherXY(view);
        }
    }
}
