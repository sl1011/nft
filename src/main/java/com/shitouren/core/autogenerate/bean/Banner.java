package com.shitouren.core.autogenerate.bean;

public class Banner {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String img;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String title1;

    /**
     * 0外联1内部
     */
    private Integer state;

    /**
     * 外联地址
     */
    private String link;

    /**
     * 0首页1抽签页
     */
    private Integer type;

    /**
     * 
     */
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1 == null ? null : title1.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}