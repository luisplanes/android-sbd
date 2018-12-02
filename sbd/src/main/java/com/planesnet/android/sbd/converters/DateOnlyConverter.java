package com.planesnet.android.sbd.converters;


import android.arch.persistence.room.TypeConverter;

import com.planesnet.android.sbd.data.DateOnly;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateOnlyConverter {
    private static final String DB_FORMAT_DATE = "yyyyMMdd";
    /*
    @TypeConverter
    public DateOnly get(Long d) {
        if (d == null) return null;
        return new DateOnly(d);
    }

    @TypeConverter
    public Long get(DateOnly d) {
        if(d==null) {
            return null;
        } else {
            return d.getTime();
        }
    }
    */

    @TypeConverter
    public DateOnly get(String d) {
        if (d == null) return null;
        Date r = null;
        try {
            d = d.substring(0, 8);
            r = new SimpleDateFormat(DB_FORMAT_DATE).parse(d);
        } catch (ParseException e) {
        }

        return new DateOnly(r);
    }

    @TypeConverter
    public String get(DateOnly d) {
        if (d == null) return null;
        DateFormat df = new SimpleDateFormat(DB_FORMAT_DATE);
        return df.format(d);
    }

}
