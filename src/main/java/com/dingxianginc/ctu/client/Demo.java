/*
 * Copyright: 2017 dingxiang-inc.com Inc. All rights reserved.
 */

package com.dingxianginc.ctu.client;

import com.alibaba.fastjson.JSON;
import com.dingxianginc.ctu.client.model.CtuRequest;
import com.dingxianginc.ctu.client.model.CtuResponse;
import com.dingxianginc.ctu.client.model.RiskLevel;

import java.util.HashMap;
import java.util.Map;

/** Created by dingxiang-inc on 2017/5/5. */
public class Demo {
  /** 风控引擎url */
  public static final String url = "http://sec.dingxiang-inc.com/ctu/event.do";
  /** 应用appKey，公钥 */
  public static final String appId = "d86cb2a29fxxxxxxxxx";
  /** 应用appSecret，私钥 */
  public static final String appSecret = "f6e7fea43fxxxxxxxxxxx";

  public static void main(String[] param) throws Exception {

    /** 业务请求数据* */
    Map<String, Object> data = new HashMap<String, Object>();
    data.put("const_id", "N4RG6TtsY6ILK5ePY6HVtjj12pu5Yi5wnjnbaUI41"); // 设备指纹id
    data.put("user_id", "12345"); // 用户ID
    data.put("phone_number", "17800000000"); // 手机号
    data.put("ip", "125.121.232.219"); // ip

    CtuRequest request = new CtuRequest();
    request.setEventCode("default_test_event");
    request.setData(data);
    request.setFlag("test1");
    CtuClient client = new CtuClient(url, appId, appSecret);
    CtuResponse response = client.checkRisk(request);
    if (RiskLevel.ACCEPT.equals(response.getResult().getRiskLevel())) {
      System.out.println(JSON.toJSONString(response));
      //... 业务代码，当前请求没有风险
    } else if (RiskLevel.REVIEW.equals(response.getResult().getRiskLevel())) {
      System.out.println(JSON.toJSONString(response));
      //... 业务代码，当前请求有一定风险，建议复审
    } else if (RiskLevel.REJECT.equals(response.getResult().getRiskLevel())) {
      System.out.println(JSON.toJSONString(response));
      //... 业务代码，当前请求有风险，建议拒绝
    }
  }
}
