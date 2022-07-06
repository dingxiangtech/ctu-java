/*
 * Copyright: 2022 dingxiang-inc.com Inc. All rights reserved.
 */
package com.dingxianginc.ctu.client.model;

import java.util.Map;

/**
 * @FileName: RiskResponse.java
 * @Description: RiskResponse.java类说明
 * @Author: Yang.Yuan
 * @Date: 2022/1/5 17:58
 */
public class RiskResponse {

    private Integer code;
    private boolean success;
    private String hardId;
    private String riskLevel;
    private String hitPolicy;
    private String action;
    private Map<String, String> deviceRisk;
    private Map<String, String> extraInfo;

    public RiskResponse() {
    }

    public static RiskResponse getFailedRiskResponse(Integer code) {
        return new RiskResponse(code, false);
    }

    public static RiskResponse getSuccessRiskResponse() {
        return new RiskResponse(0, true);
    }

    private RiskResponse(Integer code, boolean success) {
        this.code = code;
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getHardId() {
        return hardId;
    }

    public void setHardId(String hardId) {
        this.hardId = hardId;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getHitPolicy() {
        return hitPolicy;
    }

    public void setHitPolicy(String hitPolicy) {
        this.hitPolicy = hitPolicy;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Map<String, String> getDeviceRisk() {
        return deviceRisk;
    }

    public void setDeviceRisk(Map<String, String> deviceRisk) {
        this.deviceRisk = deviceRisk;
    }

    public Map<String, String> getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(Map<String, String> extraInfo) {
        this.extraInfo = extraInfo;
    }
}
