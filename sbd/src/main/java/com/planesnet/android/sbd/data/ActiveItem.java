package com.planesnet.android.sbd.data;

import android.arch.persistence.room.ColumnInfo;


public abstract class ActiveItem extends Item {


    public ActiveItem() {
        super();
        defaultValues();
    }

    public ActiveItem(int id) {
        super(id);
        defaultValues();
    }


    // Indica que el elemento está activo o ya no se utiliza.
    @ColumnInfo(name = "active")
    private transient boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private void defaultValues() {
        active = true;
    }
}
