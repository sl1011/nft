package com.shitouren.core.controller;


import com.shitouren.core.dto.BlockEventBean;
import com.shitouren.core.service.BlockEventService;
import org.junit.Test;


import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

class BlockEventServiceTest {

    DDCSdkClient client = new DDCSdkClient.Builder()
            .setAuthorityLogicAddress("0xFa1d2d3EEd20C4E4F5b927D9730d9F4D56314B29")
            .setChargeLogicAddress("0x0B8ae0e1b4a4Eb0a0740A250220eE3642d92dc4D")
            .setDDC721Address("0x354c6aF2cB870BEFEA8Ea0284C76e4A46B8F2870")
            .setDDC1155Address("0x0E762F4D11439B1130D402995328b634cB9c9973")
            .setGasLimit("300000")
            .setGasPrice("1")
            .setSignEventListener(new SignEventTest())
            .init();

    @Test
    void getBlockEvent() throws IOException, InterruptedException, ExecutionException {
        client.setGatewayUrl("https://opbningxia.bsngate.com:18602/api/f7092d4a2149497b9d5245e3b1d8d213/evmrpc");
       // client.setGatewayApiKey("903f4f9268ab4e2eac717c7200429776");
       // client.setGatewayApiValue("0c1dd14a41b14cfa83048d839a0593ff");
        BlockEventService blockEventService = new BlockEventService();
        BlockEventBean blockEvent = blockEventService.getBlockEvent(new BigInteger("14252147"));
    }
}