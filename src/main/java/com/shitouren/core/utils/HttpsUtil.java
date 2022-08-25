
package com.shitouren.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;


public class HttpsUtil {

    //form表单请求
    public static String RequestForm(String Url, Map<String, Object> Parms) {
        if (Parms.isEmpty()) {
            return "参数不能为空！";
        }
        System.out.println("111111111111");
        String PostParms = "";
        for (String key : Parms.keySet()) {
            if (!key.isEmpty()) {
                PostParms += key + "=" + Parms.get(key) + "&";
            }

        }
        System.out.println("22222222222222");
        PostParms = PostParms.substring(0, PostParms.length() - 1);

        HttpSendModel httpSendModel = new HttpSendModel(Url + "?" + PostParms);
        System.out.println("【http-post请求参数】：" + Url + "?" + PostParms);
        httpSendModel.setMethod(HttpMethod.POST);
        System.out.println("33333333333333");

        SimpleHttpResponse response = null;
        try {
            response = doRequest(httpSendModel, "utf-8");
        } catch (Exception e) {
            return e.getMessage();
        }
        System.out.println("【http请求状态码】：" + response.getStatusCode());
        return response.getEntityString();

    }

    // json 报文请求
    public static String httpPostWithJSON(String url, String json) {

        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();

        StringEntity entity = new StringEntity(json, "utf-8");//解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        System.out.println("http 请求参数:" + json);
        HttpResponse resp;
        try {
            resp = client.execute(httpPost);
            if (resp.getStatusLine().getStatusCode() == 200) {
                HttpEntity he = resp.getEntity();
                String respContent = EntityUtils.toString(he, "UTF-8");
                return respContent;
            } else {
                return ("http请求异常:" + resp.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            return "http 请求出错:" + e;
        }
    }


    /**
     * @param url 访问地址
     *  @param param 需要传输参数参数；对象可以通过json转换成String
     * @param header header 参数；可以通过下面工具类将string类型转换成map
     * @return 返回网页返回的数据
     */
//    public static String sendPost(String url, String param, Map<String, String> header) throws UnsupportedEncodingException, IOException {
//        OutputStreamWriter out;
//        BufferedReader in = null;
//        String result = "";
//        URL realUrl = new URL(url);
//        // 打开和URL之间的连接
//        HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
//        //设置超时时间
//        conn.setConnectTimeout(5000);
//        conn.setReadTimeout(15000);
//        // 设置通用的请求属性
//        if (header!=null) {
//            for (Map.Entry<String, String> entry : header.entrySet()) {
//                conn.setRequestProperty(entry.getKey(), entry.getValue());
//            }
//        }
//        conn.setRequestMethod("POST");
//        conn.addRequestProperty("Content-Type", "application/json");
//        conn.setRequestProperty("accept", "*/*");
//        conn.setRequestProperty("connection", "Keep-Alive");
//        conn.setRequestProperty("user-agent",
//                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//        // 发送POST请求必须设置如下两行
//        conn.setDoOutput(true);
//        conn.setDoInput(true);
//        // 获取URLConnection对象对应的输出流
//        out = new OutputStreamWriter( conn.getOutputStream(),"UTF-8");// utf-8编码
//        // 发送请求参数
//        out.write(param);
//
//        // flush输出流的缓冲
//        out.flush();
//        // 定义BufferedReader输入流来读取URL的响应
//        in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf8"));
//        String line;
//        while ((line = in.readLine()) != null) {
//            result += line;
//        }
//        if(out!=null){
//            out.close();
//        }
//        if(in!=null){
//            in.close();
//        }
//        return result;
//    }

    public static JSONObject sendPost(String url,JSONObject jsonParam) throws Exception {
        OutputStream out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        HttpURLConnection conn = null;
        try {
            // 创建url资源
            URL url_ = new URL(url);
            // 建立http连接
            conn = (HttpURLConnection) url_.openConnection();
            // 设置传递方式
            conn.setRequestMethod("POST");
            // 设置允许输入、允许输出
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // 设置不用缓存
            conn.setUseCaches(false);
            //设置连接超时时间和读取超时时间
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(10000);
            // 转换为字节数组
            byte[] data = (jsonParam.toString()).getBytes();
            // 设置文件长度
            conn.setRequestProperty("Content-Length", String.valueOf(data.length));
            // 设置文件类型:
            conn.setRequestProperty("contentType", "application/json");
            // 开始连接请求
            conn.connect();
            out = new DataOutputStream(conn.getOutputStream()) ;
            // 写入请求的字符串（此时jsonParam数据是放在了请求正文body里）
            out.write((jsonParam.toString()).getBytes());
            out.flush();
            out.close();
            // 请求返回的状态
            if (HttpURLConnection.HTTP_OK == conn.getResponseCode()){
                // System.out.println("连接成功");
                // 请求返回的数据
                InputStream in1 = conn.getInputStream();
                try {
                    String readLine=new String();
                    BufferedReader responseReader=new BufferedReader(new InputStreamReader(in1,"UTF-8"));
                    while((readLine=responseReader.readLine())!=null){
                        result.append(readLine).append("\n");
                    }
                    responseReader.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else {
                System.out.println("ResponseCode is an error code:" + conn.getResponseCode());
            }

        } catch (Exception e) {
            throw new Exception(e);
        }finally {
            try{
                if(out != null){
                    out.close();
                }
                if(in != null){
                    in.close();
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }

        return JSONObject.parseObject(result.toString());
    }



    public static JSONObject httpPostRequest(String param, String url, String token){
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        try (final CloseableHttpClient httpClient = HttpClients.createDefault()) {
            final HttpPost httpPost = new HttpPost(url);
            com.alibaba.fastjson.JSONObject jsonString = JSON.parseObject(param);
            //设置请求体参数
            StringEntity entity = new StringEntity(param);
            entity.setContentEncoding("utf-8");
            httpPost.setEntity(entity);
            //设置请求头部
            httpPost.setHeader("Content-Type", "application/json");
            if(token != null && !"".equals(token)){
                httpPost.setHeader("Authorization",token);
            }


            //执行请求，返回请求响应
            try (final CloseableHttpResponse response = httpClient.execute(httpPost)) {
                //请求返回状态码
                int statusCode = response.getStatusLine().getStatusCode();
                //请求成功
                if (statusCode == HttpStatus.SC_OK && statusCode <= HttpStatus.SC_TEMPORARY_REDIRECT) {
                    //取出响应体
                    final HttpEntity entity2 = response.getEntity();
                    //从响应体中解析出token
                    String responseBody = EntityUtils.toString(entity2, "utf-8");
                    jsonObject = com.alibaba.fastjson.JSONObject.parseObject(responseBody);
                    //token = jsonObject.getString("access_token");
                } else {
                    //请求失败
                    throw new ClientProtocolException("请求失败，响应码为：" + statusCode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }






//    //上传文件请求
//    public static String doUploadFile(Map<String, File> files, String url, TreeMap<String, String> params) {
//        if (StringUtils.isBlank(url) || null == files) {
//            return null;
//        }
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        CloseableHttpResponse response = null;
//        try {
//            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
//            multipartEntityBuilder.setCharset(Charset.forName(HTTP.UTF_8));//设置请求的编码格式
//            multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);//设置浏览器兼容模式
//            if (files != null && files.size() > 0) {
//                Set<Map.Entry<String, File>> entries = files.entrySet();
//                for (Map.Entry<String, File> entry : entries) {
//                    multipartEntityBuilder.addPart(entry.getKey(), new FileBody(entry.getValue()));
//                }
//            }
//            ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);
//            //如果有额外的请求参数
//            if (params != null && !params.isEmpty()) {
//                for (String key : params.keySet()) {
//                    multipartEntityBuilder.addPart(key, new StringBody(params.get(key), contentType));
//                }
//            }
//            HttpPost httpPost = new HttpPost(url);
//            httpPost.setEntity(multipartEntityBuilder.build());
////	            httpPost.addHeader("Content-Type", "multipart/form-data; boundary=----------ThIs_Is_tHe_bouNdaRY_$");
//            response = httpClient.execute(httpPost);
//            int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode != 200) {
//                httpPost.abort();
//                throw new RuntimeException("HttpClient,error status code :" + statusCode);
//            }
//            return EntityUtils.toString(response.getEntity(), "UTF-8");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (null != response) {
//                    response.close();
//                }
//                if (null != httpClient) {
//                    httpClient.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }


    public static SimpleHttpResponse doRequest(HttpSendModel httpSendModel,
                                               String getCharSet) throws Exception {

        // 创建默认的httpClient客户端端
        SimpleHttpClient simpleHttpclient = new SimpleHttpClient();

        try {
            return doRequest(simpleHttpclient, httpSendModel, getCharSet);
        } finally {
            simpleHttpclient.getHttpclient().getConnectionManager().shutdown();
        }

    }

    /**
     * @param httpSendModel
     * @param getCharSet
     * @return
     * @throws Exception
     */
    public static SimpleHttpResponse doRequest(
            SimpleHttpClient simpleHttpclient, HttpSendModel httpSendModel,
            String getCharSet) throws Exception {

        HttpRequestBase httpRequest = buildHttpRequest(httpSendModel);

        if (httpSendModel.getUrl().startsWith("https://")) {
            simpleHttpclient.enableSSL();
        }

        try {
            HttpResponse response = simpleHttpclient.getHttpclient().execute(
                    httpRequest);
            int statusCode = response.getStatusLine().getStatusCode();

            if (isRequestSuccess(statusCode)) {
                return new SimpleHttpResponse(statusCode, EntityUtils.toString(
                        response.getEntity(), getCharSet), null);
            } else {
                return new SimpleHttpResponse(statusCode, null, response
                        .getStatusLine().getReasonPhrase());
            }

        } catch (Exception e) {
            throw new Exception("http请求异常", e);
        }

    }

    /**
     * @param httpSendModel
     * @return
     * @throws Exception
     */
    protected static HttpRequestBase buildHttpRequest(
            HttpSendModel httpSendModel) throws Exception {
        HttpRequestBase httpRequest;
        if (httpSendModel.getMethod() == null) {
            throw new Exception("请求方式未设定");
        } else if (httpSendModel.getMethod() == HttpMethod.POST) {

            String url = httpSendModel.getUrl();
            String sendCharSet = httpSendModel.getCharSet();
            List<HttpFormParameter> params = httpSendModel.getParams();

            List<NameValuePair> qparams = new ArrayList<NameValuePair>();
            if (params != null && params.size() != 0) {

                for (HttpFormParameter param : params) {
                    qparams.add(new BasicNameValuePair(param.getName(), param
                            .getValue()));
                }

            }

            HttpPost httppost = new HttpPost(url);
            try {
                httppost.setEntity(new UrlEncodedFormEntity(qparams,
                        sendCharSet));
            } catch (UnsupportedEncodingException e) {
                throw new Exception("构建post请求参数失败", e);
            }

            httpRequest = httppost;
        } else if (httpSendModel.getMethod() == HttpMethod.GET) {
            HttpGet httpget = new HttpGet(httpSendModel.buildGetRequestUrl());

            httpRequest = httpget;
        } else {
            throw new Exception("请求方式不支持：" + httpSendModel.getMethod());
        }

        return httpRequest;
    }

    /**
     * 请求是否成功
     *
     * @param statusCode
     * @return
     */
    public static boolean isRequestSuccess(int statusCode) {
        return statusCode == 200;
    }


    public static String postFormData(String path, HashMap<String, String> Headers, HashMap<String, String> formMap) {
        String result = "";
        HttpURLConnection connection = null;
        String boundary = "--------------------------132183525382215881770481";
        try {
            URL url = new URL(path);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Connection", "Keep-Alive");
            // 不使用缓存
            connection.setUseCaches(false);
            if (Headers != null) {
                if (Headers.size() > 0) {
                    for (Map.Entry<String, String> entry : Headers.entrySet()) {
                        connection.setRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
            }
            StringBuffer formSB = new StringBuffer();
            if (formMap != null) {
                if (formMap.size() > 0) {
                    for (Map.Entry<String, String> entry : formMap.entrySet()) {
                        String inputName = entry.getKey();
                        String inputValue = entry.getValue();
                        formSB.append("\r\n").append("--").append(boundary).append("\r\n");
                        formSB.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
                        formSB.append(inputValue);
                    }
                    formSB.append("\r\n").append("--").append(boundary).append("--");
                }
            }
            connection.connect();
            //OutputStream out = new DataOutputStream(connection.getOutputStream());
            PrintWriter out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            out.print(formSB.toString());
            out.flush();
            //获得响应状态
            int resultCode = connection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == resultCode) {
                formSB = new StringBuffer();
                String readLine;
                BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                while ((readLine = responseReader.readLine()) != null) {
                    formSB.append(readLine).append("\n");
                }
                responseReader.close();
                result = formSB.toString();
            } else {
                result = "{\"code\":\"" + resultCode + "\"}";
            }
            out.close();
        } catch (Exception e) {
            //logger.error(e.toString());
            return "{\"code\":500,\"result\":\"" + "POST表单请求 " + path + " 时出现异常\"}";
        } finally {
            connection.disconnect();
        }
        return result;
    }



}