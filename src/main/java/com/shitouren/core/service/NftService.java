package com.shitouren.core.service;

import java.util.Map;

public interface NftService
{
    Map creatwallet();

    void connectwc();

    String deploycontract();

    Map mintnft(String denomID,String address);

    void transfer(String denomID,String nftID,String from,String to);
}
