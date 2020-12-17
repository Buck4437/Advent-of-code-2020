package com.company.Day17;

public class LayerCache {

    private String data;
    private String cache;

    public LayerCache(String data) {
        this.data = data;
        this.cache = data;
    }

    public String getData() {
        return cache;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String updateCache() {
        this.cache = data;
        return getData();
    }
}
