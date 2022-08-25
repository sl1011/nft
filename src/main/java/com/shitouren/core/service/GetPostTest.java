package com.shitouren.core.service;


import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 使用CloseableHttpClient发送HTTP请求
 */

public class GetPostTest {

    /**
     * 发送get请求
     * @param url 请求路径
     * @return 响应信息
     */
    public static String sendGet(String url){

        //初始化httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建httpGet
        HttpGet httpGet = new HttpGet(url);
        //设置请求状态参数
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(60 * 1000)     //httpclient使用连接池来管理连接，这个时间就是从连接池获取连接的超时时间，可以想象下数据库连接池
                .setConnectTimeout(60 * 1000)               //连接建立时间，三次握手完成时间
                .setSocketTimeout(60 * 1000).build();       //数据传输过程中数据包之间间隔的最大时间
        httpGet.setConfig(requestConfig);

        String resp = null;
        CloseableHttpResponse response = null;
        try {
            //发送请求
            response = httpClient.execute(httpGet);
            //获取返回状态值
            int statusCode = response.getStatusLine().getStatusCode();

            //获取返回信息
            resp = EntityUtils.toString(response.getEntity());






        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("resp :"+resp);
        return resp;
    }

    /**
     * 发送Json请求
     * @param url 请求路径
     * @param content JSON数据
     * @return 响应信息
     */
    public static String sendPost(String url, String content){

        //初始化httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建httpPost
        HttpPost httpPost = new HttpPost(url);
        //设置请求状态参数
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(60 * 1000)     //httpclient使用连接池来管理连接，这个时间就是从连接池获取连接的超时时间，可以想象下数据库连接池
                .setConnectTimeout(60 * 1000)               //连接建立时间，三次握手完成时间
                .setSocketTimeout(60 * 1000).build();       //数据传输过程中数据包之间间隔的最大时间
        httpPost.setConfig(requestConfig);
        //设置请求头信息
        httpPost.setHeader("Content-Type", "application/json");
        //设置请求参数
        httpPost.setEntity(new StringEntity(content, ContentType.create("application/json", "utf-8")));

        String resp = null;
        CloseableHttpResponse response = null;
        try {
            //发送请求
            response = httpClient.execute(httpPost);
            //获取返回状态值
            int statusCode = response.getStatusLine().getStatusCode();

            //获取返回信息
            resp = EntityUtils.toString(response.getEntity());

        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return resp;
    }

    /**
     * 发送指定格式数据
     * @param url 请求路径
     * @param content 请求参数
     * @param contentType 请求格式
     * @return 响应信息
     */
    public static String sendPost(String url, String content, String contentType) {

        //初始化httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建httpPost
        HttpPost httpPost = new HttpPost(url);
        //设置请求状态参数
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(60 * 1000)     //httpclient使用连接池来管理连接，这个时间就是从连接池获取连接的超时时间，可以想象下数据库连接池
                .setConnectTimeout(60 * 1000)               //连接建立时间，三次握手完成时间
                .setSocketTimeout(60 * 1000).build();       //数据传输过程中数据包之间间隔的最大时间
        httpPost.setConfig(requestConfig);
        //设置请求头信息
        httpPost.setHeader("Content-Type", contentType);
        //设置请求参数
        httpPost.setEntity(new StringEntity(content, ContentType.create(contentType, "utf-8")));

        String resp = null;
        CloseableHttpResponse response = null;
        try {
            //发送请求
            response = httpClient.execute(httpPost);
            //获取返回状态值
            int statusCode = response.getStatusLine().getStatusCode();

            //获取返回信息
            resp = EntityUtils.toString(response.getEntity());

        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return resp;
    }

}

