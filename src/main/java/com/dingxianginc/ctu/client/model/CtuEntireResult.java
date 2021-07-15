/*
 * Copyright: 2017 dingxiang-inc.com Inc. All rights reserved.
 */

package com.dingxianginc.ctu.client.model;


import java.util.Map;

public class CtuEntireResult extends CtuResult {
    public CtuEntireResult(){}
    public CtuEntireResult(RiskLevel riskLevel) {
        super.setRiskLevel(riskLevel);
    }
    // key:策略名称，value：该策略总得分
    private Map<String, Double> policyScore;

    //key:名单服务的名称和code对象；value:{key:第三方返回的key(不是我们设置的rule左边量)；value：即为该值}
    private Map<String, Map<String, Object>> nameListField;

    public Map<String, Double> getPolicyScore() {
        return policyScore;
    }


    public void setPolicyScore(Map<String, Double> policyScore) {
        this.policyScore = policyScore;
    }

    public Map<String, Map<String, Object>> getNameListField() {
        return nameListField;
    }

    public void setNameListField(Map<String, Map<String, Object>> nameListField) {
        this.nameListField = nameListField;
    }
}
