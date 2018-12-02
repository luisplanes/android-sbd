package com.planesnet.android.sbd.util;

import org.json.JSONException;
import org.json.JSONObject;

public class JS {

    // Extrae un valor de JSON si existe o Null en caso contrario.
    public static String getString(JSONObject o, String property) {
        try {
            if (!o.getBoolean(property)) {
                return null;
            }
        } catch (JSONException ex) {
        }
        try {
            return o.getString(property);
        } catch (JSONException ex) {
            return null;
        }
    }


    // Comprueba si un campo es nulo o falso (opcional)
    public static boolean isNull(JSONObject o, String property) {
        try {
            if (!o.getBoolean(property)) {
                return true;
            }
        } catch (JSONException ex) {
        }
        return o.isNull(property);
    }


}
