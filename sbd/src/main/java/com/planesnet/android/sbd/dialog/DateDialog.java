package com.planesnet.android.sbd.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.DatePicker;

import com.planesnet.android.sbd.R;
import com.planesnet.android.sbd.data.DateOnly;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateDialog {
    private Context context;
    private String format = "dd-MM-yyyy";
    private AlertDialog alertDialog;
    private View dialogView;
    private DatePicker datePicker;
    private GregorianCalendar calendar;


    private final OnAcceptDateListener onAcceptDateListener;



    public DateDialog(Context context , OnAcceptDateListener listener) {
        this.context = context;
        this.onAcceptDateListener = listener;

        dialogView = View.inflate(context, R.layout.date_picker, null);
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setView(dialogView);
        datePicker = (DatePicker) dialogView.findViewById(R.id.date_picker);

        dialogView.findViewById(R.id.date_time_set).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                calendar = new GregorianCalendar(datePicker.getYear(),
                        datePicker.getMonth(),
                        datePicker.getDayOfMonth(),
                        0,
                        0);

                DateFormat f = new SimpleDateFormat(format);

                onAcceptDateListener.onAccept(new DateOnly(calendar.getTime()));
                alertDialog.dismiss();
            }
        });
    }

    public DateDialog setDate(Date date) {
        calendar = new GregorianCalendar();
        if (date != null) {
            calendar.setTimeInMillis(date.getTime());
        }
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), null);
        return this;
    }

    public void show() {
        alertDialog.show();
    }

}
