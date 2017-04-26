package com.vibs.vibsbro;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

public class MainActivity extends Activity {
    LoginButton fbLoginButton;
    TextView loginTextview;
    CallbackManager callbackManager;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_main);
        fbLoginButton = (LoginButton)findViewById(R.id.login_button);
       // loginTextview = (TextView)findViewById(R.id.login_text);
        logo = (ImageView)findViewById(R.id.logo);

        Picasso
                .with(this)
                .load(R.drawable.logo)
                .resize(200,150)
                .into(logo);

        fbLoginButton.setReadPermissions("user_friends");
        callbackManager = CallbackManager.Factory.create();
        fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                loginTextview.setText("Login Successfully \n" +loginResult.getAccessToken()
                        .getUserId()+"\n"+loginResult.getAccessToken().getToken());

            }

            @Override
            public void onCancel() {
                loginTextview.setText("Login Cancel");

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
