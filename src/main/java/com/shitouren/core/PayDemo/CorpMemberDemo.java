package com.shitouren.core.PayDemo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.huifu.adapay.model.CorpMember;

/**
 * @author yingyong.wang
 */
public class CorpMemberDemo {

    /**
     * 运行 CorpMember 类接口
     *
     * @throws Exception 异常
     */
    public static void executeCorpMemberTest(String merchantKey, String app_id) throws Exception {
        CorpMemberDemo demo = new CorpMemberDemo();
        Map<String, Object> member = demo.executeCreateMember(merchantKey, app_id);
        demo.executeQueryMember(merchantKey, (String) member.get("member_id"), app_id);

    }


    /**
     * 创建 CorpMember
     *
     * @return 创建的CorpMember 对象
     * @throws Exception 异常
     */
    public Map<String, Object> executeCreateMember(String merchantKey, String app_id) throws Exception {
        System.out.println("=======execute Create CorpMember begin=======");
        Map<String, Object> memberParams = new HashMap<String, Object>(2);
        memberParams.put("member_id", "jsdk_member_" + System.currentTimeMillis());
        memberParams.put("app_id", app_id);
        memberParams.put("order_no", "jsdk_order_" + System.currentTimeMillis());
        memberParams.put("social_credit_code_expires", "1111");
        memberParams.put("business_scope", "123");

        memberParams.put("name", "中国测试有限公司");
        memberParams.put("prov_code", "0011");
        memberParams.put("area_code", "1100");
        memberParams.put("social_credit_code", "201932658452655");
        memberParams.put("legal_person", "张测试");
        memberParams.put("legal_cert_id", "321485199014234852");
        memberParams.put("legal_cert_id_expires", "20220112");
        memberParams.put("legal_mp", "13958465215");
        memberParams.put("address", "中国上海");
        memberParams.put("zip_code", "225485");
        memberParams.put("telphone", "41164452");
        memberParams.put("email", "ceshi@qq.com");
        memberParams.put("bank_code", "652142");
        memberParams.put("bank_acct_type", "1");
        memberParams.put("card_no", "622546895642156");
        memberParams.put("card_name", "中国测试有限公司");
        File file = new File("/Users/will/Project/Adapay/AdapayJava/AdapayDemo/src/main/java/com/huifu/adapay/demo/归档.zip");

        System.out.println("创建企业用户，请求参数：" + JSON.toJSONString(memberParams));
        Map<String, Object> member = CorpMember.create(memberParams, file, merchantKey);
        System.out.println("创建企业用户，返回参数：" + JSON.toJSONString(member));


        System.out.println("=======execute Create CorpMember end=======");

        return member;

    }

    /**
     * 查询 CorpMember
     *
     * @param member_id 待查询的member_id
     * @return 查询的 CorpMember 对象
     * @throws Exception 异常
     */
    public Map<String, Object> executeQueryMember(String merchantKey, String member_id, String app_id) throws Exception {
        System.out.println("=======execute query CorpMember begin=======");
        Map<String, Object> memberParams = new HashMap<String, Object>(2);
        memberParams.put("member_id", member_id);
        memberParams.put("app_id", app_id);
        System.out.println("查询企业用户，请求参数：" + JSON.toJSONString(memberParams));
        Map<String, Object> member = CorpMember.query(memberParams, merchantKey);
        System.out.println("查询企业用户，返回参数：" + JSON.toJSONString(member));

        System.out.println("=======execute query CorpMember end=======");

        return member;
    }

    /**
     * 运行 CorpMember 类接口
     *
     * @throws Exception 异常
     */
    public static void executeCorpMemberTest(String app_id) throws Exception {
        CorpMemberDemo demo = new CorpMemberDemo();
        Map<String, Object> member = demo.executeCreateMember(app_id);
        demo.executeQueryMember((String) member.get("member_id"), app_id);

    }


    /**
     * 创建 CorpMember
     *
     * @return 创建的CorpMember 对象
     * @throws Exception 异常
     */
    public Map<String, Object> executeCreateMember(String app_id) throws Exception {
        System.out.println("=======execute Create CorpMember begin=======");
        Map<String, Object> memberParams = new HashMap<String, Object>(2);
        memberParams.put("member_id", "jsdk_member_" + System.currentTimeMillis());
        memberParams.put("app_id", app_id);
        memberParams.put("order_no", "jsdk_order_" + System.currentTimeMillis());
        memberParams.put("social_credit_code_expires", "1111");
        memberParams.put("business_scope", "123");

        memberParams.put("name", "中国测试有限公司");
        memberParams.put("prov_code", "0011");
        memberParams.put("area_code", "1100");
        memberParams.put("social_credit_code", "201932658452655");
        memberParams.put("legal_person", "张测试");
        memberParams.put("legal_cert_id", "321485199014234852");
        memberParams.put("legal_cert_id_expires", "20220112");
        memberParams.put("legal_mp", "13958465215");
        memberParams.put("address", "中国上海");
        memberParams.put("zip_code", "225485");
        memberParams.put("telphone", "41164452");
        memberParams.put("email", "ceshi@qq.com");
        memberParams.put("bank_code", "652142");
        memberParams.put("bank_acct_type", "1");
        memberParams.put("card_no", "622546895642156");
        memberParams.put("card_name", "中国测试有限公司");
        String path = CorpMemberDemo.class.getClassLoader().getResource("").getPath() + "test.zip";

        File file = new File(path);
        System.out.println("创建企业用户，请求参数：" + JSON.toJSONString(memberParams));
        Map<String, Object> member = CorpMember.create(memberParams, file);
        System.out.println("创建企业用户，返回参数：" + JSON.toJSONString(member));


        System.out.println("=======execute Create CorpMember end=======");

        return member;

    }

    /**
     * 查询 CorpMember
     *
     * @param member_id 待查询的member_id
     * @return 查询的 CorpMember 对象
     * @throws Exception 异常
     */
    public Map<String, Object> executeQueryMember(String member_id, String app_id) throws Exception {
        System.out.println("=======execute query CorpMember begin=======");
        Map<String, Object> memberParams = new HashMap<String, Object>(2);
        memberParams.put("member_id", member_id);
        memberParams.put("app_id", app_id);
        System.out.println("查询企业用户，请求参数1：" + JSON.toJSONString(memberParams));
        Map<String, Object> member = CorpMember.query(memberParams);
        System.out.println("查询企业用户，返回参数：" + JSON.toJSONString(member));

        System.out.println("=======execute query CorpMember end=======");

        return member;
    }


}
