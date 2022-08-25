package com.shitouren.core.PayDemo;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.huifu.adapay.model.Member;


/**
 * @author yingyong.wang
 */
public class MemberDemo {

    /**
     * 运行 member 类接口
     *
     * @throws Exception 异常
     */
    public static Map<String, Object> executeMemberTest(String merchantKey, String app_id) throws Exception {
        MemberDemo demo = new MemberDemo();
        Map<String, Object> member = demo.executeCreateMember(merchantKey, app_id);
        demo.executeQueryMember(merchantKey, (String) member.get("member_id"), app_id);
        demo.executeUpdateMember(merchantKey, (String) member.get("member_id"), app_id);
        demo.executeQueryMember(merchantKey, (String) member.get("member_id"), app_id);
        demo.executeListMember(merchantKey, app_id);
        return member;
    }


    /**
     * 创建 member
     *
     * @return 创建的member 对象
     * @throws Exception 异常
     */
    public Map<String, Object> executeCreateMember(String merchantKey, String app_id) throws Exception {
        System.out.println("=======execute CreateMember begin=======");
        Map<String, Object> memberParams = new HashMap<String, Object>(2);
        memberParams.put("member_id", "jsdk_member_" + System.currentTimeMillis());
        memberParams.put("app_id", app_id);
        memberParams.put("location", "上海市徐汇区宜山路");
        memberParams.put("email", "123@163.com");
        memberParams.put("gender", "MALE");
        memberParams.put("tel_no", "13153333333");
        memberParams.put("nickname", "nick_name");
        System.out.println("创建用户，请求参数：" + JSON.toJSONString(memberParams));
        Map<String, Object> member = Member.create(memberParams, merchantKey);
        System.out.println("创建用户，返回参数：" + JSON.toJSONString(member));
        System.out.println("=======execute CreateMember end=======");

        return member;

    }

    /**
     * 查询 member
     *
     * @param member_id 待查询的member_id
     * @return 创建的member 对象
     * @throws Exception 异常
     */
    public Map<String, Object> executeQueryMember(String merchantKey, String member_id, String app_id) throws Exception {
        System.out.println("=======execute queryMember begin=======");
        Map<String, Object> memberParams = new HashMap<String, Object>(2);
        memberParams.put("member_id", member_id);
        memberParams.put("app_id", app_id);
        System.out.println("查询用户，请求参数：" + JSON.toJSONString(memberParams));
        Map<String, Object> member = Member.query(memberParams, merchantKey);
        System.out.println("查询用户，返回参数：" + JSON.toJSONString(member));


        System.out.println("=======execute queryMember end=======");

        return member;

    }


    /**
     * 更新 member
     *
     * @param member_id 待更新的member_id
     * @return 更新的member 对象
     * @throws Exception 异常
     */
    public Map<String, Object> executeUpdateMember(String merchantKey, String member_id, String app_id) throws Exception {
        System.out.println("=======execute update Member begin=======");
        Map<String, Object> memberParams = new HashMap<String, Object>(2);
        memberParams.put("member_id", member_id);
        memberParams.put("app_id", app_id);
        memberParams.put("location", "上海市徐汇区宜山路1");
        memberParams.put("email", "1234@163.com");
        memberParams.put("gender", "MALE");
        memberParams.put("tel_no", "13153333333");
        memberParams.put("nickname", "nick_name2");

        System.out.println("更新用户，请求参数：" + JSON.toJSONString(memberParams));
        Map<String, Object> member = Member.update(memberParams, merchantKey);
        System.out.println("更新用户，返回参数：" + JSON.toJSONString(member));

        System.out.println("=======execute update Member end=======");

        return member;

    }

    /**
     * 查询 member list
     *
     * @param app_id app_id
     * @return 查询的member list
     * @throws Exception 异常
     */
    public Map<String, Object> executeListMember(String merchantKey, String app_id) throws Exception {
        System.out.println("=======execute list Member begin=======");
        Map<String, Object> memberParams = new HashMap<String, Object>(2);
        memberParams.put("page_index", "1");
        memberParams.put("app_id", app_id);
        memberParams.put("page_size", "20");
        memberParams.put("created_gte", String.valueOf(System.currentTimeMillis() - 5 * 60 * 1000));
        memberParams.put("created_lte", String.valueOf(System.currentTimeMillis()));
        System.out.println("查询用户列表，请求参数：" + JSON.toJSONString(memberParams));
        Map<String, Object> member = Member.queryList(memberParams, merchantKey);
        System.out.println("查询用户列表，返回参数：" + JSON.toJSONString(member));


        System.out.println("=======execute list Member end=======");

        return member;

    }

    /**
     * 运行 member 类接口
     *
     * @throws Exception 异常
     */
    public static Map<String, Object> executeMemberTest(String app_id) throws Exception {
        MemberDemo demo = new MemberDemo();
        Map<String, Object> member = demo.executeCreateMember(app_id);
        demo.executeQueryMember((String) member.get("member_id"), app_id);
        demo.executeUpdateMember((String) member.get("member_id"), app_id);
        demo.executeQueryMember((String) member.get("member_id"), app_id);
        demo.executeListMember(app_id);
        return member;
    }


    /**
     * 创建 member
     *
     * @return 创建的member 对象
     * @throws Exception 异常
     */
    public Map<String, Object> executeCreateMember(String app_id) throws Exception {
        System.out.println("=======execute CreateMember begin=======");
        Map<String, Object> memberParams = new HashMap<String, Object>(2);
        memberParams.put("member_id", "jsdk_member_" + System.currentTimeMillis());
        memberParams.put("app_id", app_id);
        memberParams.put("location", "上海市徐汇区宜山路");
        memberParams.put("email", "123@163.com");
        memberParams.put("gender", "MALE");
        memberParams.put("tel_no", "13153333333");
        memberParams.put("nickname", "nick_name");
        System.out.println("创建用户，请求参数：" + JSON.toJSONString(memberParams));
        Map<String, Object> member = Member.create(memberParams);
        System.out.println("创建用户，返回参数：" + JSON.toJSONString(member));
        System.out.println("=======execute CreateMember end=======");

        return member;

    }

    /**
     * 查询 member
     *
     * @param member_id 待查询的member_id
     * @return 创建的member 对象
     * @throws Exception 异常
     */
    public Map<String, Object> executeQueryMember(String member_id, String app_id) throws Exception {
        System.out.println("=======execute queryMember begin=======");
        Map<String, Object> memberParams = new HashMap<String, Object>(2);
        memberParams.put("member_id", member_id);
        memberParams.put("app_id", app_id);
        System.out.println("查询用户，请求参数：" + JSON.toJSONString(memberParams));
        Map<String, Object> member = Member.query(memberParams);
        System.out.println("查询用户，返回参数：" + JSON.toJSONString(member));


        System.out.println("=======execute queryMember end=======");

        return member;

    }


    /**
     * 更新 member
     *
     * @param member_id 待更新的member_id
     * @return 更新的member 对象
     * @throws Exception 异常
     */
    public Map<String, Object> executeUpdateMember(String member_id, String app_id) throws Exception {
        System.out.println("=======execute update Member begin=======");
        Map<String, Object> memberParams = new HashMap<String, Object>(2);
        memberParams.put("member_id", member_id);
        memberParams.put("app_id", app_id);
        memberParams.put("location", "上海市徐汇区宜山路1");
        memberParams.put("email", "1234@163.com");
        memberParams.put("gender", "MALE");
        memberParams.put("tel_no", "13153333333");
        memberParams.put("nickname", "nick_name2");

        System.out.println("更新用户，请求参数：" + JSON.toJSONString(memberParams));
        Map<String, Object> member = Member.update(memberParams);
        System.out.println("更新用户，返回参数：" + JSON.toJSONString(member));

        System.out.println("=======execute update Member end=======");

        return member;

    }

    /**
     * 查询 member list
     *
     * @param app_id app_id
     * @return 查询的member list
     * @throws Exception 异常
     */
    public Map<String, Object> executeListMember(String app_id) throws Exception {
        System.out.println("=======execute list Member begin=======");
        Map<String, Object> memberParams = new HashMap<String, Object>(2);
        memberParams.put("page_index", "1");
        memberParams.put("app_id", app_id);
        memberParams.put("page_size", "20");
        memberParams.put("created_gte", String.valueOf(System.currentTimeMillis() - 5 * 60 * 1000));
        memberParams.put("created_lte", String.valueOf(System.currentTimeMillis()));
        System.out.println("查询用户列表，请求参数：" + JSON.toJSONString(memberParams));
        Map<String, Object> member = Member.queryList(memberParams);
        System.out.println("查询用户列表，返回参数：" + JSON.toJSONString(member));


        System.out.println("=======execute list Member end=======");

        return member;

    }


}
