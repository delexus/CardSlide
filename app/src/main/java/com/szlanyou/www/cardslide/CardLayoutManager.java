package com.szlanyou.www.cardslide;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by ly-yangyuzhi on 2016/10/25.
 */

public class CardLayoutManager extends LinearLayoutManager {


    View focusedChild;

    @Override
    public View getFocusedChild() {
        return super.getFocusedChild();
    }

    public void setFocusedChild(View focusedChild) {
        this.focusedChild = focusedChild;
    }

    public CardLayoutManager(Context context) {
        super(context);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        //if (state.isPreLayout()) {
        //}
        super.onLayoutChildren(recycler, state);
        ObjectAnimator objX = ObjectAnimator.ofFloat(getFocusedChild(), "scaleX", 1000f);
        ObjectAnimator objY = ObjectAnimator.ofFloat(getFocusedChild(), "scaleY", 1000f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.playTogether(objX, objY);
        animatorSet.start();
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View itemView = getChildAt(i);

        }
    }
}
