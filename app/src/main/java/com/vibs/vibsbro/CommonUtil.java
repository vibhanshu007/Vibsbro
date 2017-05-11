package com.vibs.vibsbro;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.View;
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

    public static PaintDrawable customGradients(final int[] colorList, final float[] colorListValue){
        ShapeDrawable.ShaderFactory shaderFactory = new ShapeDrawable.ShaderFactory() {
            @Override
            public Shader resize(int height, int width) {
                LinearGradient linearGradient = new LinearGradient(0,0,height,width,
                        colorList,
                        colorListValue, Shader.TileMode.REPEAT);
                return linearGradient;
            }
        };
        PaintDrawable p=new PaintDrawable();
        p.setShape(new RectShape());
        p.setShaderFactory(shaderFactory);
        return  p;
    }

}
