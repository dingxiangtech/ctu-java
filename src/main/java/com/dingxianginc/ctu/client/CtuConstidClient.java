package com.dingxianginc.ctu.client;

import com.alibaba.fastjson.JSONObject;
import com.dingxianginc.ctu.client.model.CtuConstidContext;
import com.dingxianginc.ctu.client.model.HttpResult;
import com.dingxianginc.ctu.client.model.RiskResponse;
import com.dingxianginc.ctu.client.util.HttpClientPool;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class CtuConstidClient {

    private String url; // 设备指纹服务域名
    private String appKey; // 颁发的公钥,可公开
    private String appSecret; // 颁发的秘钥,严禁公开,请保管好,千万不要泄露!

    private final static String DEVICE_INFO_URL = "/udid/api/getDeviceInfo";
    public final static String DEVICE_INFO_DECRYPT_URL = "/udid/api/deviceRisk";
    private final static int HTTP_RESPONSE_STATUS_SUCCESS = 200;
    private final static Boolean doTranslate = false;

    RequestConfig requestConfig = null;
    CloseableHttpClient httpClient = null;

    public CtuConstidClient(String url, String appKey, String appSecret) {
        this.url = url;
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.httpClient = HttpClientPool.getInstance().getHttpClient();
        this.requestConfig = HttpClientPool.getInstance().getRequestConfig();
    }

    public CtuConstidClient(
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

    /**
     * 获取设备指纹详情
     * @param token 设备指纹token
     * @return
     * @throws IOException
     */
    public String getDeviceInfo(String token) throws IOException {
        return getDeviceInfo(token, doTranslate);
    }

    public String getDeviceInfo(String token, Boolean translate) throws IOException {
        Map<String, Object> map = new HashMap(3);
        map.put("appId", appKey);
        map.put("sign", generationSign(appSecret, token));
        map.put("doTranslate", translate);

        try {
            map.put("token", URLEncoder.encode(token, CtuConstidContext.DEFAULT_CHARSET.name()));
        } catch (UnsupportedEncodingException var6) {
        }
        return doPost(url + DEVICE_INFO_URL, map);
    }

    /**
     * 态势感知数据解密
     * @param sid 即businessDigest
     * @param businessSign
     * @param data
     * @return
     * @throws IOException
     */
    public RiskResponse getRiskInfo(String sid, String businessSign, Map<String, String> data) throws IOException {
        if (StringUtils.isEmpty(sid)
                || StringUtils.isEmpty(businessSign)
                || StringUtils.isEmpty(appSecret)) {
            throw new IllegalArgumentException("please check your input params.");
        }
        Map<String, Object> map = new HashMap<>(5);
        map.put("appId", appKey);
        map.put("sign", DigestUtils.md5Hex(appSecret + businessSign + appSecret));
        map.put("sid", sid);
        map.put("businessSign", URLEncoder.encode(businessSign, "UTF-8"));
        if (data != null) {
            map.put("data", JSONObject.toJSONString(data));
        }
        String result = doPost(url + DEVICE_INFO_DECRYPT_URL, map);
        return JSONObject.parseObject(result, RiskResponse.class);
    }

    /**
     * 构造POST请求
     * @param regionUrl
     * @param map
     * @return
     * @throws IOException
     */
    private String doPost(String regionUrl, Map<String, Object> map) throws IOException {
        String result = "";
        CloseableHttpResponse httpResponse = null;

        try {
            StringBuilder sb = new StringBuilder();
            Set<String> keys = map.keySet();
            for (String key : keys) {
                sb.append(key).append("=");
                sb.append(map.get(key)).append("&");
            }
            StringEntity stringEntity = new StringEntity(sb.toString(), CtuConstidContext.DEFAULT_CHARSET);
            stringEntity.setContentType("application/x-www-form-urlencoded");

            HttpPost httpPost = new HttpPost(regionUrl);
            httpPost.setEntity(stringEntity);
            httpPost.setConfig(requestConfig);
            httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == HTTP_RESPONSE_STATUS_SUCCESS) {
                HttpEntity httpEntity = httpResponse.getEntity();
                result = EntityUtils.toString(httpEntity);
            } else {
                HttpResult httpResult = new HttpResult();
                httpResult.setStateCode(httpResponse.getStatusLine().getStatusCode());
                httpResult.setMessage(httpResponse.getStatusLine().getReasonPhrase());
                return JSONObject.toJSONString(httpResult);
            }
        } finally {
            if (httpResponse != null) {
                httpResponse.close();
            }
        }
        return result;
    }

    /**
     * token生成签名
     * @param appSecret
     * @param token
     * @return
     */
    public static String generationSign(String appSecret, String token) {
        return CtuConstidContext.md5(new byte[][]{appSecret.getBytes(CtuConstidContext.DEFAULT_CHARSET), token.getBytes(CtuConstidContext.DEFAULT_CHARSET), appSecret.getBytes(CtuConstidContext.DEFAULT_CHARSET)});
    }


}
