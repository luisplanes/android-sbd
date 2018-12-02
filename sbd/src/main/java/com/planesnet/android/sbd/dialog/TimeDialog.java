package com.planesnet.android.sbd.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.planesnet.android.sbd.R;
import com.planesnet.android.sbd.data.TimeOnly;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeDialog {
    private Context context;
    private String format = "HH:mm";
    private AlertDialog alertDialog;
    private View dialogView;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private GregorianCalendar calendar;


    private final OnAcceptTimeListener onAcceptTimeListener;


    public TimeDialog(Context context, OnAcceptTimeListener listener) {
        this.context = context;
        this.onAcceptTimeListener = listener;

        dialogView = View.inflate(context, R.layout.time_picker, null);
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setView(dialogView);
        timePicker = (TimePicker) dialogView.findViewById(R.id.time_picker);
        timePicker.setIs24HourView(true);

        dialogView.findViewById(R.id.date_time_set).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                calendar = new GregorianCalendar();
                calendar.set(GregorianCalendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                calendar.set(GregorianCalendar.MINUTE, timePicker.getCurrentMinute());

                DateFormat f = new SimpleDateFormat(format);

                onAcceptTimeListener.onAccept(new TimeOnly(calendar.getTime()));
                alertDialog.dismiss();
            }
        });
    }

    public TimeDialog setTime(Date date) {
        calendar = new GregorianCalendar();
        if (date != null) {
            calendar.setTimeInMillis(date.getTime());
        }
        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
        return this;
    }

    public void show() {
        alertDialog.show();
    }

}
