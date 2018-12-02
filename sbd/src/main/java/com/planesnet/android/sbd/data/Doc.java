package com.planesnet.android.sbd.data;

import android.arch.persistence.room.ColumnInfo;


public abstract class Doc extends Item {


    public Doc() {
        super();
        defaultValues();
    }

    // Fecha y hora del documento.
    @ColumnInfo(name = "datetime")
    private DateTime dateTime;
    public DateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    // Indica que el documento se ha enviado.
    @ColumnInfo(name = "sent")
    private transient boolean sent;
    public boolean isSent() {
        return sent;
    }
    public void setSent(boolean sent) {
        this.sent = sent;
    }


    private void defaultValues() {
        dateTime = new DateTime();
        sent = false;
    }
}
