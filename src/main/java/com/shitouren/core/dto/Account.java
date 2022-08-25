package com.shitouren.core.dto;

public class Account {

    private String keyseed;
    private String publicKey;
    private String privateKey;
    private String address;

    public Account() {
    }

    public String getKeyseed() {
        return keyseed;
    }

    public void setKeyseed(String keyseed) {
        this.keyseed = keyseed;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Account(String keyseed, String publicKey, String privateKey, String address) {
        this.keyseed = keyseed;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.address = address;
    }

}
