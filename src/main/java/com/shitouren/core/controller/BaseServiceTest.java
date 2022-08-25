package com.shitouren.core.controller;


import com.shitouren.core.dto.Account;
import com.shitouren.core.dto.TxInfo;
import com.shitouren.core.service.BaseService;
import org.bitcoinj.crypto.MnemonicException;
import org.junit.Test;

import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

class BaseServiceTest {

    DDCSdkClient client = new DDCSdkClient.Builder()
            .setAuthorityLogicAddress("0xFa1d2d3EEd20C4E4F5b927D9730d9F4D56314B29")
            .setChargeLogicAddress("0x0B8ae0e1b4a4Eb0a0740A250220eE3642d92dc4D")
            .setDDC721Address("0x354c6aF2cB870BEFEA8Ea0284C76e4A46B8F2870")
            .setDDC1155Address("0x0E762F4D11439B1130D402995328b634cB9c9973")
            .setGasLimit("400000")
            .setGasPrice("1")
            .setSignEventListener(new SignEventTest())
            .init();

    BaseService baseService = client.getChargeService();

    @Test
    void getBlockByNumber() throws IOException {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        EthBlock.Block block = baseService.getBlockByNumber(new BigInteger("3058179"));
        System.out.println("--------------------------------------" + block);
    }

    @Test
    void getTransReceipt() throws ExecutionException, InterruptedException {
        client.setGatewayUrl("https://opbningxia.bsngate.com:18602/api/f7092d4a2149497b9d5245e3b1d8d213/evmrpc");
        //client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        //client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        TransactionReceipt transactionReceipt = baseService.getTransReceipt("0x51d289240aba1fc4efcbc0ba598657ae65f31bcfee2affd8accaf77a1fe6bed7");
        System.out.println("--------------------------------------" + transactionReceipt);
    }

    @Test
    void getTransByHash() throws IOException {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        TxInfo transaction = baseService.getTransByHash("0x79bc4b5128e4b663876a3d4b097bd160fa512c1c5e93a615df45a86ccf0422ad");
        System.out.println("--------------------------------------" + transaction);
    }

    @Test
    void getTransByStatus() throws IOException {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/808fb54ccd604ddd9ebfa4519d112057/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        Boolean state = baseService.getTransByStatus("0x7cccfb8c469f59784fc195df21ab31c854f9b320850b22180570e3fec2cb13d4");
        System.out.println("--------------------------------------" + state);
    }

    @Test
    void getLatestBlockNumber() throws IOException {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(baseService.getLatestBlockNumber());
    }

    @Test
    void CreateAccountHex() throws MnemonicException.MnemonicLengthException {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/0e346e1fb134477cafb6c6c2583ce3c4/evmrpc");
        client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        System.out.println(baseService.createAccountHex().getAddress());
        System.out.println(baseService.createAccountHex().getPrivateKey());
        System.out.println(baseService.createAccountHex().getKeyseed());
    }

    @Test
    void AccoutHexToBech32() throws MnemonicException.MnemonicLengthException {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/f7092d4a2149497b9d5245e3b1d8d213/evmrpc");
        //client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
        //client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        Account acc = baseService.createAccountHex();
        System.out.println("acc :"+acc.getAddress());
        System.out.println(baseService.accountHexToBech32(acc.getAddress()));
    }

    @Test
    void AccountBech32ToHex() throws MnemonicException.MnemonicLengthException {
        client.setGatewayUrl("https://opbtest.bsngate.com:18602/api/f7092d4a2149497b9d5245e3b1d8d213/evmrpc");
        //client.setGatewayApiKey("9c01d01b8d88413280bef9d937e5c198");
        //client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        Account acc = baseService.createAccountHex();
        System.out.println(acc.getAddress());
        String a = baseService.accountHexToBech32(acc.getAddress());
        System.out.println(baseService.accountBech32ToHex("iaa13zjht25wmftktsqcsjvuveqp56u7q6krrd229m"));
    }
}