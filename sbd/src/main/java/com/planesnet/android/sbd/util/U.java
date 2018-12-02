package com.planesnet.android.sbd.util;

import android.content.Context;
import android.provider.Settings.Secure;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.planesnet.android.sbd.data.DateOnly;
import com.planesnet.android.sbd.data.DateTime;
import com.planesnet.android.sbd.data.TimeOnly;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

/** Utilidades **/
public class U {


    // Hace que un edit text se comporte en modo read only.
    public static void readOnly(TextView v) {
        final int[] allowedKeyCodes= {61};
        // Consume los eventos de teclado y hace que el spinner no
        v.setInputType(InputType.TYPE_NULL);
        v.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                for( int k : allowedKeyCodes) {
                    if(k==keyCode) return false;
                }
                return true;
            }
        });
    }

    // Hace que un edit text sea editable.
    public static void readWrite(TextView v, int inputType) {
        v.setInputType(inputType);
        v.setOnKeyListener(null);
    }


    static class DateLongFormatTypeAdapter extends TypeAdapter<Date> {
        @Override
        public void write(JsonWriter out, Date value) throws IOException {
            if (value != null) {
                out.value(value.getTime());
            } else {
                out.value(false);
            }
        }

        @Override
        public Date read(JsonReader in) throws IOException {
            return new Date(in.nextLong());
        }
    }

    static class DateTimeFormatTypeAdapter extends TypeAdapter<DateTime> {
        @Override
        public void write(JsonWriter out, DateTime value) throws IOException {
            if (value != null) {
                out.value(value.getTime());
            } else {
                out.value(false);
            }
        }

        @Override
        public DateTime read(JsonReader in) throws IOException {
            return new DateTime(in.nextLong());
        }
    }

    static class DateOnlyFormatTypeAdapter extends TypeAdapter<DateOnly> {
        @Override
        public void write(JsonWriter out, DateOnly value) throws IOException {
            if (value != null) {
                out.value(value.getTime());
            } else {
                out.value(false);
            }

        }

        @Override
        public DateOnly read(JsonReader in) throws IOException {
            return new DateOnly(in.nextLong());
        }
    }

    static class TimeOnlyFormatTypeAdapter extends TypeAdapter<TimeOnly> {
        @Override
        public void write(JsonWriter out, TimeOnly value) throws IOException {
            if (value != null) {
                out.value(value.getTime());
            } else {
                out.value(false);
            }

        }

        @Override
        public TimeOnly read(JsonReader in) throws IOException {
            return new TimeOnly(in.nextLong());
        }
    }


    private static Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Date.class, new DateLongFormatTypeAdapter())
            .registerTypeAdapter(DateTime.class, new DateTimeFormatTypeAdapter())
            .registerTypeAdapter(DateOnly.class, new DateOnlyFormatTypeAdapter())
            .registerTypeAdapter(TimeOnly.class, new TimeOnlyFormatTypeAdapter())
            .create();

    public static String toJson(Object src) {
        return GSON.toJson(src);
    }
    public static <T> T fromJson(String json, Type type) {
        return GSON.fromJson(json, type);
    }


    // Return a unica device id.
    public static String getDeviceID(Context context) {
        return Secure.getString(context.getContentResolver(),
                Secure.ANDROID_ID);
    }


}
