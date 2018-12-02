package com.planesnet.android.sbd.converters;


import android.arch.persistence.room.TypeConverter;

import java.util.UUID;

public class UUIDConverter {
    @TypeConverter
    public UUID get(String d) {
        if (d == null) return null;
        return UUID.fromString(d);
    }

    @TypeConverter
    public String get(UUID d) {
        return d.toString();
    }
}
