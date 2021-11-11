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
    private Map<String, Double> policyScore;

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
