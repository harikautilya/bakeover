package com.example.kautilya.bakeover.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.movies.book.R;

public class TagView extends ViewGroup {

    private int mLeftWidth;
    private int mRightWidth;

    private final Rect mTmpContainerRect = new Rect();
    private final Rect mTmpChildRect = new Rect();

    public TagView(Context context) {
        super(context);
    }

    public TagView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();

        mLeftWidth = 0;
        mRightWidth = 0;

        int maxHeight = 0;
        int maxWidth = 0;
        int childState = 0;
        int usedWidth = 0;
        int usedHeight = 0;

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                // Measure the child.
                measureChildWithMargins(child, widthMeasureSpec, usedWidth, heightMeasureSpec, 0);

                // Update our size information based on the layout params.  Children
                // that asked to be positioned on the left or right go in those gutters.
                final LayoutParams lp = (LayoutParams) child.getLayoutParams();
                mLeftWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                if (usedWidth + mLeftWidth >= getMeasuredWidth()) {
                    maxWidth = Math.max(usedWidth, maxWidth);
                    usedWidth = 0;
                    usedHeight += maxHeight;
                    measureChildWithMargins(child, widthMeasureSpec, usedWidth, heightMeasureSpec, 0);
                    mLeftWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                }

                maxHeight = Math.max(maxHeight,
                        child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);
                childState = combineMeasuredStates(childState, child.getMeasuredState());

                usedWidth += mLeftWidth;
            }
        }
        usedHeight += maxHeight;
        maxWidth = Math.max(usedWidth, maxWidth);

        // Check against our minimum height and width
        maxHeight = Math.max(usedHeight, getSuggestedMinimumHeight());
        maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());

        // Report our final dimensions.
        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, childState),
                resolveSizeAndState(maxHeight, heightMeasureSpec,
                        childState << MEASURED_HEIGHT_STATE_SHIFT));
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final int count = getChildCount();

        // These are the far left and right edges in which we are performing layout.
        int leftPos = getPaddingLeft();
        int rightPos = right - left - getPaddingRight();

        // This is the middle region inside of the gutter.
        final int middleLeft = leftPos + mLeftWidth;
        final int middleRight = rightPos - mRightWidth;

        // These are the top and bottom edges in which we are performing layout.
        int parentTop = getPaddingTop();
        int parentBottom = bottom - top - getPaddingBottom();
        int shiftHeight = 0;
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                final LayoutParams lp = (LayoutParams) child.getLayoutParams();

                final int width = child.getMeasuredWidth();
                final int height = child.getMeasuredHeight();
                shiftHeight = Math.max(shiftHeight, height);
                // Compute the frame in which we are placing this child.
                mTmpContainerRect.left = leftPos + lp.leftMargin;
                mTmpContainerRect.right = leftPos + width + lp.rightMargin;
                leftPos = mTmpContainerRect.right;


                if (mTmpContainerRect.right > rightPos) {
                    leftPos = getPaddingLeft();
                    mTmpContainerRect.left = leftPos + lp.leftMargin;
                    parentTop = shiftHeight + parentTop;
                    parentBottom = parentTop + height;
                }

                mTmpContainerRect.top = parentTop + lp.topMargin;
                mTmpContainerRect.bottom = parentBottom - lp.bottomMargin;

                // Use the child's gravity and size to determine its final
                // frame within its container.
                Gravity.apply(lp.gravity, width, height, mTmpContainerRect, mTmpChildRect);

                // Place the child.
                child.layout(mTmpChildRect.left, mTmpChildRect.top,
                        mTmpChildRect.right, mTmpChildRect.bottom);
            }
        }
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new TagView.LayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }


    public static class LayoutParams extends MarginLayoutParams {


        public int gravity = Gravity.TOP | Gravity.START;


        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);

            // Pull the layout param values from the layout XML during
            // inflation.  This is not needed if you don't care about
            // changing the layout behavior in XML.
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.TagView_Layout);
            gravity = a.getInt(R.styleable.TagView_Layout_android_layout_gravity, gravity);
            a.recycle();
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }
}
