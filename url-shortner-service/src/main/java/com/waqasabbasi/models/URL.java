package com.waqasabbasi.models;


/**
 * Parent Abstract class to implement basic setters and getters for long and short urls
 */
public abstract class URL {
    public URL(String long_url, String short_url){
        this.long_url = long_url;
        this.short_url = short_url;
    }

    private String long_url;
    private String short_url;

    public URL() { }

    public String getLong_url() {
        return long_url;
    }

    public void setLong_url(String long_url) {
        this.long_url = long_url;
    }

    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }
}

