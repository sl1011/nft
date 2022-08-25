package com.shitouren.core.utils;

/**
 * Created by Administrator.
 * Created on 2018/3/27.
 * Description: 支付宝配置类
 */
public class AliPayConfig {

    // 合作身份者ID，以2088开头由16位纯数字组成的字符串, 商户ID
    public static String PARTNER = "2088441101053984";
    // 请求网关地址
//    public static String SERVICE_URL = "https://openapi.alipaydev.com/gateway.do"; // 测试网关
    public static String SERVICE_URL = "https://openapi.alipay.com/gateway.do"; // 正式网关
    //固定值alipay.trade.app.pay
    public static String SERVICE = "alipay.trade.wap.pay";
    public static String SELLER_ID = "*******@163.com";
    //私钥
    public static String PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCiGpS56hB/HJ6jDTSFtNi3Xc9nVXanP/ggTR5JhdIaewYM8I020lXcNfmEQlLlh48PfXsf6v30E8lKTDwM3Z0Xh2w+k8pXOFDUTrBGKNYt5KoEuh7iVz32KqVUdOPkIs8KJhneTraMlvIPC5MoEU04yeazlnbjNN0KysJoRik8JlsW63n+bRxRnVOeTqrk+zuFUstIZJnJ9D6w59GhKp5CvVHlQgcaS8VKpyASoxI156EXC9PD5pfeN0vEDYQPwSz8USub+ZQdH4PTuMZ1HnVAvJXezLZ5TlkiKxBM2BRmQfRoGdKfQ9KhW9Ln6KY4C8PdEcMJfTnE4tTZgtOwZDvnAgMBAAECggEBAJutgWWmp18bwjH+7ZD25CX9fsDTb9chdQ64YTzkuqlZuq7UmhmrGHAamd1twKTUcgzDSmqVyHwOQIMNAaKtdj9xfAAA+RKYb+/k/h7y+vYqIynVAPIILOZrgEnC1FV1JHavrqoz19be4+qVCCczroC6Tcfs2ZJMY3gIz3lK4DD1apS9pGFKCbSB7xuM5S7kqxEqPKWWVR1qyw123/L0NSeWqzcl6/VF2SwLFEIe8hgPapKscDIocs5sABKWokQTudzhV902A45VkXJ9IO/Yq6DrlQnTpyH7c+fxkfo0mB0BDeL61RZxX+FuIjK0uCoWgAoYSbKyjiP7Lb2CcJgLZykCgYEA9p5eNxIp1TsI2HigZcof0sEhl6UPeiIQpZFEgbeZXqBLOYfUgLlWCs2zjkas3iN5wQC5Xzkst/yto6pPA2+sSMqVMlDFyDXbXs456UwNweuREFwjlvBv0c4Lq461jFXaqt/HliWn4Hyo9Ra6CZJpOOY9MomMeHF3gdOAA43Sa1MCgYEAqEUv+GhpSbBreYrrSFCiF1dWoHy7kTHxLF4aDQxv03hfdowLSbQ/2wcpZYvcvYwLFUCQ/hVtSPOOpkFwfFPEvST8cZC/bJgMZKsbVhKxIE0Ch7aZUkX0HWxd3IeKHY5UlvbLNZ/WSavSn1aFWr05K+TKOdOzNBLy/IflQCJCrp0CgYEAnX+ab9U7vh/LkGPJG/ivxirtRyl07uzecL6LX8RfQF1ibUVf4y1508B20YA1WlCRqz7JJBUhiAbo5cMYXvtsrbgi/FU1Wp16kdtmhKW7i4Ljy+zsFbNL1lOehFvJeexCkCoGDs/RnL5AMPXorcBBTsNLhgDuIuVkCxeFg7+JCF0CgYA83oJCzqo/kMTf+6OuzhREc0CP328OT82ZKhUsYTJWgfWucWxn5f8vYQDVjel026Lqtid88g1NVWVluMhTAQ5rCWUUQ7MrOjlJdSf3U92k/JaMtz3xKALtXdT6QEFy/ZiV34KUzFP85F3Va7vjng1/Vkzc7Dqn/K7PMO157KrIdQKBgFJU7FC+PyGJJbrqW62E17MZvh/iE7VKliMwHU0/G3VYOBHYSnI645XcNn6MUwfcEofUk/DkQUFI3IRPmuSuxFutQqw61mEHKsZBbILRie9VH7VXqB2y/scWC1gbeHJecoL89q3OuFkizr0LICoecpJUrdNRJW/IbW/jEWUBriS2";
    // 支付宝的公钥，无需修改该值（不要删除也不要修改，在接收通知的时候需要进行签名认证）
    public static String ALI_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvApuw1zdJYWcJ/MjINSoVJCoct9tkP0siee0/S6H92kYU5uVFHMN7pplcBgMPm3UodLVdGzb+v3qO07BFzr5JzjBp/udCXaQP64ZalJ+h4/cjB1CuELiModNKcqAY3Unce2+apJRNe9JJdyzjZmCPuWV600VAIp0TKGGH0OVVdZVNg8syMPzn79ZqMuxg+7qzyJ4nMQqHOn044PUDGoEpBf4ea+MlB1hiWnAjj4NP9pKxCw49zhfPMooQc7xfvKTsh+ZhAYk4CuE3GC8MWXqH276yc2Vl+zyf7Cq/f4pFrXqA3uQ7hmxHAIifaif4MzriFuGdWh25TLpUk9kOjGptQIDAQAB";
    // 商户的公钥钥 应用公钥
    public static String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAohqUueoQfxyeow00hbTYt13PZ1V2pz/4IE0eSYXSGnsGDPCNNtJV3DX5hEJS5YePD317H+r99BPJSkw8DN2dF4dsPpPKVzhQ1E6wRijWLeSqBLoe4lc99iqlVHTj5CLPCiYZ3k62jJbyDwuTKBFNOMnms5Z24zTdCsrCaEYpPCZbFut5/m0cUZ1Tnk6q5Ps7hVLLSGSZyfQ+sOfRoSqeQr1R5UIHGkvFSqcgEqMSNeehFwvTw+aX3jdLxA2ED8Es/FErm/mUHR+D07jGdR51QLyV3sy2eU5ZIisQTNgUZkH0aBnSn0PSoVvS5+imOAvD3RHDCX05xOLU2YLTsGQ75wIDAQAB";
    // 字符编码格式 目前支持 GBK 或 UTF-8
    public static String INPUT_CHARSET = "UTF-8";
    // 签名方式
    public static String SIGN_TYPE = "RSA2";
    //APPID
    public static String APPID = "2021002196659200";
    // 支付宝回调地址,需要外网能访问的地址
    public static String NOTIFY_URL = "http://www.xiaiyuzhou.com:8200/pay/alipay/notify";
    //    public static String NOTIFY_URL = "http://m40593s324.zicp.vip:13450/pay/alipay/notify";
    public static String RETURN_URL = "";
    // 返回格式
    public static String FORMAT = "JSON";

}