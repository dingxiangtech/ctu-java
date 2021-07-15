/*
 * Copyright: 2017 dingxiang-inc.com Inc. All rights reserved.
 */

package com.dingxianginc.ctu.client.model;

public enum RiskType {
    RUBBISH_REGISTRATION("垃圾注册"),
    ACCOUNT_STOLEN("账号盗用"),
    MACHINE_CRAWLING("机器爬取"),
    BATCH_LOGON("批量登陆"),
    MALICIOUS_GRAB("黄牛抢单"),
    UNKNWON("未定义");

    private String description;

    private RiskType(String description) {
        this.description = description;
    }

    public static RiskType fromString(String riskCode) {
        for (RiskType rt : RiskType.values()) {
            if (rt.name().equalsIgnoreCase(riskCode)) {
                return rt;
            }
        }

        throw new IllegalArgumentException("No RiskType : " + riskCode + " found.");
    }

}
