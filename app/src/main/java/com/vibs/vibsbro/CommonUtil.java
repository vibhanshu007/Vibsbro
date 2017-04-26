package com.vibs.vibsbro;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.view.View;
import android.view.animation.Animation;

import java.util.Calendar;

/**
 * Created by root on 19/4/17.
 */

public class CommonUtil {

    public static void hideSystemUI(View v){
        v.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    public static String getSalute() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        String greeting = "Hi";
        if (hour > 0 && hour <= 12)
            greeting = "Good Morning";
        else
        if (hour > 12 && hour <= 18)
            greeting = "Good Afternoon";
        else
        if (hour > 18 && hour <= 21)
            greeting = "Good Evening";
        else
        if (hour > 21 && hour <= 24)
            greeting = "Good Night";

        return greeting;
    }

    public static void colorAnimation() {

    }

}
