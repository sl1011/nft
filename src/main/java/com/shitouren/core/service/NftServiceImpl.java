package com.shitouren.core.service;


import com.google.protobuf.GeneratedMessageV3;
import irita.sdk.client.BaseClient;
import irita.sdk.client.IritaClient;
import irita.sdk.client.OpbClient;
import irita.sdk.config.ClientConfig;
import irita.sdk.config.OpbConfig;
import irita.sdk.constant.enums.BroadcastMode;
import irita.sdk.key.AlgoEnum;
import irita.sdk.key.KeyInfo;
import irita.sdk.key.KeyManager;
import irita.sdk.key.KeyManagerFactory;
import irita.sdk.model.*;
import irita.sdk.module.nft.*;
import irita.sdk.module.perm.PermClient;
import irita.sdk.module.tibc.TibcClient;
import irita.sdk.util.AddressUtils;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;
import proto.cosmos.base.query.v1beta1.Pagination;
import proto.nft.Tx;
import proto.perm.Perm;

import java.io.IOException;
import java.util.*;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Service
public class NftServiceImpl implements NftService {
    private KeyManager km;
    private NftClient nftClient;
    private BaseClient baseClient;
    private TibcClient tibcClient;
    private IritaClient client;

    private final BaseTx baseTx = new BaseTx(200000, new Fee("200000", "ugas"), BroadcastMode.Commit);



    @SneakyThrows
    @Override
    public Map creatwallet() {

        KeyManager km= KeyManagerFactory.createDefault();
        String mnemonic=km.add();
        System.out.println("助记词："+mnemonic);
        System.out.println("地址："+km.getCurrentKeyInfo().getAddress());
        System.out.println("私钥："+km.getCurrentKeyInfo().getPrivKey());
        Map map=new HashMap();
        map.put("mnemonic",mnemonic);
        map.put("address",km.getCurrentKeyInfo().getAddress());
        map.put("privkey",km.getCurrentKeyInfo().getPrivKey());
        return map;
    }

    @Override
    public void connectwc() {
        String mnemonic="opera vivid pride shallow brick crew found resist decade neck expect apple chalk belt sick author know try tank detail tree impact hand best";
        KeyManager km=KeyManagerFactory.createDefault();
        km.recover(mnemonic);

        // projectID: 填你的 projectID
        String nodeUri="http://47.100.192.234:26657";
        String grpcAddr="47.100.192.234:9090";
        String chainId="testing";
        ClientConfig clientConfig=new ClientConfig(nodeUri,grpcAddr,chainId);
        OpbConfig opbConfig=null;

        OpbClient client=new OpbClient(clientConfig,opbConfig,km);
        assertEquals("iaa1ytemz2xqq2s73ut3ys8mcd6zca2564a5lfhtm3",km.getCurrentKeyInfo().getAddress());
    }

    @SneakyThrows
    @Override
    @Test
    @Disabled
    public String deploycontract() {
        String mnemonic="opera vivid pride shallow brick crew found resist decade neck expect apple chalk belt sick author know try tank detail tree impact hand best";
        KeyManager km=KeyManagerFactory.createDefault();
        km.recover(mnemonic);

        // projectID: 填你的 projectID
        String nodeUri="http://47.100.192.234:26657";
        String grpcAddr="47.100.192.234:9090";
        String chainId="testing";
        ClientConfig clientConfig=new ClientConfig(nodeUri,grpcAddr,chainId);
        OpbConfig opbConfig=null;

        OpbClient client=new OpbClient(clientConfig,opbConfig,km);

        nftClient = client.getNftClient();
        baseClient = client.getBaseClient();
        assertEquals("iaa1ytemz2xqq2s73ut3ys8mcd6zca2564a5lfhtm3",km.getCurrentKeyInfo().getAddress());


        String denomID = "testdenom" + new Random().nextInt(1000);
        String denomName = "测试nft";
        String schema = "no shcema";
        String symbol = "symbol";
        //是否限制发行
        //true  只有 Denom 的所有者可以发行此类别的 NFT 给自己（发行到别的地址也是不可以的）
        //false 任何人都可以发行 NFT
        boolean mintRestricted = false;
        //是否限制更新 NFT
        //true  任何人都不可以更新 NFT
        //false 只有此 NFT 的所有者才能更新
        boolean updateRestricted = false;

        //issue denom 创建 denom
        IssueDenomRequest req = new IssueDenomRequest()
                .setId(denomID)
                .setName(denomName)
                .setSchema(schema)
                .setSymbol(symbol)
                .setMintRestricted(mintRestricted)
                .setUpdateRestricted(updateRestricted);
        System.out.println("baseTx :"+baseTx);
        System.out.println("req :"+req);
        ResultTx resultTx = nftClient.issueDenom(req, baseTx);
        assertNotNull(resultTx.getResult().getHash());

        //query denom 通过denomID查询denom信息
        QueryDenomResp denom = nftClient.queryDenom(denomID);
        assertEquals(denomID, denom.getId());
        assertEquals(denomName, denom.getName());
        assertEquals(schema, denom.getSchema());
        assertEquals(symbol, denom.getSymbol());
        assertEquals(mintRestricted, denom.isMintRestricted());
        assertEquals(updateRestricted, denom.isUpdateRestricted());


        return denomID;
    }

    @SneakyThrows
    @Override
    public Map mintnft(String denomID,String address) {
        String mnemonic="opera vivid pride shallow brick crew found resist decade neck expect apple chalk belt sick author know try tank detail tree impact hand best";
        KeyManager km=KeyManagerFactory.createDefault();
        km.recover(mnemonic);

        // projectID: 填你的 projectID
        String nodeUri="http://47.100.192.234:26657";
        String grpcAddr="47.100.192.234:9090";
        String chainId="testing";
        ClientConfig clientConfig=new ClientConfig(nodeUri,grpcAddr,chainId);
        OpbConfig opbConfig=null;

        OpbClient client=new OpbClient(clientConfig,opbConfig,km);

        nftClient = client.getNftClient();
        baseClient = client.getBaseClient();
        assertEquals("iaa1ytemz2xqq2s73ut3ys8mcd6zca2564a5lfhtm3",km.getCurrentKeyInfo().getAddress());



        String nftID = "nft" + new Random().nextInt(1000);

        String nftName = "测试nft";
        String uri = "https://www.baidu.com";
        String data = "any data";
        BaseTx baseTx=new BaseTx(200000,new Fee("200000","ugas"),BroadcastMode.Commit);
        MintNFTRequest mintReq=new MintNFTRequest()
                .setDenom(denomID)
                .setId(nftID)
                .setName(nftName)
                .setUri(uri)
                .setData(data)
                .setRecipient(address);
        ResultTx resultTx=nftClient.mintNft(mintReq,baseTx);
        System.out.println("resultTx :"+resultTx);
        Map map=new HashMap();
        map.put("nftID",nftID);
        map.put("Hash",resultTx.getResult().getHash());



        return map;
    }

    @SneakyThrows
    @Override
    public void transfer(String denomID,String nftID,String from,String to) {
        String mnemonic="opera vivid pride shallow brick crew found resist decade neck expect apple chalk belt sick author know try tank detail tree impact hand best";
        KeyManager km=KeyManagerFactory.createDefault();
        km.recover(mnemonic);

        // projectID: 填你的 projectID
        String nodeUri="http://47.100.192.234:26657";
        String grpcAddr="47.100.192.234:9090";
        String chainId="testing";
        ClientConfig clientConfig=new ClientConfig(nodeUri,grpcAddr,chainId);
        OpbConfig opbConfig=null;

        OpbClient client=new OpbClient(clientConfig,opbConfig,km);

        PermClient permClient = client.getPermClient();
        nftClient = client.getNftClient();
        baseClient = client.getBaseClient();
        assertEquals("iaa1ytemz2xqq2s73ut3ys8mcd6zca2564a5lfhtm3",km.getCurrentKeyInfo().getAddress());

       // String denomOwnerNew = "iaa14s9hekvzhtf3y3962zn3vzv45k0ay7mguyqhrl";
        QueryDenomResp denom = nftClient.queryDenom(denomID);
        System.out.println("denomID :"+denomID);
        System.out.println("getCreator: "+denom.getCreator());

        String denomOwnerNew = from;
        TransferDenomRequest transferDenomReq = new TransferDenomRequest()
                .setId(denomID)
                .setRecipient(denomOwnerNew);
        ResultTx resultTx = nftClient.transferDenom(transferDenomReq, baseTx);
        System.out.println("resultTx :"+resultTx);
        assertNotNull(resultTx);


        denom = nftClient.queryDenom(denomID);
        System.out.println("new getCreator :"+denom.getCreator());
        assertEquals(denomOwnerNew, denom.getCreator());


        String reci = "iaa1hehve24qcr3haw49syyhzp793qw27uksz3f69h";
        TransferNFTRequest transferReq = new TransferNFTRequest()
                .setDenom(denomID)
                .setId(nftID)
                .setRecipient(to);
         resultTx = nftClient.transferNFt(transferReq, baseTx);
        assertNotNull(resultTx.getResult().getHash());

    }


}
