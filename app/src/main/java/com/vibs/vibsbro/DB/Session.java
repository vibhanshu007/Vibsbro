package com.vibs.vibsbro.DB;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by root on 18/5/17.
 */

public class Session {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public Session(Context context){
        this.context= context;
        sharedPreferences= context.getSharedPreferences("myapp",Context.MODE_PRIVATE);
        sharedPreferences.edit();
    }
    public void setLogin(boolean login){
        editor.putBoolean("LoginMode",login);

    }
    public boolean login(){
        return sharedPreferences.getBoolean("LoginMode",false);
    }
}
