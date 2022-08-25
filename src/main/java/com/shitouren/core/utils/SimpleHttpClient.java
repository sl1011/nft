package com.shitouren.core.utils;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


public class SimpleHttpClient {

    private HttpClient httpclient;

    /**
     * 请求超时
     */
    private int connectionTimeout;

    /**
     * 读取超时
     */
    private int soTimeout;

    /**
     * 默认请求超时（单位：毫秒）
     */
    private static final int DEFAULT_CONNECTION_TIMEOUT = 30000;
    /**
     * 默认读取超时（单位：毫秒）
     */
    private static final int DEFAULT_SO_TIMEOUT = 90000;

    /**
     * 按默认请求超时，读取超时设置
     */
    public SimpleHttpClient() {
        this.connectionTimeout = DEFAULT_CONNECTION_TIMEOUT;
        this.soTimeout = DEFAULT_SO_TIMEOUT;

        this.httpclient = new DefaultHttpClient();

        this.httpclient.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeout);
        this.httpclient.getParams().setParameter(
                CoreConnectionPNames.SO_TIMEOUT, soTimeout);
    }

    /**
     * @param connectionTimeout 请求超时
     * @param soTimeout         读取超时
     */
    public SimpleHttpClient(int connectionTimeout, int soTimeout) {
        this.connectionTimeout = connectionTimeout;
        this.soTimeout = soTimeout;

        this.httpclient = new DefaultHttpClient();

        this.httpclient.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeout);
        this.httpclient.getParams().setParameter(
                CoreConnectionPNames.SO_TIMEOUT, soTimeout);
    }

    public void enableSSL() {
		/*try {
			httpclient
					.getConnectionManager()
					.getSchemeRegistry()
					.register(
							new Scheme("https", 443, new SSLSocketFactory(
									new TrustStrategy() {
										@Override
										public boolean isTrusted(
												X509Certificate[] chain,
												String authType)
												throws CertificateException {
											return true;
										}
									})));
		} catch (Exception e) {
			//
		}*/
        try {
            SSLContext ctx = SSLContext.getInstance("TLSv1.1");
            X509TrustManager tm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            ctx.init(null, new TrustManager[]{tm}, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = httpclient.getConnectionManager();
            SchemeRegistry sr = ccm.getSchemeRegistry();
            sr.register(new Scheme("https", 443, ssf));
        } catch (Exception e) {
            //
        }

    }

    /**
     * @return the httpclient
     */
    public HttpClient getHttpclient() {
        return httpclient;
    }

    /**
     * @return the connectionTimeout
     */
    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    /**
     * @param connectionTimeout the connectionTimeout to set
     */
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
        this.httpclient.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeout);
    }

    /**
     * @return the soTimeout
     */
    public int getSoTimeout() {
        return soTimeout;
    }

    /**
     * @param soTimeout the soTimeout to set
     */
    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
        this.httpclient.getParams().setParameter(
                CoreConnectionPNames.SO_TIMEOUT, soTimeout);
    }

}
