/*
 * Copyright: 2017 dingxiang-inc.com Inc. All rights reserved.
 */

package com.dingxianginc.ctu.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CtuResult implements Serializable {
    private String handleFlag;                      // 处置标记
    private RiskLevel riskLevel;                     // 请求的风险级别
    private String riskType;                         // 风险类型,
    private String hitPolicyCode;                    // 命中策略code
    private String hitPolicyName;                    // 命中策略标题
    private List<HitRule> hitRules;                  // 命中规则
    private List<SuggestPolicy> suggestPolicies;     // 建议防控策略
    private List<Suggestion> suggestion;             // 命中策略处置建议

    private String flag;                             // 客户端请求带上来的标记
    private Map<String, Object> extraInfo;           // 附加信息
    private Map<String, String> nameListJson;


    public CtuResult() {
    }

    public CtuResult(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Map<String, String> getNameListJson() {
        return nameListJson;
    }

    public void setNameListJson(Map<String, String> nameListJson) {
        this.nameListJson = nameListJson;
    }

    /**
     * Reject和Review都认为是存在风险
     *
     * @return
     */
    public boolean hasRisk() {
        return RiskLevel.REJECT == riskLevel || RiskLevel.REVIEW == riskLevel;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public String getHitPolicyCode() {
        return hitPolicyCode;
    }

    public void setHitPolicyCode(String hitPolicyCode) {
        this.hitPolicyCode = hitPolicyCode;
    }

    public String getHitPolicyName() {
        return hitPolicyName;
    }

    public void setHitPolicyName(String hitPolicyName) {
        this.hitPolicyName = hitPolicyName;
    }

    public List<HitRule> getHitRules() {
        return hitRules;
    }

    public List<HitRule> addHitRule(HitRule hitRule) {
        if (this.hitRules == null) {
            this.hitRules = new ArrayList<HitRule>();
        }

        this.hitRules.add(hitRule);
        return this.hitRules;
    }

    public List<SuggestPolicy> getSuggestPolicies() {
        return suggestPolicies;
    }

    public void setSuggestPolicies(List<SuggestPolicy> suggestPolicies) {
        this.suggestPolicies = suggestPolicies;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Map<String, Object> getExtraInfo() {
        return extraInfo;
    }

    public void setHitRules(List<HitRule> hitRules) {
        this.hitRules = hitRules;
    }

    public void setExtraInfo(Map<String, Object> extraInfo) {
        this.extraInfo = extraInfo;
    }

    public void addExtInfo(String key, Object value) {
        if (this.extraInfo == null) {
            this.extraInfo = new HashMap<String, Object>();
        }

        this.extraInfo.put(key, value);
    }

    public Object getExtValue(String key) {
        if (this.extraInfo == null) {
            return null;
        }

        return this.extraInfo.get(key);
    }

    public void setSuggestion(List<Suggestion> suggestion) {
        this.suggestion = suggestion;
    }

    public List<Suggestion> getSuggestion() {
        return suggestion;
    }

    public String getHandleFlag() {
        return handleFlag;
    }

    public void setHandleFlag(String handleFlag) {
        this.handleFlag = handleFlag;
    }
}
