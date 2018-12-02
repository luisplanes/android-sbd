package com.planesnet.android.sbd.activity;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.planesnet.android.sbd.R;

public class CustomToast {
    public static Toast makeText(Activity activity, CharSequence text, int length) {
        return makeText(activity, text, length, R.layout.custom_toast_primary_layout);
    }

    public static Toast makePrimary(Activity activity, CharSequence text, int length) {
        return makeText(activity, text, length, R.layout.custom_toast_primary_layout);
    }

    public static Toast makeSecondary(Activity activity, CharSequence text, int length) {
        return makeText(activity, text, length, R.layout.custom_toast_secondary_layout);
    }

    public static Toast makeSuccess(Activity activity, CharSequence text, int length) {
        return makeText(activity, text, length, R.layout.custom_toast_success_layout);
    }

    public static Toast makeWarning(Activity activity, CharSequence text, int length) {
        return makeText(activity, text, length, R.layout.custom_toast_warning_layout);
    }

    public static Toast makeError(Activity activity, Throwable ex, int length) {
        return makeText(activity, ex.getMessage(), length, R.layout.custom_toast_danger_layout);
    }

    public static Toast makeError(Activity activity, CharSequence text, int length) {
        return makeText(activity, text, length, R.layout.custom_toast_danger_layout);
    }

    public static Toast makeDanger(Activity activity, CharSequence text, int length) {
        return makeText(activity, text, length, R.layout.custom_toast_danger_layout);
    }


    public static Toast makeText(Activity activity, CharSequence text, int length, int layout_id) {

        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(layout_id, (ViewGroup) activity.findViewById(R.id.custom_toast_layout_root));

        ImageView image = (ImageView) layout.findViewById(R.id.image);
        image.setImageResource(R.drawable.custom_toast_error);
        TextView textView = (TextView) layout.findViewById(R.id.message);
        textView.setText(text);

        Toast toast = new Toast(activity.getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        return toast;


    }






}
