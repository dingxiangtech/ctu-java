/*
 * Copyright: 2017 dingxiang-inc.com Inc. All rights reserved.
 */

package com.dingxianginc.ctu.client.model;

public class CaptchaResponse {
    private Boolean result;
	private String captchaStatus;
    private String ip;
	private String tpc;
    private String uid;
	private String code;

	public CaptchaResponse(Boolean result, String captchaStatus) {
		this.result = result;
		this.captchaStatus = captchaStatus;
	}

    public CaptchaResponse(Boolean result, String captchaStatus, String ip, String tpc, String uid, String code){
        this(result, captchaStatus);
        this.ip = ip;
		this.tpc = tpc;
        this.uid = uid;
        this.code = code;
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


	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

    public String getTpc() {
        return tpc;
    }

    public void setTpc(String tpc) {
        this.tpc = tpc;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
	
	public String getCode(){return code;}

	public void setCode(String code){this.code = code;}
}
