package com.oceanmtech.dmt.demo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.Scroller;
import java.util.LinkedList;
import java.util.Queue;

public class HorizontalListView extends AdapterView<ListAdapter> {
    protected ListAdapter mAdapter;
    public boolean mAlwaysOverrideTouch = true;
    protected int mCurrentX;
    public boolean mDataChanged = false;
    private DataSetObserver mDataObserver = new DataSetObserver() {
        public void onChanged() {
            synchronized (HorizontalListView.this) {
                HorizontalListView.this.mDataChanged = true;
            }
            HorizontalListView.this.invalidate();
            HorizontalListView.this.requestLayout();
        }

        public void onInvalidated() {
            HorizontalListView.this.reset();
            HorizontalListView.this.invalidate();
            HorizontalListView.this.requestLayout();
        }
    };
    private int mDisplayOffset = 0;
    private GestureDetector mGesture;
    public int mLeftViewIndex = -1;
    private int mMaxX = Integer.MAX_VALUE;
    protected int mNextX;
    private GestureDetector.OnGestureListener mOnGesture = new GestureDetector.SimpleOnGestureListener() {
        public boolean onDown(MotionEvent motionEvent) {
            return HorizontalListView.this.onDown(motionEvent);
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return HorizontalListView.this.onFling(motionEvent, motionEvent2, f, f2);
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            synchronized (HorizontalListView.this) {
                HorizontalListView.this.mNextX += (int) f;
            }
            HorizontalListView.this.requestLayout();
            return true;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            int i = 0;
            while (i < HorizontalListView.this.getChildCount()) {
                View childAt = HorizontalListView.this.getChildAt(i);
                if (isEventWithinView(motionEvent, childAt)) {
                    if (HorizontalListView.this.mOnItemClicked != null) {
                        OnItemClickListener onItemClickListener = HorizontalListView.this.mOnItemClicked;
                        HorizontalListView horizontalListView = HorizontalListView.this;
                        onItemClickListener.onItemClick(horizontalListView, childAt, horizontalListView.mLeftViewIndex + 1 + i, HorizontalListView.this.mAdapter.getItemId(HorizontalListView.this.mLeftViewIndex + 1 + i));
                    }
                    if (HorizontalListView.this.mOnItemSelected != null) {
                        OnItemSelectedListener onItemSelectedListener = HorizontalListView.this.mOnItemSelected;
                        HorizontalListView horizontalListView2 = HorizontalListView.this;
                        onItemSelectedListener.onItemSelected(horizontalListView2, childAt, horizontalListView2.mLeftViewIndex + 1 + i, HorizontalListView.this.mAdapter.getItemId(HorizontalListView.this.mLeftViewIndex + 1 + i));
                    }
                } else {
                    i++;
                }
            }
            return true;
        }

        public void onLongPress(MotionEvent motionEvent) {
            int childCount = HorizontalListView.this.getChildCount();
            int i = 0;
            while (i < childCount) {
                View childAt = HorizontalListView.this.getChildAt(i);
                if (!isEventWithinView(motionEvent, childAt)) {
                    i++;
                } else if (HorizontalListView.this.mOnItemLongClicked != null) {
                    OnItemLongClickListener onItemLongClickListener = HorizontalListView.this.mOnItemLongClicked;
                    HorizontalListView horizontalListView = HorizontalListView.this;
                    onItemLongClickListener.onItemLongClick(horizontalListView, childAt, horizontalListView.mLeftViewIndex + 1 + i, HorizontalListView.this.mAdapter.getItemId(HorizontalListView.this.mLeftViewIndex + 1 + i));
                    return;
                } else {
                    return;
                }
            }
        }

        private boolean isEventWithinView(MotionEvent motionEvent, View view) {
            Rect rect = new Rect();
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            int i = iArr[0];
            int i2 = iArr[1];
            rect.set(i, i2, view.getWidth() + i, view.getHeight() + i2);
            return rect.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
        }
    };
    public OnItemClickListener mOnItemClicked;
    public OnItemLongClickListener mOnItemLongClicked;
    public OnItemSelectedListener mOnItemSelected;
    private Queue<View> mRemovedViewQueue = new LinkedList();
    private int mRightViewIndex = 0;
    private Runnable mRunnable = new Runnable() {
        public void run() {
            HorizontalListView.this.requestLayout();
        }
    };
    protected Scroller mScroller;

    public static void verbose(String str, String str2, Object... objArr) {
    }

    public View getSelectedView() {
        return null;
    }

    public void setSelection(int i) {
    }

    public HorizontalListView(Context context) {
        super(context);
        initView();
    }

    public HorizontalListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    private synchronized void initView() {
        this.mLeftViewIndex = -1;
        this.mRightViewIndex = 0;
        this.mDisplayOffset = 0;
        this.mCurrentX = 0;
        this.mNextX = 0;
        this.mMaxX = Integer.MAX_VALUE;
        this.mScroller = new Scroller(getContext());
        this.mGesture = new GestureDetector(getContext(), this.mOnGesture);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.mOnItemSelected = onItemSelectedListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClicked = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClicked = onItemLongClickListener;
    }

    public ListAdapter getAdapter() {
        return this.mAdapter;
    }

    public void setAdapter(ListAdapter listAdapter) {
        ListAdapter listAdapter2 = this.mAdapter;
        if (listAdapter2 != null) {
            listAdapter2.unregisterDataSetObserver(this.mDataObserver);
        }
        this.mAdapter = listAdapter;
        listAdapter.registerDataSetObserver(this.mDataObserver);
        reset();
    }

    public synchronized void reset() {
        initView();
        removeAllViewsInLayout();
        requestLayout();
    }

    @SuppressLint("WrongConstant")
    private void addAndMeasureChild(View view, int i) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-1, -1);
        }
        addViewInLayout(view, i, layoutParams, true);
        view.measure(MeasureSpec.makeMeasureSpec(getWidth(), Integer.MIN_VALUE), MeasureSpec.makeMeasureSpec(getHeight(), Integer.MIN_VALUE));
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00e3, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onLayout(boolean r5, int r6, int r7, int r8, int r9) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.lang.String r0 = "ResizableStickerView"
            java.lang.String r1 = "onLayout"
            r2 = 0
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch:{ all -> 0x00e4 }
            verbose(r0, r1, r3)     // Catch:{ all -> 0x00e4 }
            super.onLayout(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x00e4 }
            android.widget.ListAdapter r5 = r4.mAdapter     // Catch:{ all -> 0x00e4 }
            if (r5 != 0) goto L_0x0014
            monitor-exit(r4)
            return
        L_0x0014:
            boolean r5 = r4.mDataChanged     // Catch:{ all -> 0x00e4 }
            if (r5 == 0) goto L_0x002d
            java.lang.String r5 = "ResizableStickerView"
            java.lang.String r6 = "data changed"
            java.lang.Object[] r7 = new java.lang.Object[r2]     // Catch:{ all -> 0x00e4 }
            verbose(r5, r6, r7)     // Catch:{ all -> 0x00e4 }
            int r5 = r4.mCurrentX     // Catch:{ all -> 0x00e4 }
            r4.initView()     // Catch:{ all -> 0x00e4 }
            r4.removeAllViewsInLayout()     // Catch:{ all -> 0x00e4 }
            r4.mNextX = r5     // Catch:{ all -> 0x00e4 }
            r4.mDataChanged = r2     // Catch:{ all -> 0x00e4 }
        L_0x002d:
            android.widget.Scroller r5 = r4.mScroller     // Catch:{ all -> 0x00e4 }
            boolean r5 = r5.computeScrollOffset()     // Catch:{ all -> 0x00e4 }
            r6 = 1
            if (r5 == 0) goto L_0x004d
            android.widget.Scroller r5 = r4.mScroller     // Catch:{ all -> 0x00e4 }
            int r5 = r5.getCurrX()     // Catch:{ all -> 0x00e4 }
            r4.mNextX = r5     // Catch:{ all -> 0x00e4 }
            java.lang.String r7 = "ResizableStickerView"
            java.lang.String r8 = "Computed scroll offset. Current x = %1$d"
            java.lang.Object[] r9 = new java.lang.Object[r6]     // Catch:{ all -> 0x00e4 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x00e4 }
            r9[r2] = r5     // Catch:{ all -> 0x00e4 }
            verbose(r7, r8, r9)     // Catch:{ all -> 0x00e4 }
        L_0x004d:
            int r5 = r4.mNextX     // Catch:{ all -> 0x00e4 }
            if (r5 > 0) goto L_0x0069
            java.lang.String r5 = "ResizableStickerView"
            java.lang.String r7 = "mNextX <= 0: %1$d"
            java.lang.Object[] r8 = new java.lang.Object[r6]     // Catch:{ all -> 0x00e4 }
            int r9 = r4.mNextX     // Catch:{ all -> 0x00e4 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x00e4 }
            r8[r2] = r9     // Catch:{ all -> 0x00e4 }
            verbose(r5, r7, r8)     // Catch:{ all -> 0x00e4 }
            r4.mNextX = r2     // Catch:{ all -> 0x00e4 }
            android.widget.Scroller r5 = r4.mScroller     // Catch:{ all -> 0x00e4 }
            r5.forceFinished(r6)     // Catch:{ all -> 0x00e4 }
        L_0x0069:
            int r5 = r4.mNextX     // Catch:{ all -> 0x00e4 }
            int r7 = r4.mMaxX     // Catch:{ all -> 0x00e4 }
            r8 = 2
            if (r5 < r7) goto L_0x0092
            java.lang.String r5 = "ResizableStickerView"
            java.lang.String r7 = "mNextX >= max: %1$d : %2$d"
            java.lang.Object[] r9 = new java.lang.Object[r8]     // Catch:{ all -> 0x00e4 }
            int r0 = r4.mNextX     // Catch:{ all -> 0x00e4 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x00e4 }
            r9[r2] = r0     // Catch:{ all -> 0x00e4 }
            int r0 = r4.mMaxX     // Catch:{ all -> 0x00e4 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x00e4 }
            r9[r6] = r0     // Catch:{ all -> 0x00e4 }
            verbose(r5, r7, r9)     // Catch:{ all -> 0x00e4 }
            int r5 = r4.mMaxX     // Catch:{ all -> 0x00e4 }
            r4.mNextX = r5     // Catch:{ all -> 0x00e4 }
            android.widget.Scroller r5 = r4.mScroller     // Catch:{ all -> 0x00e4 }
            r5.forceFinished(r6)     // Catch:{ all -> 0x00e4 }
        L_0x0092:
            java.lang.String r5 = "ResizableStickerView"
            java.lang.String r7 = "mCurrentX = %1$d; ; mNextX = %2$d"
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ all -> 0x00e4 }
            int r9 = r4.mCurrentX     // Catch:{ all -> 0x00e4 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x00e4 }
            r8[r2] = r9     // Catch:{ all -> 0x00e4 }
            int r9 = r4.mNextX     // Catch:{ all -> 0x00e4 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x00e4 }
            r8[r6] = r9     // Catch:{ all -> 0x00e4 }
            verbose(r5, r7, r8)     // Catch:{ all -> 0x00e4 }
            int r5 = r4.mCurrentX     // Catch:{ all -> 0x00e4 }
            int r7 = r4.mNextX     // Catch:{ all -> 0x00e4 }
            int r5 = r5 - r7
            java.lang.String r7 = "ResizableStickerView"
            java.lang.String r8 = "dx = %1$d"
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ all -> 0x00e4 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x00e4 }
            r6[r2] = r9     // Catch:{ all -> 0x00e4 }
            verbose(r7, r8, r6)     // Catch:{ all -> 0x00e4 }
            r4.removeNonVisibleItems(r5)     // Catch:{ all -> 0x00e4 }
            r4.fillList(r5)     // Catch:{ all -> 0x00e4 }
            r4.positionItems(r5)     // Catch:{ all -> 0x00e4 }
            int r5 = r4.mNextX     // Catch:{ all -> 0x00e4 }
            r4.mCurrentX = r5     // Catch:{ all -> 0x00e4 }
            android.widget.Scroller r5 = r4.mScroller     // Catch:{ all -> 0x00e4 }
            boolean r5 = r5.isFinished()     // Catch:{ all -> 0x00e4 }
            if (r5 != 0) goto L_0x00e2
            java.lang.String r5 = "ResizableStickerView"
            java.lang.String r6 = "Scroller is not finished"
            java.lang.Object[] r7 = new java.lang.Object[r2]     // Catch:{ all -> 0x00e4 }
            verbose(r5, r6, r7)     // Catch:{ all -> 0x00e4 }
            java.lang.Runnable r5 = r4.mRunnable     // Catch:{ all -> 0x00e4 }
            r4.post(r5)     // Catch:{ all -> 0x00e4 }
        L_0x00e2:
            monitor-exit(r4)
            return
        L_0x00e4:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.msl.demo.view.HorizontalListView.onLayout(boolean, int, int, int, int):void");
    }

    public static void error(String str, String str2) {
        error(str, str2);
    }

    private void fillList(int i) {
        View childAt = getChildAt(getChildCount() - 1);
        int i2 = 0;
        fillListRight(childAt != null ? childAt.getRight() : 0, i);
        View childAt2 = getChildAt(0);
        if (childAt2 != null) {
            i2 = childAt2.getLeft();
        }
        fillListLeft(i2, i);
    }

    private void fillListRight(int i, int i2) {
        while (i + i2 < getWidth() && this.mRightViewIndex < this.mAdapter.getCount()) {
            View view = this.mAdapter.getView(this.mRightViewIndex, this.mRemovedViewQueue.poll(), this);
            addAndMeasureChild(view, -1);
            i += view.getMeasuredWidth();
            if (this.mRightViewIndex == this.mAdapter.getCount() - 1) {
                this.mMaxX = (this.mCurrentX + i) - getWidth();
            }
            if (this.mMaxX < 0) {
                this.mMaxX = 0;
            }
            this.mRightViewIndex++;
        }
    }

    private void fillListLeft(int i, int i2) {
        int i3;
        while (i + i2 > 0 && (i3 = this.mLeftViewIndex) >= 0) {
            View view = this.mAdapter.getView(i3, this.mRemovedViewQueue.poll(), this);
            addAndMeasureChild(view, 0);
            i -= view.getMeasuredWidth();
            this.mLeftViewIndex--;
            this.mDisplayOffset -= view.getMeasuredWidth();
        }
    }

    private void removeNonVisibleItems(int i) {
        View childAt = getChildAt(0);
        while (childAt != null && childAt.getRight() + i <= 0) {
            this.mDisplayOffset += childAt.getMeasuredWidth();
            this.mRemovedViewQueue.offer(childAt);
            removeViewInLayout(childAt);
            this.mLeftViewIndex++;
            childAt = getChildAt(0);
        }
        View childAt2 = getChildAt(getChildCount() - 1);
        while (childAt2 != null && childAt2.getLeft() + i >= getWidth()) {
            this.mRemovedViewQueue.offer(childAt2);
            removeViewInLayout(childAt2);
            this.mRightViewIndex--;
            childAt2 = getChildAt(getChildCount() - 1);
        }
    }

    private void positionItems(int i) {
        if (getChildCount() > 0) {
            int i2 = this.mDisplayOffset + i;
            this.mDisplayOffset = i2;
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                View childAt = getChildAt(i3);
                int measuredWidth = childAt.getMeasuredWidth();
                childAt.layout(i2, 0, i2 + measuredWidth, childAt.getMeasuredHeight());
                i2 += measuredWidth + childAt.getPaddingRight();
            }
        }
    }

    public synchronized void scrollTo(int i) {
        this.mScroller.startScroll(this.mNextX, 0, i - this.mNextX, 0);
        requestLayout();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent) | this.mGesture.onTouchEvent(motionEvent);
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        synchronized (this) {
            this.mScroller.fling(this.mNextX, 0, (int) (-f), 0, 0, this.mMaxX, 0, 0);
        }
        requestLayout();
        return true;
    }

    public boolean onDown(MotionEvent motionEvent) {
        this.mScroller.forceFinished(true);
        return true;
    }
}
