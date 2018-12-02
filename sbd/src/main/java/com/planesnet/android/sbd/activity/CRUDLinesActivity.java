package com.planesnet.android.sbd.activity;

import android.os.Bundle;


public abstract class CRUDLinesActivity extends CRUDActivity implements OnLineActionsListener {
    static final String TAG = CRUDLinesActivity.class.getSimpleName();

    //private FloatingActionButton fabPlus;

    public abstract void addLine();
    public abstract void deleteLine(int position);
    public abstract void loadLines();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Al insertar añadimos una línea directamente.
    @Override
    protected void insertWorkflow() {
        super.insertWorkflow();
        workflowNewLine();
        setDocumentIsNotSave();
    }

    protected void workflowNewLine() {
        addLine();
        loadLines();
    }

    @Override
    public void onLineAdd() {
        workflowNewLine();
        setDocumentIsNotSave();
    }

    @Override
    public void onLineDelete(int position) {
        deleteLine(position);
        setDocumentIsNotSave();
    }

    @Override
    public void onLineUpdate(int position) {
        setDocumentIsNotSave();
    }

    @Override
    public void onLineClick(int position) {

    }


}
