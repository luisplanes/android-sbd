package com.planesnet.android.sbd.converters;


import android.arch.persistence.room.TypeConverter;

import com.planesnet.android.sbd.data.DateTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeConverter {
    private static final String DB_FORMAT_DATETIME = "yyyyMMddHHmmss";
    /*
    @TypeConverter
    public DateTime get(Long d) {
        if (d == null) return null;
        return new DateTime(d);
    }

    @TypeConverter
    public Long get(DateTime d) {
        if(d==null) {
            return null;
        } else {
            return d.getTime();
        }
    }
    */


    @TypeConverter
    public DateTime get(String d) {
        if (d == null) return null;
        Date r = null;
        try {
            r = new SimpleDateFormat(DB_FORMAT_DATETIME).parse(d);
        } catch (ParseException e) {
        }

        return new DateTime(r);
    }

    @TypeConverter
    public String get(DateTime d) {
        if (d == null) return null;
        DateFormat df = new SimpleDateFormat(DB_FORMAT_DATETIME);
        return df.format(d);
    }
}
