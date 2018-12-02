package com.planesnet.android.sbd.widget;
public class CheckValue {
    private boolean checked;
    private Object value;

    public CheckValue(Object value) {
        this.value = value;
    }

    public CheckValue(Object value, boolean checked) {
        this.value = value;
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Object getValue() {
        return value;
    }

    public String toString() {
        if (value == null) {
            return "";
        }

        return value.toString();
    }

}
