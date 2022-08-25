package com.shitouren.core.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.shitouren.core.service.DDC1155Service;
import com.shitouren.core.service.DictService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DDC1155ServiceTest {
    @Autowired
    DictService dictService;

    DDCSdkClient client = new DDCSdkClient.Builder()
            .setAuthorityLogicAddress("0xFa1d2d3EEd20C4E4F5b927D9730d9F4D56314B29")
            .setChargeLogicAddress("0x0B8ae0e1b4a4Eb0a0740A250220eE3642d92dc4D")
            .setDDC721Address("0x354c6aF2cB870BEFEA8Ea0284C76e4A46B8F2870")
            .setDDC1155Address("0x0E762F4D11439B1130D402995328b634cB9c9973")
            .setGasLimit("800000")
            .setGasPrice("1")
            .setSignEventListener(new SignEventTest())
            .init();

    DDC1155Service ddc1155Service = client.getDDC1155Service();



    @Test
    String safeMint(String to,BigInteger amount,String url) throws Exception {
        String sender = dictService.getValue("mintmanger");
        String projectid = dictService.getValue("projectid");

        client.setGatewayUrl("https://opbningxia.bsngate.com:18602/api/"+projectid+"/evmrpc");

        //client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        //client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        byte[] data = {10, 10, 10};
        Map map=new HashMap();

        map.put("url",url);
        String ddcurl= JSON.toJSONString(map);

        System.out.println("ddcurl "+ddcurl);
        System.out.println(ddc1155Service.safeMint(sender, to, amount, ddcurl, data));
        return null;
    }

  /*  @Test
    void safeMintBatch() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        Multimap<BigInteger, String> ddcInfo = ArrayListMultimap.create();
        ;
        ddcInfo.put(new BigInteger("3"), "12");
        byte[] data = {10, 10, 10};
        System.out.println(ddc1155Service.safeMintBatch(sender, "918F7F275A6C2D158E5B76F769D3F1678958A334", ddcInfo, data));
    }*/

  /*  @Test
    void setApprovalForAll() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(ddc1155Service.setApprovalForAll(sender, "918F7F275A6C2D158E5B76F769D3F1678958A334", true));
    }

    @Test
    void isApprovedForAll() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(ddc1155Service.isApprovedForAll("0x2A14331F1f2D3BA0D750f4c4916E69B1DC38d721", "918F7F275A6C2D158E5B76F769D3F1678958A334"));
    }*/

    @Test
    String safeTransferFrom(String from,String to,BigInteger ddcid) throws Exception {
        String sender = dictService.getValue("mintmanger");
        String projectid = dictService.getValue("projectid");

        client.setGatewayUrl("https://opbningxia.bsngate.com:18602/api/"+projectid+"/evmrpc");
        //client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        //client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        byte[] data = {10, 10, 10};
        System.out.println(ddc1155Service.safeTransferFrom(sender,from, to,ddcid,new BigInteger("1"),data));
        return null;
    }

   /* @Test
    void safeBatchTransferFrom() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        Multimap<BigInteger, BigInteger> ddcs = ArrayListMultimap.create();
        ddcs.put(new BigInteger("3"), new BigInteger("3"));
        byte[] data = {10, 10, 10};
        System.out.println(ddc1155Service.safeBatchTransferFrom(sender,"0x2A14331F1f2D3BA0D750f4c4916E69B1DC38d721", "918F7F275A6C2D158E5B76F769D3F1678958A334",ddcs,data));
    }

    @Test
    void freeze() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(ddc1155Service.freeze(sender, new BigInteger("12")));
    }

    @Test
    void unFreeze() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(ddc1155Service.unFreeze(sender, new BigInteger("12")));
    }

    @Test
    void burn() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(ddc1155Service.burn(sender, "918F7F275A6C2D158E5B76F769D3F1678958A334", new BigInteger("2")));
    }

    @Test
    void burnBatch() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        List<BigInteger> ddcIds = new ArrayList<>();
        ddcIds.add(BigInteger.valueOf(12));
        System.out.println(ddc1155Service.burnBatch(sender, "918F7F275A6C2D158E5B76F769D3F1678958A334",ddcIds));
    }*/

    @Test
    String balanceOf(String owner,BigInteger ddcid) throws Exception {
        String sender = dictService.getValue("mintmanger");
        String projectid = dictService.getValue("projectid");

        client.setGatewayUrl("https://opbningxia.bsngate.com:18602/api/"+projectid+"/evmrpc");
        //client.setGatewayUrl("https://opbningxia.bsngate.com:18602/api/f7092d4a2149497b9d5245e3b1d8d213/evmrpc");
        //client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        //client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(ddc1155Service.balanceOf(owner, ddcid));
        return null;
    }

    /*@Test
    void balanceOfBatch() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        Multimap<String, BigInteger> ddcs = ArrayListMultimap.create();
        ddcs.put("918F7F275A6C2D158E5B76F769D3F1678958A334", new BigInteger("3"));
        ddcs.put("918F7F275A6C2D158E5B76F769D3F1678958A334", new BigInteger("5"));
        List<BigInteger> a = new ArrayList<>(2);
        a = ddc1155Service.balanceOfBatch(ddcs);
        System.out.println(a);
    }

    @Test
    void ddcURI() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(ddc1155Service.ddcURI(new BigInteger("3")));
    }

    @Test
    void setURI() throws Exception {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(ddc1155Service.setURI(sender, "918F7F275A6C2D158E5B76F769D3F1678958A334", new BigInteger("3"), "1234"));
    }*/
}