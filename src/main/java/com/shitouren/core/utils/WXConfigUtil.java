package com.shitouren.core.utils;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 微信支付配置(单例)
 */
public class WXConfigUtil implements WXPayConfig {



    private byte[] certData;
    private static WXConfigUtil INSTANCE;


    public static final String APP_ID = "wx19028a3c551aa73f";//应用AppID
    public static final String KEY = "A4uyaYXuTdMyGitNXH5BUnQ76uwEWJxA";//商户密钥
    public static final String MCH_ID = "1619444784";//商户号

    public WXConfigUtil() throws Exception {
        //String certPath = WXConfigUtil.class.getClassLoader().getResource("").getPath();//从微信商户平台下载的安全证书存放的路径
       // File file = new File(certPath+ "apiclient_cert.p12");
        //InputStream certStream = new FileInputStream(file);
       // this.certData = new byte[(int) file.length()];
       // certStream.read(this.certData);
       // certStream.close();
    }

    //双重检查加锁
    public static WXConfigUtil getInstance() throws Exception {
        if (INSTANCE == null) {
            synchronized (WXConfigUtil.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WXConfigUtil();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public String getAppID() {
        return APP_ID;
    }

    //parnerid，商户号
    @Override
    public String getMchID() {
        return MCH_ID;
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}