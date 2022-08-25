package com.shitouren.core.controller;

import com.shitouren.core.service.AuthorityService;
import org.junit.Test;


import java.math.BigInteger;

class AuthorityServiceTest {

    DDCSdkClient client = new DDCSdkClient.Builder()
            .setAuthorityLogicAddress("0xFa1d2d3EEd20C4E4F5b927D9730d9F4D56314B29")
            .setChargeLogicAddress("0x0B8ae0e1b4a4Eb0a0740A250220eE3642d92dc4D")
            .setDDC721Address("0x354c6aF2cB870BEFEA8Ea0284C76e4A46B8F2870")
            .setDDC1155Address("0x0E762F4D11439B1130D402995328b634cB9c9973")
            .setGasLimit("300000")
            .setGasPrice("1")
            .setSignEventListener(new SignEventTest())
            .init();


    AuthorityService authorityService = client.getAuthorityService();
    String sender = "0x1CB52E75287F49838C17345F6B87367CAE8822F9";

    @Test
    void addConsumerByOperator() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(authorityService.addAccountByOperator(sender, "0x5804A5F927CE7382AD194FD25BCAA189DAD92A39", "test1", "did:wenchangoperator", "did:wenchangoperator"));

    }

    @Test
    void getAccount() throws Exception {
        client.setGatewayUrl("https://opbningxia.bsngate.com:18602/api/f7092d4a2149497b9d5245e3b1d8d213/evmrpc");
        //client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        //client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(authorityService.getAccount("0x1CB52E75287F49838C17345F6B87367CAE8822F9"));

    }

    @Test
    void updateAccState() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(authorityService.updateAccState(sender, "0x5804A5F927CE7382AD194FD25BCAA189DAD92A39", new BigInteger("0"), true));
    }

    @Test
    void crossPlatformApproval() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(authorityService.crossPlatformApproval(sender, "0x5804A5F927CE7382AD194FD25BCAA189DAD92A39","918F7F275A6C2D158E5B76F769D3F1678958A334",true));
    }
}