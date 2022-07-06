package com.dingxianginc.ctu.client;


import com.alibaba.fastjson.JSON;

import java.io.IOException;

public class ConstantIdDemo {

    private static String appId = "0091a3x4xxxxxxx88557fac67b2f5afb";
    private static String appSecret = "e38dc3xxxxxxx86807c9e1edebaa2836";

    public static void main(String[] args) throws IOException {
        // 填写设备指纹域名或者url如：http://127.0.0.1:8080
        CtuConstidClient client = new CtuConstidClient("https://constid.dingxiang-inc.com", appId, appSecret, 10000, 10000, 10000);
        String result = client.getDeviceInfo("62c501xxxxxxhSnNu2fwCTtTtKW5BwWtQq9u1f1");

//        RiskResponse riskInfo = client.getRiskInfo("1", "xxxxxxxxxxx", null);
                System.out.println(JSON.toJSON(result));
//        System.out.println(JSON.toJSONString(riskInfo));
    }

}
