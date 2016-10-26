package com.szlanyou.www.cardslide;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ly-yangyuzhi on 2016/10/26.
 */

public class CardRecyclerView extends RecyclerView {

    private int mInitialTouchX;
    private int mInitialTouchY;
    private int mLastTouchX;
    private int mLastTouchY;
    private int mTouchSlop;

    public CardRecyclerView(Context context) {
        super(context);
    }

    public CardRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CardRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        super.onTouchEvent(e);
        int action = MotionEventCompat.getActionMasked(e);
        int actionIndex = MotionEventCompat.getActionIndex(e);

        // 处理item focus的逻辑
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mInitialTouchX = mLastTouchX = (int) (e.getX() + 0.5f);
                mInitialTouchY = mLastTouchY = (int) (e.getY() + 0.5f);
                break;


            case MotionEvent.ACTION_MOVE:

                final int x = (int) (e.getX() + 0.5f);
                final int y = (int) (e.getY() + 0.5f);
                int dx = mLastTouchX - x;
                int dy = mLastTouchY - y;

//                if (getLayoutManager().canScrollHorizontally() && Math.abs(dx) > mTouchSlop) {
//                    requestLayout();
//                } else
                if (getLayoutManager().canScrollVertically() && Math.abs(dy) > mTouchSlop) {
                    requestLayout();
                }

                break;

        }


        return true;

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);


        View item0 = getChildAt(0);
        int itemHeight = item0.getHeight();
        int itemWidth = item0.getWidth();
        mTouchSlop = itemHeight;
        int revealHeightSlop = itemHeight/3;
        int revealWidthSlop = itemWidth/5;
        int focusIndex = getHeight() / itemHeight / 2;
        View focusView = getChildAt(focusIndex);
//        ObjectAnimator objX = ObjectAnimator.ofFloat(itemView, "scaleX", 0f, 1.1f);
        ObjectAnimator objY = ObjectAnimator.ofFloat(focusView, "scaleY", 0f, 2f);
        objY.start();
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.setDuration(200);
//        animatorSet.playTogether(objX, objY);
//        animatorSet.start();
        final int childCount = getChildCount();
        int leftIndex, rightIndex;
        leftIndex = rightIndex = focusIndex;
        while (leftIndex-- > 0) {
            View view = getChildAt(leftIndex);
            view.layout(focusView.getLeft() - revealWidthSlop, focusView.getHeight() - revealHeightSlop,
                    focusView.getLeft() - revealWidthSlop + view.getWidth(),
                    focusView.getHeight() - revealHeightSlop + view.getHeight());
        }
    }


}
