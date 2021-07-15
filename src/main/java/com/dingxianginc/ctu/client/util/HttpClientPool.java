/*
 * Copyright: 2017 dingxiang-inc.com Inc. All rights reserved.
 */


package com.dingxianginc.ctu.client.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class HttpClientPool {
    private static PoolingHttpClientConnectionManager connManager = null;
    RequestConfig requestConfig = null;
    CloseableHttpClient httpClient = null;
    private int connectTimeout = 2000;
    private int connectionRequestTimeout = 2000;
    private int socketTimeout = 2000;

    public static HttpClientPool instance = new HttpClientPool();

    public static HttpClientPool getInstance() {
        return instance;
    }

    public RequestConfig getRequestConfig() {
        return requestConfig;
    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    private HttpClientPool() {
        connManager = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(2000);
        connManager.setDefaultMaxPerRoute(100);
        requestConfig = RequestConfig.custom()
                .setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout).build();
        httpClient = HttpClients.custom()
                .setConnectionManager(connManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }
}
