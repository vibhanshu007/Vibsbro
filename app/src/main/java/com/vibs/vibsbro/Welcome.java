package com.vibs.vibsbro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

/**
 * Created by root on 19/4/17.
 */

public class Welcome extends Activity {
    public static final int WELCOME_TIMEOUT=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        CommonUtil.hideSystemUI(getWindow().getDecorView());
        TextView greeting = (TextView)findViewById(R.id.fullscreen_content);
        greeting.setText(CommonUtil.getSalute());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Welcome.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },WELCOME_TIMEOUT);
    }
}
