package com.example.alexander.cascade;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.support.v7.widget.AppCompatImageView;


/**
 * Created by Alexander on 2018-02-22.
 */



public class Orb extends AppCompatImageView {

    public float left;
    public float bottom;
    private float width;
    private int color;
    private int orb;

    public Orb(Context context, float left, float bottom, float width, int color) {
        super(context);

        this.left = left;
        this.bottom = bottom;
        this.width = width;
        this.color = color;

        if(color == Color.BLACK) this.setVisibility(INVISIBLE);
        else if(color == Color.BLUE) orb = R.drawable.blue;
        else if(color == Color.GREEN) orb = R.drawable.green;
        else if(color == Color.YELLOW) orb = R.drawable.yellow;
        else if(color == Color.RED) orb = R.drawable.red;
        else if(color == Color.MAGENTA) orb = R.drawable.purple;

        this.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        this.setImageResource(orb);
        this.setClickable(true);
        this.setFocusableInTouchMode(true);
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        layout((int)left,(int)(bottom-width),(int)(left+width),(int)bottom);
    }

    public void setColor(int color) {
        this.color = color;
        this.setVisibility(VISIBLE);
        if(color == Color.BLACK) this.setVisibility(INVISIBLE);
        else if(color == Color.BLUE) orb = R.drawable.blue;
        else if(color == Color.GREEN) orb = R.drawable.green;
        else if(color == Color.YELLOW) orb = R.drawable.yellow;
        else if(color == Color.RED) orb = R.drawable.red;
        else if(color == Color.MAGENTA) orb = R.drawable.purple;
        this.setImageResource(orb);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension((int)width, (int)width);
    }
    @Override
    protected void onAnimationEnd() {
        super.onAnimationEnd();
        setImageResource(orb);
    }
}
