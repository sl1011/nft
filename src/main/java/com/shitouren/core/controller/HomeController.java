package com.shitouren.core.controller;

import com.alipay.api.AlipayApiException;
import com.shitouren.core.annotation.Access;
import com.shitouren.core.annotation.GetLoginUser;
import com.shitouren.core.bean.param.SysUserParam;
import com.shitouren.core.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Api(value = "首页", tags = "首页")
public class HomeController {

    @Autowired
    HomeService homeService;

    @PostMapping("/Home/shows")
    @ApiOperation(value = "新首页", notes = "\n" +
            "},\n")
    @Access(value = false)
    public Map show() {
        return homeService.show();
    }

    @PostMapping("/Home/drawbanner")
    @ApiOperation(value = "drawbanner", notes = "\n" +
            "},\n")
    @Access(value = false)
    public List drawbanner() {
        return homeService.drawbanner();
    }

    @PostMapping("/Home/collections")
    @ApiOperation(value = "藏品", notes = "\n" +
            "},\n")
    @Access(value = false)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "0藏品1盲盒2合成"),
    })
    public Map collections(int type) {
        return homeService.collections(type);
    }




    @PostMapping("/Home/bannerdetails")
    @ApiOperation(value = "轮播详情", notes = "\n" +
            "},\n")
    @Access(value = false)
    public String bannerdetails(int id) {
        return homeService.bannerdetails(id);
    }

    @PostMapping("/Home/show")
    @ApiOperation(value = "首页", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    public Map shows(SysUserParam sysUserParam) {
        return homeService.shows(sysUserParam.getLogUserPid());
    }

    @PostMapping("/Home/details")
    @ApiOperation(value = "藏品详情", notes = "\n" +
            "isgrant: 0未预约1已预约,\n" +
            "fragment: 盲盒新增字段 里边是list返回的是name和img,\n" +
           /* "list: 记录,\n" +
            "{" +
            "name: 名称,\n" +
            "price: 价格,\n" +
            "time: 时间,\n" +*/

            "},\n")
    @Access
    @GetLoginUser
    public Map details(SysUserParam sysUserParam, int id) {
        return homeService.details(sysUserParam.getLogUserPid(), id);
    }

    @PostMapping("/Home/noregdetails")
    @ApiOperation(value = "未登录藏品详情", notes = "\n" +
            "isgrant: 0未预约1已预约2未登录,\n" +
            "fragment: 盲盒新增字段 里边是list返回的是name和img,\n" +
           /* "list: 记录,\n" +
            "{" +
            "name: 名称,\n" +
            "price: 价格,\n" +
            "time: 时间,\n" +*/

            "},\n")
    @Access(value = false)
    public Map noregdetails( int id) {
        return homeService.noregdetails( id);
    }

   /* @PostMapping("/Home/detailss")
    @ApiOperation(value = "藏品详情", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    public int detailss(SysUserParam sysUserParam, int id) {
        return homeService.detailss(sysUserParam.getLogUserPid(), id);
    }*/

    @PostMapping("/Home/persondetails")
    @ApiOperation(value = "个人藏品详情", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    public Map persondetails(SysUserParam sysUserParam, int id) {
        return homeService.persondetails(sysUserParam.getLogUserPid(), id);
    }

    @PostMapping("/Home/ordertype")
    @ApiOperation(value = "订单详情", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
    })
    public int ordertype(SysUserParam sysUserParam, int id) {
        return homeService.ordertype(id);
    }

    @PostMapping("/Home/Confirmorderdetails")
    @ApiOperation(value = "订单详情", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "藏品id"),
    })
    public Map Confirmorderdetails(SysUserParam sysUserParam, int id) {

        return homeService.Confirmorderdetails(sysUserParam.getLogUserPid(), id);
    }

    @PostMapping("/Home/Confirmorder")
    @ApiOperation(value = "确认订单", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "藏品id"),
            //@ApiImplicitParam(name = "orderno", value = "编号"),
    })
    public int Confirmorder(SysUserParam sysUserParam, int id) {
        return homeService.Confirmorder(sysUserParam.getLogUserPid(), id);
    }

    @PostMapping("/Home/personConfirmorder")
    @ApiOperation(value = "个人确认订单", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "藏品id"),
            @ApiImplicitParam(name = "orderno", value = "编号"),
    })
    public int personConfirmorder(SysUserParam sysUserParam, int id, String orderno) {
        return homeService.personConfirmorder(sysUserParam.getLogUserPid(), id, orderno);
    }


    @PostMapping("/Home/Payorder")
    @ApiOperation(value = "付款", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
            @ApiImplicitParam(name = "type", value = "1.平台2.衫德支付3.支付宝4.其他"),
            @ApiImplicitParam(name = "paytype", value = "1.app2.H5"),
            @ApiImplicitParam(name = "ip", value = "ip"),
    })
    public Map Payorder(SysUserParam sysUserParam, int id, int type , int paytype, String ip) throws Exception {
        return homeService.Payorder(sysUserParam.getLogUserPid(), id, type, paytype,ip);
    }

    @PostMapping("/Home/personPayorder")
    @ApiOperation(value = "个人付款", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
            @ApiImplicitParam(name = "type", value = "1.平台2.微信3.支付宝4.其他"),
            @ApiImplicitParam(name = "paytype", value = "1.app2.H5"),
    })
    public Map personPayorder(SysUserParam sysUserParam, int id, int type , int paytype) throws Exception {
        return homeService.personPayorder(sysUserParam.getLogUserPid(), id, type, paytype);
    }


    @PostMapping("/Home/mhPayorder")
    @ApiOperation(value = "盲盒付款", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
            @ApiImplicitParam(name = "type", value = "1.平台2.微信3.支付宝4.其他"),
            @ApiImplicitParam(name = "paytype", value = "1.app2.H5"),
    })
    public Map mhPayorder(SysUserParam sysUserParam, int id, int type, String password, int paytype) throws Exception {
        return homeService.mhPayorder(sysUserParam.getLogUserPid(), id, type, password, paytype);
    }

    @PostMapping("/Home/mintnft")
    @ApiOperation(value = "发行", notes = "\n" +
            "},\n")
    @Access(value = false)
    public void mintnft(int collectionid, int user_grantid) {
        homeService.mintnft(collectionid, user_grantid);
    }

    @PostMapping("/Home/adduser")
    @ApiOperation(value = "更新用户", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", value = "地址"),
            @ApiImplicitParam(name = "privateKey", value = "私钥"),
    })
    public void adduser(SysUserParam sysUserParam, String address, String privateKey) {
        homeService.adduser(sysUserParam.getLogUserPid(), address, privateKey);
    }

    @PostMapping("/Home/houtaidetails")
    @ApiOperation(value = "后台藏品详情", notes = "\n" +
            "name: 合约名称,\n" +
            "minname: 合约简称\n" +
            "limits: 发布数量\n" +
            "isdeploy: 0未部署1已部署（已部署不让再次部署）\n" +
            "},\n")
    @Access(value = false)
    public Map houtaidetails(int id) {
        return homeService.houtaidetails(id);
    }

    @PostMapping("/Home/updateaddress")
    @ApiOperation(value = "保存合约地址", notes = "\n" +
            "},\n")
    @Access(value = false)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "藏品id"),
            @ApiImplicitParam(name = "address", value = "合约地址"),
    })
    public void updateaddress(int id, String address) {
        homeService.updateaddress(id, address);
    }

    @PostMapping("/Home/agreementss")
    @ApiOperation(value = "协议", notes = "\n" +
            "},\n")
    @Access(value = false)
    public String agreementss(int id) {
        return homeService.agreementss(id);
    }

//    @PostMapping("/Home/tui")
//    @ApiOperation(value = "退款", notes = "\n" +
//            "},\n")
//    @Access(value = false)
//    public void tui() {
//        homeService.tui();
//    }

    @PostMapping("/Home/hqaddress")
    @ApiOperation(value = "获取地址", notes = "\n" +
            "},\n")
    @Access(value = false)
    public Map hqaddress(String count,String code) {
        return homeService.hqaddress(count,code);
    }

    @PostMapping("/Home/searchcommodity")
    @ApiOperation(value = "搜索", notes = "\n" +
            "},\n")
    @Access(value = false)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "内容"),
            @ApiImplicitParam(name = "type", value = "1最新上架2价格降序3价格升序"),
    })
    public List searchcommodity(String content,int type) {
      return   homeService.searchcommodity(content,type);
    }


    //预约
    @PostMapping("/Home/appointmentinfo")
    @ApiOperation(value = "预约", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    public Map appointmentinfo(SysUserParam sysUserParam,int id) {
        return   homeService.appointmentinfo(sysUserParam.getLogUserPid(),id);
    }

    //预约
    @PostMapping("/Home/prompt")
    @ApiOperation(value = "是否有提示type=0未读1已读,state0失败1成功,content商品名称", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    public Map prompt(SysUserParam sysUserParam) {
        return   homeService.prompt(sysUserParam.getLogUserPid());
    }
    //预约
    @PostMapping("/Home/readprompt")
    @ApiOperation(value = "", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    public void readprompt(SysUserParam sysUserParam,int id) {
           homeService.readprompt(sysUserParam.getLogUserPid(),id);
    }

    @PostMapping("/Home/detailsimg")
    @ApiOperation(value = "详情商品id的分享页面", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    public String detailsimg(SysUserParam sysUserParam,int id,int ids,String type) {
      return   homeService.detailsimg(sysUserParam.getLogUserPid(),id,ids,type);
    }

    //预约
   /* @PostMapping("/Home/trial")
    @ApiOperation(value = "", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    public void trial() {
        homeService.trial();
    }*/

    //预约
    @PostMapping("/Home/trial")
    @ApiOperation(value = "测试", notes = "\n" +
            ",\n")
    @Access(value = false)
    public void trial(int type) {
        homeService.trial(type);
    }


    @PostMapping("/Home/draw")
    @ApiOperation(value = "申购抽签", notes = "\n" +
            "name: 名称,\n" +
            "img: 图片,\n" +
            "number: 发放数量,\n" +
            "price: 价格,\n" +
            "halfprice: 半价,\n" +
            "drawnumber: 预约人数,\n" +
            "starttime: 开始时间,\n" +
            "endtime: 结束时间,\n" +
            "drawtime: 抽签时间,\n" +
            ",\n")
    @Access
    @GetLoginUser
    public List draw(SysUserParam sysUserParam) {
     return    homeService.draw(sysUserParam.getLogUserPid());
    }

    @PostMapping("/Home/signup")
    @ApiOperation(value = "抽签报名", notes = "\n" +
            ",\n")
    @Access
    @GetLoginUser
    public void signup(SysUserParam sysUserParam,int id) {
            homeService.signup(sysUserParam.getLogUserPid(),id);
    }

    @PostMapping("/Home/cansend")
    @ApiOperation(value = "是否可以转赠", notes = "\n" +
            ",\n")
    @Access
    @GetLoginUser
    public Map cansend(SysUserParam sysUserParam,int id) {
      return   homeService.cansend(sysUserParam.getLogUserPid(),id);
    }

}
