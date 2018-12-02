package com.planesnet.android.sbd.activity;

import android.view.View;

public abstract class CRUDLinesFragment extends CRUDFragment implements OnLineActionsListener {

    public abstract void findViews(View v);

    public abstract void displayViews();

    public abstract boolean saveData();

    public abstract void requestFirstFocus();

    public abstract void addLine();

    public abstract void deleteLine(int position);

    public abstract void loadLines();


    protected void workflowNewLine() {
        addLine();
        loadLines();
    }


    @Override
    public void onLineAdd() {
        workflowNewLine();
    }

    @Override
    public void onLineDelete(int position) {
        deleteLine(position);
    }

    @Override
    public void onLineUpdate(int position) {

    }

    @Override
    public void onLineClick(int position) {

    }


}
