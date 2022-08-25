package com.shitouren.core.controller;


import com.shitouren.core.listener.SignEvent;
import com.shitouren.core.listener.SignEventListener;
import com.shitouren.core.service.DictService;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.utils.Numeric;

public class SignEventTest implements SignEventListener {

    @Autowired
    DictService dictService;


    @Override
    @Test
    public String signEvent(SignEvent signEvent) {

        Credentials credentials = Credentials.create("ac0b2ea09f5129655b3a9ab71e7625e660b2929589378a5e5159d5e2d88b23c0");
        byte[] signMessage = TransactionEncoder.signMessage(signEvent.getRawTransaction(), credentials);
        return Numeric.toHexString(signMessage);
    }
}
