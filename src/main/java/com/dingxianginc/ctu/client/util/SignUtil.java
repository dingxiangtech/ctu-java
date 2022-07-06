/*
 * Copyright: 2017 dingxiang-inc.com Inc. All rights reserved.
 */

package com.dingxianginc.ctu.client.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dingxianginc.ctu.client.model.CtuRequest;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

public class SignUtil {
    private static final String EVENT_CODE = "eventCode";
    private static final String FLAG = "flag";
    private static final String DATA = "data";
    private static final String UTF8_ENCODE = "UTF-8";

    /**
     * @param appSecret
     * @param ctuRequest
     * @return
     */
    public static String sign(String appSecret, CtuRequest ctuRequest) {
        String sortedParams = sortedParams(ctuRequest);

        return DigestUtils.md5Hex(new StringBuilder(appSecret).append(sortedParams)
                .append(appSecret).toString());
    }

    public static String sign_v0(String appSecret, CtuRequest ctuRequest) {
        String sortedParams = sortedParams_v0(ctuRequest);
        return DigestUtils.md5Hex(new StringBuilder(appSecret).append(sortedParams)
                .append(appSecret).toString());
    }

    /**
     * @param appSecret
     * @param requestSign
     * @param requestInputStream
     * @return
     * @throws IOException
     */
    public static boolean checkSign(String appSecret, String requestSign, Integer version, InputStream requestInputStream) throws IOException {
        CtuRequest ctuRequest = fromRequestInputStream(requestInputStream);
        return checkSign(appSecret, requestSign, version, ctuRequest);
    }

    public static boolean checkSign(String appSecret, String requestSign, Integer version, CtuRequest ctuRequest) throws IOException {
        if (StringUtils.isEmpty(appSecret) || StringUtils.isEmpty(requestSign)) {
            return false;
        }

        if (ctuRequest == null) {
            return false;
        }

        String sign = null;
        if (version != null) {
            sign = sign(appSecret, ctuRequest);
        } else {
            sign = sign_v0(appSecret, ctuRequest);
        }

        if (requestSign.equals(sign)) {
            return true;
        }

        return false;
    }

    public static CtuRequest fromRequestInputStream(InputStream requestInputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = InputStreamUtils.readToBytes(requestInputStream);
        if (byteArrayOutputStream == null) {
            return null;
        }

        String data = new String(Base64.decodeBase64(byteArrayOutputStream.toByteArray()), UTF8_ENCODE);
        JSONObject jsonObject = JSON.parseObject(data);
        if (jsonObject == null) {
            return null;
        }

        CtuRequest request = new CtuRequest();
        request.setFlag(jsonObject.getString(FLAG));
        request.setEventCode(jsonObject.getString(EVENT_CODE));
        request.setData(jsonObject.getObject(DATA, Map.class));
        return request;
    }

    private static String sortedParams(CtuRequest request) {
        String eventCode = request.getEventCode();
        String flag = request.getFlag();
        Map<String, Object> data = new TreeMap<String, Object>(request.getData());

        StringBuilder sb = new StringBuilder(200);
        sb.append(EVENT_CODE).append(eventCode).append(FLAG).append(flag);
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            sb.append(entry.getKey()).append(entry.getValue());
        }
        return sb.toString();
    }

    private static String sortedParams_v0(CtuRequest request) {
        String eventCode = request.getEventCode();
        String flag = request.getFlag();
        Map<String, Object> data = request.getData();

        String jsonStringData = JSON.toJSONString(data);
        return new StringBuilder(EVENT_CODE).append(eventCode).append(DATA).append(jsonStringData)
                .append(FLAG).append(flag).toString();
    }

}
