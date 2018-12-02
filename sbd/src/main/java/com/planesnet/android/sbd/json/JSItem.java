package com.planesnet.android.sbd.json;

public class JSItem {
    private int id;

    private String name;  // No se serializa en formato Gson.


    public JSItem(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
