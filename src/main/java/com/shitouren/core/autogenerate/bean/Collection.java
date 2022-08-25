package com.shitouren.core.autogenerate.bean;

import java.math.BigDecimal;

public class Collection {
    /**
     * id
     */
    private Integer id;

    /**
     * 藏品图
     */
    private String img;

    /**
     * 藏品名称
     */
    private String name;

    /**
     * 藏品缩写
     */
    private String minname;

    /**
     * 限量
     */
    private Integer limits;

    /**
     * 标签
     */
    private String label;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 详情展示图
     */
    private String img1;

    /**
     * 创作者
     */
    private String creator;

    /**
     * 发行者
     */
    private String publisher;

    /**
     * 0.藏品1.盲盒藏品
     */
    private Integer type;

    /**
     * 对应盲盒id
     */
    private Integer boxid;

    /**
     * 0未部署1已部署
     */
    private Integer isdeploy;

    /**
     * 合约地址
     */
    private String contractaddress;

    /**
     * 已售
     */
    private Integer sold;

    /**
     * 创作者头像
     */
    private String creatorimg;

    /**
     * 碎片专属可售库存
     */
    private Integer stockc;

    /**
     * 编号设置
     */
    private String nosetup;

    /**
     * 专辑名称
     */
    private String albumname;

    /**
     * 专辑id
     */
    private Integer albumid;

    /**
     * 
     */
    private Long ddcid;

    /**
     * 
     */
    private String pravirty;

    /**
     * 赠送的drawid
     */
    private Integer drawid;

    /**
     * 卡的背景图片
     */
    private String drawimg;

    /**
     * 天河链地址
     */
    private String thcontractaddress;

    /**
     * 详情
     */
    private String details;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMinname() {
        return minname;
    }

    public void setMinname(String minname) {
        this.minname = minname == null ? null : minname.trim();
    }

    public Integer getLimits() {
        return limits;
    }

    public void setLimits(Integer limits) {
        this.limits = limits;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1 == null ? null : img1.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getBoxid() {
        return boxid;
    }

    public void setBoxid(Integer boxid) {
        this.boxid = boxid;
    }

    public Integer getIsdeploy() {
        return isdeploy;
    }

    public void setIsdeploy(Integer isdeploy) {
        this.isdeploy = isdeploy;
    }

    public String getContractaddress() {
        return contractaddress;
    }

    public void setContractaddress(String contractaddress) {
        this.contractaddress = contractaddress == null ? null : contractaddress.trim();
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public String getCreatorimg() {
        return creatorimg;
    }

    public void setCreatorimg(String creatorimg) {
        this.creatorimg = creatorimg == null ? null : creatorimg.trim();
    }

    public Integer getStockc() {
        return stockc;
    }

    public void setStockc(Integer stockc) {
        this.stockc = stockc;
    }

    public String getNosetup() {
        return nosetup;
    }

    public void setNosetup(String nosetup) {
        this.nosetup = nosetup == null ? null : nosetup.trim();
    }

    public String getAlbumname() {
        return albumname;
    }

    public void setAlbumname(String albumname) {
        this.albumname = albumname == null ? null : albumname.trim();
    }

    public Integer getAlbumid() {
        return albumid;
    }

    public void setAlbumid(Integer albumid) {
        this.albumid = albumid;
    }

    public Long getDdcid() {
        return ddcid;
    }

    public void setDdcid(Long ddcid) {
        this.ddcid = ddcid;
    }

    public String getPravirty() {
        return pravirty;
    }

    public void setPravirty(String pravirty) {
        this.pravirty = pravirty == null ? null : pravirty.trim();
    }

    public Integer getDrawid() {
        return drawid;
    }

    public void setDrawid(Integer drawid) {
        this.drawid = drawid;
    }

    public String getDrawimg() {
        return drawimg;
    }

    public void setDrawimg(String drawimg) {
        this.drawimg = drawimg == null ? null : drawimg.trim();
    }

    public String getThcontractaddress() {
        return thcontractaddress;
    }

    public void setThcontractaddress(String thcontractaddress) {
        this.thcontractaddress = thcontractaddress == null ? null : thcontractaddress.trim();
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }
}