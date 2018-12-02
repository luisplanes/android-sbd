package com.planesnet.android.sbd.util;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.planesnet.android.sbd.activity.CustomToast;

public class Checking {
    private Activity activity;
    private String TAG;


    public Checking(Activity activity, String TAG) {
        this.TAG = TAG;
        this.activity = activity;
    }

    // Muestra un error generico de datos no informados.
    public boolean error(View v, String msg) {
        if(msg==null) {
            msg = "El dato introducido es incorrecto";
        }
        CustomToast.makeWarning(this.activity, msg, Toast.LENGTH_SHORT).show();
        try {
            if(v!=null) {
                v.requestFocus();
            }
        } catch (Exception ex){}
        return false;
    }




}
