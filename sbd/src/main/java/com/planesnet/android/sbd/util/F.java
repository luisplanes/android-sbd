package com.planesnet.android.sbd.util;

import android.text.InputFilter;

import java.text.DecimalFormat;
import java.text.Format;


/* Format */
public class F {
    private Format _format;

    public static F decimalFormat(String pattern) {
        F f = new F();
        f._format = new DecimalFormat(pattern);
        return f;
    }


    // Alineación a la derecha.
    public String right(Object value, int length) {
        String s = this._format.format(value);
        s = "                    "+s;
        s = s.substring(s.length()-length);
        return s;
    }

    // Add filter to existing filters
    public static InputFilter[] addFilter(InputFilter[] filters, InputFilter filter) {
        InputFilter[] all = new InputFilter[filters.length+1];
        for(int i=0; i<filters.length;i++) {
            all[i] = filters[i];
        }
        all[all.length-1] = filter;
        return all;
    }

}
