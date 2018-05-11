package com.example.alexander.cascade;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    private FrameLayout main;
    private Orb orbs[][] = new Orb[5][6];
    private int colors[] = new int[5];
    private float width = (Resources.getSystem().getDisplayMetrics().widthPixels/5);
    private float bottom = Resources.getSystem().getDisplayMetrics().heightPixels;
    private float orbLeft, orbBottom;
    private boolean swapping = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = findViewById(R.id.main_view);
        colors[0] = Color.BLUE;
        colors[1] = Color.GREEN;
        colors[2] = Color.YELLOW;
        colors[3] = Color.RED;
        colors[4] = Color.MAGENTA;

        for (int i=0; i<5;i++) {
            for (int j=0; j<6;j++) {

                orbs[i][j] = new Orb(this,width*i,bottom-width*(1+j),width,colors[(int) (Math.random()*5)]);
                main.addView(orbs[i][j]);
                orbs[i][j].setOnTouchListener(this);
            }
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        float initialX = motionEvent.getRawX();
        float initialY = motionEvent.getRawY();
        Orb orb = (Orb) view;
        int row = rowIndex(orb);
        int col = columnIndex(orb);
        Animations animations = new Animations();
        switch (motionEvent.getAction()) {

            case MotionEvent.ACTION_DOWN:
                orbLeft = orb.left;
                orbBottom = orb.bottom;
                swapping = false;
                return true;

            case MotionEvent.ACTION_MOVE:
                return true;

            case MotionEvent.ACTION_UP:
                if (!swapping) {
                    swapping = true;
                    float finalX = motionEvent.getRawX();
                    float finalY = motionEvent.getRawY();
                    if (initialX - finalX < 0.5) {            // Left to Right
                        if (row == 4) {                       // if on edge
                            animations.errorAnimation(this, orb);
                        } else {
                            Orb orb2 = orbs[(row + 1)][col];
                            animations.swapLRanimation(this, orb, orb2);
                            swap(orb, row, col, (row + 1), col);
                        }
                        break;
                    }

                    if (initialX - finalX < -0.5) {            // Right to Left
                        if (row == 0) {                        // if on edge
                            animations.errorAnimation(this, orb);
                        } else {
                            Orb orb2 = orbs[(row - 1)][col];
                            swap(orb, row, col, (row - 1), col);
                            animations.swapRLanimation(this, orb, orb2);
                        }
                        break;
                    }

                    if (initialY - finalY < 0.5) {            // Up to Down
                        if (col == 0) {                       // if on edge
                            animations.errorAnimation(this, orb);
                        } else {
                            Orb orb2 = orbs[row][col - 1];
                            swap(orb, row, col, row, col - 1);
                            animations.swapUDanimation(this, orb, orb2);
                        }
                        break;
                    }

                    if (initialY - finalY < -0.5) {            // Down to Up
                        if (col == 5) {                        // if on edge
                            animations.errorAnimation(this, orb);
                        } else {
                            Orb orb2 = orbs[row][col + 1];
                            swap(orb, row, col, row, col + 1);
                            animations.swapDUanimation(this, orb, orb2);
                        }
                        break;
                    }
                }
                break;
        }
        return true;
    }



    public int rowIndex(Orb orb){               // finds row index
        int rowIndex = 0;
        for (int i=0; i<5; i++) {
            for (int j=0; j<6; j++) {
                if (orbs[i][j] == orb) {
                    rowIndex = i;
                }
            }
        }
        return rowIndex;
    }

    public int columnIndex(Orb orb){            // finds column index
        int columnIndex = 0;
        for (int i=0; i<5; i++) {
            for (int j=0; j<6; j++) {
                if (orbs[i][j] == orb) {
                    columnIndex = j;
                }
            }
        }
        return columnIndex;
    }

    public void swap(Orb orb, int row1,int col1,int row2,int col2) {

        float besLeft = orbs[row2][col2].left;
        float besBottom = orbs[row2][col2].bottom;

        orbs[row1][col1].left = besLeft;
        orbs[row1][col1].bottom = besBottom;

        orbs[row2][col2].left = orbLeft;
        orbs[row2][col2].bottom = orbBottom;

        orbs[row1][col1] = orbs[row2][col2];
        orbs[row2][col2] = orb;

    }
}
