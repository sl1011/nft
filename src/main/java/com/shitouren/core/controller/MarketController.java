package com.shitouren.core.controller;

import com.alipay.api.AlipayApiException;
import com.shitouren.core.annotation.Access;
import com.shitouren.core.annotation.GetLoginUser;
import com.shitouren.core.bean.param.SysUserParam;
import com.shitouren.core.service.MarketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "市场", tags = "市场")
public class MarketController {
    @Autowired
    MarketService marketService;

    @PostMapping("/Market/show")
    @ApiOperation(value = "市场", notes = "type=7转卖中8支付中\n" +
            "},\n")
    @Access
    @GetLoginUser
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "collectionstype", value = "0藏品"),
                    @ApiImplicitParam(name = "albumid", value = "0全部"),
                    @ApiImplicitParam(name = "search", value = "搜索内容"),
                    @ApiImplicitParam(name = "type", value = "type1是时间最新2价格是由小到大"),
            }
    )

    public List show(SysUserParam sysUserParam,int collectionstype,int albumid,String search, int type) {
        return marketService.show(sysUserParam.getLogUserPid(),collectionstype,albumid,search,type);
    }

    @PostMapping("/Market/search")
    @ApiOperation(value = "搜索", notes = "type1是时间由大到小2是由小到大3是价格由高到低4是价格由低到高\n" +
            "},\n")
    @Access
    @GetLoginUser
    @ApiImplicitParam(name = "search", value = "搜索内容")
    public List search(SysUserParam sysUserParam, String search, int type,int row) {
        return marketService.search(sysUserParam.getLogUserPid(), search, type,row);
    }

    @PostMapping("/Market/details")
    @ApiOperation(value = "市场藏品详情", notes = "\n" +
            "list: 记录,\n" +
            "{" +
            "name: 名称,\n" +
            "price: 价格,\n" +
            "time: 时间,\n" +
            "},\n")
    @Access
    @GetLoginUser
    public Map details(SysUserParam sysUserParam, int id) {
        return marketService.details(sysUserParam.getLogUserPid(), id);
    }

  /*  @PostMapping("/Market/noregdetails")
    @ApiOperation(value = "市场未登录藏品详情", notes = "\n" +
            "list: 记录,\n" +
            "{" +
            "name: 名称,\n" +
            "price: 价格,\n" +
            "time: 时间,\n" +
            "},\n")
    @Access(value = false)
    public Map noregdetails(int id) {
        return marketService.noregdetails(id);
    }*/

    @PostMapping("/Market/Confirmorder")
    @ApiOperation(value = "确认订单", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
    })
    public int Confirmorder(SysUserParam sysUserParam, int id) {
        return marketService.Confirmorder(sysUserParam.getLogUserPid(), id);
    }

    @PostMapping("/Market/Confirmorderdetails")
    @ApiOperation(value = "订单详情", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
    })
    public Map Confirmorderdetails(SysUserParam sysUserParam, int id) {
        return marketService.Confirmorderdetails(sysUserParam.getLogUserPid(), id);
    }

    @PostMapping("/Market/Payorder")
    @ApiOperation(value = "支付订单", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
            @ApiImplicitParam(name = "type", value = "1.平台2.衫德支付3.支付宝4.其他"),
            @ApiImplicitParam(name = "paytype", value = "1.app2.H5"),
            @ApiImplicitParam(name = "ip", value = "ip"),
    })
    public Map Payorder(SysUserParam sysUserParam, int id, int type, String paytype,String ip) throws Exception {
        Map map = new HashMap();
        map.put("ailay", marketService.Payorder(sysUserParam.getLogUserPid(), id, type, paytype,ip));
        return map;
    }
    //all album
    @PostMapping("/Market/allalbum")
    @ApiOperation(value = "所有专辑", notes = "\n" +
            "},\n")
    @Access
    @GetLoginUser
    public List allalbum(SysUserParam sysUserParam) {
        return marketService.allalbum(sysUserParam.getLogUserPid());
    }

//<p style="text-align: justify;">&nbsp; &nbsp; &nbsp; &nbsp;盘古是中国古代传说时期中开天辟地的神。
//    在天地还没有开辟以前，宇宙就像是一个大鸡蛋一样混沌一团。有个叫做盘古的巨人在这个“大鸡蛋”中一直酣睡了约18000年后醒来，
//    盘古凭借着自己的神力把天地开辟出来了。他的左眼变成了太阳，右眼变成了月亮；头发和胡须变成了夜空的星星；他的身体变成了东、西、
//    南、北四极和雄伟的三山五岳；血液变成了江河；牙齿、骨骼和骨髓变成了地下矿藏；皮肤和汗毛变成了大地上的草木；汗水变成了雨露。 盘古是人
//    的老祖宗。艺文类聚天地浑沌如鸡子，盘古生其中。万八千岁，天地开辟，阳清为天，阴浊为地。盘古在其中，一日九变，神于天，圣于地。天日高一丈，地日厚一
//    丈，盘古日长一丈，如此万八千岁。天数极高地数极深，盘古极长。后乃有三皇。数起于一，立于三，成于五，盛于七，极于九，故天去地九万里。天气蒙鸿，萌芽兹始，
//    遂分天地，肇立乾坤，启阴感阳分布元气，乃孕中和，是为人也。首生盘古，垂死化身；气成风云，声为雷霆，左眼为日，右眼为月，四肢五体为四极五岳，血液为江河，筋脉为地
//    里，肌肉为田土，发髭为星辰，皮毛为草木，齿骨为金石，精髓为珠玉，汗流为雨泽，身之诸虫，因风所感，化为黎氓。&nbsp;<b style=""> &nbsp;</b></p>
}
