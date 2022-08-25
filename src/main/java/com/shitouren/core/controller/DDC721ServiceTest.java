package com.shitouren.core.controller;


import com.shitouren.core.service.DDC721Service;
import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.TransactionManager;


import java.math.BigInteger;

class DDC721ServiceTest {

    DDCSdkClient client = new DDCSdkClient.Builder()
            .setAuthorityLogicAddress("0xFa1d2d3EEd20C4E4F5b927D9730d9F4D56314B29")
            .setChargeLogicAddress("0x0B8ae0e1b4a4Eb0a0740A250220eE3642d92dc4D")
            .setDDC721Address("0x354c6aF2cB870BEFEA8Ea0284C76e4A46B8F2870")
            .setDDC1155Address("0x0E762F4D11439B1130D402995328b634cB9c9973")
            .setGasLimit("300000")
            .setGasPrice("10000000")
            .setSignEventListener(new SignEventTest())
            .init();

    DDC721Service ddc721Service = client.getDDC721Service();

    String sender = "0x953488F7E292A7D6CB0BFF81BA806B82E5FD47A2";

    @Test
    void mint() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/f7092d4a2149497b9d5245e3b1d8d213/evmrpc");
        //client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        //client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(ddc721Service.mint(sender, "0x2A14331F1f2D3BA0D750f4c4916E69B1DC38d721", "11111"));
    }





    @Test
    void safeMint() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        byte[] data = {10, 10, 10};
        System.out.println(ddc721Service.safeMint(sender, "0x2A14331F1f2D3BA0D750f4c4916E69B1DC38d721", "11111",data));
    }

    @Test
    void approve() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(ddc721Service.approve(sender, "6F561802FDAD741EDA7254C3F5651DAAAB266A90", new BigInteger("11")));
    }

    @Test
    void getApproved() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(ddc721Service.getApproved(new BigInteger("12")));
    }

    @Test
    void setApprovalForAll() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(ddc721Service.setApprovalForAll(sender, "6F561802FDAD741EDA7254C3F5651DAAAB266A90", true));
    }

    @Test
    void isApprovedForAll() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(ddc721Service.isApprovedForAll("0x2A14331F1f2D3BA0D750f4c4916E69B1DC38d721", "6F561802FDAD741EDA7254C3F5651DAAAB266A90"));
    }

    @Test
    void safeTransferFrom() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(ddc721Service.safeTransferFrom(sender, "0x2A14331F1f2D3BA0D750f4c4916E69B1DC38d721", "918F7F275A6C2D158E5B76F769D3F1678958A334", new BigInteger("2"), new byte[]{10}));
    }

    @Test
    void transferFrom() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(ddc721Service.transferFrom(sender, "0x2A14331F1f2D3BA0D750f4c4916E69B1DC38d721", "918F7F275A6C2D158E5B76F769D3F1678958A334", new BigInteger("2")));
    }

    @Test
    void freeze() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(ddc721Service.freeze(sender, new BigInteger("2")));
    }

    @Test
    void unFreeze() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(ddc721Service.unFreeze(sender, new BigInteger("2")));
    }

    @Test
    void burn() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(ddc721Service.burn(sender, new BigInteger("4")));
    }

    @Test
    void balanceOf() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(ddc721Service.balanceOf("0x2A14331F1f2D3BA0D750f4c4916E69B1DC38d721"));
    }

    @Test
    void ownerOf() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(ddc721Service.ownerOf(new BigInteger("12")));
    }

    @Test
    void name() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(ddc721Service.name());
    }

    @Test
    void symbol() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(ddc721Service.symbol());
    }

    @Test
    void ddcURI() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(ddc721Service.ddcURI(new BigInteger("12")));
    }

    @Test
    void setURI() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(ddc721Service.setURI(sender, new BigInteger("12"), "1234"));
    }
}