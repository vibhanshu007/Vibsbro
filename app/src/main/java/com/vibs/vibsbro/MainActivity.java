package com.vibs.vibsbro;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import static com.facebook.share.internal.GameRequestValidation.validate;


public class MainActivity extends Activity {
    LoginButton fbLoginButton;
    TextView loginTextview;
    CallbackManager callbackManager;
    ImageView logo;
    EditText username_edittext,password_edittext;
    Button loginButton;
    TextView singUpLink,forgot_password;
    RelativeLayout relativeLayout;
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_main);

        //getID section
        relativeLayout = (RelativeLayout)findViewById(R.id.login_parent);
        fbLoginButton = (LoginButton)findViewById(R.id.login_button);
        logo = (ImageView)findViewById(R.id.logo);
        loginButton = (Button)findViewById(R.id.login_button);
        username_edittext = (EditText)findViewById(R.id.username);
        password_edittext = (EditText)findViewById(R.id.password);
        singUpLink = (TextView)findViewById(R.id.signup_id);
        forgot_password = (TextView)findViewById(R.id.forgot_password_id);

        //Picasso section
        Picasso
                .with(this)
                .load(R.drawable.logo)
                .resize(150,200)
                .into(logo);

        //Facebook sevtion

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

        //ClickEvent Setions
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        singUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    //ToDo work on login method
    public void login(){
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }
        loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                R.style.Theme_AppCompat_DayNight);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String username = username_edittext.getText().toString();
        String password = password_edittext.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);

    }

    public boolean validate() {
        boolean valid = true;

        String email = username_edittext.getText().toString();
        String password = password_edittext.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            username_edittext.setError("enter a valid email address");
            valid = false;
        } else {
            username_edittext.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            password_edittext.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            password_edittext.setError(null);
        }

        return valid;
    }

    public void onLoginSuccess() {
        loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Snackbar.make(relativeLayout,"Login Fail",Snackbar.LENGTH_LONG);
        loginButton.setEnabled(true);
    }



}
