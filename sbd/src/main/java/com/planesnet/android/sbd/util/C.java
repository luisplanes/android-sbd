package com.planesnet.android.sbd.util;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.planesnet.android.sbd.data.DateOnly;
import com.planesnet.android.sbd.data.DateTime;
import com.planesnet.android.sbd.data.TimeOnly;

import org.json.JSONObject;



/* Conversion */
public class C {

    public static String getString(TextView v) {
        String s = v.getText().toString();
        if(s!=null && s.length()==0) s = null;
        return s;
    }

    public static int getInt(TextView v) {
        try {
            return Integer.parseInt(v.getText().toString());
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public static long getLong(TextView v) {
        try {
            return Long.parseLong(v.getText().toString());
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public static float getFloat(TextView v) {
        try {
            return Float.parseFloat(v.getText().toString());
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public static double getDouble(TextView v) {
        try {
            return Double.parseDouble(v.getText().toString());
        } catch (NumberFormatException ex) {
            return 0;
        }
    }


    // Métodos set.
    public static void set(EditText v, String value) {
        v.setText(value);
    }
    public static void set(EditText v, int value) {
        v.setText(value == 0 ? "" : ""+ value );
    }

    public static void set(EditText v, float value) {
        v.setText(value == 0 ? "" : "" + value);
    }

    public static void set(TextView v, DateTime value) {
        v.setText(value == null ? "" : "" + value.toString());
    }

    public static void set(TextView v, DateOnly value) {
        v.setText(value == null ? "" : "" + value.toString());
    }

    public static void set(TextView v, TimeOnly value) {
        v.setText(value == null ? "" : "" + value.toString());
    }

    public static void set(CheckBox v, Boolean value) {
        if(value!=null) {
            v.setChecked(value);
        } else {
            // TODO ver si no se conoce el estado.
        }

    }



    // JSON Methods
    public static Integer getIntOrNull(JSONObject o, String name) {
        try {
            return o.getInt(name);
        } catch (Exception ex) {
            return null;
        }
    }



}
