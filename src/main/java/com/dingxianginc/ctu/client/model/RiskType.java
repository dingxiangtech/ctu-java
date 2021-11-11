/*
 * Copyright: 2017 dingxiang-inc.com Inc. All rights reserved.
 */

package com.dingxianginc.ctu.client.model;

public enum RiskType {
    RUBBISH_REGISTRATION("RUBBISH_REGISTRATION"),
    ACCOUNT_STOLEN("ACCOUNT_STOLEN"),
    MACHINE_CRAWLING("MACHINE_CRAWLING"),
    BATCH_LOGON("BATCH_LOGON"),
    MALICIOUS_GRAB("MALICIOUS_GRAB"),
    UNKNWON("UNKNWON");

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
