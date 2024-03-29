package com.dev.leo.animalstest.model;

import java.io.Serializable;

public class AnimalViewModel implements Serializable {
    private String url;
    private String title;

    public AnimalViewModel(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
