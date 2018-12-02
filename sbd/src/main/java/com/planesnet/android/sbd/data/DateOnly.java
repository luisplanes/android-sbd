package com.planesnet.android.sbd.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateOnly extends DateTime {
    public DateOnly() {
        super();
        init();
    }

    public DateOnly(java.util.Date date) {
        super(date);
        init();
    }

    public DateOnly(Long datetime) {
        super(datetime);
        init();
    }

    public DateOnly(String datetime) {
        super();
        if (datetime != null) {
            try {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                Date date = (Date) f.parse(datetime);
                this.setTime(date.getTime());
            } catch (ParseException e) {
            }

        }
        init();
    }

    private void init() {
        setFormat("dd-MM-yyyy");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date d = null;
        try {
            d = sdf.parse(sdf.format(this));
            this.setTime(d.getTime());
        } catch (ParseException e) {
        }
        if (d != null) {
            this.setTime(d.getTime());
        }

    }


}
