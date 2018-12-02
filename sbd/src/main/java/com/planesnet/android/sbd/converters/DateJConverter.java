package com.planesnet.android.sbd.converters;


import android.arch.persistence.room.TypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateJConverter {
    private static final String DB_FORMAT_DATETIME = "yyyyMMddHHmmss";
    /*
    @TypeConverter
    public Date get(Long d) {
        if (d == null) return null;
        return new Date(d);
    }

    @TypeConverter
    public Long get(Date d) {
        if (d == null) return null;
        return d.getTime();
    }
    */

    @TypeConverter
    public Date get(String d) {
        if (d == null) return null;
        Date r = null;
        try {
            r = new SimpleDateFormat(DB_FORMAT_DATETIME).parse(d);
        } catch (ParseException e) {
        }

        return r;
    }

    @TypeConverter
    public String get(Date d) {
        if (d == null) return null;
        DateFormat df = new SimpleDateFormat(DB_FORMAT_DATETIME);
        return df.format(d);
    }


}
