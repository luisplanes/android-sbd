package com.planesnet.android.sbd.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.Gson;


public abstract class AutoItem {

    public AutoItem() {
        super();
        defaultValues();
    }

    @PrimaryKey(autoGenerate = true)
    protected Integer id=null;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @ColumnInfo(name = "name")
    protected String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Fecha y hora de creación.
    @ColumnInfo(name = "create_date")
    protected DateTime createDate;
    public DateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(DateTime createDate) {
        this.createDate = createDate;
    }

    // Fecha y hora de actualización.
    @ColumnInfo(name = "write_date")
    protected DateTime writeDate;
    public DateTime getWriteDate() {
        return writeDate;
    }
    public void setWriteDate(DateTime writeDate) {
        this.writeDate = writeDate;
    }

    @Override
    public String toString() {
        return name;
    }

    public String toJson() {
        return new Gson().toJson(this).toString();
    }

    // Establecer los valores por defecto.
    private void defaultValues() {
        createDate = new DateTime();
        writeDate = new DateTime();
    }
}
