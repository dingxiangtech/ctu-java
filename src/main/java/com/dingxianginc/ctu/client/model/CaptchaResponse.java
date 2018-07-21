/*
 * Copyright: 2017 dingxiang-inc.com Inc. All rights reserved.
 */

package com.dingxianginc.ctu.client.model;

/**
 * @FileName: CaptchaResponse.java
 * @Description: CaptchaResponse.java类说明
 * @Author: 杜威
 * @Date: 2017/12/8 14:37
 */
public class CaptchaResponse {
    private Boolean result;
    private String captchaStatus;

    public CaptchaResponse(Boolean result,String captchaStatus){
        this.result = result;
        this.captchaStatus = captchaStatus;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getCaptchaStatus() {
        return captchaStatus;
    }

    public void setCaptchaStatus(String captchaStatus) {
        this.captchaStatus = captchaStatus;
    }

}
