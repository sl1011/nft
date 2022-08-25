package com.shitouren.core.dto;

import org.web3j.protocol.core.methods.response.BaseEventResponse;

import java.util.ArrayList;

public class BlockEventBean {
    private ArrayList<BaseEventResponse> list;
    private String timeStamp;

    public BlockEventBean(ArrayList<BaseEventResponse> list, String timeStamp) {
        this.list = list;
        this.timeStamp = timeStamp;
    }

    public ArrayList<BaseEventResponse> getList() {
        return list;
    }

    public void setList(ArrayList<BaseEventResponse> list) {
        this.list = list;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
