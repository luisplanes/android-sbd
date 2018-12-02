package com.planesnet.android.sbd.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.planesnet.android.sbd.R;
import com.planesnet.android.sbd.data.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeDialog {
    private Context context;
    private String format = "dd-MM-yyyy HH:mm";
    private AlertDialog alertDialog;
    private View dialogView;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private GregorianCalendar calendar;


    private final OnAcceptDateTimeListener onAcceptDateTimeListener;



    public DateTimeDialog(Context context , OnAcceptDateTimeListener listener) {
        this.context = context;
        this.onAcceptDateTimeListener = listener;

        dialogView = View.inflate(context, R.layout.date_time_picker, null);
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setView(dialogView);
        datePicker = (DatePicker) dialogView.findViewById(R.id.date_picker);
        timePicker = (TimePicker) dialogView.findViewById(R.id.time_picker);
        timePicker.setIs24HourView(true);

        dialogView.findViewById(R.id.date_time_set).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                calendar = new GregorianCalendar(datePicker.getYear(),
                        datePicker.getMonth(),
                        datePicker.getDayOfMonth(),
                        timePicker.getCurrentHour(),
                        timePicker.getCurrentMinute());

                DateFormat f = new SimpleDateFormat(format);

                onAcceptDateTimeListener.onAccept(new DateTime(calendar.getTime()));
                alertDialog.dismiss();
            }
        });
    }

    public DateTimeDialog setDate(Date date) {
        calendar = new GregorianCalendar();
        if (date != null) {
            calendar.setTimeInMillis(date.getTime());
        }
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), null);
        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
        return this;
    }

    public void show() {
        alertDialog.show();
    }

}
