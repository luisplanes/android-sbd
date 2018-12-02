package com.planesnet.android.sbd.activity;

import android.view.View;

public interface OnChecking {
    void valid();

    void invalid(View view, String msg);

    void invalid(String msg);

}
