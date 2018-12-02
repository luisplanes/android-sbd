package com.planesnet.android.sbd.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeOnly extends DateTime {
    public TimeOnly() {
        super();
        init();
    }

    public TimeOnly(Date date) {
        super(date);
        init();
    }

    public TimeOnly(Long time) {
        super(time);
        init();
    }

    public TimeOnly(String time) {
        super();
        if (time != null) {
            try {
                SimpleDateFormat f = new SimpleDateFormat("HH:mm");
                Date date = (Date) f.parse(time);
                this.setTime(date.getTime());
            } catch (ParseException e) {
            }

        }
        init();
    }

    private void init() {
        setFormat("HH:mm");
    }

}
