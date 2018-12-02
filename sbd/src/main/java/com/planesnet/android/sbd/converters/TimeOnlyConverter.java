package com.planesnet.android.sbd.converters;


import android.arch.persistence.room.TypeConverter;

import com.planesnet.android.sbd.data.TimeOnly;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeOnlyConverter {
    private static final String DB_FORMAT_TIME = "HHmmss";
    /*
    @TypeConverter
    public TimeOnly get(Long d) {
        if (d == null) return null;
        return new TimeOnly(d);
    }

    @TypeConverter
    public Long get(TimeOnly d) {
        if (d == null) {
            return null;
        } else {
            return d.getTime();
        }
    }
    */

    @TypeConverter
    public TimeOnly get(String d) {
        if (d == null) return null;
        Date r = null;
        try {
            r = new SimpleDateFormat(DB_FORMAT_TIME).parse(d);
        } catch (ParseException e) {
        }

        return new TimeOnly(r);
    }

    @TypeConverter
    public String get(TimeOnly d) {
        if (d == null) return null;
        DateFormat df = new SimpleDateFormat(DB_FORMAT_TIME);
        return df.format(d);
    }

}
