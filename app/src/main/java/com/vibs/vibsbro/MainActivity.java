package com.vibs.vibsbro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;
import com.vibs.vibsbro.DB.DataBaseHelper;
import com.vibs.vibsbro.DB.InputValidation;
import com.vibs.vibsbro.DB.Session;


public class MainActivity extends Activity implements View.OnClickListener {

    private NestedScrollView nestedScrollView;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private AppCompatButton appCompatButtonLogin;
    private AppCompatTextView textViewLinkRegister;
    private AppCompatImageView imageViewLogo;
    private InputValidation inputValidation;
    private DataBaseHelper databaseHelper;
    CallbackManager callbackManager;
    LoginButton fbLoginButton;
    private Session session;

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_login);
        initViews();
        initListner();
        initObjects();

        session = new Session(this);
        if (session.login()){
            Log.e("CHECK POINT","CHECK POINT 0");
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
            finish();
        }

        //Picasso section
        Picasso
                .with(this)
                .load(R.drawable.logo)
                .resize(150,200)
                .into(imageViewLogo);
        fbIntegration(); //facebook integration

    }

    //init workes...
    private void initViews(){

        imageViewLogo = (AppCompatImageView) findViewById(R.id.logo);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);
        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);
        fbLoginButton = (LoginButton)findViewById(R.id.login_button);
    }

    private void initListner(){
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }

    private void initObjects() {
        databaseHelper = new DataBaseHelper(MainActivity.this);
        inputValidation = new InputValidation(MainActivity.this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.appCompatButtonLogin:
                Log.e("CHECK POINT","CHECK POINT 1");
                verifyFromSQLite();
                break;
            case R.id.textViewLinkRegister:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intentRegister);
                break;
        }

    }
    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {

        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail,
                getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail,
                getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword,
                getString(R.string.error_message_email))) {
            return;
        }
        Log.e("CHECK POINT","CHECK POINT 2");

        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {
            session.setLogin(true);
            Log.e("CHECK POINT","CHECK POINT 3");
            Intent accountsIntent = new Intent(MainActivity.this, HomeActivity.class);
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);
            finish();

        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password),
                    Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }

    private void fbIntegration(){
        fbLoginButton.setReadPermissions("user_friends");
        callbackManager = CallbackManager.Factory.create();
        fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                /*loginTextview.setText("Login Successfully \n" +loginResult.getAccessToken()
                        .getUserId()+"\n"+loginResult.getAccessToken().getToken());*/
            }

            @Override
            public void onCancel() {
               // loginTextview.setText("Login Cancel");
            }
            @Override
            public void onError(FacebookException error) {

            }
        });
    }

}
