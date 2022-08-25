package com.shitouren.core.listener;

import org.web3j.crypto.RawTransaction;


public class SignEvent {
    String sender;
    RawTransaction rawTransaction;

    public SignEvent(String sender, RawTransaction rawTransaction) {
        this.sender = sender;
        this.rawTransaction = rawTransaction;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public RawTransaction getRawTransaction() {
        return rawTransaction;
    }

    public void setRawTransaction(RawTransaction rawTransaction) {
        this.rawTransaction = rawTransaction;
    }
}
