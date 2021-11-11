/*
 * Copyright: 2017 dingxiang-inc.com Inc. All rights reserved.
 */

package com.dingxianginc.ctu.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dingxianginc.ctu.client.model.*;
import com.dingxianginc.ctu.client.util.HttpClientPool;
import com.dingxianginc.ctu.client.util.InputStreamUtils;
import com.dingxianginc.ctu.client.util.SignUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;


public class CtuClient {
  private String url;
  private String appKey;
  private String appSecret;
  private static final String UTF8_ENCODE = "UTF-8";
  private static final Integer version = 1;

  RequestConfig requestConfig = null;
  CloseableHttpClient httpClient = null;

  public CtuClient(String url, String appKey, String appSecret) {
    this.url = url;
    this.appKey = appKey;
    this.appSecret = appSecret;
    this.httpClient = HttpClientPool.getInstance().getHttpClient();
    this.requestConfig = HttpClientPool.getInstance().getRequestConfig();
  }

  public CtuClient(
          String url,
          String appKey,
          String appSecret,
          int connectTimeout,
          int connectionRequestTimeout,
          int socketTimeout) {
    this.url = url;
    this.appKey = appKey;
    this.appSecret = appSecret;
    this.httpClient = HttpClientPool.getInstance().getHttpClient();
    this.requestConfig = RequestConfig.custom()
            .setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectionRequestTimeout)
            .setSocketTimeout(socketTimeout).build();
  }

  public CtuResponse checkRisk(CtuRequest request) throws Exception {
    String sign = SignUtil.sign(appSecret, request);
    String reqUrl = String.format("%s?appKey=%s&sign=%s&version=%s", url, appKey, sign, version);
    String reqJsonString = JSON.toJSONString(request, SerializerFeature.WriteMapNullValue);
    byte[] base64Request = Base64.encodeBase64(reqJsonString.getBytes(UTF8_ENCODE));

    HttpPost httpPost = new HttpPost(reqUrl);
    httpPost.setConfig(requestConfig);
    httpPost.setEntity(new ByteArrayEntity(base64Request));
    CloseableHttpResponse response = null;

    String errorMsg = null;
    try {
      response = httpClient.execute(httpPost);
      if (response.getStatusLine().getStatusCode() == 200) {
        String resData = InputStreamUtils.readToString(response.getEntity().getContent());
        return JSON.parseObject(resData, CtuResponse.class);
      }
    }catch (Exception e){
      errorMsg = e.getMessage();
    }finally {
      if (response != null) {
        response.close();
      }
      httpPost.releaseConnection();
    }
    CtuResponse ctuResponse = new CtuResponse();
    CtuEntireResult ctuEntireResult = new CtuEntireResult();
    ctuEntireResult.addExtInfo("_exception_msg", errorMsg);
    ctuEntireResult.setRiskLevel(RiskLevel.ACCEPT);
    ctuResponse.setResult(ctuEntireResult);
    ctuResponse.setStatus("SERVER_CONNECT_FAILED");
    return ctuResponse;
  }

}
