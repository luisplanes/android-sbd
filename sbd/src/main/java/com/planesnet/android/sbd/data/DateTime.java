package com.planesnet.android.sbd.data;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime extends Date {
    transient String format = "dd-MM-yyyy HH:mm";

    public DateTime() {
        super();
    }

    public DateTime(Date date) {
        super();
        this.setTime(date.getTime());
    }

    public DateTime(Long datetime) {
        super();
        if(datetime!=null) {
            this.setTime(datetime.longValue());
        }
    }


    /*
    SimpleDateFormat newFormat = new SimpleDateFormat("MM-dd-yyyy");
    String finalString = newFormat.format(date);
     */
    public DateTime(String datetime) {
        super();
        if(datetime!=null) {
            try {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = (Date)f.parse(datetime);
                this.setTime(date.getTime());
            } catch (ParseException e) {
            }

        }
    }


    public DateOnly getDateOnly() {
        return new DateOnly(this);
    }

    public TimeOnly getTimeOnly() {
        return new TimeOnly(this);
    }


    public String toString() {
        DateFormat f = new SimpleDateFormat(format);
        return f.format(this);
    }


    public void setFormat(String format) {
        this.format = format;
    }

    public String toJson() {
        return new Gson().toJson(this).toString();
    }


}
