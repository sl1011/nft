package com.shitouren.core.PayDemo;

import com.alibaba.fastjson.JSON;
import com.huifu.adapay.model.Drawcash;
import com.huifu.adapay.model.SettleAccount;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yingyong.wang
 */
public class SettleAccountDemo {

    /**
     * 运行结算账户类接口
     *
     * @throws Exception 异常
     */
    public static void executeSettleAccountTest(String merchantKey, String app_id, String member_id) throws Exception {
        SettleAccountDemo demo = new SettleAccountDemo();
        // 创建结算账户
        Map<String, Object> settlecount = demo.executeCreateSettleAccount(merchantKey, app_id, member_id);
        String settleCount_id = (String) settlecount.get("id");


        // 查询结算账户
        demo.executeQuerySettleAccount(merchantKey, settleCount_id, app_id, member_id);

        // 查询结算账户明细列表
        demo.executeQuerySettleDetails(merchantKey, app_id, member_id, settleCount_id);
        // 删除结算账户
        demo.executeDeleteSettleAccount(merchantKey, settleCount_id, app_id, member_id);

        member_id = "user_test_10001";
        settleCount_id = "0023056905335360";

        demo.executeModifySettleAccount(merchantKey, settleCount_id, app_id, member_id);

//        SettleAccountDemo.executeDrawCash(merchantKey, app_id, member_id);
//
//        SettleAccountDemo.executeQueryBalance(merchantKey, app_id, member_id, settleCount_id);

        demo.executeTransfer(app_id, member_id);

        demo.executeTransferList(app_id, member_id, settleCount_id);

        demo.executeCommission(app_id, member_id);
        demo.executeCommissionList(app_id);
    }

    /**
     * 运行查询结算明细列表接口
     *
     * @throws Exception 异常
     */
//    public static void executeQuerySettleDetailTest(String merchantKey, String appId, String memberId, String settleAccountId, String beginDate, String endDate) throws Exception {
//        SettleAccountDemo demo = new SettleAccountDemo();
//        demo.executeQuerySettleDetails(merchantKey, appId, memberId, settleAccountId, beginDate, endDate);
//    }


    /**
     * 创建 settleCount
     *
     * @return 创建的settleCount 对象
     * @throws Exception 异常
     */
    public Map<String, Object> executeCreateSettleAccount(String merchantKey, String app_id, String member_id) throws Exception {
        System.out.println("=======execute Create SettleAccount begin=======");
        Map<String, Object> settleCountParams = new HashMap<String, Object>(2);
        Map<String, Object> accountInfo = new HashMap<String, Object>(2);
        accountInfo.put("card_id", "6222021703001692221");
        accountInfo.put("card_name", "袁电茜");
        accountInfo.put("cert_id", "310109200006062491");
        accountInfo.put("cert_type", "00");
        accountInfo.put("tel_no", "18888888881");
        accountInfo.put("bank_code", "03060000");
        accountInfo.put("bank_acct_type", "1");
        accountInfo.put("prov_code", "0031");
        accountInfo.put("area_code", "3100");
        settleCountParams.put("member_id", member_id);
        settleCountParams.put("app_id", app_id);
        settleCountParams.put("channel", "bank_account");
        settleCountParams.put("account_info", accountInfo);
        System.out.println("创建结算账户，请求参数：" + JSON.toJSONString(settleCountParams));
        Map<String, Object> settleCount = SettleAccount.create(settleCountParams, merchantKey);
        System.out.println("创建结算账户，返回参数：" + JSON.toJSONString(settleCount));
        System.out.println("=======execute Create SettleAccount end=======");

        return settleCount;

    }

    /**
     * 查询 SettleAccount
     *
     * @param settleCount_id 待查询的settleCount_id
     * @param app_id         app_id
     * @return 查询的settleCount 对象
     * @throws Exception 异常
     */
    public Map<String, Object> executeQuerySettleAccount(String merchantKey, String settleCount_id, String app_id, String member_id) throws Exception {
        System.out.println("=======execute query SettleAccount begin=======");
        Map<String, Object> settleCountParams = new HashMap<String, Object>(2);
        settleCountParams.put("settle_account_id", settleCount_id);
        settleCountParams.put("member_id", member_id);
        settleCountParams.put("app_id", app_id);
        System.out.println("查询结算账户，请求参数：" + JSON.toJSONString(settleCountParams));
        Map<String, Object> settleCount = SettleAccount.query(settleCountParams, merchantKey);
        System.out.println("查询结算账户，返回参数：" + JSON.toJSONString(settleCount));
        System.out.println("=======execute query SettleAccount end=======");

        return settleCount;
    }

    /**
     * 删除 SettleAccount
     *
     * @param settleCount_id 待删除的settleCount_id
     * @param app_id         app_id
     * @return delete的settleCount 对象
     * @throws Exception 异常
     */
    public Map<String, Object> executeDeleteSettleAccount(String merchantKey, String settleCount_id, String app_id, String member_id) throws Exception {
        System.out.println("=======execute delete SettleAccount begin=======");
        Map<String, Object> settleCountParams = new HashMap<String, Object>(2);
        settleCountParams.put("settle_account_id", settleCount_id);
        settleCountParams.put("member_id", member_id);
        settleCountParams.put("app_id", app_id);
        System.out.println("删除结算账户，请求参数：" + JSON.toJSONString(settleCountParams));
        Map<String, Object> settleCount = SettleAccount.delete(settleCountParams, merchantKey);
        System.out.println("删除结算账户，返回参数：" + JSON.toJSONString(settleCount));
        System.out.println("=======execute delete SettleAccount end=======");

        return settleCount;
    }

    /**
     * 查询结算明细列表
     *
     * @param merchantKey
     * @param app_id          app_id
     * @param member_id       待查询的member_id
     * @param settleAccountId 待查询的settleAccountId
     * @return
     * @throws Exception 异常
     */
    public Map<String, Object> executeQuerySettleDetails(String merchantKey, String app_id, String member_id,
                                                         String settleAccountId) throws Exception {
        System.out.println("=======execute query settle details begin=======");
        Map<String, Object> querySettleDetailParams = new HashMap<String, Object>(2);
        querySettleDetailParams.put("app_id", app_id);
        querySettleDetailParams.put("member_id", member_id);
        querySettleDetailParams.put("settle_account_id", settleAccountId);
        querySettleDetailParams.put("begin_date", "20191008");
        querySettleDetailParams.put("end_date", "20191010");
        System.out.println("查询结算明细列表，请求参数：" + JSON.toJSONString(querySettleDetailParams));
        Map<String, Object> settleCount = SettleAccount.detail(querySettleDetailParams, merchantKey);
        System.out.println("查询结算明细列表，返回参数：" + JSON.toJSONString(settleCount));
        System.out.println("=======execute query settle details end=======");

        return settleCount;
    }


    /**
     * 运行结算账户类接口
     *
     * @throws Exception 异常
     */
    public static void executeSettleAccountTest(String app_id, String member_id) throws Exception {
        SettleAccountDemo demo = new SettleAccountDemo();
        // 创建结算账户
        Map<String, Object> settlecount = demo.executeCreateSettleAccount(app_id, member_id);
        String settleCount_id = (String) settlecount.get("id");
        // 查询结算账户
        demo.executeQuerySettleAccount(settleCount_id, app_id, member_id);
        // 查询结算账户明细列表
        demo.executeQuerySettleDetails(app_id, member_id, settleCount_id);

        demo.executeTransfer(app_id, member_id);

        demo.executeTransferList(app_id, member_id, settleCount_id);

        demo.executeFreeze(app_id, member_id);

        demo.executeFreezeList(app_id, member_id, settleCount_id);

        demo.executeUnFreeze(app_id, member_id);

        demo.executeUnFreezeList(app_id, member_id, settleCount_id);

        demo.executeCommission(app_id, member_id);

        demo.executeCommissionList(app_id);
    }

    /**
     * 运行查询结算明细列表接口
     *
     * @throws Exception 异常
     */
//    public static void executeQuerySettleDetailTest( String appId, String memberId, String settleAccountId, String beginDate, String endDate) throws Exception {
//        SettleAccountDemo demo = new SettleAccountDemo();
//        demo.executeQuerySettleDetails( appId, memberId, settleAccountId, beginDate, endDate);
//    }


    /**
     * 创建 settleCount
     *
     * @return 创建的settleCount 对象
     * @throws Exception 异常
     */
    public Map<String, Object> executeCreateSettleAccount(String app_id, String member_id) throws Exception {
        System.out.println("=======execute Create SettleAccount begin=======");
        Map<String, Object> settleCountParams = new HashMap<String, Object>(2);
        Map<String, Object> accountInfo = new HashMap<String, Object>(2);
        accountInfo.put("card_id", "6222021703001692221");
        accountInfo.put("card_name", "袁电茜");
        accountInfo.put("cert_id", "310109200006062491");
        accountInfo.put("cert_type", "00");
        accountInfo.put("tel_no", "18888888881");
        accountInfo.put("bank_code", "03060000");
        accountInfo.put("bank_acct_type", "1");
        accountInfo.put("prov_code", "0031");
        accountInfo.put("area_code", "3100");
        settleCountParams.put("member_id", member_id);
        settleCountParams.put("app_id", app_id);
        settleCountParams.put("channel", "bank_account");
        settleCountParams.put("account_info", accountInfo);
        System.out.println("创建结算账户，请求参数：" + JSON.toJSONString(settleCountParams));
        Map<String, Object> settleCount = SettleAccount.create(settleCountParams);
        System.out.println("创建结算账户，返回参数：" + JSON.toJSONString(settleCount));
        System.out.println("=======execute Create SettleAccount end=======");

        return settleCount;

    }

    /**
     * 查询 SettleAccount
     *
     * @param settleCount_id 待查询的settleCount_id
     * @param app_id         app_id
     * @return 查询的settleCount 对象
     * @throws Exception 异常
     */
    public Map<String, Object> executeQuerySettleAccount(String settleCount_id, String app_id, String member_id) throws Exception {
        System.out.println("=======execute query SettleAccount begin=======");
        Map<String, Object> settleCountParams = new HashMap<String, Object>(2);
        settleCountParams.put("settle_account_id", settleCount_id);
        settleCountParams.put("member_id", member_id);
        settleCountParams.put("app_id", app_id);
        System.out.println("查询结算账户，请求参数：" + JSON.toJSONString(settleCountParams));
        Map<String, Object> settleCount = SettleAccount.query(settleCountParams);
        System.out.println("查询结算账户，返回参数：" + JSON.toJSONString(settleCount));
        System.out.println("=======execute query SettleAccount end=======");

        return settleCount;
    }

    /**
     * 修改 SettleAccount
     *
     * @param settleCount_id 待修改的settleCount_id
     * @param app_id         app_id
     * @return 修改的settleCount 对象
     * @throws Exception 异常
     */
    public Map<String, Object> executeModifySettleAccount(String settleCount_id, String app_id, String member_id) throws Exception {
        System.out.println("=======execute modify SettleAccount begin=======");
        Map<String, Object> settleCountParams = new HashMap<String, Object>(2);
        settleCountParams.put("settle_account_id", settleCount_id);
        settleCountParams.put("member_id", member_id);
        settleCountParams.put("app_id", app_id);

        settleCountParams.put("min_amt", "0.10");
        settleCountParams.put("remained_amt", "0.10");
        System.out.println("修改结算账户，请求参数：" + JSON.toJSONString(settleCountParams));
        Map<String, Object> settleCount = SettleAccount.update(settleCountParams);
        System.out.println("修改结算账户，返回参数：" + JSON.toJSONString(settleCount));
        System.out.println("=======execute modify SettleAccount end=======");

        return settleCount;
    }

    /**
     * 修改 SettleAccount
     *
     * @param settleCount_id 待修改的settleCount_id
     * @param app_id         app_id
     * @return 修改的settleCount 对象
     * @throws Exception 异常
     */
    public Map<String, Object> executeModifySettleAccount(String merchantKey, String settleCount_id, String app_id, String member_id) throws Exception {
        System.out.println("=======execute modify SettleAccount begin=======");
        Map<String, Object> settleCountParams = new HashMap<String, Object>(2);
        settleCountParams.put("settle_account_id", settleCount_id);
        settleCountParams.put("member_id", member_id);
        settleCountParams.put("app_id", app_id);
        settleCountParams.put("min_amt", "");
        settleCountParams.put("remained_amt", "");

        System.out.println("修改结算账户，请求参数：" + JSON.toJSONString(settleCountParams));
        Map<String, Object> settleCount = SettleAccount.modify(settleCountParams, merchantKey);
        System.out.println("修改结算账户，返回参数：" + JSON.toJSONString(settleCount));
        System.out.println("=======execute modify SettleAccount end=======");

        return settleCount;
    }


    /**
     * 删除 SettleAccount
     *
     * @param settleCount_id 待删除的settleCount_id
     * @param app_id         app_id
     * @return delete的settleCount 对象
     * @throws Exception 异常
     */
    public Map<String, Object> executeDeleteSettleAccount(String settleCount_id, String app_id, String member_id) throws Exception {
        System.out.println("=======execute delete SettleAccount begin=======");
        Map<String, Object> settleCountParams = new HashMap<String, Object>(2);
        settleCountParams.put("settle_account_id", settleCount_id);
        settleCountParams.put("member_id", member_id);
        settleCountParams.put("app_id", app_id);
        System.out.println("删除结算账户，请求参数：" + JSON.toJSONString(settleCountParams));
        Map<String, Object> settleCount = SettleAccount.delete(settleCountParams);
        System.out.println("删除结算账户，返回参数：" + JSON.toJSONString(settleCount));
        System.out.println("=======execute delete SettleAccount end=======");

        return settleCount;
    }

    /**
     * 查询结算明细列表
     *
     * @param app_id          app_id
     * @param member_id       待查询的member_id
     * @param settleAccountId 待查询的settleAccountId
     * @return
     * @throws Exception 异常
     */
    public Map<String, Object> executeQuerySettleDetails(String app_id, String member_id,
                                                         String settleAccountId) throws Exception {
        System.out.println("=======execute query settle details begin=======");
        Map<String, Object> querySettleDetailParams = new HashMap<String, Object>(2);
        querySettleDetailParams.put("app_id", app_id);
        querySettleDetailParams.put("member_id", member_id);
        querySettleDetailParams.put("settle_account_id", settleAccountId);
        querySettleDetailParams.put("begin_date", "20191008");
        querySettleDetailParams.put("end_date", "20191010");
        System.out.println("查询结算明细列表，请求参数：" + JSON.toJSONString(querySettleDetailParams));
        Map<String, Object> settleCount = SettleAccount.detail(querySettleDetailParams);
        System.out.println("查询结算明细列表，返回参数：" + JSON.toJSONString(settleCount));
        System.out.println("=======execute query settle details end=======");

        return settleCount;
    }

    /**
     * 取现
     *
     * @param merchantKey
     * @param app_id
     * @param member_id
     * @return
     * @throws Exception
     */
    public static Map<String, Object> executeDrawCash(String merchantKey, String app_id, String member_id) throws Exception {
        System.out.println("=======execute modify SettleAccount begin=======");
        Map<String, Object> settleCountParams = new HashMap<String, Object>();

        settleCountParams.put("order_no", "jsdk_payment_" + System.currentTimeMillis());
        settleCountParams.put("cash_amt", "0.01");
        settleCountParams.put("member_id", "user_00008");
        settleCountParams.put("app_id", app_id);
        // settleCountParams.put("settle_account_id", "0008919797515968");
        settleCountParams.put("cash_type", "T1");
        settleCountParams.put("notify_url", "");

        System.out.println("取现接口，请求参数：" + JSON.toJSONString(settleCountParams));
        Map<String, Object> settleCount = Drawcash.create(settleCountParams, merchantKey);
        System.out.println("取现接口返回参数" + JSON.toJSONString(settleCount));
        System.out.println("=======execute modify SettleAccount end=======");

        return settleCount;
    }

    /**
     * 查询余额
     *
     * @param merchantKey
     * @param app_id
     * @param member_id
     * @param settleCount_id
     * @return
     * @throws Exception
     */
    public static Map<String, Object> executeQueryBalance(String merchantKey, String app_id, String member_id, String settleCount_id) throws Exception {
        System.out.println("=======execute modify SettleAccount begin=======");
        Map<String, Object> settleCountParams = new HashMap<String, Object>();

        settleCountParams.put("app_id", app_id);
        settleCountParams.put("member_id", "user_00008");
        settleCountParams.put("settle_account_id", "0035172521665088");


        System.out.println("查询余额账户，请求参数：" + JSON.toJSONString(settleCountParams));
        Map<String, Object> settleCount = SettleAccount.balance(settleCountParams, merchantKey);
        System.out.println("查询余额账户，返回参数：" + JSON.toJSONString(settleCount));
        System.out.println("=======execute modify SettleAccount end=======");

        return settleCount;
    }

    /**
     * 取现
     *
     * @param app_id
     * @param member_id
     * @return
     * @throws Exception
     */
    public static Map<String, Object> executeDrawCash(String app_id, String member_id) throws Exception {
        System.out.println("=======execute modify SettleAccount begin=======");
        Map<String, Object> settleCountParams = new HashMap<String, Object>();

        settleCountParams.put("order_no", "jsdk_payment_" + System.currentTimeMillis());
        settleCountParams.put("cash_amt", "0.01");
        settleCountParams.put("member_id", "user_00008");
        settleCountParams.put("app_id", app_id);
        // settleCountParams.put("settle_account_id", "0008919797515968");
        settleCountParams.put("cash_type", "T1");
        settleCountParams.put("notify_url", "");

        System.out.println("取现接口，请求参数：" + JSON.toJSONString(settleCountParams));
        Map<String, Object> settleCount = Drawcash.create(settleCountParams);
        System.out.println("取现接口返回参数" + JSON.toJSONString(settleCount));
        System.out.println("=======execute modify SettleAccount end=======");

        return settleCount;
    }

    /**
     * 查询余额
     *
     * @param app_id
     * @param member_id
     * @param settleCount_id
     * @return
     * @throws Exception
     */
    public static Map<String, Object> executeQueryBalance(String app_id, String member_id, String settleCount_id) throws Exception {
        System.out.println("=======execute modify SettleAccount begin=======");
        Map<String, Object> settleCountParams = new HashMap<String, Object>();

        settleCountParams.put("app_id", app_id);
        settleCountParams.put("member_id", "user_00008");
        settleCountParams.put("settle_account_id", "0035172521665088");


        System.out.println("查询余额账户，请求参数：" + JSON.toJSONString(settleCountParams));
        Map<String, Object> settleCount = SettleAccount.balance(settleCountParams);
        System.out.println("查询余额账户，返回参数：" + JSON.toJSONString(settleCount));
        System.out.println("=======execute modify SettleAccount end=======");

        return settleCount;
    }

    /**
     * 创建转账对象
     *
     * @param app_id
     * @param member_id
     * @return
     * @throws Exception
     */
    public Map<String, Object> executeTransfer(String app_id, String member_id) throws Exception {
        System.out.println("=======execute transfer SettleAccount begin=======");
        Map<String, Object> transferParams = new HashMap<>();
        transferParams.put("order_no", "order_no_123");
        transferParams.put("trans_amt", "0.01");
        transferParams.put("app_id", "app_XXXXXXXX");
        transferParams.put("in_member_id", "0");
        transferParams.put("out_member_id", "user_member_id_test");
        System.out.println("创建转账对象，请求参数：" + JSON.toJSONString(transferParams));

        Map<String, Object> result = SettleAccount.transfer(transferParams);
        System.out.println("创建转账对象，返回参数：" + JSON.toJSONString(result));

        System.out.println("=======execute transfer SettleAccount end=======");

        return result;
    }

    /**
     * 创建转账对象
     *
     * @param app_id
     * @param member_id
     * @return
     * @throws Exception
     */
    public Map<String, Object> executeTransfer(String merchantKey, String app_id, String member_id) throws Exception {
        System.out.println("=======execute transfer SettleAccount begin=======");
        Map<String, Object> transferParams = new HashMap<>();
        transferParams.put("order_no", "order_no_124");
        transferParams.put("trans_amt", "0.01");
        transferParams.put("app_id", "app_XXXXXXXX");
        transferParams.put("in_member_id", "0");
        transferParams.put("out_member_id", "user_member_id_test");
        System.out.println("创建转账对象，请求参数：" + JSON.toJSONString(transferParams));

        Map<String, Object> result = SettleAccount.transfer(transferParams);
        System.out.println("创建转账对象，返回参数：" + JSON.toJSONString(result));

        System.out.println("=======execute transfer SettleAccount end=======");

        return result;
    }

    /**
     * 查询账户转账对象列表
     *
     * @param app_id
     * @param member_id
     * @param settleCount_id
     * @return
     * @throws Exception
     */
    public Map<String, Object> executeTransferList(String app_id, String member_id, String settleCount_id) throws Exception {
        System.out.println("=======execute query transferList begin=======");
        Map<String, Object> transferListParams = new HashMap<String, Object>();

        transferListParams.put("app_id", "app_XXXXXXXX");
        transferListParams.put("status", "succeeded");
        transferListParams.put("page_index", "1");
        transferListParams.put("page_size", "10");
        transferListParams.put("created_gte", "1571466657929");
        transferListParams.put("created_lte", "1571898657929");

        System.out.println("查询账户转账对象列表，请求参数：" + JSON.toJSONString(transferListParams));
        Map<String, Object> result = SettleAccount.transferList(transferListParams);
        System.out.println("查询账户转账对象列表，返回参数：" + JSON.toJSONString(result));
        System.out.println("=======execute query transferList end=======");

        return result;
    }

    /**
     * 查询账户转账对象列表
     *
     * @param app_id
     * @param member_id
     * @param settleCount_id
     * @return
     * @throws Exception
     */
    public Map<String, Object> executeTransferList(String merchantKey, String app_id, String member_id, String settleCount_id) throws Exception {
        System.out.println("=======execute query transferList begin=======");
        Map<String, Object> transferListParams = new HashMap<String, Object>();

        transferListParams.put("app_id", "app_XXXXXXXX");
        transferListParams.put("status", "succeeded");
        transferListParams.put("page_index", "1");
        transferListParams.put("page_size", "10");
        transferListParams.put("created_gte", "1571466657928");
        transferListParams.put("created_lte", "1571898657928");

        System.out.println("查询账户转账对象列表，请求参数：" + JSON.toJSONString(transferListParams));
        Map<String, Object> result = SettleAccount.transferList(transferListParams);
        System.out.println("查询账户转账对象列表，返回参数：" + JSON.toJSONString(result));
        System.out.println("=======execute query transferList end=======");

        return result;
    }

    /**
     * 创建冻结对象
     *
     * @param app_id
     * @param member_id
     * @return
     * @throws Exception
     */
    public Map<String, Object> executeFreeze(String app_id, String member_id) throws Exception {
        System.out.println("=======execute freeze SettleAccount begin=======");
        Map<String, Object> freezeParam = new HashMap<String, Object>(4);
        freezeParam.put("order_no", "1579163031383");
        freezeParam.put("app_id", "app_XXXXXXXX");
        freezeParam.put("account_freeze_id", "002112020111717230410174704123849117696");
        Map<String, Object> result = SettleAccount.freeze(freezeParam);
        System.out.println("创建冻结对象，返回参数：" + JSON.toJSONString(result));

        System.out.println("=======execute freeze SettleAccount end=======");

        return result;
    }

    /**
     * 创建冻结对象
     *
     * @param app_id
     * @param member_id
     * @return
     * @throws Exception
     */
    public Map<String, Object> executeFreeze(String merchantKey, String app_id, String member_id) throws Exception {
        System.out.println("=======execute freeze SettleAccount begin=======");
        Map<String, Object> freezeParam = new HashMap<String, Object>(4);
        freezeParam.put("order_no", "1579163031384");
        freezeParam.put("app_id", "app_XXXXXXXX");
        freezeParam.put("account_freeze_id", "002112020111717230410174704123849117696");
        Map<String, Object> result = SettleAccount.freeze(freezeParam);
        System.out.println("创建冻结对象，返回参数：" + JSON.toJSONString(result));

        System.out.println("=======execute freeze SettleAccount end=======");
        return result;
    }

    /**
     * 查询账户冻结对象列表
     *
     * @param app_id
     * @param member_id
     * @param settleCount_id
     * @return
     * @throws Exception
     */
    public Map<String, Object> executeFreezeList(String app_id, String member_id, String settleCount_id) throws Exception {
        System.out.println("=======execute query freezeList begin=======");
        Map<String, Object> params = new HashMap<>();
        System.out.println("查询账户冻结对象列表，请求参数：" + JSON.toJSONString(params));
        params.put("app_id", "app_XXXXXXXX");
        params.put("status", "succeeded");
        params.put("page_index", "1");
        params.put("page_size", "10");
        params.put("created_gte", "1571466657929");
        params.put("created_lte", "1571898657929");
        Map<String, Object> result = SettleAccount.freezeList(params);
        System.out.println("查询账户冻结对象列表，返回参数：" + JSON.toJSONString(result));
        System.out.println("=======execute query freezeList end=======");
        return result;
    }

    /**
     * 查询账户冻结对象列表
     *
     * @param app_id
     * @param member_id
     * @param settleCount_id
     * @return
     * @throws Exception
     */
    public Map<String, Object> executeFreezeList(String merchantKey, String app_id, String member_id, String settleCount_id) throws Exception {
        System.out.println("=======execute query freezeList begin=======");
        Map<String, Object> params = new HashMap<>();
        System.out.println("查询账户冻结对象列表，请求参数：" + JSON.toJSONString(params));
        params.put("app_id", "app_XXXXXXXX");
        params.put("status", "succeeded");
        params.put("page_index", "1");
        params.put("page_size", "10");
        params.put("created_gte", "1571466657928");
        params.put("created_lte", "1571898657928");
        Map<String, Object> result = SettleAccount.freezeList(params);
        System.out.println("查询账户冻结对象列表，返回参数：" + JSON.toJSONString(result));
        System.out.println("=======execute query freezeList end=======");
        return result;
    }

    /**
     * 创建解冻对象
     *
     * @param app_id
     * @param member_id
     * @return
     * @throws Exception
     */
    public Map<String, Object> executeUnFreeze(String app_id, String member_id) throws Exception {
        System.out.println("=======execute unfreeze SettleAccount begin=======");
        Map<String, Object> unfreezeParam = new HashMap<String, Object>(4);
        unfreezeParam.put("order_no", "1579163031383");
        unfreezeParam.put("app_id", "app_XXXXXXXX");
        unfreezeParam.put("account_freeze_id", "002112020111717230410174704123849117696");
        Map<String, Object> result = SettleAccount.unfreeze(unfreezeParam);
        System.out.println("创建解冻对象，返回参数：" + JSON.toJSONString(result));

        System.out.println("=======execute unfreeze SettleAccount end=======");

        return result;
    }

    /**
     * 创建解冻对象
     *
     * @param app_id
     * @param member_id
     * @return
     * @throws Exception
     */
    public Map<String, Object> executeUnFreeze(String merchantKey, String app_id, String member_id) throws Exception {
        System.out.println("=======execute unfreeze SettleAccount begin=======");
        Map<String, Object> unfreezeParam = new HashMap<String, Object>(4);
        unfreezeParam.put("order_no", "1579163031384");
        unfreezeParam.put("app_id", "app_XXXXXXXX");
        unfreezeParam.put("account_freeze_id", "002112020111717230410174704123849117696");
        Map<String, Object> result = SettleAccount.unfreeze(unfreezeParam);
        System.out.println("创建解冻对象，返回参数：" + JSON.toJSONString(result));

        System.out.println("=======execute unfreeze SettleAccount end=======");

        return result;
    }

    /**
     * 查询账户解冻对象列表
     *
     * @param app_id
     * @param member_id
     * @param settleCount_id
     * @return
     * @throws Exception
     */
    public Map<String, Object> executeUnFreezeList(String app_id, String member_id, String settleCount_id) throws Exception {
        System.out.println("=======execute query unfreezeList begin=======");
        Map<String, Object> params = new HashMap<>();
        System.out.println("查询账户解冻对象列表，请求参数：" + JSON.toJSONString(params));
        params.put("app_id", "app_XXXXXXXX");
        params.put("status", "succeeded");
        params.put("page_index", "1");
        params.put("page_size", "10");
        params.put("created_gte", "1571466657928");
        params.put("created_lte", "1571898657928");
        Map<String, Object> result = SettleAccount.unfreezeList(params);
        System.out.println("查询账户解冻对象列表，返回参数：" + JSON.toJSONString(result));
        System.out.println("=======execute query unfreezeList end=======");
        return result;
    }

    /**
     * 查询账户解冻对象列表
     *
     * @param app_id
     * @param member_id
     * @param settleCount_id
     * @return
     * @throws Exception
     */
    public Map<String, Object> executeUnFreezeList(String merchantKey, String app_id, String member_id, String settleCount_id) throws Exception {
        System.out.println("=======execute query unfreezeList begin=======");
        Map<String, Object> params = new HashMap<>();
        System.out.println("查询账户解冻对象列表，请求参数：" + JSON.toJSONString(params));
        params.put("app_id", "app_XXXXXXXX");
        params.put("status", "succeeded");
        params.put("page_index", "1");
        params.put("page_size", "10");
        params.put("created_gte", "1571466657929");
        params.put("created_lte", "1571898657929");
        Map<String, Object> result = SettleAccount.unfreezeList(params);
        System.out.println("查询账户解冻对象列表，返回参数：" + JSON.toJSONString(result));
        System.out.println("=======execute query unfreezeList end=======");
        return result;
    }

    /**
     * 创建服务商分账对象
     *
     * @param app_id
     * @return
     * @throws Exception
     */
    public Map<String, Object> executeCommission(String app_id, String member_id) throws Exception {
        System.out.println("=======execute commissions SettleAccount begin=======");
        Map<String, Object> params = new HashMap<String, Object>(4);
        params.put("order_no", "commissions_10414212480035000003");
        params.put("payment_id", "002112021011909284110197415174743904256");
        params.put("trans_amt", "0.01");
        System.out.println("创建服务商分账对象，请求参数：" + JSON.toJSONString(params));
        Map<String, Object> result = SettleAccount.commission(params);
        System.out.println("创建服务商分账对象，返回参数：" + JSON.toJSONString(result));
        System.out.println("=======execute commissions SettleAccount end=======");
        return result;
    }

    /**
     * 创建服务商分账对象
     *
     * @param app_id
     * @return
     * @throws Exception
     */
    public Map<String, Object> executeCommission(String merchantKey, String app_id, String member_id) throws Exception {
        System.out.println("=======execute commissions SettleAccount begin=======");
        Map<String, Object> params = new HashMap<String, Object>(4);
        params.put("order_no", "1579163031383");
        params.put("payment_id", "002112020111717230410174704123849117696");
        params.put("trans_amt", "0.01");
        System.out.println("创建服务商分账对象，请求参数：" + JSON.toJSONString(params));
        Map<String, Object> result = SettleAccount.commission(params, merchantKey);
        System.out.println("创建服务商分账对象，返回参数：" + JSON.toJSONString(result));
        System.out.println("=======execute commissions SettleAccount end=======");
        return result;
    }

    /**
     * 查询服务商分账对象列表
     *
     * @param app_id
     * @return
     * @throws Exception
     */
    public Map<String, Object> executeCommissionList(String app_id) throws Exception {
        System.out.println("=======execute query unfreezeList begin=======");
        Map<String, Object> params = new HashMap<>();
        params.put("app_id", app_id);
        params.put("status", "succeeded");
        params.put("page_index", "1");
        params.put("page_size", "10");
        params.put("created_gte", "1571466657929");
        params.put("created_lte", "1571898657929");
        System.out.println("查询服务商分账对象列表，请求参数：" + JSON.toJSONString(params));
        Map<String, Object> result = SettleAccount.commissionList(params);
        System.out.println("查询服务商分账对象列表，返回参数：" + JSON.toJSONString(result));
        System.out.println("=======execute query unfreezeList end=======");
        return result;
    }

    /**
     * 查询服务商分账对象列表
     *
     * @param app_id
     * @return
     * @throws Exception
     */
    public Map<String, Object> executeCommissionList(String merchantKey, String app_id) throws Exception {
        System.out.println("=======execute query unfreezeList begin=======");
        Map<String, Object> params = new HashMap<>();
        params.put("app_id", app_id);
        params.put("status", "succeeded");
        params.put("page_index", "1");
        params.put("page_size", "10");
        params.put("created_gte", "1571466657929");
        params.put("created_lte", "1571898657929");
        System.out.println("查询服务商分账对象列表，请求参数：" + JSON.toJSONString(params));
        Map<String, Object> result = SettleAccount.commissionList(params, merchantKey);
        System.out.println("查询服务商分账对象列表，返回参数：" + JSON.toJSONString(result));
        System.out.println("=======execute query unfreezeList end=======");
        return result;
    }
}
