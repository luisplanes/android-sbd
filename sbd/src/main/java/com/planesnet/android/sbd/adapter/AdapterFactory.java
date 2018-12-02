package com.planesnet.android.sbd.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.planesnet.android.sbd.data.ItemDao;

import java.util.List;

public class AdapterFactory<T> {
    private Context context;
    private ItemDao<T> dao;
    private int itemLayout = android.R.layout.simple_dropdown_item_1line;
    private ArrayAdapter<T> arrayAdapter;


    public AdapterFactory(Context context) {
        this.context = context;
        this.arrayAdapter = null;
    }

    public AdapterFactory(Context context, ItemDao dao) {
        this.context = context;
        this.dao = dao;
        this.arrayAdapter = null;
    }

    // Retorna un ArrayAdapter para un tipo de items determinado.
    public ArrayAdapter<T> getArrayAdapter() {

        if(this.arrayAdapter!=null) {
            return this.arrayAdapter;
        }

        List<T> items;
        items = dao.getAll();
        this.arrayAdapter = new ArrayAdapter<T>(context, itemLayout, items);
        return this.arrayAdapter;
    }

    public ArrayAdapter<T> getArrayAdapter(List<T> items) {
        this.arrayAdapter = new ArrayAdapter<T>(context, itemLayout, items);
        return this.arrayAdapter;
    }

    public void notifyDataSetInvalidated() {
        this.arrayAdapter = null;
    }

}
