package com.shitouren.core.utils;

import javax.net.ssl.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class X509TrustUtiil implements X509TrustManager {

    @Override
    public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        // TODO Auto-generated method stub
    }

    @Override
    public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        // TODO Auto-generated method stub
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        // TODO Auto-generated method stub
        return null;
    }


    /**
          * TODO 下载文件到本地
          *
          * @param fileUrl   远程地址
          * @param fileLocal 本地路径
          * @throws Exception
          */


    public static void downloadFile(String fileUrl, String fileLocal) throws Exception {
        SSLContext sslcontext = SSLContext.getInstance("SSL", "SunJSSE");
        sslcontext.init(null, new TrustManager[]{new  com.shitouren.core.utils.X509TrustUtiil()}, new java.security.SecureRandom());
//        URL url = new URL(fileUrl);
        URL url= new URL(null, fileUrl, new sun.net.www.protocol.https.Handler());
        HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
            public boolean verify(String s, SSLSession sslsession) {
            System.out.println("WARNING: Hostname is not matched for cert.");
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
        HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());
        HttpsURLConnection urlCon = (HttpsURLConnection) url.openConnection();
        urlCon.setConnectTimeout(6000);
        urlCon.setReadTimeout(6000);
        int code = urlCon.getResponseCode();
        if (code != HttpURLConnection.HTTP_OK) {
            throw new Exception("文件读取失败");
        }
        // 读文件流
        DataInputStream in = new DataInputStream(urlCon.getInputStream());
        DataOutputStream out = new DataOutputStream(new FileOutputStream(fileLocal));
        byte[] buffer = new byte[2048];
        int count = 0;
        while ((count = in.read(buffer)) > 0) {
            out.write(buffer, 0, count);
        }
        out.close();
        in.close();
    }

    public static void main(String[] args) {
        String fileUrl = "https://c-ssl.duitang.com/uploads/item/201508/28/20150828184950_sRZv8.jpeg";
        String fileLocal = "C://Users/97938/EnglistWorld/image/10013.jpg";
        try {
            downloadFile(fileUrl,fileLocal);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
