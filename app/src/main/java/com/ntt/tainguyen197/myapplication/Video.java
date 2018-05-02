package com.ntt.tainguyen197.myapplication;

/**
 * Created by NGUYENTRUNGTAI on 4/30/2018.
 */

public class Video {
    public Video(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String url;
    private String name;
}
