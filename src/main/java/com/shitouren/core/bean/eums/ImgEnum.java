package com.shitouren.core.bean.eums;

public enum ImgEnum {
    //        img("http://192.168.3.161:8600/img/", "D:/img/", ""),
  // QrCode("http://192.168.3.161:8600/img/", "D:/img/", "");
    img("http://39.106.7.14:8600/img/", "/files/img/", ""),
    QrCode("http://39.106.7.14:8600/img/", "/files/img/", "");
    private String url;
    private String path;
    private String src;

    ImgEnum(String url, String path, String src) {
        this.url = url;
        this.path = path;
        this.src = src;
    }

    public String getUrl() {
        return url;
    }

    public String getPath() {
        return path;
    }

    public String getSrc() {
        return src;
    }


}
