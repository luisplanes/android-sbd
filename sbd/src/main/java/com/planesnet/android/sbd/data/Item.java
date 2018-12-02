package com.planesnet.android.sbd.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.Gson;


public abstract class Item implements IItem {

    public Item() {
        super();
        defaultValues();
    }
    public Item(int id) {
        this();
        this.id = id;
    }

    @PrimaryKey()
    private int id=0;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo(name = "name")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        if (name == null) return "";
        return name;
    }

    // Fecha y hora de creación.
    @ColumnInfo(name = "create_date")
    private DateTime createDate;
    public DateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(DateTime createDate) {
        this.createDate = createDate;
    }

    // Fecha y hora de actualización.
    @ColumnInfo(name = "write_date")
    private DateTime writeDate;
    public DateTime getWriteDate() {
        return writeDate;
    }
    public void setWriteDate(DateTime writeDate) {
        this.writeDate = writeDate;
    }

    public String toJson() {
        return new Gson().toJson(this).toString();
    }

    // Establecer los valores por defecto.
    private void defaultValues() {
        createDate = new DateTime();
        writeDate = new DateTime();
    }


    @Override
    public boolean equals(Object obj) {
        return id == ((Item) obj).getId();
    }
}
