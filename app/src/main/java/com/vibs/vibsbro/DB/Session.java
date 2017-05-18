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
        sharedPreferences= context.getSharedPreferences("UserManager",Context.MODE_PRIVATE);

    }
    public void setLogin(boolean login){
        editor=sharedPreferences.edit();
        editor.putBoolean("LoginMode",login);
        editor.commit();

    }
    public boolean login(){
        return sharedPreferences.getBoolean("LoginMode",false);
    }
}
