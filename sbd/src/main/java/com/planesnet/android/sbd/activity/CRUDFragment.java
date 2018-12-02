package com.planesnet.android.sbd.activity;

import android.view.View;

public abstract class CRUDFragment extends android.support.v4.app.Fragment {

    public abstract void findViews(View v);

    public abstract void displayViews();

    public abstract boolean saveData();

    public abstract void requestFirstFocus();

    public abstract void settingAdapters();


}
