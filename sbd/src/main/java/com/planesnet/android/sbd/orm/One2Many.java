package com.planesnet.android.sbd.orm;

import android.content.Intent;

import com.google.gson.reflect.TypeToken;
import com.planesnet.android.sbd.util.U;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class One2Many implements Meta {
    static final Type TYPE = new TypeToken<Map<String, Integer>>(){}.getType();
    Map<String,Integer> meta;

    public One2Many() {
        meta = new HashMap<>();
    }

    public One2Many setParent(Integer parent) {
        meta.put("parent", parent);
        return this;
    }
    public Integer getParent() {
         return meta.get("parent");

    }

    public One2Many setJson(String meta) {
        this.meta = U.fromJson(meta, TYPE);
        return this;
    }
    public String getJson() {
        return U.toJson(meta);
    }

}
