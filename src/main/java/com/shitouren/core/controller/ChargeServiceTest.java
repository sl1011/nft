package com.shitouren.core.controller;

import com.shitouren.core.service.ChargeService;
import org.junit.Test;


import java.math.BigInteger;

class ChargeServiceTest {

    DDCSdkClient client = new DDCSdkClient.Builder()
            .setAuthorityLogicAddress("0xFa1d2d3EEd20C4E4F5b927D9730d9F4D56314B29")
            .setChargeLogicAddress("0x0B8ae0e1b4a4Eb0a0740A250220eE3642d92dc4D")
            .setDDC721Address("0x354c6aF2cB870BEFEA8Ea0284C76e4A46B8F2870")
            .setDDC1155Address("0x0E762F4D11439B1130D402995328b634cB9c9973")
            .setGasLimit("400000")
            .setGasPrice("1")
            .setSignEventListener(new SignEventTest())
            .init();

    ChargeService chargeService = client.getChargeService();
    String sender = "0x1CB52E75287F49838C17345F6B87367CAE8822F9";

    @Test
    void recharge() throws Exception {
        client.setGatewayUrl("https://opbningxia.bsngate.com:18602/api/f7092d4a2149497b9d5245e3b1d8d213/evmrpc");
        //client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        //client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        String hash = chargeService.recharge(sender, "0x1CB52E75287F49838C17345F6B87367CAE8822F9", new BigInteger("10"));
        System.out.print(hash);
    }

    @Test
    void balanceOf() throws Exception {
        client.setGatewayUrl("https://opbningxia.bsngate.com:18602/api/f7092d4a2149497b9d5245e3b1d8d213/evmrpc");
        //client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        //client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        BigInteger balance = chargeService.balanceOf("0x1CB52E75287F49838C17345F6B87367CAE8822F9");
        System.out.print(balance);
    }

    @Test
    void queryFee() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        BigInteger fee = chargeService.queryFee("0x503f45958F57Da55170B54796F4eD224c9fef9d7", "0xe985e9c5");
        System.out.print(fee);
    }

    @Test
    void selfRecharge() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        String hash = chargeService.selfRecharge(sender, new BigInteger("1000"));
        System.out.print(hash);
    }

    @Test
    void setFee() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        String hash = chargeService.setFee(sender, "0x503f45958F57Da55170B54796F4eD224c9fef9d7", "0xe985e9c5", new BigInteger("1000"));
        System.out.print(hash);
    }

    @Test
    void delFee() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        String hash = chargeService.delFee(sender, "0x503f45958F57Da55170B54796F4eD224c9fef9d7", "0xe985e9c5");
        System.out.print(hash);
    }

    @Test
    void delDDC() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        String hash = chargeService.delDDC(sender, "0x503f45958F57Da55170B54796F4eD224c9fef9d7");
        System.out.print(hash);
    }

}