package com.planesnet.android.sbd.data;

import java.util.List;

public abstract interface ItemDao<T> {
    // Queries
    public abstract List<T> getAll();
    public abstract T searchById(int id);
}
