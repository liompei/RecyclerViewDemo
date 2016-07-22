package com.liompei.app2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by BLM on 2016/7/21.
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;  //0
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;  //1

    private Drawable drawable;  //用来存分割线
    private int orientation;  //记录方向

    public DividerItemDecoration(Context context, int orientation) {
        TypedArray typedArray = context.obtainStyledAttributes(ATTRS);  //作为分割线
        drawable = typedArray.getDrawable(0);
        typedArray.recycle();  //回收
        setOrientation(orientation);
    }


    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            //如果传入方向不是水平或者垂直,则抛出参数异常:无效的orientation
            throw new IllegalArgumentException("请传入正确参数");
        }
        this.orientation = orientation;
    }

    //绘制
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        Log.d("itemDecoration", "onDraw");
        //是垂直还是水平
        if (orientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    //绘制纵向 item 分割线
    public void drawVertical(Canvas canvas, RecyclerView recyclerView) {
        int left = recyclerView.getPaddingLeft();  //左边的坐标
        int right = recyclerView.getWidth() - recyclerView.getPaddingRight();  //右边的坐标

        int childCount = recyclerView.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = recyclerView.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + layoutParams.bottomMargin;
            int bottom = top + drawable.getIntrinsicHeight();
            drawable.setBounds(left, top, right, bottom);  //drawable将要被绘制在canvas内的矩形区域
            drawable.draw(canvas);  //将drawable画到屏幕上
        }

    }

    //绘制横向 item 分割线
    public void drawHorizontal(Canvas canvas, RecyclerView recyclerView) {
        int top = recyclerView.getPaddingTop();  //顶部距离
        int bottom = recyclerView.getMeasuredHeight() - recyclerView.getPaddingBottom();  //总长度减去离底部距离
        int childCount = recyclerView.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = recyclerView.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + layoutParams.rightMargin;
            int right = left + drawable.getIntrinsicHeight();

            drawable.setBounds(left, top, right, bottom);
            drawable.draw(canvas);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    //绘制的范围
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (orientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, drawable.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, drawable.getIntrinsicWidth(), 0);
        }
    }
}
