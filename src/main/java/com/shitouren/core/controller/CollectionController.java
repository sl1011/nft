package com.shitouren.core.controller;

import com.shitouren.core.annotation.Access;
import com.shitouren.core.annotation.GetLoginUser;
import com.shitouren.core.autogenerate.bean.UserGrant;
import com.shitouren.core.bean.param.SysUserParam;
import com.shitouren.core.service.CollectionService;
import com.shitouren.core.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "收藏", tags = "收藏")
public class CollectionController {

    @Autowired
    CollectionService collectionService;
    @Autowired
    HomeService homeService;

    @PostMapping("/Collection/show")
    @ApiOperation(value = "我的藏品(时间排序)", notes = "\n"
        /*"list1: 全部,\n" +
        "list2: 上架,\n" +
        "list3: 卖出,\n" +
            "},type 0.待上架1.已上架5.锁仓3已完成\n"*/
    )
    @Access
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "0藏品"),
    })
    public Map show(SysUserParam sysUserParam,int type) {
        return collectionService.show(sysUserParam.getLogUserPid(),type);
    }

    @PostMapping("/Collection/newshow")
    @ApiOperation(value = "我的藏品(标签排序)", notes = "\n"
            /*"list1: 全部,\n" +
            "list2: 上架,\n" +
            "list3: 卖出,\n" +
                "},type 0.待上架1.已上架5.锁仓3已完成\n"*/
    )
    @Access
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "0藏品"),
    })
    public List newshow(SysUserParam sysUserParam, int type) {
        return collectionService.newshow(sysUserParam.getLogUserPid(),type);
    }



    @PostMapping("/Collection/details")
    @ApiOperation(value = "收藏商品详情", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    public Map details(SysUserParam sysUserParam,int id) {
        return collectionService.details(sysUserParam.getLogUserPid(),id);
    }

    @PostMapping("/Collection/sell")
    @ApiOperation(value = "出售", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    public void sell(SysUserParam sysUserParam, int id, BigDecimal price, String pass) {
        collectionService.sell(sysUserParam.getLogUserPid(), id, price, pass);
    }

    @PostMapping("/Collection/Passon")
    @ApiOperation(value = "转赠", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    public void Passon(SysUserParam sysUserParam, int id, String phone) {
        collectionService.Passon(sysUserParam.getLogUserPid(), id, phone);
    }

    @PostMapping("/Collection/Passonlist")
    @ApiOperation(value = "转赠记录", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    public List Passonlist(SysUserParam sysUserParam) {
      return   collectionService.Passonlist(sysUserParam.getLogUserPid());
    }

    @PostMapping("/Collection/synthesisprizelist")
    @ApiOperation(value = "合成记录", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    public List synthesisprizelist(SysUserParam sysUserParam) {
        return   collectionService.synthesisprizelist(sysUserParam.getLogUserPid());
    }




    @PostMapping("/Collection/withdraw")
    @ApiOperation(value = "撤回", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    public void withdraw(SysUserParam sysUserParam, int id) {
        collectionService.withdraw(sysUserParam.getLogUserPid(), id);
    }

    @PostMapping("/Collection/canshu")
    @ApiOperation(value = "参数", notes = "\n" +
            "},\n")
    @Access(value = false)
    public Map canshu() {
        return collectionService.canshu();
    }

  /*  @PostMapping("/Collection/lock")
    @ApiOperation(value = "锁仓", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    public void lock(SysUserParam sysUserParam) {
        collectionService.lock(sysUserParam.getLogUserPid());
    }

    @PostMapping("/Collection/unlock")
    @ApiOperation(value = "解锁", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    public void unlock(SysUserParam sysUserParam) {
        collectionService.unlock(sysUserParam.getLogUserPid());
    }

    @PostMapping("/Collection/soldout")
    @ApiOperation(value = "下架", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    public void soldout(SysUserParam sysUserParam) {
        collectionService.soldout(sysUserParam.getLogUserPid());
    }*/

    @PostMapping("/Collection/myblindbox")
    @ApiOperation(value = "我的盲盒", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    public Map myblindbox(SysUserParam sysUserParam) {

     return    collectionService.myblindbox(sysUserParam.getLogUserPid());
    }



}
