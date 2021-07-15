/*
 * Copyright: 2017 dingxiang-inc.com Inc. All rights reserved.
 */

package com.dingxianginc.ctu.client.model;

import java.io.Serializable;

public class CtuResponse implements Serializable {
    private String uuid;               // 服务端返回的请求标识码，供服务端排查问题
    private CtuResponseStatus status;  // 状态码
    private CtuEntireResult ctuEntireResult;          // 防控结果


    public CtuResponse() {
    }

    public CtuResponse(String uuid) {
        this.uuid = uuid;
    }

    public CtuResponse(String uuid, CtuEntireResult ctuEntireResult) {
        this(uuid, ctuEntireResult, CtuResponseStatus.SUCCESS);
    }

    public CtuResponse(String uuid, CtuEntireResult ctuEntireResult, CtuResponseStatus status) {
        this.uuid = uuid;
        this.ctuEntireResult = ctuEntireResult;
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public CtuResponseStatus getStatus() {
        return status;
    }

    public void setStatus(CtuResponseStatus status) {
        this.status = status;
    }

    public CtuEntireResult getResult() {
        return ctuEntireResult;
    }

    public void setResult(CtuEntireResult ctuEntireResult) {
        this.ctuEntireResult = ctuEntireResult;
    }
}
