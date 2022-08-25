package com.shitouren.core.controller;

import com.shitouren.core.annotation.Access;
import com.shitouren.core.service.NftService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Api(value = "文昌链", tags = "文昌链")
public class NftController {

    @Autowired
    NftService nftService;


    @PostMapping("/Nft/creatwallet")
    @ApiOperation(value = "创建钱包", notes = "\n" +
            "mnemonic: 助记词,\n" +
            "address: 地址,\n" +
            "privkey: 私钥,\n" +
            ",\n")
    @Access(value = false)
    public Map creatwallet() {
       return nftService.creatwallet();
    }

    @PostMapping("/Nft/transfer")
    @ApiOperation(value = "转币", notes = "\n" +
            ",\n")
    @Access(value = false)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "denomID", value = "合约id"),
            @ApiImplicitParam(name = "nftID", value = "nftID"),
            @ApiImplicitParam(name = "from", value = "发送地址"),
            @ApiImplicitParam(name = "to", value = "接受地址"),
    })
    public void transfer(String denomID,String nftID,String from,String to) {
         nftService.transfer(denomID,nftID,from,to);
    }

    @PostMapping("/Nft/deploycontract")
    @ApiOperation(value = "部署合约", notes = "\n" +
            "denomID: 合约id,\n" +
            ",\n")
    @Access(value = false)
    public String deploycontract() {
        return    nftService.deploycontract();
    }

    @PostMapping("/Nft/mintnft")
    @ApiOperation(value = "发行nft", notes = "\n" +
            "nftID:币的id\n"+
            "Hash:生成的hash\n"+
            ",\n")
    @Access(value = false)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "denomID", value = "合约id"),
            @ApiImplicitParam(name = "address", value = "接受nft地址"),
    })
    public Map mintnft(String denomID,String address) {
        return    nftService.mintnft(denomID,address);
    }


}
