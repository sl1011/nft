package com.shitouren.core.controller;

import com.shitouren.core.service.MineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.ParseException;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class ScheduledControl {

    @Autowired
    MineService mineService;

    //    @Autowired
//    FruitsService fruitsService;
//
////    /**
////     * 每日重置签到次数
////     * 每天凌晨执行一次    @Scheduled(cron = "0 0 0 * * ?")    @Scheduled(cron = "0 0 0 * * ?")[秒] [分] [小时] [日] [月] [周] [年]
////     */
//    @Scheduled(cron = "1 0 0 * * ?")
//    public void beoverdue() {
//        mineService.beoverdue();
//    }

    /**
     * 检查交易 未指定时间付款 账号禁用
     */
    //@Scheduled(cron = "0 */5 * * * ?")
   // public void checkTradeStatus() {
       // mineService.checkTradeStatus();
   // }
//
//    /**
//     * 检查交易 未指定时间确认收款 自动确认
//     */
//    @Scheduled(cron = "0 */5 * * * ?")
//    public void checkTradeStatus1() {
//        fruitsService.checkTradeStatus1();
//    }
//
//    /**
//     * 检查收益
//     *
//     * @throws ParseException
//     */
//    @Scheduled(cron = "0 */10 * * * ?")
//    public void checkTrade() throws ParseException {
//        fruitsService.checkTrade();
//    }
//
    @Scheduled(cron = "0 */2 * * * ?")
    public void sendhash() {
        mineService.sendhash();
    }
    /**
     * 上架
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void settle() {
        mineService.settle();
    }
    //时间到了修改状态
    @Scheduled(cron = "0 */1 * * * ?")
    public void changetime() {
        mineService.changetime();
    }

    //时间到了修改状态
    @Scheduled(cron = "0 */1 * * * ?")
    public void changeendtime() {
        mineService.changeendtime();
    }
    //时间到了修改状态
//    @Scheduled(cron = "0 */10 * * * ?")
//    public void changetickttime() {
//        mineService.changetickttime();
//    }

//
//    @Scheduled(cron = "10 0 0 * * ?")
//    public void clear() {
//        fruitsService.clear();
//    }
//
//    @Scheduled(cron = "20 0 0 * * ?")
//    public void clear1() {
//        fruitsService.clear1();
//    }
//
//    @Scheduled(cron = "30 0 0 * * ?")
//    public void huan() {
//        fruitsService.huan();
//    }
//
}
