package com.shitouren.core.bean.eums;

/**
 * prosumer相关枚举类
 *
 * @Autho： 王涛
 * @DATE： 2018/6/4 11:35
 */
public class EumUser {


    /**
     * 账号状态： 0 = 启用。 1 = 禁用
     */
    public enum UserStatus {
        启用(0),
        禁用(1),
        ;
        private final Integer value;

        UserStatus(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * 手机验证码类型
     * redis数据库key的前缀
     */
    public enum CellVerifyCodeType {
        注册("reg_code_"),
        忘记密码("forget_pwd_code_"),
        校验手机号("check_code_"),
        交易密码("update_trade_pwd_");

        private final String value;

        CellVerifyCodeType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 是否激活会员：0 = 未激活，1 = 已激活
     */
    public enum activationFlag {
        未激活(0),
        已激活(1),
        ;
        private final Integer value;

        activationFlag(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * 会员级别：1 = X会员(高级) ，2 = Y会员(钻石)，只有会员状态为已激活此字段才会生效
     */
    public enum userLevel {
        X会员(1),
        Y会员(2),
        ;
        private final Integer value;

        userLevel(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

}
