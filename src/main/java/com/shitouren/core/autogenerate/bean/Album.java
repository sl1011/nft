package com.shitouren.core.autogenerate.bean;

public class Album {
    /**
     * 
     */
    private Integer id;

    /**
     * 发行商
     */
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}