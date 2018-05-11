package com.example.alexander.cascade;

import android.content.Context;

import android.view.animation.Animation;

import android.view.animation.AnimationUtils;

/**
 * Created by Alexander on 2018-02-23.
 */



public class Animations {
    Animation a;
    Animation b;
    public void swapLRanimation(Context context, Orb orb, Orb orb2){
        a = AnimationUtils.loadAnimation(context,R.anim.right_anim);
        b = AnimationUtils.loadAnimation(context,R.anim.left_anim);
        a.reset();
        b.reset();
        orb.clearAnimation();
        orb2.clearAnimation();
        orb.startAnimation(a);
        orb2.startAnimation(b);
    }

    public void swapRLanimation(Context context, Orb orb, Orb orb2){
        a = AnimationUtils.loadAnimation(context,R.anim.left_anim);
        b = AnimationUtils.loadAnimation(context,R.anim.right_anim);
        a.reset();
        b.reset();
        orb.clearAnimation();
        orb2.clearAnimation();
        orb.startAnimation(a);
        orb2.startAnimation(b);
    }

    public void swapUDanimation(Context context, Orb orb, Orb orb2){
        a = AnimationUtils.loadAnimation(context,R.anim.up_anim);
        b = AnimationUtils.loadAnimation(context,R.anim.down_anim);
        a.reset();
        b.reset();
        orb.clearAnimation();
        orb2.clearAnimation();
        orb.startAnimation(a);
        orb2.startAnimation(b);
    }

    public void swapDUanimation(Context context, Orb orb, Orb orb2){
        a = AnimationUtils.loadAnimation(context,R.anim.down_anim);
        b = AnimationUtils.loadAnimation(context,R.anim.up_anim);
        a.reset();
        b.reset();
        orb.clearAnimation();
        orb2.clearAnimation();
        orb.startAnimation(a);
        orb2.startAnimation(b);
    }

    public void errorAnimation(Context context, Orb orb) {
        a = AnimationUtils.loadAnimation(context, R.anim.error_anim);
        a.reset();
        orb.clearAnimation();
        orb.startAnimation(a);
    }
}
